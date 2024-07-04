package de.laazuli.diamond_core;

import de.laazuli.diamond_core.block.ModBlocks;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiamondCore implements ModInitializer {

    public static final String MOD_ID = "diamond_core";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        System.out.println("Hello from " + MOD_ID + " initialization");

        ModBlocks.initializeModBlocks();

        ModItems.initializeModItems();

        /* TODO: Mixin in Entity#isSteppingCarefully modifyReturnValue and add if entity.isWaringFullSetOfSculkaniteArmor
                 and change description of "Sneak 100" Advancement to "... or wear a full set of Sculkanite Armor" through TP
         */
    }
}
