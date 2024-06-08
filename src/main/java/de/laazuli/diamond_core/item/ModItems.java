package de.laazuli.diamond_core.item;

import de.laazuli.diamond_core.DiamondCore;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

public class ModItems {

    public static final Item SCULKANITE_INGOT = registerItem("sculkanite_ingot", new Item(new Item.Properties()));
    // TODO: make Enderite stuff void resistant
    public static final Item ENDERITE_CLUMP = registerItem("enderite_clump", new Item(new Item.Properties()));
    public static final Item ENDERITE_INGOT = registerItem("enderite_ingot", new Item(new Item.Properties()));

    private static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DiamondCore.MOD_ID, id), item);
    }

    private static void groupModItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> {
            content.addAfter(Items.NETHERITE_INGOT, SCULKANITE_INGOT);
            content.addAfter(SCULKANITE_INGOT, ENDERITE_CLUMP);
            content.addAfter(ENDERITE_CLUMP, ENDERITE_INGOT);
        });
    }

    public static void registerModItems() {
        DiamondCore.LOGGER.info("Registering items for " + DiamondCore.MOD_ID);

        groupModItems();
    }
}
