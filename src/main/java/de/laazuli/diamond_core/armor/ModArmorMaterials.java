package de.laazuli.diamond_core.armor;

import de.laazuli.diamond_core.DiamondCore;
import de.laazuli.diamond_core.item.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModArmorMaterials {

    public static final ArmorMaterial SCULKANITE = new ArmorMaterial(new HashMap<ArmorItem.Type, Integer>() {{
        put(ArmorItem.Type.BOOTS, 4);
        put(ArmorItem.Type.LEGGINGS, 6);
        put(ArmorItem.Type.CHESTPLATE, 8);
        put(ArmorItem.Type.HELMET, 3);
        put(ArmorItem.Type.BODY, 11);
    }}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItems.SCULKANITE_INGOT), List.of(new ArmorMaterial.Layer(new ResourceLocation(DiamondCore.MOD_ID, "sculkanite"))), 0f, 0f); // TODO: adapt toughness and kbRes values

    public static final ArmorMaterial ENDERITE = new ArmorMaterial(new HashMap<ArmorItem.Type, Integer>() {{
        put(ArmorItem.Type.BOOTS, 4);
        put(ArmorItem.Type.LEGGINGS, 6);
        put(ArmorItem.Type.CHESTPLATE, 8);
        put(ArmorItem.Type.HELMET, 3);
        put(ArmorItem.Type.BODY, 11);
    }}, 10, SoundEvents.ARMOR_EQUIP_GENERIC, () -> Ingredient.of(ModItems.SCULKANITE_INGOT), List.of(new ArmorMaterial.Layer(new ResourceLocation(DiamondCore.MOD_ID, "enderite"))), 0f, 0f);
}
