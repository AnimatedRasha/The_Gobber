package net.animatedrasha.thegobber.entity.custom;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;

public class GobberEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache = new SingletonAnimatableInstanceCache(this);

    private static final RawAnimation IDLE =
            RawAnimation.begin().thenLoop("idle");

    private static final RawAnimation WALK =
            RawAnimation.begin().thenLoop("walk");

    private static final RawAnimation ATTACK =
            RawAnimation.begin().thenPlay("attack");

    public GobberEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(2,
                new MeleeAttackGoal(this, 1.1D, false));

        this.goalSelector.addGoal(5,
                new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(6,
                new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.goalSelector.addGoal(7,
                new RandomLookAroundGoal(this));

        this.targetSelector.addGoal(1,
                new HurtByTargetGoal(this));

        this.targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<>(this, Player.class, true));
    }

    public static AttributeSupplier.Builder createAttributes() {

        return Monster.createMonsterAttributes()

                .add(Attributes.MAX_HEALTH, 80D)

                .add(Attributes.ATTACK_DAMAGE, 8D)

                .add(Attributes.ARMOR, 6D)

                .add(Attributes.MOVEMENT_SPEED, 0.27D)

                .add(Attributes.FOLLOW_RANGE, 32D)

                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

        controllers.add(

                new AnimationController<>(this, "controller", 0, state -> {

                    if (this.swinging) {
                        state.setAnimation(ATTACK);
                        return PlayState.CONTINUE;
                    }

                    if (state.isMoving()) {
                        state.setAnimation(WALK);
                    } else {
                        state.setAnimation(IDLE);
                    }

                    return PlayState.CONTINUE;
                })

        );

    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}