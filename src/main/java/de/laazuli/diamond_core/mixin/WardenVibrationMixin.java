package de.laazuli.diamond_core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.laazuli.diamond_core.data_component.ModDataComponents;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.entity.monster.warden.Warden$VibrationUser")
public class WardenVibrationMixin {
    @ModifyReturnValue(method = "canReceiveVibration", at = @At("RETURN"))
    private boolean triggerUnlessSilentTool(boolean original, @Local(argsOnly = true) Holder<GameEvent> gameEvent, @Local(argsOnly = true) GameEvent.Context context) {
        System.out.println("Game event: " + gameEvent + ", Affected state: " + context.affectedState());
        if (!(gameEvent.is(GameEvent.BLOCK_DESTROY.key()) || gameEvent.is(GameEvent.BLOCK_CHANGE.key()))) {
            return original;
        }
        if (context.sourceEntity() instanceof LivingEntity livingEntity) {
             return original && !livingEntity.getMainHandItem().has(ModDataComponents.SILENT);
        }
        return original;
    }
}
