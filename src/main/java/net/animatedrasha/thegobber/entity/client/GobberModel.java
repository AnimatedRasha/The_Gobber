package net.animatedrasha.thegobber.entity.client;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.entity.custom.GobberEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.model.GeoModel;

public class GobberModel extends GeoModel<GobberEntity> {

    @Override
    public ResourceLocation getModelResource(GobberEntity animatable) {
        return new ResourceLocation(TheGobber.MOD_ID, "geo/gobber.geo.json");
    }

    @Override
    public ResourceLocation getTextureResource(GobberEntity animatable) {
        return new ResourceLocation(TheGobber.MOD_ID, "textures/entity/gobber.png");
    }

    @Override
    public ResourceLocation getAnimationResource(GobberEntity animatable) {
        return new ResourceLocation(TheGobber.MOD_ID, "animations/gobber.animation.json");
    }
}