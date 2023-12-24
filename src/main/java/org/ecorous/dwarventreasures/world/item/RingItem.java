package org.ecorous.dwarventreasures.world.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.ecorous.dwarventreasures.world.item.enchantment.DwarvenTreasuresEnchantments;

import java.util.List;

public class RingItem extends EnchantableTrinketItem implements Equipable
{
	private static final List<Enchantment> RING_ENCHANTMENTS = List.of(
			DwarvenTreasuresEnchantments.SUSURRUS,
			DwarvenTreasuresEnchantments.ANOINTMENT,
			DwarvenTreasuresEnchantments.RADIANCE,
			DwarvenTreasuresEnchantments.FROST
	);

	public RingItem(Properties settings)
	{
		super(settings);
	}

	@Override
	public void inventoryTick(ItemStack stack, Level level, Entity entity, int slotId, boolean isSelected)
	{
		super.inventoryTick(stack, level, entity, slotId, isSelected);
		if (stack.isEnchantable())
			stack.enchant(RING_ENCHANTMENTS.get(level.random.nextInt(RING_ENCHANTMENTS.size())), 1);
		if (stack.getOrCreateTag().contains(DwarvenTreasuresEnchantments.SUSURRUS_TICKS_TAG) && slotId != Inventory.SLOT_OFFHAND && !isSelected)
		{
			int ticks = stack.getOrCreateTag().getInt(DwarvenTreasuresEnchantments.SUSURRUS_TICKS_TAG);
			stack.getOrCreateTag().putInt(DwarvenTreasuresEnchantments.SUSURRUS_TICKS_TAG, Math.max(0, ticks - 2));
		}
	}

	@Override
	public boolean isEnchantable(ItemStack stack)
	{
		return getMaxStackSize() == 1;
	}

	@Override
	public EquipmentSlot getEquipmentSlot()
	{
		return EquipmentSlot.OFFHAND;
	}
}
