package de.laazuli.diamond_core.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import de.laazuli.diamond_core.data_component.ModDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

import java.util.function.Consumer;
import java.util.function.Supplier;

@Mixin(Block.class)
public abstract class BlockMixin {
    @Shadow
    private static void popResource(Level level, Supplier<ItemEntity> supplier, ItemStack itemStack) {}

    @ModifyArg(method = "dropResources(Lnet/minecraft/world/level/block/state/BlockState;Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/world/level/block/entity/BlockEntity;Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/item/ItemStack;)V", at = @At(value = "INVOKE", target = "Ljava/util/List;forEach(Ljava/util/function/Consumer;)V"), index = 0)
    private static Consumer<ItemStack> conditionallyTagItemEntityAsSilent(Consumer<ItemStack> original, @Local(argsOnly = true) Level level, @Local(argsOnly = true) BlockPos blockPos, @Local(argsOnly = true) ItemStack usedTool) {
//        System.out.println("dropResourcesMixin, usedTool: " + usedTool);
        if (!usedTool.isEmpty() && usedTool.has(ModDataComponents.SILENT)) {
            return itemStack -> diamond_core$popResourceSilently(level, blockPos, itemStack);
        }
        return original;
    }

    @Unique
    private static void diamond_core$popResourceSilently(Level level, BlockPos blockPos, ItemStack itemStack) {
        double d = (double) EntityType.ITEM.getHeight() / 2.0;
        double e = (double)blockPos.getX() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        double f = (double)blockPos.getY() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25) - d;
        double g = (double)blockPos.getZ() + 0.5 + Mth.nextDouble(level.random, -0.25, 0.25);
        popResource(level, () -> {
            ItemEntity itemEntity = new ItemEntity(level, e, f, g, itemStack);
            itemEntity.getEntityData().

        }, itemStack);
    }
}
