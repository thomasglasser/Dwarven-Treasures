package org.ecorous.dwarventreasures.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.ecorous.dwarventreasures.data.lang.DwarvenTreasuresEnUsLanguageProvider;
import org.ecorous.dwarventreasures.data.models.DwarvenTreasuresModelProvider;
import org.ecorous.dwarventreasures.data.recipes.DwarvenTreasuresRecipeProvider;

public class DwarvenTreasuresDataGenerators implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DwarvenTreasuresModelProvider::new);
		pack.addProvider(DwarvenTreasuresEnUsLanguageProvider::new);
		pack.addProvider(DwarvenTreasuresRecipeProvider::new);
	}
}
