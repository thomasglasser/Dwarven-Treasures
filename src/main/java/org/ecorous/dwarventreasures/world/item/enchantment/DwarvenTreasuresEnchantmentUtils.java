package org.ecorous.dwarventreasures.world.item.enchantment;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

import java.util.concurrent.atomic.AtomicInteger;

public class DwarvenTreasuresEnchantmentUtils
{
	public static int getEnchantmentLevel(Enchantment enchantment, LivingEntity entity)
	{
		AtomicInteger i = new AtomicInteger();
		TrinketsApi.getTrinketComponent(entity).ifPresent(trinketComponent -> trinketComponent.forEach(((slotReference, stack) ->
		{
			int j = EnchantmentHelper.getItemEnchantmentLevel(enchantment, stack);
			if (j >= i.get())
				i.set(j);
		})));
		return Math.max(EnchantmentHelper.getEnchantmentLevel(enchantment, entity), i.get());
	}
}
