package org.ecorous.dwarventreasures.tags;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import org.ecorous.dwarventreasures.DwarvenTreasures;

public class DwarvenTreasuresEntityTypeTags
{
	public static final TagKey<EntityType<?>> FISHES = registerCommon("fishes");

	private static TagKey<EntityType<?>> register(String name)
	{
		return TagKey.create(Registries.ENTITY_TYPE, DwarvenTreasures.modLoc(name));
	}

	private static TagKey<EntityType<?>> registerCommon(String name)
	{
		return TagKey.create(Registries.ENTITY_TYPE, new ResourceLocation("c", name));
	}
}
