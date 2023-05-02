package org.ecorous.dwarventreasures.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;

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
    }
}
