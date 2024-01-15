package org.ecorous.dwarventreasures.data.tags;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresItemTags;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;

import java.util.concurrent.CompletableFuture;

public class DwarvenTreasuresItemTagsProvider extends FabricTagProvider.ItemTagProvider
{
	public DwarvenTreasuresItemTagsProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> completableFuture)
	{
		super(output, completableFuture);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider)
	{
		getOrCreateTagBuilder(DwarvenTreasuresItemTags.RINGS)
				.add(DwarvenTreasuresItems.GOLD_RING)
				.add(DwarvenTreasuresItems.COPPER_RING)
				.add(DwarvenTreasuresItems.NETHERITE_RING)
				.add(DwarvenTreasuresItems.ANCIENT_DEBRIS_RING)
				.add(DwarvenTreasuresItems.MITHRIL_RING);

		getOrCreateTagBuilder(DwarvenTreasuresItemTags.ATTUNABLE)
				.add(DwarvenTreasuresItems.MITHRIL_SWORD)
				.add(DwarvenTreasuresItems.MITHRIL_AXE)
				.add(DwarvenTreasuresItems.MITHRIL_PICKAXE)
				.add(DwarvenTreasuresItems.MITHRIL_SHOVEL)
				.add(DwarvenTreasuresItems.MITHRIL_HOE);

		trinketsTag("hand", "ring")
				.addTag(DwarvenTreasuresItemTags.RINGS);
		trinketsTag("offhand", "ring")
				.addTag(DwarvenTreasuresItemTags.RINGS);
	}

	protected FabricTagProvider<Item>.FabricTagBuilder trinketsTag(String group, String slot)
	{
		return getOrCreateTagBuilder(TagKey.create(Registries.ITEM, new ResourceLocation("trinkets", group + "/" + slot)));
	}
}
