package de.laazuli.diamond_core.mixin;

import de.laazuli.diamond_core.ItemStackContextInfo;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(GameEvent.Context.class)
public class GameEventContextMixin implements ItemStackContextInfo {

    @Unique
    private ItemStack diamond_core$affectedItemStack = null;

    @Override
    public void putAffectedItemStack(ItemStack affectedItemStack) {
        this.diamond_core$affectedItemStack = affectedItemStack;
    }

    @Override
    public ItemStack affectedItemStack() {
        return diamond_core$affectedItemStack;
    }
}
