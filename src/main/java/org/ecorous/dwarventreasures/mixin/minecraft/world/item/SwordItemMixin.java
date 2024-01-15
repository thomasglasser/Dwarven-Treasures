package org.ecorous.dwarventreasures.mixin.minecraft.world.item;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.Tag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SwordItem.class)
public abstract class SwordItemMixin extends TieredItem
{
	private SwordItemMixin(Tier tier, Properties builder)
	{
		super(tier, builder);
	}

	@ModifyReturnValue(method = "getDestroySpeed", at = @At("TAIL"))
	private float dwarventreasures_getDestroySpeed(float original, ItemStack stack, BlockState state)
	{
		CompoundTag tag = stack.getOrCreateTag().getCompound("AttunementData");
		if (tag.contains("Attuned") && tag.getBoolean("Attuned") && tag.contains("AttunementBlock") && state.is(BuiltInRegistries.BLOCK.get(new ResourceLocation(tag.getString("AttunementBlock")))))
		{
			return original * 2;
		}
		return original;
	}
}
