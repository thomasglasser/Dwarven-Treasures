package org.ecorous.dwarventreasures.world.item;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.tslat.effectslib.api.ExtendedEnchantment;

public class EnchantableTrinketItem extends TrinketItem
{
	public EnchantableTrinketItem(Properties settings)
	{
		super(settings);
	}

	@Override
	public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity)
	{
		super.onEquip(stack, slot, entity);
		System.out.println(EnchantmentHelper.getEnchantments(stack));
		if (stack.isEnchanted()) EnchantmentHelper.getEnchantments(stack).keySet().forEach(e ->
		{
			if (e instanceof ExtendedEnchantment ee)
				ee.onEquip(entity, null, ItemStack.EMPTY, stack, EnchantmentHelper.getItemEnchantmentLevel(ee, stack));
		});
	}

	@Override
	public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity)
	{
		super.onUnequip(stack, slot, entity);
		if (stack.isEnchanted()) EnchantmentHelper.getEnchantments(stack).keySet().forEach(e ->
		{
			if (e instanceof ExtendedEnchantment ee)
				ee.onEquip(entity, null, stack, ItemStack.EMPTY, EnchantmentHelper.getItemEnchantmentLevel(ee, stack));
		});
	}
}
