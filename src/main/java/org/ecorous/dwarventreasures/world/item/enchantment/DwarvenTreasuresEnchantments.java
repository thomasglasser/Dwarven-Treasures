package org.ecorous.dwarventreasures.world.item.enchantment;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.tags.EntityTypeTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.phys.Vec3;
import org.ecorous.dwarventreasures.DwarvenTreasures;

import java.util.List;

public class DwarvenTreasuresEnchantments
{
	public static final String SUSURRUS_TICKS_TAG = "SusurrusTicks";

	public static final Enchantment SUSURRUS = register("susurrus", new RingEnchantment()
	{
		@Override
		public void equippedTick(LivingEntity livingEntity, Level level, ItemStack stack)
		{
			super.equippedTick(livingEntity, level, stack);
			if (livingEntity.getActiveEffects().stream().noneMatch(effect -> effect.getEffect() == MobEffects.INVISIBILITY && effect.getAmplifier() >= 0 && !effect.endsWithin(20)))
			{
				livingEntity.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 60));
			}
			int ticks = stack.getOrCreateTag().getInt(SUSURRUS_TICKS_TAG);
			System.out.println(ticks);
			stack.getOrCreateTag().putInt(SUSURRUS_TICKS_TAG, ticks + 1);
			List<MobEffect> effects = List.of(MobEffects.MOVEMENT_SLOWDOWN, MobEffects.BLINDNESS, MobEffects.WITHER);
			for (int i = 1; i <= effects.size(); i++)
			{
				int finalI = i;
				if (ticks >= FIVE_MINUTES * i && livingEntity.getActiveEffects().stream().noneMatch((effect) -> effect.getEffect() == effects.get(finalI - 1) && effect.getAmplifier() >= 0 && !effect.endsWithin(20)))
				{
					livingEntity.addEffect(new MobEffectInstance(effects.get(i - 1), 60));
				}
			}

		}

		@Override
		public void onUnequip(LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to, int level)
		{
			super.onUnequip(entity, slot, from, to, level);
		}

		@Override
		public void onEquip(LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to, int level)
		{
			super.onEquip(entity, slot, from, to, level);
		}
	});
	public static final Enchantment ANOINTMENT = register("anointment", new RingEnchantment());
	public static final Enchantment RADIANCE = register("radiance", new RingEnchantment()
	{
		@Override
		public void equippedTick(LivingEntity livingEntity, Level level, ItemStack stack)
		{
			super.equippedTick(livingEntity, level, stack);
			List<Entity> entities = level.getEntities(livingEntity, livingEntity.getBoundingBox().inflate(8), target -> target.getType().is(EntityTypeTags.UNDEAD));
			entities.forEach(target -> target.setSecondsOnFire(5));
		}
	});
	public static final Enchantment FROST = register("frost", new RingEnchantment()
	{
		@Override
		public void equippedTick(LivingEntity livingEntity, Level level, ItemStack stack)
		{
			super.equippedTick(livingEntity, level, stack);
			List<Entity> entities = level.getEntities(livingEntity, livingEntity.getBoundingBox().inflate(8), entity -> true);
			for (Entity entity : entities)
			{
				entity.makeStuckInBlock(level.getBlockState(entity.blockPosition()), new Vec3(0.9f, 1.5, 0.9f));
				if (level.isClientSide) {
					boolean bl;
					RandomSource randomSource = level.getRandom();
					bl = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
					if (bl && randomSource.nextBoolean()) {
						level.addParticle(ParticleTypes.SNOWFLAKE, entity.getX(), entity.getY() + 1, entity.getZ(), Mth.randomBetween(randomSource, -1.0f, 1.0f) * 0.083333336f, 0.05f, Mth.randomBetween(randomSource, -1.0f, 1.0f) * 0.083333336f);
					}
				}
				entity.setIsInPowderSnow(true);
				entity.setTicksFrozen(entity.getTicksFrozen() + 20);
				entity.setRemainingFireTicks(-1);
			}
			List<BlockPos> posList = BlockPos.betweenClosedStream(livingEntity.getBoundingBox().inflate(16)).toList();
			posList.forEach(pos ->
			{
				if (level.getBlockState(pos).getBlock() instanceof CropBlock)
					level.destroyBlock(pos, true);
			});
		}

		@Override
		public void onUnequip(LivingEntity entity, EquipmentSlot slot, ItemStack from, ItemStack to, int level)
		{
			super.onUnequip(entity, slot, from, to, level);
			entity.setTicksFrozen(0);
			entity.setIsInPowderSnow(false);
		}
	});

	private static Enchantment register(String name, Enchantment enchantment)
	{
		return Registry.register(BuiltInRegistries.ENCHANTMENT, DwarvenTreasures.modLoc(name), enchantment);
	}
}
