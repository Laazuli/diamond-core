package de.laazuli.diamond_core.block;

import de.laazuli.diamond_core.DiamondCore;
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

public class ModBlocks {

    public static final Block CONDENSED_SCULK = registerBlock("condensed_sculk", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.ANCIENT_DEBRIS)));
    public static final Block LUMINESCENT_END_STONE = registerBlock("luminescent_end_stone", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.END_STONE)));
    public static final Block ENDERITE_BLOCK = registerBlock("enderite_block", new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK)));

    private static Item registerBlockItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(DiamondCore.MOD_ID, id), item);
    }

    private static Block registerBlock(String id, Block block) {
        registerBlockItem(id, new BlockItem(block, new Item.Properties()));
        return Registry.register(BuiltInRegistries.BLOCK, ResourceLocation.fromNamespaceAndPath(DiamondCore.MOD_ID, id), block);
    }

    private static void groupModBlockItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.NATURAL_BLOCKS).register(content -> {
            content.addAfter(Items.ANCIENT_DEBRIS, CONDENSED_SCULK);
            content.addAfter(CONDENSED_SCULK, LUMINESCENT_END_STONE);
        });

        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.BUILDING_BLOCKS).register(content -> {
            content.addAfter(Items.NETHERITE_BLOCK, ENDERITE_BLOCK);
        });
    }

    public static void initializeModBlocks() {
        DiamondCore.LOGGER.info("Initializing blocks for " + DiamondCore.MOD_ID);

        groupModBlockItems();
    }
}
