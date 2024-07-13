package de.laazuli.diamond_core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.laazuli.diamond_core.DiamondCore;
import net.minecraft.core.Holder;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.gameevent.vibrations.VibrationSystem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(VibrationSystem.User.class)
public interface VibrationSystem$UserMixin {
    @ModifyReturnValue(method = "isValidVibration", at = @At("RETURN"))
    private boolean suppressSilentItemVibrations(boolean original, @Local(argsOnly = true) Holder<GameEvent> gameEvent, @Local(argsOnly = true) GameEvent.Context context) {
//        System.out.println("Game event: " + gameEvent + ", Source Entity: " + context.sourceEntity() + ", Affected state: " + context.affectedState());
        return original && !DiamondCore.suppressesVibration(gameEvent, context);
    }
}
