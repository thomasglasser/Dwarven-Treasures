package org.ecorous.dwarventreasures.mixin.minecraft.world.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ProjectileUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresBlockTags;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresEntityTypeTags;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresItemTags;
import org.ecorous.dwarventreasures.world.item.AttunementUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.ecorous.dwarventreasures.world.item.AttunementUtils.ATTUNEMENT_DATA_TAG;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.ATTUNEMENT_THRESHOLD;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.BLOCKS_BROKEN_TAG;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.ENTITIES_KILLED_TAG;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.attune;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.isAttuned;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.isAttunedForBlock;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin
{
	@Unique
	private final ItemStack INSTANCE = (ItemStack) (Object) this;

	@Shadow public abstract CompoundTag getOrCreateTag();

	@Shadow public abstract Item getItem();

	@Inject(method = "hurtEnemy", at = @At("TAIL"))
	public void dwarventreasures_hurtEnemy(LivingEntity entity, Player player, CallbackInfo ci) {
		if (INSTANCE.is(DwarvenTreasuresItemTags.ATTUNABLE))
		{
			CompoundTag attunementDataTag = getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
			if (!isAttuned(attunementDataTag) && entity.getHealth() <= 0.0f && !entity.getType().is(DwarvenTreasuresEntityTypeTags.UNATTUNABLE))
			{
				String entityKey = BuiltInRegistries.ENTITY_TYPE.getKey(entity.getType()).toString();
				CompoundTag entitiesKilled = attunementDataTag.getCompound(ENTITIES_KILLED_TAG);
				entitiesKilled.putInt(entityKey, entitiesKilled.getInt(entityKey) + 1);
				attunementDataTag.put(ENTITIES_KILLED_TAG, entitiesKilled);
				getOrCreateTag().put(ATTUNEMENT_DATA_TAG, attunementDataTag);
				if (entitiesKilled.getInt(entityKey) >= ATTUNEMENT_THRESHOLD)
				{
					player.setItemInHand(player.getUsedItemHand(), attune(INSTANCE, entity.getType()));
				}
			}
		}
	}

	@Inject(method = "mineBlock", at = @At("TAIL"))
	public void dwarventreasures_mineBlock(Level level, BlockState state, BlockPos pos, Player player, CallbackInfo ci) {
		if (INSTANCE.is(DwarvenTreasuresItemTags.ATTUNABLE))
		{
			CompoundTag attunementDataTag = getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
			if (state.getDestroySpeed(level, pos) != 0.0f && !isAttuned(attunementDataTag) && !state.is(DwarvenTreasuresBlockTags.UNATTUNABLE))
			{
				String blockKey = BuiltInRegistries.BLOCK.getKey(state.getBlock()).toString();
				CompoundTag blocksBroken = attunementDataTag.getCompound(BLOCKS_BROKEN_TAG);
				blocksBroken.putInt(blockKey, blocksBroken.getInt(blockKey) + 1);
				attunementDataTag.put(BLOCKS_BROKEN_TAG, blocksBroken);
				getOrCreateTag().put(ATTUNEMENT_DATA_TAG, attunementDataTag);
				if (blocksBroken.getInt(blockKey) >= ATTUNEMENT_THRESHOLD)
				{
					player.setItemInHand(player.getUsedItemHand(), attune(INSTANCE, state.getBlock()));
				}
			}
		}
	}

	@ModifyReturnValue(method = "getDestroySpeed", at = @At("TAIL"))
	private float dwarventreasures_getDestroySpeed(float original, BlockState state)
	{
		if (isAttunedForBlock(getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG), state.getBlock()))
		{
			return original * 2;
		}
		return original;
	}

	@Inject(method = "use", at = @At("TAIL"))
	private void dwarventreasures_use(Level level, Player player, InteractionHand usedHand, CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir)
	{
		HitResult hitResult = ProjectileUtil.getHitResultOnViewVector(player, entity -> !entity.isSpectator() && entity.isPickable(), Player.getPickRange(player.isCreative()));
		if (hitResult instanceof BlockHitResult blockHitResult)
		{
			BlockPos blockPos = blockHitResult.getBlockPos();
			BlockState state = level.getBlockState(blockPos);
			if (state.is(Blocks.LAVA_CAULDRON) && isAttuned(player.getItemInHand(usedHand).getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG)))
			{
				if (!player.getAbilities().instabuild)
				{
					INSTANCE.hurtAndBreak(INSTANCE.getMaxDamage() / 3, player, (p) -> p.broadcastBreakEvent(usedHand));
					level.setBlockAndUpdate(blockPos, Blocks.CAULDRON.defaultBlockState());
				}
				Item item = INSTANCE.getItem();
				player.setItemInHand(usedHand, AttunementUtils.unattune(INSTANCE));
				player.awardStat(Stats.USE_CAULDRON);
				player.awardStat(Stats.ITEM_USED.get(item));
				level.playSound(null, blockPos, SoundEvents.LAVA_EXTINGUISH, SoundSource.BLOCKS, 1.0f, 1.0f);
			}
		}
	}
}
