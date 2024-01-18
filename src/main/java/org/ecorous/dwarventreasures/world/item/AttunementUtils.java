package org.ecorous.dwarventreasures.world.item;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;

import java.util.function.Function;

public class AttunementUtils
{
	public static final Function<Block, String> BLOCK_PREFIX = Block::getDescriptionId;
	public static final Function<EntityType<?>, String> ENTITY_TYPE_PREFIX = EntityType::getDescriptionId;
	public static final Function<Item, String> ATTUNED_SUFFIX = item -> item.getDescriptionId() + ".attuned";

	public static final int ATTUNEMENT_THRESHOLD = 40;

	public static final String ATTUNEMENT_DATA_TAG = "AttunementData";
	public static final String PREFIX_TAG = "Prefix";
	public static final String SUFFIX_TAG = "Suffix";
	public static final String ENTITIES_KILLED_TAG = "EntitiesKilled";
	public static final String BLOCKS_BROKEN_TAG = "BlocksBroken";

	public static final String ATTUNED_TAG = "Attuned";
	public static final String ATTUNEMENT_BLOCK_TAG = "AttunementBlock";
	public static final String ATTUNEMENT_ENTITY_TYPE_TAG = "AttunementEntityType";

	public static ItemStack attune(ItemStack stack, String prefix)
	{
		stack = stack.copy();
		CompoundTag attunementTag = stack.getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
		if (!attunementTag.getBoolean(ATTUNED_TAG))
		{
			attunementTag.putBoolean(ATTUNED_TAG, true);
			attunementTag.putString(PREFIX_TAG, prefix);
			attunementTag.putString(SUFFIX_TAG, ATTUNED_SUFFIX.apply(stack.getItem()));
		}
		return stack;
	}

	public static ItemStack attune(ItemStack stack, Block block)
	{
		String blockKey = BLOCK_PREFIX.apply(block);
		stack = attune(stack, blockKey);
		CompoundTag attunementTag = stack.getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
		attunementTag.putString(ATTUNEMENT_BLOCK_TAG, blockKey);
		return stack;
	}

	public static ItemStack attune(ItemStack stack, EntityType<?> entityType)
	{
		String entityTypeKey = ENTITY_TYPE_PREFIX.apply(entityType);
		stack = attune(stack, entityTypeKey);
		CompoundTag attunementTag = stack.getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
		attunementTag.putString(ATTUNEMENT_ENTITY_TYPE_TAG, entityTypeKey);
		return stack;
	}

	public static ItemStack unattune(ItemStack stack)
	{
		stack = stack.copy();
		stack.getOrCreateTag().remove(ATTUNEMENT_DATA_TAG);
		stack.resetHoverName();
		return stack;
	}

	public static boolean isAttuned(CompoundTag tag)
	{
		return tag.getBoolean(ATTUNED_TAG);
	}

	public static boolean isAttuned(ItemStack stack)
	{
		return stack.getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG).getBoolean(ATTUNED_TAG);
	}

	public static boolean isAttunedForBlock(CompoundTag tag, Block block)
	{
		return isAttuned(tag) && tag.getString(ATTUNEMENT_BLOCK_TAG).equals(BLOCK_PREFIX.apply(block));
	}

	public static boolean isAttunedForEntityType(CompoundTag tag, EntityType<?> entityType)
	{
		return isAttuned(tag) && tag.getString(ATTUNEMENT_ENTITY_TYPE_TAG).equals(ENTITY_TYPE_PREFIX.apply(entityType));
	}

	public static Component getAttunedTooltip(ItemStack stack)
	{
		CompoundTag attunementTag = tag(stack);
		return Component.translatable(attunementTag.getString(PREFIX_TAG)).append("-").append(Component.translatable(attunementTag.getString(SUFFIX_TAG)));
	}

	public static CompoundTag tag(ItemStack stack)
	{
		return stack.getOrCreateTag().getCompound(ATTUNEMENT_DATA_TAG);
	}
}
