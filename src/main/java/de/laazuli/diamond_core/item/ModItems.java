package de.laazuli.diamond_core.item;

import de.laazuli.diamond_core.DiamondCore;
import de.laazuli.diamond_core.armor.ModArmorMaterials;
import de.laazuli.diamond_core.tool.ModToolTiers;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.*;

public class ModItems {

    // sculkanite
    // TODO: make Sculkanite stuff silent (undetectable by sculk sensor)
    public static final Item SCULKANITE_INGOT = registerItem("sculkanite_ingot", new Item(new Item.Properties()));

    public static final Item SCULKANITE_HELMET = registerItem("sculkanite_helmet", new ArmorItem(ModArmorMaterials.SCULKANITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37)))); // TODO: alter durability multiplier for material
    public static final Item SCULKANITE_CHESTPLATE = registerItem("sculkanite_chestplate", new ArmorItem(ModArmorMaterials.SCULKANITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final Item SCULKANITE_LEGGINGS = registerItem("sculkanite_leggings", new ArmorItem(ModArmorMaterials.SCULKANITE, ArmorItem.Type.LEGGINGS, new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final Item SCULKANITE_BOOTS = registerItem("sculkanite_boots", new ArmorItem(ModArmorMaterials.SCULKANITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final Item SCULKANITE_SWORD = registerItem("sculkanite_sword", new SwordItem(ModToolTiers.SCULKANITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.SCULKANITE, 3, -2.4f))));
    public static final Item SCULKANITE_SHOVEL = registerItem("sculkanite_shovel", new ShovelItem(ModToolTiers.SCULKANITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.SCULKANITE, 1.5f, -3.0f))));
    public static final Item SCULKANITE_PICKAXE = registerItem("sculkanite_pickaxe", new PickaxeItem(ModToolTiers.SCULKANITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.SCULKANITE, 1.0f, -2.8f))));
    public static final Item SCULKANITE_AXE = registerItem("sculkanite_axe", new AxeItem(ModToolTiers.SCULKANITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.SCULKANITE, 5.0f, -3.0f))));
    public static final Item SCULKANITE_HOE = registerItem("sculkanite_hoe", new HoeItem(ModToolTiers.SCULKANITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.SCULKANITE, -3.0f, 0.0f))));

    // enderite
    // TODO: make Enderite stuff void resistant
    public static final Item ENDERITE_CLUMP = registerItem("enderite_clump", new Item(new Item.Properties()));
    public static final Item ENDERITE_INGOT = registerItem("enderite_ingot", new Item(new Item.Properties()));

    public static final Item ENDERITE_HELMET = registerItem("enderite_helmet", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.HELMET, new Item.Properties().durability(ArmorItem.Type.HELMET.getDurability(37))));
    public static final Item ENDERITE_CHESTPLATE = registerItem("enderite_chestplate", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.CHESTPLATE, new Item.Properties().durability(ArmorItem.Type.CHESTPLATE.getDurability(37))));
    public static final Item ENDERITE_LEGGINGS = registerItem("enderite_leggings", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.LEGGINGS,new Item.Properties().durability(ArmorItem.Type.LEGGINGS.getDurability(37))));
    public static final Item ENDERITE_BOOTS = registerItem("enderite_boots", new ArmorItem(ModArmorMaterials.ENDERITE, ArmorItem.Type.BOOTS, new Item.Properties().durability(ArmorItem.Type.BOOTS.getDurability(37))));

    public static final Item ENDERITE_SWORD = registerItem("enderite_sword", new SwordItem(ModToolTiers.ENDERITE, new Item.Properties().attributes(SwordItem.createAttributes(ModToolTiers.ENDERITE, 3, -2.4f))));
    public static final Item ENDERITE_SHOVEL = registerItem("enderite_shovel", new ShovelItem(ModToolTiers.ENDERITE, new Item.Properties().attributes(ShovelItem.createAttributes(ModToolTiers.ENDERITE, 1.5f, -3.0f))));
    public static final Item ENDERITE_PICKAXE = registerItem("enderite_pickaxe", new PickaxeItem(ModToolTiers.ENDERITE, new Item.Properties().attributes(PickaxeItem.createAttributes(ModToolTiers.ENDERITE, 1.0f, -2.8f))));
    public static final Item ENDERITE_AXE = registerItem("enderite_axe", new AxeItem(ModToolTiers.ENDERITE, new Item.Properties().attributes(AxeItem.createAttributes(ModToolTiers.ENDERITE, 5.0f, -3.0f))));
    public static final Item ENDERITE_HOE = registerItem("enderite_hoe", new HoeItem(ModToolTiers.ENDERITE, new Item.Properties().attributes(HoeItem.createAttributes(ModToolTiers.ENDERITE, -3.0f, 0.0f))));

    private static Item registerItem(String id, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, ResourceLocation.fromNamespaceAndPath(DiamondCore.MOD_ID, id), item);
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

            content.addAfter(Items.NETHERITE_SWORD, SCULKANITE_SWORD);
            content.addAfter(Items.NETHERITE_AXE, SCULKANITE_AXE);

            content.addAfter(SCULKANITE_BOOTS, ENDERITE_HELMET);
            content.addAfter(ENDERITE_HELMET, ENDERITE_CHESTPLATE);
            content.addAfter(ENDERITE_CHESTPLATE, ENDERITE_LEGGINGS);
            content.addAfter(ENDERITE_LEGGINGS, ENDERITE_BOOTS);

            content.addAfter(SCULKANITE_SWORD, ENDERITE_SWORD);
            content.addAfter(SCULKANITE_AXE, ENDERITE_AXE);
        });
        ItemGroupEvents.modifyEntriesEvent(CreativeModeTabs.TOOLS_AND_UTILITIES).register(content -> {
            content.addAfter(Items.NETHERITE_HOE, SCULKANITE_SHOVEL);
            content.addAfter(SCULKANITE_SHOVEL, SCULKANITE_PICKAXE);
            content.addAfter(SCULKANITE_PICKAXE, SCULKANITE_AXE);
            content.addAfter(SCULKANITE_AXE, SCULKANITE_HOE);

            content.addAfter(SCULKANITE_HOE, ENDERITE_SHOVEL);
            content.addAfter(ENDERITE_SHOVEL, ENDERITE_PICKAXE);
            content.addAfter(ENDERITE_PICKAXE, ENDERITE_AXE);
            content.addAfter(ENDERITE_AXE, ENDERITE_HOE);
        });
    }

    public static void initializeModItems() {
        DiamondCore.LOGGER.info("Initializing items for " + DiamondCore.MOD_ID);

        groupModItems();
    }
}
