package net.animatedrasha.thegobber.entity;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.entity.custom.GobberEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheGobber.MOD_ID);

    public static final RegistryObject<EntityType<GobberEntity>> GOBBER =
            ENTITY_TYPES.register("gobber",
                    () -> EntityType.Builder.of(GobberEntity::new, MobCategory.MONSTER)
                            .sized(2.5F, 2F)
                            .build("gobber"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}