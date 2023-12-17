package org.ecorous.dwarventreasures;

import net.fabricmc.api.ModInitializer;

import net.minecraft.resources.ResourceLocation;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwarvenTreasures implements ModInitializer {
	public static final String MOD_NAME = "Dwarven Treasures";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final String MOD_ID = "dwarventreasures";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Dwarven Treasures...");

		initRegistries();
	}

	private void initRegistries()
	{
		DwarvenTreasuresItems.init();
	}

	public static ResourceLocation modLoc(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}
}