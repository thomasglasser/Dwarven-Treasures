package org.ecorous.dwarventreasures.world.item;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SmithingTemplateItem;
import org.ecorous.dwarventreasures.DwarvenTreasures;

import static net.minecraft.world.item.SmithingTemplateItem.*;

public class DwarvenTreasuresItems
{
    private static final Item.Properties RING_PROPERTIES = new Item.Properties().fireResistant().stacksTo(1);

    public static final Component MITHRIL_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", DwarvenTreasures.modLoc("smithing_template.mithril_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    private static final Component MITHRIL_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", DwarvenTreasures.modLoc("mithril_upgrade"))).withStyle(TITLE_FORMAT);
    public static final Component MITHRIL_UPGRADE_APPLIES_TO = NETHERITE_UPGRADE_APPLIES_TO;
    private static final Component MITHRIL_UPGRADE_BASE_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", DwarvenTreasures.modLoc("smithing_template.mithril_upgrade.base_slot_description")));
    private static final Component MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", DwarvenTreasures.modLoc("smithing_template.mithril_upgrade.additions_slot_description")));


    public static final Item NETHERITE_RING = register("netherite_ring", new Item(RING_PROPERTIES));
    public static final Item GOLD_RING = register("gold_ring", new Item(RING_PROPERTIES));
    public static final Item ANCIENT_DEBRIS_RING = register("ancient_debris_ring", new Item(RING_PROPERTIES));
    public static final Item COPPER_RING = register("copper_ring", new Item(RING_PROPERTIES));

    // Mithril
    public static final Item MITHRIL_INGOT = register("mithril_ingot");
    public static final Item MITHRIL_UPGRADE_SMITHING_TEMPLATE = registerUpgradeSmithingTemplate("mithril");

    private static Item register(String name, Item item)
    {
        return Registry.register(BuiltInRegistries.ITEM, DwarvenTreasures.modLoc(name), item);
    }

    private static Item register(String name)
    {
        return register(name, new Item(new Item.Properties()));
    }

    private static Item registerUpgradeSmithingTemplate(String name)
    {
        return register(name + "_upgrade_smithing_template", new SmithingTemplateItem(MITHRIL_UPGRADE_APPLIES_TO, MITHRIL_UPGRADE_INGREDIENTS, MITHRIL_UPGRADE, MITHRIL_UPGRADE_BASE_SLOT_DESCRIPTION, MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createNetheriteUpgradeIconList(), createNetheriteUpgradeMaterialList()));
    }

    public static void init() {}
}
