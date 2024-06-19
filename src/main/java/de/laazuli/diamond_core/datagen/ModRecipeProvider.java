package de.laazuli.diamond_core.datagen;

import de.laazuli.diamond_core.block.ModBlocks;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.*;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.world.item.Item;
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
        System.out.println("Build recipes");
        smeltingAndBlastingRecipe(exporter, ModBlocks.CONDENSED_SCULK, Items.ECHO_SHARD, 2.0f);
        smeltingAndBlastingRecipe(exporter, ModBlocks.LUMINESCENT_END_STONE, ModItems.ENDERITE_CLUMP, 2.0f);

        netheriteIngotLikeRecipe(exporter, Items.ECHO_SHARD, Items.AMETHYST_SHARD, ModItems.SCULKANITE_INGOT);
        netheriteIngotLikeRecipe(exporter, Items.QUARTZ, ModItems.ENDERITE_CLUMP, ModItems.ENDERITE_INGOT);
        
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_SWORD);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_SHOVEL);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_PICKAXE);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_AXE);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_HOE);

        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SWORD, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_SWORD);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_SHOVEL, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_SHOVEL);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_PICKAXE, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_PICKAXE);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_AXE, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_AXE);
        smithing(exporter, RecipeCategory.TOOLS, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HOE, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_HOE);

        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HELMET, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_HELMET);
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_CHESTPLATE, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_CHESTPLATE);
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_LEGGINGS, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_LEGGINGS);
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_BOOTS, ModItems.SCULKANITE_INGOT, ModItems.SCULKANITE_BOOTS);

        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_HELMET, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_HELMET);
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_CHESTPLATE, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_CHESTPLATE);
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_LEGGINGS, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_LEGGINGS);
        smithing(exporter, RecipeCategory.COMBAT, Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, Items.DIAMOND_BOOTS, ModItems.ENDERITE_INGOT, ModItems.ENDERITE_BOOTS);

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

    public static void smithing(RecipeOutput exporter, RecipeCategory recipeCategory, ItemLike template, Item base, Item material, Item result) {
        SmithingTransformRecipeBuilder.smithing(Ingredient.of(template), Ingredient.of(base), Ingredient.of(material), recipeCategory, result).unlocks(getHasName(material), RecipeProvider.has(material)).save(exporter, RecipeProvider.getItemName(result) + "_smithing");
    }

//    private void swordRecipe(RecipeOutput exporter, ItemLike output, ItemLike material) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
//                .pattern("#")
//                .pattern("#")
//                .pattern("I")
//                .define('#', material)
//                .define('I', Items.STICK)
//                .unlockedBy(getHasName(material), has(material))
//                .save(exporter);
//    }
//
//    private void shovelRecipe(RecipeOutput exporter, ItemLike output, ItemLike material) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
//                .pattern("#")
//                .pattern("I")
//                .pattern("I")
//                .define('#', material)
//                .define('I', Items.STICK)
//                .unlockedBy(getHasName(material), has(material))
//                .save(exporter);
//    }
//
//    private void pickaxeRecipe(RecipeOutput exporter, ItemLike output, ItemLike material) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
//                .pattern("###")
//                .pattern(" I ")
//                .pattern(" I ")
//                .define('#', material)
//                .define('I', Items.STICK)
//                .unlockedBy(getHasName(material), has(material))
//                .save(exporter);
//    }
//
//    private void axeRecipe(RecipeOutput exporter, ItemLike output, ItemLike material) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
//                .pattern("##")
//                .pattern("#I")
//                .pattern(" I")
//                .define('#', material)
//                .define('I', Items.STICK)
//                .unlockedBy(getHasName(material), has(material))
//                .save(exporter);
//    }
//
//    private void hoeRecipe(RecipeOutput exporter, ItemLike output, ItemLike material) {
//        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
//                .pattern("##")
//                .pattern(" #")
//                .pattern(" I")
//                .define('#', material)
//                .define('I', Items.STICK)
//                .unlockedBy(getHasName(material), has(material))
//                .save(exporter);
//    }
}
