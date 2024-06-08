package de.laazuli.diamond_core;

import de.laazuli.diamond_core.block.ModBlocks;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiamondCore implements ModInitializer {

    public static final String MOD_ID = "diamond_core";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        System.out.println("Hello from " + MOD_ID + " initialization");

        ModBlocks.registerModBlocks();

        ModItems.registerModItems();
    }
}
