package org.ecorous.dwarventreasures.world.item.enchantment;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.tslat.effectslib.api.ExtendedEnchantment;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresItemTags;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class RingEnchantment extends ExtendedEnchantment
{
	public static final int FIVE_MINUTES = 20 * 60 * 5;

	private static final List<String> ALLOWED_SOURCES = List.of(LOOT, COMMAND);

	public RingEnchantment() {
		super(Rarity.RARE, EnchantmentCategory.WEARABLE);
	}

	@Override
	public boolean isApplicable(ItemStack stack, int level, @Nullable LivingEntity entity, @Nullable EquipmentSlot slot)
	{
		return super.isApplicable(stack, level, entity, slot) && stack.is(DwarvenTreasuresItemTags.RINGS);
	}

	@Override
	public boolean canEnchant(ItemStack stack, String enchantSource)
	{
		return stack.is(DwarvenTreasuresItemTags.RINGS) && ALLOWED_SOURCES.contains(enchantSource);
	}

	@Override
	protected boolean checkCompatibility(@NotNull Enchantment other)
	{
		return super.checkCompatibility(other) && !(other instanceof RingEnchantment);
	}

	@Override
	public @Nullable Boolean shouldGrindstoneRemove(ItemStack stack)
	{
		return false;
	}
}