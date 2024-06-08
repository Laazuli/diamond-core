package de.laazuli.diamond_core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;

public class DiamondCore implements ModInitializer {

    public static final String MOD_ID = "diamond_core";

    public static final Block CONDENSED_SCULK = registerBlock("condensed_sculk", new Block(BlockBehaviour.Properties.of()));
    public static final Block LUMINESCENT_END_STONE = registerBlock("luminescent_end_stone", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final Block ENDERITE_BLOCK = registerBlock("enderite_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    public static final Item SCULKANITE_INGOT = registerItem("sculkanite_ingot", new Item(new Item.Properties()));
    // TODO: make Enderite stuff void resistant
    public static final Item ENDERITE_CLUMP = registerItem("enderite_clump", new Item(new Item.Properties()));
    public static final Item ENDERITE_INGOT = registerItem("enderite_ingot", new Item(new Item.Properties()));

    @Override
    public void onInitialize() {
        System.out.println("Hello from " + MOD_ID + " initialization");
//        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "luminescent_end_stone"), LUMINESCENT_END_STONE);
//        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "luminescent_end_stone"), new BlockItem(LUMINESCENT_END_STONE, new Item.Properties()));
//
//        Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, "condensed_sculk"), CONDENSED_SCULK);
//        Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, "condensed_sculk"), new BlockItem(CONDENSED_SCULK, new Item.Properties()));

        // group block items
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.addAfter(Items.ANCIENT_DEBRIS, CONDENSED_SCULK);
            content.addAfter(CONDENSED_SCULK, LUMINESCENT_END_STONE);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.NETHERITE_BLOCK, ENDERITE_BLOCK);
        });

        // group items
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> {
            content.addAfter(Items.NETHERITE_INGOT, SCULKANITE_INGOT);
            content.addAfter(SCULKANITE_INGOT, ENDERITE_CLUMP);
            content.addAfter(ENDERITE_CLUMP, ENDERITE_INGOT);
        });

    }

    private static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(MOD_ID, id), item);
    }

    private static Block registerBlock(String id, Block block) {
        registerItem(id, new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, new ResourceLocation(MOD_ID, id), block);
    }
}
