package org.ecorous.dwarventreasures.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;

public class DwarvenTreasuresClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ItemGroupEvents.MODIFY_ENTRIES_ALL.register((group, entries) ->
                entries.acceptAll(DwarvenTreasuresItems.getItemsForTab(group)));
    }
}
