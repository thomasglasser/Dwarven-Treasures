package org.ecorous.dwarventreasures.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import org.ecorous.dwarventreasures.data.lang.DwarvenTreasuresEnUsLanguageProvider;
import org.ecorous.dwarventreasures.data.models.DwarvenTreasuresModelProvider;
import org.ecorous.dwarventreasures.data.recipes.DwarvenTreasuresRecipeProvider;
import org.ecorous.dwarventreasures.data.tags.DwarvenTreasuresEntityTypeTagsProvider;
import org.ecorous.dwarventreasures.data.tags.DwarvenTreasuresItemTagsProvider;

public class DwarvenTreasuresDataGenerators implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

		pack.addProvider(DwarvenTreasuresModelProvider::new);
		pack.addProvider(DwarvenTreasuresEnUsLanguageProvider::new);
		pack.addProvider(DwarvenTreasuresRecipeProvider::new);

		pack.addProvider(DwarvenTreasuresItemTagsProvider::new);
		pack.addProvider(DwarvenTreasuresEntityTypeTagsProvider::new);
	}
}
