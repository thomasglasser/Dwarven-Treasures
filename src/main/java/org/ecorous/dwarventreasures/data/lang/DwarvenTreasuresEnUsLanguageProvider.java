package org.ecorous.dwarventreasures.data.lang;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.contents.TranslatableContents;
import net.minecraft.world.item.ArmorItem;
import org.ecorous.dwarventreasures.DwarvenTreasures;
import org.ecorous.dwarventreasures.server.DwarvenTreasuresServerConfig;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.item.RingItem;
import org.ecorous.dwarventreasures.world.item.enchantment.DwarvenTreasuresEnchantments;
import org.ecorous.dwarventreasures.world.level.block.DwarvenTreasuresBlocks;

import java.util.Map;

public class DwarvenTreasuresEnUsLanguageProvider extends FabricLanguageProvider
{
    private TranslationBuilder translationBuilder;

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
        this.translationBuilder = translationBuilder;

        translationBuilder.add(DwarvenTreasuresItems.COPPER_RING, "Copper Ring");
        translationBuilder.add(DwarvenTreasuresItems.ANCIENT_DEBRIS_RING, "Ancient Debris Ring");
        translationBuilder.add(DwarvenTreasuresItems.NETHERITE_RING, "Netherite Ring");
        translationBuilder.add(DwarvenTreasuresItems.GOLD_RING, "Gold Ring");
        translationBuilder.add(DwarvenTreasuresItems.MITHRIL_RING, "Mithril Ring");
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

        translationBuilder.add(DwarvenTreasuresEnchantments.SUSURRUS, "Susurrus");
        translationBuilder.add(DwarvenTreasuresEnchantments.ANOINTMENT, "Anointment");
        translationBuilder.add(DwarvenTreasuresEnchantments.RADIANCE, "Radiance");
        translationBuilder.add(DwarvenTreasuresEnchantments.FROST, "Frost");

        add(DwarvenTreasuresItems.MITHRIL_UPGRADE_INGREDIENTS, "Mithril Ingot");
        add(DwarvenTreasuresItems.MITHRIL_UPGRADE, "Mithril Upgrade");
        add(DwarvenTreasuresItems.MITHRIL_UPGRADE_ADDITIONS_SLOT_DESCRIPTION, "Put a Mithril Ingot here");

        addConfigs();
    }

    protected void add(Component component, String name)
    {
        if (component.getContents() instanceof TranslatableContents translatableContents)
        {
            translationBuilder.add(translatableContents.getKey(), name);
        }
    }

    protected void add(String key, String name)
    {
        translationBuilder.add(key, name);
    }

    public void addConfigs()
    {
        add(DwarvenTreasures.MOD_ID + ".midnightconfig.title", DwarvenTreasures.MOD_NAME);

        // Server
        addConfigCategory("rings", "Rings");
        addConfig("rings_comment", DwarvenTreasuresServerConfig.rings_comment);
        addConfig("ring_effect_target_comment", DwarvenTreasuresServerConfig.ring_effect_target_comment);
        addConfig("ring_effect_target_options_comment", DwarvenTreasuresServerConfig.ring_effect_target_options_comment);
        addConfig("ring_effect_target_all_comment", DwarvenTreasuresServerConfig.ring_effect_target_all_comment);
        addConfig("ring_effect_target_enemies_comment", DwarvenTreasuresServerConfig.ring_effect_target_enemies_comment);
        addConfig("ring_effect_target_non_players_comment", DwarvenTreasuresServerConfig.ring_effect_target_non_players_comment);
        addConfig("ring_effect_target_enemies_and_players_comment", DwarvenTreasuresServerConfig.ring_effect_target_enemies_and_players_comment);
        addConfig("ring_effect_target_players_comment", DwarvenTreasuresServerConfig.ring_effect_target_players_comment);
        addConfig("ringEffectTarget", "Ring Effect Target");
        addConfig(RingItem.RingEffectTarget.ALL, "All");
        addConfig(RingItem.RingEffectTarget.ENEMIES, "Enemies");
        addConfig(RingItem.RingEffectTarget.NON_PLAYERS, "Non-Players");
        addConfig(RingItem.RingEffectTarget.ENEMIES_AND_PLAYERS, "Enemies and Players");
        addConfig(RingItem.RingEffectTarget.PLAYERS, "Players");
    }

    private void addConfig(String field, String name)
    {
        add(DwarvenTreasures.MOD_ID + ".midnightconfig." + field, name);
    }

    private void addConfig(Enum<?> e, String name)
    {
        add(DwarvenTreasures.MOD_ID + ".midnightconfig.enum." + e.getDeclaringClass().getSimpleName() + "." + e.name(), name);
    }

    private void addConfigCategory(String field, String name)
    {
        add(DwarvenTreasures.MOD_ID + ".midnightconfig.category." + field, name);
    }
}
