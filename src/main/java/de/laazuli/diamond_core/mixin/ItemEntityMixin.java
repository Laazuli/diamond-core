package de.laazuli.diamond_core.mixin;

import de.laazuli.diamond_core.VibrationSuppressible;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemEntity.class)
public class ItemEntityMixin implements VibrationSuppressible {

    @Unique
    private boolean diamond_core$suppress_vibrations = false;

    @Override
    public void setSuppressVibrations(boolean suppressVibrations) {
        this.diamond_core$suppress_vibrations = suppressVibrations;
    }

    @Override
    public boolean areVibrationsSuppressed() {
        return diamond_core$suppress_vibrations;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void addSuppressVibration(CompoundTag compoundTag, CallbackInfo ci) {
        compoundTag.putBoolean("diamond_core.suppress_vibration", this.diamond_core$suppress_vibrations);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void readSuppressVibration(CompoundTag compoundTag, CallbackInfo ci) {
        this.diamond_core$suppress_vibrations = compoundTag.getBoolean("diamond_core.suppress_vibration"); // defaults to false
    }

    @Inject(method = "tryToMerge", at = @At("HEAD"), cancellable = true)
    private void tryToMerge(ItemEntity otherItemEntity, CallbackInfo ci) {
        if (otherItemEntity instanceof VibrationSuppressible suppressible && suppressible.areVibrationsSuppressed()) {
            ci.cancel();
        }
    }
}
