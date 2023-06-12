package org.ecorous.dwarventreasures.data.models;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.world.item.Item;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.level.block.DwarvenTreasuresBlocks;

public class DwarvenTreasuresModelProvider extends FabricModelProvider {
    public DwarvenTreasuresModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        blockStateModelGenerator.createTrivialCube(DwarvenTreasuresBlocks.MITHRIL_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        flat(itemModelGenerator, DwarvenTreasuresItems.COPPER_RING);
        flat(itemModelGenerator, DwarvenTreasuresItems.ANCIENT_DEBRIS_RING);
        flat(itemModelGenerator, DwarvenTreasuresItems.NETHERITE_RING);
        flat(itemModelGenerator, DwarvenTreasuresItems.GOLD_RING);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_INGOT);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_UPGRADE_SMITHING_TEMPLATE);
        DwarvenTreasuresItems.MITHRIL_ARMOR.forEach((type, item) ->
                flat(itemModelGenerator, item));
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_SWORD);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_AXE);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_PICKAXE);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_SHOVEL);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_HOE);
    }
    
    protected void flat(ItemModelGenerators itemModelGenerators, Item item)
    {
        itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }
}
