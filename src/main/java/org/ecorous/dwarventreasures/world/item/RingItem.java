package org.ecorous.dwarventreasures.world.item;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Equipable;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import org.ecorous.dwarventreasures.world.item.enchantment.DwarvenTreasuresEnchantmentUtils;
import org.ecorous.dwarventreasures.world.item.enchantment.DwarvenTreasuresEnchantments;
import org.ecorous.dwarventreasures.world.level.storage.DwarvenTreasuresEntityComponents;
import org.ecorous.dwarventreasures.world.level.storage.RingComponent;

import java.util.List;
import java.util.function.Predicate;

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
		RingComponent ringComponent = DwarvenTreasuresEntityComponents.RING.get(entity);
		int ticks = ringComponent.getSusurrusCooldown();
		if (!level.isClientSide && ticks > 0 && entity instanceof LivingEntity livingEntity && DwarvenTreasuresEnchantmentUtils.getEnchantmentLevel(DwarvenTreasuresEnchantments.SUSURRUS, livingEntity) <= 0)
		{
			ringComponent.setSusurrusCooldown(Math.max(0, ticks - 2));
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

	public enum RingEffectTarget
	{
		ALL(entity -> true),
		ENEMIES(entity -> entity instanceof Enemy),
		NON_PLAYERS(entity -> !(entity instanceof Player)),
		ENEMIES_AND_PLAYERS(entity -> entity instanceof Enemy || entity instanceof Player),
		PLAYERS(entity -> entity instanceof Player);

		private final Predicate<Entity> criteria;

		RingEffectTarget(Predicate<Entity> criteria)
		{
			this.criteria = criteria;
		}

		public boolean test(Entity holder, Entity target)
		{
			return criteria.test(target) && !target.isAlliedTo(holder);
		}
	}
}
