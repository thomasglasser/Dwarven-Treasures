package org.ecorous.dwarventreasures.client;

import eu.midnightdust.lib.config.MidnightConfig;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import org.ecorous.dwarventreasures.DwarvenTreasures;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;

public class DwarvenTreasuresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) ->
                entries.acceptAll(DwarvenTreasuresItems.getItemsForTab(BuiltInRegistries.CREATIVE_MODE_TAB.getResourceKey(group).orElseThrow())));

        MidnightConfig.init(DwarvenTreasures.MOD_ID, DwarvenTreasuresClientConfig.class);
    }
}
