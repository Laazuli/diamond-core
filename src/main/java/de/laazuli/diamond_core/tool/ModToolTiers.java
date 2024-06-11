package de.laazuli.diamond_core.tool;

import com.google.common.base.Suppliers;
import de.laazuli.diamond_core.item.ModItems;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public enum ModToolTiers implements Tier {

    // TODO: adapt values
    SCULKANITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, Tiers.NETHERITE.getUses(), Tiers.NETHERITE.getSpeed(), Tiers.NETHERITE.getAttackDamageBonus(), Tiers.NETHERITE.getEnchantmentValue(), () -> Ingredient.of(ModItems.SCULKANITE_INGOT)),
    ENDERITE(BlockTags.INCORRECT_FOR_NETHERITE_TOOL, Tiers.NETHERITE.getUses(), Tiers.NETHERITE.getSpeed(), Tiers.NETHERITE.getAttackDamageBonus(), Tiers.NETHERITE.getEnchantmentValue(), () -> Ingredient.of(ModItems.ENDERITE_INGOT));

    private final TagKey<Block> incorrectBlocksForDrops;
    private final int uses;
    private final float speed;
    private final float damage;
    private final int enchantmentValue;
    private final Supplier<Ingredient> repairIngredient;

    ModToolTiers(TagKey<Block> incorrectBlocksForDrops, int uses, float speed, float damage, int enchantmentValue, Supplier<Ingredient> repairIngredient) {
        this.incorrectBlocksForDrops = incorrectBlocksForDrops; // TODO: eventually add new tag
        this.uses = uses;
        this.speed = speed;
        this.damage = damage;
        this.enchantmentValue = enchantmentValue;
        this.repairIngredient = Suppliers.memoize(repairIngredient::get);
    }

    @Override
    public int getUses() {
        return this.uses;
    }

    @Override
    public float getSpeed() {
        return this.speed;
    }

    @Override
    public float getAttackDamageBonus() {
        return this.damage;
    }

    @Override
    public @NotNull TagKey<Block> getIncorrectBlocksForDrops() {
        return this.incorrectBlocksForDrops;
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
