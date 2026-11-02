package com.sdarthur.aatm.compat;

import com.sdarthur.aatm.AnotherAppleTreeMod;
import com.sdarthur.aatm.block.AATMBlocks;
import com.sdarthur.aatm.block.custom.AppleLeafBlock;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.IServerDataProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.theme.IThemeHelper;
import snownee.jade.api.ui.IElement;
import snownee.jade.api.ui.IElementHelper;

public enum AgeComponentProvider implements IBlockComponentProvider, IServerDataProvider<BlockAccessor> {
    INSTANCE;

    public static final ResourceLocation UID = ResourceLocation.fromNamespaceAndPath(AnotherAppleTreeMod.MODID, "aatm");

    @Override
    public @Nullable IElement getIcon(BlockAccessor accessor, IPluginConfig config, IElement currentIcon) {
        ItemStack stack = ItemStack.EMPTY;

        switch(accessor.getBlockState().getValue(AppleLeafBlock.AGE)) {
            case 0:
                stack = new ItemStack(AATMBlocks.APPLE_LEAVES.asItem());
                break;
            case 1:
                stack = new ItemStack(AATMBlocks.APPLE_FLOWER_LEAVES.asItem());
                break;
            case 2:
                stack = new ItemStack(AATMBlocks.UNRIPE_APPLE_LEAVES.asItem());
                break;
            case 3:
                stack = new ItemStack(AATMBlocks.RIPE_APPLE_LEAVES.asItem());
                break;
        }

        return stack == ItemStack.EMPTY ? currentIcon : IElementHelper.get().item(stack);
    }

    @Override
    public void appendTooltip(ITooltip iTooltip, BlockAccessor blockAccessor, IPluginConfig iPluginConfig) {
        BlockState state = blockAccessor.getBlockState();
        Block block = state.getBlock();

        if(blockAccessor.getServerData().contains("Age") && block instanceof AppleLeafBlock appleLeafBlock) {
            addMaturityTooltip(iTooltip, appleLeafBlock.getAge(state) / (float) appleLeafBlock.getMaxAge());
        }
    }

    private static void addMaturityTooltip(ITooltip tooltip, float growthValue) {
        MutableComponent component;

        if(growthValue < 1) {
            component = IThemeHelper.get().info(String.format("%.0f%%", growthValue * 100));
        }
        else {
            component= IThemeHelper.get().success(Component.translatable("tooltip.jade.crop_mature"));
        }
        tooltip.add(Component.translatable("tooltip.jade.crop_growth", component));
    }

    @Override
    public void appendServerData(CompoundTag compoundTag, BlockAccessor blockAccessor) {

        BlockState state = blockAccessor.getBlockState();
        Block block = state.getBlock();


        if(block instanceof AppleLeafBlock appleLeafBlock) {
            compoundTag.putInt("Age", appleLeafBlock.getAge(state));
        }
    }

    @Override
    public ResourceLocation getUid() {
        return UID;
    }
}
