package de.laazuli.diamond_core.datagen;

import de.laazuli.diamond_core.block.ModBlocks;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.model.ModelTemplates;
import net.minecraft.data.models.model.TexturedModel;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockStateModelGenerator) {
        System.out.println("Generate block models");

        blockStateModelGenerator.createTrivialBlock(ModBlocks.CONDENSED_SCULK, TexturedModel.COLUMN);
        blockStateModelGenerator.createTrivialCube(ModBlocks.LUMINESCENT_END_STONE);
        blockStateModelGenerator.createTrivialCube(ModBlocks.ENDERITE_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerator) {
        System.out.println("Generate item models");

        itemModelGenerator.generateFlatItem(ModItems.SCULKANITE_INGOT, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDERITE_CLUMP, ModelTemplates.FLAT_ITEM);
        itemModelGenerator.generateFlatItem(ModItems.ENDERITE_INGOT, ModelTemplates.FLAT_ITEM);
    }
}
