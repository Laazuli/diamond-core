package de.laazuli.diamond_core.data_component;

import de.laazuli.diamond_core.DiamondCore;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;

import java.util.function.UnaryOperator;

public class ModDataComponents {

    public static final DataComponentType<Unit> SILENT = register("silent", builder -> builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));

    private static <T> DataComponentType<T> register(String id, UnaryOperator<DataComponentType.Builder<T>> unaryOperator) {
        return Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ResourceLocation.fromNamespaceAndPath(DiamondCore.MOD_ID, id), unaryOperator.apply(DataComponentType.builder()).build());
    }
}
