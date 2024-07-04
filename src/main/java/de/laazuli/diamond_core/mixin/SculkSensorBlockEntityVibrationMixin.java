package de.laazuli.diamond_core.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import de.laazuli.diamond_core.data_component.ModDataComponents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.world.level.block.entity.SculkSensorBlockEntity$VibrationUser")
public class SculkSensorBlockEntityVibrationMixin {

    @ModifyReturnValue(method = "canReceiveVibration", at = @At("RETURN"))
    private boolean activateUnlessSilentTool(boolean original, @Local(argsOnly = true) GameEvent.Context context) {
        if (context.sourceEntity() instanceof LivingEntity livingEntity) {
//            boolean itemIsSilent = livingEntity.getMainHandItem().has(ModDataComponents.SILENT);
//            System.out.println("Living entity has silent item? " + itemIsSilent);
//            return original && !itemIsSilent;
            return original && !livingEntity.getMainHandItem().has(ModDataComponents.SILENT);
        }
        return original;
    }
}
