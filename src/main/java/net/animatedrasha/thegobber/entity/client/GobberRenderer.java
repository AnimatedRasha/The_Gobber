package net.animatedrasha.thegobber.entity.client;

import net.animatedrasha.thegobber.entity.custom.GobberEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import software.bernie.geckolib.renderer.GeoEntityRenderer;

public class GobberRenderer extends GeoEntityRenderer<GobberEntity> {

    public GobberRenderer(EntityRendererProvider.Context context) {
        super(context, new GobberModel());

        this.shadowRadius = 0.45F;
    }
}