package org.ecorous.dwarventreasures.world.item.enchantment;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.level.Level;
import net.tslat.effectslib.api.ExtendedEnchantment;

public abstract class ExtendedExtendedEnchantment extends ExtendedEnchantment
{
	public ExtendedExtendedEnchantment(Rarity rarity, EnchantmentCategory category)
	{
		super(rarity, category);
	}

	public void equippedTick(LivingEntity livingEntity, Level level, ItemStack stack) {}
}
