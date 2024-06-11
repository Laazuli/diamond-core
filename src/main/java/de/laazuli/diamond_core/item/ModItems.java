package de.laazuli.diamond_core.item;

import de.laazuli.diamond_core.DiamondCore;
import de.laazuli.diamond_core.armor.ModArmorMaterials;
import de.laazuli.diamond_core.tool.ModToolTiers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

public class ModItems {

    // sculkanite
    public static final Item SCULKANITE_INGOT = registerItem("sculkanite_ingot", new Item(new Item.Properties()));

    public static final Item SCULKANITE_HELMET = registerItem("sculkanite_helmet", new ArmorItem(Holder.direct(ModArmorMaterials.SCULKANITE), ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37)))); // TODO: alter durability multiplier for material
    public static final Item SCULKANITE_CHESTPLATE = registerItem("sculkanite_chestplate", new ArmorItem(Holder.direct(ModArmorMaterials.SCULKANITE), ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final Item SCULKANITE_LEGGINGS = registerItem("sculkanite_leggings", new ArmorItem(Holder.direct(ModArmorMaterials.SCULKANITE), ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final Item SCULKANITE_BOOTS = registerItem("sculkanite_boots", new ArmorItem(Holder.direct(ModArmorMaterials.SCULKANITE), ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final Item SCULKANITE_AXE = registerItem("sculkanite_axe", new AxeItem(ModToolTiers.SCULKANITE, new Item.Properties()));

    // enderite
    // TODO: make Enderite stuff void resistant
    public static final Item ENDERITE_CLUMP = registerItem("enderite_clump", new Item(new Item.Properties()));
    public static final Item ENDERITE_INGOT = registerItem("enderite_ingot", new Item(new Item.Properties()));
    public static final Item ENDERITE_HELMET = registerItem("enderite_helmet", new ArmorItem(Holder.direct(ModArmorMaterials.ENDERITE), ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final Item ENDERITE_CHESTPLATE = registerItem("enderite_chestplate", new ArmorItem(Holder.direct(ModArmorMaterials.ENDERITE), ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final Item ENDERITE_LEGGINGS = registerItem("enderite_leggings", new ArmorItem(Holder.direct(ModArmorMaterials.ENDERITE), ArmorItem.Type.LEGGINGS,new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final Item ENDERITE_BOOTS = registerItem("enderite_boots", new ArmorItem(Holder.direct(ModArmorMaterials.ENDERITE), ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    private static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(DiamondCore.MOD_ID, id), item);
    }

    private static void groupModItems() {
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.INGREDIENTS).register(content -> {
            content.addAfter(Items.NETHERITE_INGOT, SCULKANITE_INGOT);
            content.addAfter(SCULKANITE_INGOT, ENDERITE_CLUMP);
            content.addAfter(ENDERITE_CLUMP, ENDERITE_INGOT);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.COMBAT).register(content -> {
            content.addAfter(Items.NETHERITE_BOOTS, SCULKANITE_HELMET);
            content.addAfter(SCULKANITE_HELMET, SCULKANITE_CHESTPLATE);
            content.addAfter(SCULKANITE_CHESTPLATE, SCULKANITE_LEGGINGS);
            content.addAfter(SCULKANITE_LEGGINGS, SCULKANITE_BOOTS);

            content.addAfter(Items.NETHERITE_AXE, SCULKANITE_AXE);

            content.addAfter(SCULKANITE_BOOTS, ENDERITE_HELMET);
            content.addAfter(ENDERITE_HELMET, ENDERITE_CHESTPLATE);
            content.addAfter(ENDERITE_CHESTPLATE, ENDERITE_LEGGINGS);
            content.addAfter(ENDERITE_LEGGINGS, ENDERITE_BOOTS);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> {
            content.addAfter(Items.NETHERITE_HOE, SCULKANITE_AXE);
        });
    }

    public static void initializeModItems() {
        DiamondCore.LOGGER.info("Initializing items for " + DiamondCore.MOD_ID);

        groupModItems();
    }
}
