package org.ecorous.dwarventreasures.server;

import eu.midnightdust.lib.config.MidnightConfig;
import org.ecorous.dwarventreasures.world.item.RingItem;

public class DwarvenTreasuresServerConfig extends MidnightConfig
{
	// Rings
	@Comment(category = "rings", centered = true) public static final String rings_comment = "Settings for the Rings";
	@Comment(category = "rings") public static final String ring_effect_target_comment = "Determines the targets of the Ring's effects";
	@Comment(category = "rings") public static final String ring_effect_target_options_comment = "Options:";
	@Comment(category = "rings") public static final String ring_effect_target_all_comment = "ALL: All non-allied entities, including enemies, animals, and players";
	@Comment(category = "rings") public static final String ring_effect_target_enemies_comment = "ENEMIES: All non-allied hostile entities";
	@Comment(category = "rings") public static final String ring_effect_target_non_players_comment = "NON_PLAYERS: All non-player and non-allied entities, including enemies and animals";
	@Comment(category = "rings") public static final String ring_effect_target_enemies_and_players_comment = "ENEMIES_AND_PLAYERS: All non-allied hostile entities and players";
	@Comment(category = "rings") public static final String ring_effect_target_players_comment = "PLAYERS: All non-allied players";
	@Entry(category = "rings") public static RingItem.RingEffectTarget ringEffectTarget = RingItem.RingEffectTarget.ALL;
}
