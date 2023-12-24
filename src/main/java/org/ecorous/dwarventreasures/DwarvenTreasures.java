package org.ecorous.dwarventreasures;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.TagEntry;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresEntityTypeTags;
import org.ecorous.dwarventreasures.tags.DwarvenTreasuresItemTags;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.level.block.DwarvenTreasuresBlocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class DwarvenTreasures implements ModInitializer {
	public static final String MOD_NAME = "Dwarven Treasures";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);
	public static final String MOD_ID = "dwarventreasures";

	private MinecraftServer server;

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing Dwarven Treasures...");

		initRegistries();
		registerEvents();
	}

	private void initRegistries()
	{
		DwarvenTreasuresItems.init();
		DwarvenTreasuresBlocks.init();
	}

	private void registerEvents()
	{
		ServerLifecycleEvents.SERVER_STARTED.register(server -> this.server = server);

		LootTableEvents.MODIFY.register((resourceManager, lootManager, id, tableBuilder, source) ->
		{
			if (server != null)
			{
				ServerLevel level = server.overworld();
				List<? extends EntityType<?>> fishes = level.registryAccess().registryOrThrow(Registries.ENTITY_TYPE).getTag(DwarvenTreasuresEntityTypeTags.FISHES).orElseThrow().stream().map(Holder::value).toList();
				for (EntityType<?> type : fishes)
				{
					if (id.equals(type.getDefaultLootTable()))
						tableBuilder.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 1)).add(TagEntry.expandTag(DwarvenTreasuresItemTags.RINGS)).conditionally(LootItemRandomChanceCondition.randomChance(0.3f).build()));
				}

				List<ResourceLocation> treasureTables = List.of(
						BuiltInLootTables.SIMPLE_DUNGEON,
						BuiltInLootTables.VILLAGE_ARMORER,
						BuiltInLootTables.ABANDONED_MINESHAFT,
						BuiltInLootTables.NETHER_BRIDGE,
						BuiltInLootTables.STRONGHOLD_LIBRARY,
						BuiltInLootTables.STRONGHOLD_CROSSING,
						BuiltInLootTables.STRONGHOLD_CORRIDOR,
						BuiltInLootTables.DESERT_PYRAMID,
						BuiltInLootTables.JUNGLE_TEMPLE,
						BuiltInLootTables.UNDERWATER_RUIN_SMALL,
						BuiltInLootTables.UNDERWATER_RUIN_BIG,
						BuiltInLootTables.BURIED_TREASURE,
						BuiltInLootTables.SHIPWRECK_TREASURE,
						BuiltInLootTables.ANCIENT_CITY,
						BuiltInLootTables.TRIAL_CHAMBERS_REWARD,
						BuiltInLootTables.FISHING_TREASURE,
						BuiltInLootTables.ARMORER_GIFT,
						BuiltInLootTables.DESERT_WELL_ARCHAEOLOGY,
						BuiltInLootTables.DESERT_PYRAMID_ARCHAEOLOGY,
						BuiltInLootTables.TRAIL_RUINS_ARCHAEOLOGY_RARE,
						BuiltInLootTables.OCEAN_RUIN_WARM_ARCHAEOLOGY,
						BuiltInLootTables.OCEAN_RUIN_COLD_ARCHAEOLOGY
				);
				if (treasureTables.contains(id))
					tableBuilder.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 1)).add(TagEntry.expandTag(DwarvenTreasuresItemTags.RINGS)).conditionally(LootItemRandomChanceCondition.randomChance(0.3f).build()));

				List<ResourceLocation> netherTreasureTables = List.of(
					BuiltInLootTables.BASTION_TREASURE,
					BuiltInLootTables.PIGLIN_BARTERING
				);
				if (netherTreasureTables.contains(id))
					tableBuilder.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(0, 1)).add(LootItem.lootTableItem(DwarvenTreasuresItems.ANCIENT_DEBRIS_RING)).add(LootItem.lootTableItem(DwarvenTreasuresItems.GOLD_RING)).add(LootItem.lootTableItem(DwarvenTreasuresItems.NETHERITE_RING)).conditionally(LootItemRandomChanceCondition.randomChance(0.3f).build()));
			}
		});
	}

	public static ResourceLocation modLoc(String path)
	{
		return new ResourceLocation(MOD_ID, path);
	}
}