package net.animatedrasha.thegobber.entity.client;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.entity.custom.GobberEntity;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.cache.object.GeoBone; // or CoreGeoBone depending on your GeckoLib version
import software.bernie.geckolib.constant.DataTickets;
import software.bernie.geckolib.model.data.EntityModelData;

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

    @Override
    public void setCustomAnimations(GobberEntity animatable, long instanceId, AnimationState<GobberEntity> animationState) {
        super.setCustomAnimations(animatable, instanceId, animationState);

        GeoBone head = (GeoBone) getAnimationProcessor().getBone("head");

        if (head != null) {
            EntityModelData data = animationState.getData(DataTickets.ENTITY_MODEL_DATA);

            head.setRotX(data.headPitch() * ((float)Math.PI / 180F));
            head.setRotY(data.netHeadYaw() * ((float)Math.PI / 180F));
        }
    }
}