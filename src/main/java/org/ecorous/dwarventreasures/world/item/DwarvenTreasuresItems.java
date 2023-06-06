package org.ecorous.dwarventreasures.world.item;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.*;
import org.ecorous.dwarventreasures.DwarvenTreasures;

import java.util.*;

import static net.minecraft.world.item.SmithingTemplateItem.*;

public class DwarvenTreasuresItems
{
    private static final Map<CreativeModeTab, Item> ITEMS_FOR_TABS = new HashMap<>();
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
    public static final Map<ArmorItem.Type, Item> MITHRIL_ARMOR = registerArmor("mithril", DwarvenTreasuresArmorMaterials.MITHRIL);

//    TODO: Make tools for mithril and update recipe provider
//    public static final Item MITHRIL_SWORD

    private static Item register(String name, Item item, CreativeModeTab... tabs)
    {
        Arrays.stream(tabs).forEach(creativeModeTab -> ITEMS_FOR_TABS.put(creativeModeTab, item));
        return Registry.register(BuiltInRegistries.ITEM, DwarvenTreasures.modLoc(name), item);
    }

    private static Item register(String name, CreativeModeTab... tabs)
    {
        return register(name, new Item(new Item.Properties()), tabs);
    }

    private static Item registerUpgradeSmithingTemplate(String name)
    {
        return register(name + "_upgrade_smithing_template", new SmithingTemplateItem(MITHRIL_UPGRADE_APPLIES_TO, MITHRIL_UPGRADE_INGREDIENTS, MITHRIL_UPGRADE, MITHRIL_UPGRADE_BASE_SLOT_DESCRIPTION, MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createNetheriteUpgradeIconList(), createNetheriteUpgradeMaterialList()), CreativeModeTabs.INGREDIENTS);
    }

    private static Map<ArmorItem.Type, Item> registerArmor(String name, ArmorMaterial material)
    {
        Item helmet = register(name + "_helmet", new ArmorItem(material, ArmorItem.Type.HELMET, new Item.Properties()), CreativeModeTabs.COMBAT);
        Item chestplate = register(name + "_chestplate", new ArmorItem(material, ArmorItem.Type.CHESTPLATE, new Item.Properties()), CreativeModeTabs.COMBAT);
        Item leggings = register(name + "_leggings", new ArmorItem(material, ArmorItem.Type.LEGGINGS, new Item.Properties()), CreativeModeTabs.COMBAT);
        Item boots = register(name + "_boots", new ArmorItem(material, ArmorItem.Type.BOOTS, new Item.Properties()), CreativeModeTabs.COMBAT);
        return Map.of(ArmorItem.Type.HELMET, helmet, ArmorItem.Type.CHESTPLATE, chestplate, ArmorItem.Type.LEGGINGS, leggings, ArmorItem.Type.BOOTS, boots);
    }

    private static Item registerSword(String name, Tier tier)
    {
        return register(name, new SwordItem(tier, 3, -2.4f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    // TODO: Fix values for tools below
    private static Item registerAxe(String name, Tier tier)
    {
        return register(name, new SwordItem(tier, 3, -2.4f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerPickaxe(String name, Tier tier)
    {
        return register(name, new SwordItem(tier, 3, -2.4f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerHoe(String name, Tier tier)
    {
        return register(name, new SwordItem(tier, 3, -2.4f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerShovel(String name, Tier tier)
    {
        return register(name, new SwordItem(tier, 3, -2.4f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    public static void init() {}
}
