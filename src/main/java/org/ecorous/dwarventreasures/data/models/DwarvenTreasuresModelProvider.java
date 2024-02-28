package org.ecorous.dwarventreasures.data.models;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.data.models.blockstates.VariantProperty;
import net.minecraft.data.models.model.ModelLocationUtils;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
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
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_RING);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_INGOT);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_UPGRADE_SMITHING_TEMPLATE);
        flat(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_WAISTCOAT);
        DwarvenTreasuresItems.MITHRIL_ARMOR.forEach((type, item) ->
                flat(itemModelGenerator, item));

        handheld(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_SWORD);
        handheld(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_AXE);
        handheld(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_PICKAXE);
        handheld(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_SHOVEL);
        handheld(itemModelGenerator, DwarvenTreasuresItems.MITHRIL_HOE);
    }
    
    public static void flat(ItemModelGenerators itemModelGenerators, Item item)
    {
        itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_ITEM);
    }

    public static void handheld(ItemModelGenerators itemModelGenerators, Item item)
    {
        itemModelGenerators.generateFlatItem(item, ModelTemplates.FLAT_HANDHELD_ITEM);
    }

    protected void createFourLayeredBlocks(BlockModelGenerators blockModelGenerators, Block block, Block fullBlock) {
        TextureMapping textureMapping = TextureMapping.cube(block);
        ResourceLocation resourceLocation = ModelTemplates.CUBE_ALL.create(fullBlock, textureMapping, blockModelGenerators.modelOutput);
        blockModelGenerators.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(PropertyDispatch.property(BlockStateProperties.LAYERS).generate((integer) -> {
            Variant var10000 = Variant.variant();
            VariantProperty var10001 = VariantProperties.MODEL;
            ResourceLocation var2;
            if (integer < 8) {
                Block var10002 = block;
                int var10003 = integer;
                var2 = ModelLocationUtils.getModelLocation(var10002, "_height" + var10003 * 2);
            } else {
                var2 = resourceLocation;
            }

            return var10000.with(var10001, var2);
        })));
        blockModelGenerators.delegateItemModel(block, ModelLocationUtils.getModelLocation(block, "_height2"));
        blockModelGenerators.blockStateOutput.accept(BlockModelGenerators.createSimpleBlock(fullBlock, resourceLocation));
    }
}
