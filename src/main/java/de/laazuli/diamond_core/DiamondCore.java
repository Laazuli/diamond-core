package de.laazuli.diamond_core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DiamondCore implements ModInitializer {

    public static final String MOD_ID = "diamond_core";

    public static final Block LUMINESCENT_END_STONE = new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE));
    public static final Block CONDENSED_SCULK = new Block(BlockBehaviour.Properties.of());

    @Override
    public void onInitialize() {
        System.out.println("Hello from " + MOD_ID + " initialization");
        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "luminescent_end_stone"), LUMINESCENT_END_STONE);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "luminescent_end_stone"), new BlockItem(LUMINESCENT_END_STONE, new Item.Properties()));

        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "condensed_sculk"), CONDENSED_SCULK);
        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "condensed_sculk"), new BlockItem(CONDENSED_SCULK, new Item.Properties()));

        // group items
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.prepend(LUMINESCENT_END_STONE);
            content.prepend(CONDENSED_SCULK);
        });
    }
}
