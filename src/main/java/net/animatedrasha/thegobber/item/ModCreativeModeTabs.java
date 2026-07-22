package net.animatedrasha.thegobber.item;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheGobber.MOD_ID);

    public static final RegistryObject<CreativeModeTab> THE_GOBBER_TAB = CREATIVE_MODE_TABS.register("the_gobber_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.CROOKED_FANG.get()))
                    .title(Component.translatable("creativetab.the_gobber_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.CRYSTASLICER.get());
                        pOutput.accept(ModItems.CROOKED_FANG.get());
                        pOutput.accept(ModItems.LAZULI_STEEL.get());
                        pOutput.accept(ModBlocks.LAZULI_STEEL_BLOCK.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
