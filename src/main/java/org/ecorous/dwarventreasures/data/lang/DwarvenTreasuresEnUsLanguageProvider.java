package org.ecorous.dwarventreasures.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ArmorItem;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.level.block.DwarvenTreasuresBlocks;

import java.util.Map;

public class DwarvenTreasuresEnUsLanguageProvider extends FabricLanguageProvider
{
    public static final Map<ArmorItem.Type, String> ARMOR_TYPE_SUFFIXES = Map.of(
            ArmorItem.Type.HELMET, "Helmet",
            ArmorItem.Type.CHESTPLATE, "Chestplate",
            ArmorItem.Type.LEGGINGS, "Leggings",
            ArmorItem.Type.BOOTS, "Boots"
    );

    public DwarvenTreasuresEnUsLanguageProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generateTranslations(TranslationBuilder translationBuilder) {
        translationBuilder.add(DwarvenTreasuresItems.COPPER_RING, "Copper Ring");
        translationBuilder.add(DwarvenTreasuresItems.ANCIENT_DEBRIS_RING, "Ancient Debris Ring");
        translationBuilder.add(DwarvenTreasuresItems.NETHERITE_RING, "Netherite Ring");
        translationBuilder.add(DwarvenTreasuresItems.GOLD_RING, "Gold Ring");
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_INGOT, "Mithril Ingot");
        DwarvenTreasuresItems.MITHRIL_ARMOR.forEach((type, item) ->
                translationBuilder.add(item, "Mithril " + ARMOR_TYPE_SUFFIXES.get(type))
        );
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_SWORD, "Mithril Sword");
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_AXE, "Mithril Axe");
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_PICKAXE, "Mithril Pickaxe");
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_SHOVEL, "Mithril Shovel");
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_HOE, "Mithril Hoe");

        translationBuilder.add(DwarvenTreasuresBlocks.MITHRIL_BLOCK, "Block of Mithril");

        add(translationBuilder, DwarvenTreasuresItems.MITHRIL_UPGRADE_INGREDIENTS, "Mithril Ingot");
        add(translationBuilder, DwarvenTreasuresItems.MITHRIL_UPGRADE, "Mithril Upgrade");
        add(translationBuilder, DwarvenTreasuresItems.MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, "Put a Mithril Ingot here");
    }

    protected void add(TranslationBuilder translationBuilder, Component component, String name)
    {
        if (component.getContents() instanceof TranslatableContents translatableContents)
        {
            translationBuilder.add(translatableContents.getKey(), name);
        }
    }
}
