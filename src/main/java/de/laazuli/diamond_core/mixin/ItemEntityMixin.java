package de.laazuli.diamond_core.mixin;

import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.item.ItemEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(ItemEntity.class)
public class ItemEntityMixin {
    @Unique
    private static final EntityDataAccessor<Boolean> diamond_core$SOUND_SUPPRESSED = SynchedEntityData.defineId(ItemEntity.class, EntityDataSerializers.BOOLEAN);
}
