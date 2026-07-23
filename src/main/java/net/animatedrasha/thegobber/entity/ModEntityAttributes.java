package net.animatedrasha.thegobber.entity;

import net.animatedrasha.thegobber.TheGobber;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheGobber.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntityAttributes {

    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {

        event.put(
                ModEntities.GOBBER.get(),
                net.animatedrasha.thegobber.entity.custom.GobberEntity
                        .createAttributes()
                        .build()
        );

    }

}