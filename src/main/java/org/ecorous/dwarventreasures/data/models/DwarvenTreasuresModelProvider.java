package org.ecorous.dwarventreasures.data.models;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;

public class DwarvenTreasuresModelProvider extends FabricModelProvider {
    public DwarvenTreasuresModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {

    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        itemModelGenerator.generateFlatItem(DwarvenTreasuresItems.COPPER_RING, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DwarvenTreasuresItems.ANCIENT_DEBRIS_RING, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DwarvenTreasuresItems.NETHERITE_RING, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(DwarvenTreasuresItems.GOLD_RING, ModelTemplates.FLAT_ITEM);
    }
}
