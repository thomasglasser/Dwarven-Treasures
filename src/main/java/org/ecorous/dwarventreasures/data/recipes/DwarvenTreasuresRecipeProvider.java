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

public class DwarvenTreasuresRecipeProvider extends FabricRecipeProvider {
    public DwarvenTreasuresRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void buildRecipes(RecipeOutput writer) {
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_CHESTPLATE, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.CHESTPLATE));
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_LEGGINGS, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.LEGGINGS));
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_HELMET, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.HELMET));
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_BOOTS, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_ARMOR.get(ArmorItem.Type.BOOTS));
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_SWORD, RecipeCategory.COMBAT, DwarvenTreasuresItems.MITHRIL_SWORD);
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_AXE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_AXE);
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_PICKAXE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_PICKAXE);
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_HOE, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_HOE);
        DwarvenTreasuresRecipeProvider.mithrilSmithing(writer, Items.DIAMOND_SHOVEL, RecipeCategory.TOOLS, DwarvenTreasuresItems.MITHRIL_SHOVEL);
    }

    public static void mithrilSmithing(RecipeOutput finishedRecipeConsumer, Item ingredientItem, RecipeCategory category, Item resultItem) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(DwarvenTreasuresItems.MITHRIL_UPGRADE_SMITHING_TEMPLATE), Ingredient.of(ingredientItem), Ingredient.of(DwarvenTreasuresItems.MITHRIL_INGOT), category, resultItem).unlocks("has_mithril_ingot", has(DwarvenTreasuresItems.MITHRIL_INGOT)).save(finishedRecipeConsumer, getItemName(resultItem) + "_smithing");
    }
}
