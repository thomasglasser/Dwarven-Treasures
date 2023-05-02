package org.ecorous.dwarventreasures.world.item;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import org.ecorous.dwarventreasures.DwarvenTreasures;

public class DwarvenTreasuresItems
{
    private static final Item.Properties RING_PROPERTIES = new Item.Properties().fireResistant().stacksTo(1);

    public static final Item NETHERITE_RING = register("netherite_ring", new Item(RING_PROPERTIES));
    public static final Item GOLD_RING = register("gold_ring", new Item(RING_PROPERTIES));
    public static final Item ANCIENT_DEBRIS_RING = register("ancient_debris_ring", new Item(RING_PROPERTIES));
    public static final Item COPPER_RING = register("copper_ring", new Item(RING_PROPERTIES));

    private static Item register(String name, Item item)
    {
        return Registry.register(BuiltInRegistries.ITEM, DwarvenTreasures.modLoc(name), item);
    }
    public static void init() {}
}
