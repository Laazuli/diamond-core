package de.laazuli.diamond_core.mixin;

import de.laazuli.diamond_core.VibrationSuppressible;
import de.laazuli.diamond_core.data_component.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Block.class)
public abstract class BlockMixin {

    @Unique
    private static boolean diamond_core$SUPPRESS_DROP_VIBRATIONS = false;

    @Inject(method = "dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"))
    private static void setSilencedDrops(BlockState blockState, Level level, BlockPos blockPos, BlockEntity blockEntity, Entity entity, ItemStack usedItemStack, CallbackInfo ci) {
        if (!usedItemStack.isEmpty() && usedItemStack.has(ModDataComponents.SILENT)) { // if the item the block has been mined with, is silent, mark every dropped item to suppress emitted vibrations
            diamond_core$SUPPRESS_DROP_VIBRATIONS = true;
//            System.out.println("dropResources before forEach, suppress_drop_vibrations set to: " + diamond_core$SUPPRESS_DROP_VIBRATIONS);
        }
    }

    @Inject(method = "dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V", shift = At.Shift.AFTER))
    private static void resetSilencedDrops(BlockState blockState, Level level, BlockPos blockPos, BlockEntity blockEntity, Entity entity, ItemStack itemStack, CallbackInfo ci) {
        if (diamond_core$SUPPRESS_DROP_VIBRATIONS) {
            diamond_core$SUPPRESS_DROP_VIBRATIONS = false;
//            System.out.println("dropResources after forEach, suppress_drop_vibrations set to: " + diamond_core$SUPPRESS_DROP_VIBRATIONS);
        }
    }

    @ModifyArg(method = "popResource(Lnet/minecraft/world/level/Level;Ljava/util/function/Supplier;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity(Lnet/minecraft/world/entity/Entity;)Z"), index = 0)
    private static Entity suppressItemEntityIfNecessary(Entity original) {
//        System.out.println("popResource, suppressItemEntityIfNecessary; Entity: " + original);
        if (original instanceof VibrationSuppressible && diamond_core$SUPPRESS_DROP_VIBRATIONS) {
            ((VibrationSuppressible) original).setSuppressVibrations(true);
//            System.out.println("Set suppress vibrations to true, get: " + ((VibrationSuppressible) original).areVibrationsSuppressed());
        }
        return original;
    }
}
