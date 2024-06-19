package de.laazuli.diamond_core;

import de.laazuli.diamond_core.datagen.*;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.registries.BuiltInRegistries;

public class DiamondCoreDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        System.out.println("Hello from " + DiamondCore.MOD_ID + " datagen");

        System.out.println(BuiltInRegistries.ITEM.getKey(ModItems.SCULKANITE_HELMET));

        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();

        pack.addProvider(ModBlockTagProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModLootTableProvider::new);
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModRecipeProvider::new);

        System.out.println("Data Generation done");
    }
}
