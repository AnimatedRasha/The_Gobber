package net.animatedrasha.thegobber.item;

import net.animatedrasha.thegobber.TheGobber;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.animatedrasha.thegobber.item.custom.CrystaslicerItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.animatedrasha.thegobber.entity.ModEntities;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheGobber.MOD_ID);

    public static final RegistryObject<Item> GOBBER_SPAWN_EGG =
            ITEMS.register("gobber_spawn_egg",
                    () -> new ForgeSpawnEggItem(
                            ModEntities.GOBBER,
                            0x3a3d2d,   // primary color
                            0xbd7aff,   // spots
                            new Item.Properties()));
    public static final RegistryObject<Item> LAZULI_STEEL = ITEMS.register("lazuli_steel",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CROOKED_FANG = ITEMS.register("crooked_fang",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CRYSTASLICER = ITEMS.register("crystaslicer",
            () -> new CrystaslicerItem(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
