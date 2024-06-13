package de.laazuli.diamond_core.datagen;

import de.laazuli.diamond_core.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        System.out.println("Genrate loot tables");

        dropSelf(ModBlocks.CONDENSED_SCULK);
        dropSelf(ModBlocks.LUMINESCENT_END_STONE);
        dropSelf(ModBlocks.ENDERITE_BLOCK);
    }
}
