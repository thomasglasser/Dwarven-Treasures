package org.ecorous.dwarventreasures.mixin.minecraft.world.entity;

import dev.emi.trinkets.api.TrinketComponent;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;
import net.tslat.effectslib.api.ExtendedEnchantment;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.item.enchantment.DwarvenTreasuresEnchantmentUtils;
import org.ecorous.dwarventreasures.world.item.enchantment.DwarvenTreasuresEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static org.ecorous.dwarventreasures.world.item.AttunementUtils.ATTUNEMENT_DATA_TAG;
import static org.ecorous.dwarventreasures.world.item.AttunementUtils.isAttunedForEntityType;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin
{
	@Shadow public abstract ItemStack getItemBySlot(EquipmentSlot var1);

	@Unique
	private final LivingEntity INSTANCE = ((LivingEntity)(Object)this);

	@Inject(method = "canStandOnFluid", at = @At("TAIL"), cancellable = true)
	private void dwarventreasures_canStandOnFluid(FluidState fluidState, CallbackInfoReturnable<Boolean> cir)
	{
		if (fluidState.is(FluidTags.WATER) && DwarvenTreasuresEnchantmentUtils.getEnchantmentLevel(DwarvenTreasuresEnchantments.ANOINTMENT, INSTANCE) > 0)
			cir.setReturnValue(true);
	}

	@Inject(method = "tick", at = @At("TAIL"))
	private void dwarventreasures_tick(CallbackInfo ci)
	{
		TrinketsApi.getTrinketComponent(INSTANCE).ifPresent(trinketComponent -> trinketComponent.forEach(((slotReference, stack) -> EnchantmentHelper.getEnchantments(stack).keySet().forEach(enchantment ->
		{
			if (stack.getCount() > 0 && enchantment instanceof ExtendedEnchantment ee)
			{
				ee.tick(INSTANCE, stack);
			}
		}))));
	}

	@ModifyVariable(method = "hurt", at = @At("HEAD"), index = 2, argsOnly = true)
	private float dwarventreasures_hurt(float value, DamageSource source)
	{
		boolean doubleDamage = source.getDirectEntity() instanceof LivingEntity livingEntity && isAttunedForEntityType(livingEntity.getMainHandItem().getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG), INSTANCE.getType());
		if (INSTANCE instanceof Player player && ((doubleDamage && value * 2.0 >= player.getHealth()) || value >= player.getHealth()))
		{
			TrinketComponent trinketComponent = TrinketsApi.getTrinketComponent(player).orElseThrow();
			if (trinketComponent.isEquipped(DwarvenTreasuresItems.MITHRIL_WAISTCOAT))
			{
				trinketComponent.getEquipped(DwarvenTreasuresItems.MITHRIL_WAISTCOAT).get(0).getB().hurtAndBreak(DwarvenTreasuresItems.MITHRIL_WAISTCOAT.getMaxDamage(), INSTANCE, (entity) -> entity.broadcastBreakEvent(EquipmentSlot.CHEST));
				return INSTANCE.getHealth() - 1;
			}
		}

		if (doubleDamage)
		{
			return value * 2F;
		}

		return value;
	}
}
