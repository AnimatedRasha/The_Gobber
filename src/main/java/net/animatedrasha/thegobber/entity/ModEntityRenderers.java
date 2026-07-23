package net.animatedrasha.thegobber.entity;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.entity.client.GobberRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(
        modid = TheGobber.MOD_ID,
        bus = Mod.EventBusSubscriber.Bus.MOD,
        value = Dist.CLIENT
)
public class ModEntityRenderers {

    @SubscribeEvent
    public static void register(EntityRenderersEvent.RegisterRenderers event) {

        event.registerEntityRenderer(
                ModEntities.GOBBER.get(),
                GobberRenderer::new
        );
    }

}