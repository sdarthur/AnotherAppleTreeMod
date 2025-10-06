package com.sdarthur.aatm;

import com.sdarthur.aatm.block.AATMBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class AATMCreativeTab {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AnotherAppleTreeMod.MODID);

    public static final Supplier<CreativeModeTab> AATM_CREATIVE_TAB = CREATIVE_MODE_TAB.register("aatm_creative_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(AATMBlocks.APPLE_TREE_SAPLING.get().asItem()))
                    .title(Component.translatable("creativetab.aatm.aatm_creative_tab"))
                    .displayItems((ItemDisplayParameters, output) -> {
                        output.accept(AATMBlocks.APPLE_TREE_SAPLING);
                        output.accept(AATMBlocks.APPLE_LEAVES);
                        output.accept(AATMBlocks.APPLE_FLOWER_LEAVES);
                        output.accept(AATMBlocks.UNRIPE_APPLE_LEAVES);
                        output.accept(AATMBlocks.RIPE_APPLE_LEAVES);
                        output.accept(AATMBlocks.APPLE_FRUIT_LEAVES);
    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}
