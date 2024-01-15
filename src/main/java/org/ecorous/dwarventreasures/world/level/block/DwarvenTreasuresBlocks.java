package org.ecorous.dwarventreasures.world.level.block;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import org.ecorous.dwarventreasures.DwarvenTreasures;

public class DwarvenTreasuresBlocks
{
    public static final Block MITHRIL_BLOCK = register("mithril_block", FabricBlockSettings.copyOf(Blocks.DIAMOND_BLOCK), true);

    private static Block register(String name, FabricBlockSettings settings, boolean item)
    {
        Block block = new Block(settings);
        ResourceLocation rl = DwarvenTreasures.modLoc(name);
        if (item) Registry.register(BuiltInRegistries.ITEM, rl, new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, rl, block);
    }

    public static void init() {}
}
