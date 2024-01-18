package org.ecorous.dwarventreasures.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.entity.EntityType;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresEntityTypeTags;

import java.util.concurrent.CompletableFuture;

public class DwarvenTreasuresEntityTypeTagsProvider extends FabricTagProvider.EntityTypeTagProvider
{
	public DwarvenTreasuresEntityTypeTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
	{
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider)
	{
		getOrCreateTagBuilder(DwarvenTreasuresEntityTypeTags.FISHES)
				.add(EntityType.COD)
				.add(EntityType.SALMON)
				.add(EntityType.PUFFERFISH)
				.add(EntityType.TROPICAL_FISH);

		getOrCreateTagBuilder(DwarvenTreasuresEntityTypeTags.UNATTUNABLE)
				.add(EntityType.PLAYER);
	}
}
