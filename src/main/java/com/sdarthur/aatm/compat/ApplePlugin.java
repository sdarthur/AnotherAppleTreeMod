package com.sdarthur.aatm.compat;

import com.sdarthur.aatm.AnotherAppleTreeMod;
import com.sdarthur.aatm.block.custom.AppleLeafBlock;
import net.minecraft.resources.ResourceLocation;
import snownee.jade.api.IWailaClientRegistration;
import snownee.jade.api.IWailaCommonRegistration;
import snownee.jade.api.IWailaPlugin;
import snownee.jade.api.WailaPlugin;

@WailaPlugin
public class ApplePlugin implements IWailaPlugin {

    public static ResourceLocation AGE = ResourceLocation.fromNamespaceAndPath(AnotherAppleTreeMod.MODID, "age_resource");

    @Override
    public void register(IWailaCommonRegistration registration) {
        registration.registerBlockDataProvider(AgeComponentProvider.INSTANCE, AppleLeafBlock.class);
    }

    @Override
    public void registerClient(IWailaClientRegistration registration) {
        registration.registerBlockIcon(AgeComponentProvider.INSTANCE, AppleLeafBlock.class);
        registration.registerBlockComponent(AgeComponentProvider.INSTANCE, AppleLeafBlock.class);
    }
}
