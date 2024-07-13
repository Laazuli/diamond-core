package de.laazuli.diamond_core;

import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

public interface ItemStackContextInfo {
    default void putAffectedItemStack(ItemStack affectedItemStack) {}

    @Nullable
    default ItemStack affectedItemStack() {
        return null;
    }
}
