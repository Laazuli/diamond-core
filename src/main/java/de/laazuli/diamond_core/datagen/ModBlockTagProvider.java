package de.laazuli.diamond_core.datagen;

import de.laazuli.diamond_core.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider wrapperLookup) {
        System.out.println("adding block tags");

        getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.CONDENSED_SCULK)
                .add(ModBlocks.LUMINESCENT_END_STONE)
                .add(ModBlocks.ENDERITE_BLOCK);

        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.CONDENSED_SCULK)
                .add(ModBlocks.LUMINESCENT_END_STONE)
                .add(ModBlocks.ENDERITE_BLOCK);
    }
}
