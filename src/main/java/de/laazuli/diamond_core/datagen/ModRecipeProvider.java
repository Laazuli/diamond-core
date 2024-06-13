package de.laazuli.diamond_core.datagen;

import de.laazuli.diamond_core.block.ModBlocks;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void buildRecipes(RecipeOutput exporter) {
        System.out.println("Build recipies");
        smeltingAndBlastingRecipe(exporter, ModBlocks.CONDENSED_SCULK, Items.ECHO_SHARD, 2.0f);
        smeltingAndBlastingRecipe(exporter, ModBlocks.LUMINESCENT_END_STONE, ModItems.ENDERITE_CLUMP, 2.0f);

        netheriteIngotLikeRecipe(exporter, Items.ECHO_SHARD, Items.AMETHYST_SHARD, ModItems.SCULKANITE_INGOT);
        netheriteIngotLikeRecipe(exporter, Items.QUARTZ, ModItems.ENDERITE_CLUMP, ModItems.ENDERITE_INGOT);

        compacting(exporter, RecipeCategory.MISC, ModItems.ENDERITE_INGOT, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ENDERITE_BLOCK);
    }

    private void smeltingRecipe(RecipeOutput exporter, RecipeCategory recipeCategory, ItemLike input, ItemLike result, float experience, int smeltingTime) {
        SimpleCookingRecipeBuilder.smelting(Ingredient.of(input), recipeCategory, result, experience, smeltingTime).unlockedBy(RecipeProvider.getHasName(input), RecipeProvider.has(input)).save(exporter);
    }

    private void smeltingRecipe(RecipeOutput exporter, ItemLike input, ItemLike result, float experience) {
        smeltingRecipe(exporter, RecipeCategory.MISC, input, result, experience, 200);
    }

    private void blastingRecipe(RecipeOutput exporter, RecipeCategory recipeCategory, ItemLike input, ItemLike result, float experience, int smeltingTime) {
        SimpleCookingRecipeBuilder.blasting(Ingredient.of(input), recipeCategory, result, experience, smeltingTime).unlockedBy(getHasName(input), has(input)).save(exporter, VanillaRecipeProvider.getBlastingRecipeName(result));
    }

    private void blastingRecipe(RecipeOutput exporter, ItemLike input, ItemLike result, float experience) {
        blastingRecipe(exporter, RecipeCategory.MISC, input, result, experience, 100);
    }

    private void smeltingAndBlastingRecipe(RecipeOutput exporter, RecipeCategory recipeCategory, ItemLike input, ItemLike result, float experience, int smeltingTime, int blastingTime) {
        smeltingRecipe(exporter, recipeCategory, input, result, experience, smeltingTime);
        blastingRecipe(exporter, recipeCategory, input, result, experience, blastingTime);
    }

    private void smeltingAndBlastingRecipe(RecipeOutput exporter, ItemLike input, ItemLike result, float experience) {
        smeltingAndBlastingRecipe(exporter, RecipeCategory.MISC, input, result, experience, 200, 100);
    }

    private void netheriteIngotLikeRecipe(RecipeOutput exporter, ItemLike inputA, ItemLike inputB, ItemLike output) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output).requires(inputA, 4).requires(inputB, 4).unlockedBy(getHasName(inputA), has(inputA)).unlockedBy(getHasName(inputB), has(inputB)).save(exporter);
    }

    // compacting with custom names in both directions
    private void compacting(RecipeOutput exporter, RecipeCategory unpackedCategory, ItemLike unpacked, RecipeCategory packedCategory, ItemLike packed) {
        nineBlockStorageRecipes(exporter, unpackedCategory, unpacked, packedCategory, packed, getConversionRecipeName(packed, unpacked), null, getConversionRecipeName(unpacked, packed), null);
    }
}
