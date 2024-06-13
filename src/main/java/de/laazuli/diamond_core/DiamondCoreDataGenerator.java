package de.laazuli.diamond_core;

import de.laazuli.diamond_core.datagen.ModBlockTagProvider;
import de.laazuli.diamond_core.datagen.ModLootTableProvider;
import de.laazuli.diamond_core.datagen.ModModelProvider;
import de.laazuli.diamond_core.datagen.ModRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class DiamondCoreDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        System.out.println("Hello from " + DiamondCore.MOD_ID + " datagen");

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        System.out.println("Data Generation done");
    }
}
