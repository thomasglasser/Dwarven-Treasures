package org.ecorous.dwarventreasures.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import org.ecorous.dwarventreasures.DwarvenTreasures;

public class DwarvenTreasuresBlockTags
{
	public static final TagKey<Block> UNATTUNABLE = register("unattunable");

	private static TagKey<Block> register(String name)
	{
		return TagKey.create(Registries.BLOCK, DwarvenTreasures.modLoc(name));
	}
}
