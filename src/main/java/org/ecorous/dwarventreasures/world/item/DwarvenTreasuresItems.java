package org.ecorous.dwarventreasures.world.item;

import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import org.ecorous.dwarventreasures.DwarvenTreasures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static net.minecraft.world.item.SmithingTemplateItem.DESCRIPTION_FORMAT;
import static net.minecraft.world.item.SmithingTemplateItem.NETHERITE_UPGRADE_APPLIES_TO;
import static net.minecraft.world.item.SmithingTemplateItem.NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION;
import static net.minecraft.world.item.SmithingTemplateItem.TITLE_FORMAT;
import static net.minecraft.world.item.SmithingTemplateItem.createNetheriteUpgradeIconList;
import static net.minecraft.world.item.SmithingTemplateItem.createNetheriteUpgradeMaterialList;

public class DwarvenTreasuresItems
{
    private static final Map<ResourceKey<CreativeModeTab>, List<Item>> ITEMS_FOR_TABS = new HashMap<>();
    private static final Item.Properties EMPTY_PROPERTIES = new Item.Properties();
    private static final Item.Properties RING_PROPERTIES = new Item.Properties().fireResistant().stacksTo(1);

    public static final Component MITHRIL_UPGRADE_INGREDIENTS = Component.translatable(Util.makeDescriptionId("item", DwarvenTreasures.modLoc("smithing_template.mithril_upgrade.ingredients"))).withStyle(DESCRIPTION_FORMAT);
    public static final Component MITHRIL_UPGRADE = Component.translatable(Util.makeDescriptionId("upgrade", DwarvenTreasures.modLoc("mithril_upgrade"))).withStyle(TITLE_FORMAT);
    public static final Component MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION = Component.translatable(Util.makeDescriptionId("item", DwarvenTreasures.modLoc("smithing_template.mithril_upgrade.additions_slot_description")));

    // Mithril
    public static final Item MITHRIL_INGOT = register("mithril_ingot", CreativeModeTabs.INGREDIENTS);
    public static final Item MITHRIL_UPGRADE_SMITHING_TEMPLATE = registerUpgradeSmithingTemplate("mithril");
    public static final Map<ArmorItem.Type, Item> MITHRIL_ARMOR = registerArmor("mithril", DwarvenTreasuresArmorMaterials.MITHRIL);
    public static final Item MITHRIL_WAISTCOAT = registerTrinket("mithril_waistcoat", EMPTY_PROPERTIES.durability(Items.CHAINMAIL_CHESTPLATE.getMaxDamage()), CreativeModeTabs.COMBAT);
    public static final Item MITHRIL_SWORD = registerSword("mithril_sword", DwarvenTreasuresTiers.MITHRIL);
    public static final Item MITHRIL_AXE = registerAxe("mithril_axe", DwarvenTreasuresTiers.MITHRIL);
    public static final Item MITHRIL_PICKAXE = registerPickaxe("mithril_pickaxe", DwarvenTreasuresTiers.MITHRIL);
    public static final Item MITHRIL_SHOVEL = registerShovel("mithril_shovel", DwarvenTreasuresTiers.MITHRIL);
    public static final Item MITHRIL_HOE = registerHoe("mithril_hoe", DwarvenTreasuresTiers.MITHRIL);

    // Rings
    public static final Item GOLD_RING = registerRing("gold_ring");
    public static final Item COPPER_RING = registerRing("copper_ring");
    public static final Item NETHERITE_RING = registerRing("netherite_ring");
    public static final Item ANCIENT_DEBRIS_RING = registerRing("ancient_debris_ring");
    public static final Item MITHRIL_RING = registerRing("mithril_ring");

    @SafeVarargs
    private static Item register(String name, Item item, ResourceKey<CreativeModeTab>... tabs)
    {
        for (ResourceKey<CreativeModeTab> tab: tabs) {
            List<Item> list = ITEMS_FOR_TABS.computeIfAbsent(tab, empty -> new ArrayList<>());
            list.add(item);
        }
        return Registry.register(BuiltInRegistries.ITEM, DwarvenTreasures.modLoc(name), item);
    }

    @SafeVarargs
    private static Item register(String name, ResourceKey<CreativeModeTab>... tabs)
    {
        return register(name, new Item(EMPTY_PROPERTIES), tabs);
    }

    private static Item registerUpgradeSmithingTemplate(String name)
    {
        return register(name + "_upgrade_smithing_template", new SmithingTemplateItem(NETHERITE_UPGRADE_APPLIES_TO, MITHRIL_UPGRADE_INGREDIENTS, MITHRIL_UPGRADE, NETHERITE_UPGRADE_BASE_SLOT_DESCRIPTION, MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, createNetheriteUpgradeIconList(), createNetheriteUpgradeMaterialList()), CreativeModeTabs.INGREDIENTS);
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

    private static Item registerAxe(String name, Tier tier)
    {
        return register(name, new AxeItem(tier, 6.0f, -3.1f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerPickaxe(String name, Tier tier)
    {
        return register(name, new PickaxeItem(tier, 1, -2.8f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerHoe(String name, Tier tier)
    {
        return register(name, new HoeItem(tier, -2, -1.0f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerShovel(String name, Tier tier)
    {
        return register(name, new ShovelItem(tier, 1.5f, -3.0f, new Item.Properties()), CreativeModeTabs.COMBAT);
    }

    private static Item registerRing(String name)
    {
        return register(name, new RingItem(RING_PROPERTIES), CreativeModeTabs.INGREDIENTS, CreativeModeTabs.COMBAT);
    }

    @SafeVarargs
    private static Item registerTrinket(String name, Item.Properties properties, ResourceKey<CreativeModeTab>... tabs)
    {
        return register(name, new EnchantableTrinketItem(properties), tabs);
    }

    public static Map<ResourceKey<CreativeModeTab>, List<Item>> getItemsForTabs() {
        return ITEMS_FOR_TABS;
    }
    
    public static List<ItemStack> getItemsForTab(ResourceKey<CreativeModeTab> tab)
    {
        List<ItemStack> items = new ArrayList<>();

        getItemsForTabs().forEach((itemTab, itemLikes) -> {
            if (tab == itemTab)
            {
                itemLikes.forEach((itemLike) -> items.add(itemLike.getDefaultInstance()));
            }
        });

        return items;
    }

    public static void init() {}
}
