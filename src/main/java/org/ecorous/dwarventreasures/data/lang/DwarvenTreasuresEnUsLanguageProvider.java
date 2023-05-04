package org.ecorous.dwarventreasures.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.level.block.DwarvenTreasuresBlocks;

public class DwarvenTreasuresEnUsLanguageProvider extends FabricLanguageProvider
{
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
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_UPGRADE_SMITHING_TEMPLATE, "Mithril Upgrade Smithing Template");

        translationBuilder.add(DwarvenTreasuresBlocks.MITHRIL_BLOCK, "Block of Mithril");
    }
}
