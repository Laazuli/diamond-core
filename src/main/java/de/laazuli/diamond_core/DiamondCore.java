package de.laazuli.diamond_core;

import de.laazuli.diamond_core.block.ModBlocks;
import de.laazuli.diamond_core.data_component.ModDataComponents;
import de.laazuli.diamond_core.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.core.Holder;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DiamondCore implements ModInitializer {

    public static final String MOD_ID = "diamond_core";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        System.out.println("Hello from " + MOD_ID + " initialization");

        ModBlocks.initializeModBlocks();

        ModItems.initializeModItems();
    }

    public static boolean suppressesVibration(Holder<GameEvent> gameEventHolder, GameEvent.@NotNull Context gameEventContext) {
//        System.out.println("DiamondCore#suppressesVibration Game event: " + gameEventHolder + ", Source Entity: " + gameEventContext.sourceEntity() + ", Affected state: " + gameEventContext.affectedState());
        Entity sourceEntity = gameEventContext.sourceEntity();

        if (sourceEntity == null) {
            return false;
        }

        if (sourceEntity instanceof LivingEntity livingEntity) {
//            System.out.println("Source Entity instance of Living entity");
            return isMainHandItemSilent(gameEventHolder, livingEntity) || isArmorSilent(gameEventHolder, livingEntity) || isAffectedItemStackSilent(gameEventHolder, gameEventContext);
        }

        if (sourceEntity instanceof ItemEntity itemEntity) {
            boolean blockInteractionSilent = false;
            BlockState affectedState = gameEventContext.affectedState();
            if (affectedState != null) {
                blockInteractionSilent = isBlockInteractionSilent(gameEventHolder, affectedState, itemEntity);
            }
            return isItemEntitySilent(gameEventHolder, itemEntity) || blockInteractionSilent;
        }

        return false;
    }

    public static boolean isWearingFullSetOfSilenceArmor(@NotNull LivingEntity livingEntity) {
        int silentArmorCount = 0;
        for (ItemStack armorItemStack : livingEntity.getArmorSlots()) {
            if (armorItemStack.has(ModDataComponents.SILENT)) {
                silentArmorCount++;
            }
        }
        return silentArmorCount > 3;
    }

    private static boolean isMainHandItemSilent(@NotNull Holder<GameEvent> gameEvent, LivingEntity sourceEntity) {
        if (gameEvent.is(GameEvent.BLOCK_CHANGE.key()) || gameEvent.is(GameEvent.BLOCK_DESTROY.key())) {
            return sourceEntity.getMainHandItem().has(ModDataComponents.SILENT);
        }
        return false;
    }

    private static boolean isArmorSilent(@NotNull Holder<GameEvent> gameEvent, LivingEntity sourceEntity) {
        if (gameEvent.is(GameEvent.HIT_GROUND.key()) || gameEvent.is(GameEvent.SPLASH.key()) || gameEvent.is(GameEvent.STEP.key()) || gameEvent.is(GameEvent.SWIM.key())) {
            return isWearingFullSetOfSilenceArmor(sourceEntity);
        }
        return false;
    }

    private static boolean isAffectedItemStackSilent(@NotNull Holder<GameEvent> gameEvent, GameEvent.Context context) {
//        System.out.println("DiamondCore#isAffectedItemStackSilent");
        if (!(gameEvent.is(GameEvent.EQUIP.key()) || gameEvent.is(GameEvent.UNEQUIP.key()))) {
//            System.out.println("incorrect event");
            return false;
        }

        ItemStack affectedItemStack = context.affectedItemStack();
//        System.out.println("isAffectedItemStackSilent; affected item stack: " + affectedItemStack);
        if (affectedItemStack == null) {
            return false;
        }

        return affectedItemStack.has(ModDataComponents.SILENT);
    }

    private static boolean isItemEntitySilent(@NotNull Holder<GameEvent> gameEvent, ItemEntity itemEntity) {
        if (gameEvent.is(GameEvent.HIT_GROUND.key()) || gameEvent.is(GameEvent.SPLASH.key())) {
            boolean itemSilent = itemEntity.getItem().has(ModDataComponents.SILENT);
            boolean itemEntitySilent = itemEntity instanceof VibrationSuppressible suppressible && suppressible.areVibrationsSuppressed();
            return itemSilent || itemEntitySilent;
        }
        return false;
    }

    private static boolean isBlockInteractionSilent(@NotNull Holder<GameEvent> gameEvent, @NotNull BlockState affectedState, ItemEntity itemEntity) {
        if (gameEvent.is(GameEvent.BLOCK_ACTIVATE.key()) || gameEvent.is(GameEvent.BLOCK_DEACTIVATE.key())) {
            return affectedState.is(BlockTags.PRESSURE_PLATES) || affectedState.is(Blocks.TRIPWIRE_HOOK);
        }
        return false;
    }
}
