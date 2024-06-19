package de.laazuli.diamond_core.armor;

import de.laazuli.diamond_core.DiamondCore;
import de.laazuli.diamond_core.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ArmorMaterials;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.*;
import java.util.function.Supplier;

public class ModArmorMaterials {

    public static final Holder<ArmorMaterial> SCULKANITE = registerNetheriteLikeArmorMaterial("sculkanite", SoundEvents.ARMOR_EQUIP_GENERIC, ModItems.SCULKANITE_INGOT);

    public static final Holder<ArmorMaterial> ENDERITE = registerNetheriteLikeArmorMaterial("enderite", SoundEvents.ARMOR_EQUIP_GENERIC, ModItems.ENDERITE_INGOT);


    private static Holder<ArmorMaterial> registerNetheriteLikeArmorMaterial(String id, Holder<SoundEvent> equipSound, ItemLike repairItem) {
        return registerArmorMaterial(id, ArmorMaterials.NETHERITE.value().defense(), equipSound, ArmorMaterials.NETHERITE.value().enchantmentValue(), ArmorMaterials.NETHERITE.value().toughness(), ArmorMaterials.NETHERITE.value().knockbackResistance(), () -> Ingredient.of(repairItem));
    }

    private static Holder<ArmorMaterial> registerArmorMaterial(String id, Map<ArmorItem.Type, Integer> defense, Holder<SoundEvent> equipSound, int enchantmentValue, float toughness, float knockbackResistance, Supplier<Ingredient> repairIngredient) {
        List<ArmorMaterial.Layer> layers = List.of(new ArmorMaterial.Layer(new ResourceLocation(id)));
        EnumMap<ArmorItem.Type, Integer> completeDefenseMap = new EnumMap<ArmorItem.Type, Integer>(ArmorItem.Type.class);
        for (ArmorItem.Type type : ArmorItem.Type.values()) {
            completeDefenseMap.put(type, defense.get(type));
        }
        return Registry.registerForHolder(BuiltInRegistries.ARMOR_MATERIAL, new ResourceLocation(DiamondCore.MOD_ID, id), new ArmorMaterial(completeDefenseMap, enchantmentValue, equipSound, repairIngredient, layers, toughness, knockbackResistance));
    }
}
