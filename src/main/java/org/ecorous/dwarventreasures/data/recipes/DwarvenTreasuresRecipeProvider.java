package org.ecorous.dwarventreasures.data.recipes;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.SmithingTransformRecipeBuilder;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.ecorous.dwarventreasures.world.item.DwarvenTreasuresItems;
import org.ecorous.dwarventreasures.world.level.block.DwarvenTreasuresBlocks;

public class DwarvenTreasuresRecipeProvider extends FabricRecipeProvider {
    public DwarvenTreasuresRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(RecipeOutput writer) {
        mithrilSmithing(writer, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.CHESTPLATE));
        mithrilSmithing(writer, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.LEGGINGS));
        mithrilSmithing(writer, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.HELMET));
        mithrilSmithing(writer, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.BOOTS));
        mithrilSmithing(writer, Items.DIAMOND_SWORD, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_SWORD);
        mithrilSmithing(writer, Items.DIAMOND_AXE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_AXE);
        mithrilSmithing(writer, Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_PICKAXE);
        mithrilSmithing(writer, Items.DIAMOND_HOE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_HOE);
        mithrilSmithing(writer, Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_SHOVEL);
        mithrilSmithing(writer, Items.CHAINMAIL_CHESTPLATE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_WAISTCOAT);

        nineBlockStorageRecipes(writer, RecipeCategory.MISC, DwarvenTreasuresItems.MITHRIL_INGOT, RecipeCategory.BUILDING_BLOCKS, DwarvenTreasuresBlocks.MITHRIL_BLOCK.asItem());
    }

    public static void mithrilSmithing(RecipeOutput finishedRecipeConsumer, Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DwarvenTreasuresItems.MITHRIL_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredientItem), Ingredient.of(DwarvenTreasuresItems.MITHRIL_INGOT), category, resultItem).unlocks("has_mithril_ingot", has(DwarvenTreasuresItems.MITHRIL_INGOT)).save(finishedRecipeConsumer, getItemName(resultItem) + "_smithing");
    }
}
