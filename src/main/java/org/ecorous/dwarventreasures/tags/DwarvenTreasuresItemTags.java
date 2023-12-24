package org.ecorous.dwarventreasures.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.ecorous.dwarventreasures.DwarvenTreasures;

public class DwarvenTreasuresItemTags
{
	public static final TagKey<Item> RINGS = register("rings");

	private static TagKey<Item> register(String name)
	{
		return TagKey.create(Registries.ITEM, DwarvenTreasures.modLoc(name));
	}
}
