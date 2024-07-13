package de.laazuli.diamond_core.mixin;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.Holder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

    public LivingEntityMixin(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

//    @Override
//    public boolean isSteppingCarefully() {
//        return super.isSteppingCarefully() && DiamondCore.isWearingFullSetOfSilenceArmor((LivingEntity) (Object) this);
//    }

//    @Inject(method = "onEquipItem", at = @At("HEAD"))
//    private void print(EquipmentSlot equipmentSlot, ItemStack takenStack, ItemStack putStack, CallbackInfo ci) {
//        if (!level().isClientSide) {
//            System.out.println("LivingEntity#onEquipItem, equipment slot: " + equipmentSlot + ", takenStack: " + takenStack + ", putStack: " + putStack);
//            System.out.println("this" + this);
//        }
//    }

    @Unique
    private void diamond_core$gameEvent(Holder<GameEvent> gameEventHolder, ItemStack affectedItemStack) {
        GameEvent.Context gameEventContext = new GameEvent.Context(this, null);
        gameEventContext.putAffectedItemStack(affectedItemStack);
//        System.out.println("diamond_core$gameEvent: Affected stack: " + gameEventContext.affectedItemStack());
        this.level().gameEvent(gameEventHolder, this.position(), gameEventContext);
    }

    @Redirect(method = "onEquipItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;gameEvent(Lnet/minecraft/core/Holder;)V"))
    private void provideExtraInformation(LivingEntity instance, Holder<GameEvent> gameEvent, @Local(argsOnly = true, ordinal = 0) ItemStack takenStack, @Local(argsOnly = true, ordinal = 1) ItemStack putStack) {
//        System.out.println("provideExtraInformation: gameEvent: " + gameEvent + ", takenStack: " + takenStack + ", putStack: " + putStack);
        diamond_core$gameEvent(gameEvent, gameEvent.is(GameEvent.UNEQUIP.key()) ? takenStack : putStack);
    }

//    public void postEquipItem(EquipmentSlot appliedSlot, ItemStack takenStack, ItemStack putStack) {
//        boolean putAndTakenStackEmpty = putStack.isEmpty() && takenStack.isEmpty();
//        if (!putAndTakenStackEmpty && !ItemStack.isSameItemSameComponents(takenStack, putStack) && !this.firstTick) {
//            Equipable putEquipment = Equipable.get(putStack);
//            if (!this.level().isClientSide() && !this.isSpectator()) {
//                if (!this.isSilent() && putEquipment != null && putEquipment.getEquipmentSlot() == appliedSlot) {
//                    this.level()
//                            .playSeededSound(null, this.getX(), this.getY(), this.getZ(), putEquipment.getEquipSound(), this.getSoundSource(), 1.0F, 1.0F, this.random.nextLong());
//                }
//
//                if (this.doesEmitEquipEvent(appliedSlot)) {
//                    this.gameEvent(putEquipment != null ? GameEvent.EQUIP : GameEvent.UNEQUIP);
//                }
//            }
//        }
//    }
}
