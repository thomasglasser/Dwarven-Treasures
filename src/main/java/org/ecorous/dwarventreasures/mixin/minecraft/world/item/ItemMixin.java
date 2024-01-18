package org.ecorous.dwarventreasures.mixin.minecraft.world.item;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.ecorous.dwarventreasures.world.item.AttunementUtils;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Item.class)
public class ItemMixin
{
	@Inject(method = "appendHoverText", at = @At("TAIL"))
	private void dwarventreasures_appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltipComponents, TooltipFlag isAdvanced, CallbackInfo ci)
	{
		if (AttunementUtils.isAttuned(stack))
		{
			tooltipComponents.add(AttunementUtils.getAttunedTooltip(stack));
		}
	}
}
