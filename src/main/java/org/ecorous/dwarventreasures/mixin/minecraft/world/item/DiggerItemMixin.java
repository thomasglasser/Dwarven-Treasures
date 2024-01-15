package org.ecorous.dwarventreasures.mixin.minecraft.world.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import org.ecorous.dwarventreasures.world.item.AttunementUtils;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import static org.ecorous.dwarventreasures.world.item.AttunementUtils.ATTUNED_TAG;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.ATTUNEMENT_DATA_TAG;

@Mixin(DiggerItem.class)
public abstract class DiggerItemMixin extends TieredItem
{
	private DiggerItemMixin(Tier tier, Properties properties)
	{
		super(tier, properties);
	}

	@ModifyReturnValue(method = "getDestroySpeed", at = @At("TAIL"))
	private float dwarventreasures_getDestroySpeed(float original, ItemStack stack, BlockState state)
	{
		CompoundTag tag = stack.getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
		if (AttunementUtils.isAttunedForBlock(tag, state.getBlock()))
		{
			return original * 2;
		}
		return original;
	}
}
