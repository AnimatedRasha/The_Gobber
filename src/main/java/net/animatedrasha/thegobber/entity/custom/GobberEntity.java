package net.animatedrasha.thegobber.entity.custom;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.warden.Warden;

import software.bernie.geckolib.animatable.GeoEntity;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.core.animatable.instance.SingletonAnimatableInstanceCache;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.util.GeckoLibUtil;


public class GobberEntity extends Monster implements GeoEntity {

    private final AnimatableInstanceCache cache =
            GeckoLibUtil.createInstanceCache(this);

    private static final RawAnimation IDLE =
            RawAnimation.begin().thenLoop("animation.gobber.idle");

    private static final RawAnimation WALK =
            RawAnimation.begin().thenLoop("animation.gobber.walk");

    private static final RawAnimation ATTACK =
            RawAnimation.begin().thenPlay("animation.gobber.attack");

    public GobberEntity(EntityType<? extends Monster> entityType, Level level) {
        super(entityType, level);
        this.xpReward = 20;
    }

    public class GobberMeleeAttackGoal extends MeleeAttackGoal {

        public GobberMeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
            super(mob, speedModifier, followingTargetEvenIfNotSeen);
        }

        @Override
        protected double getAttackReachSqr(LivingEntity target) {
            return 9.0D; // 2-block reach (2 * 2)
        }
    }

    @Override
    protected void registerGoals() {

        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.targetSelector.addGoal(2,
                new HurtByTargetGoal(this));

        this.targetSelector.addGoal(1,
                new NearestAttackableTargetGoal<>(this, Warden.class, true));

        this.targetSelector.addGoal(2,
                new NearestAttackableTargetGoal<>(this, Player.class, true));

        this.goalSelector.addGoal(2,
                new GobberMeleeAttackGoal(this, 1.2D, false));

        this.goalSelector.addGoal(5,
                new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(6,
                new LookAtPlayerGoal(this, Player.class, 8.0F));

        this.goalSelector.addGoal(7,
                new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {

        return Monster.createMonsterAttributes()

                .add(Attributes.MAX_HEALTH, 450D)

                .add(Attributes.ATTACK_DAMAGE, 10D)

                .add(Attributes.ARMOR, 25D)

                .add(Attributes.MOVEMENT_SPEED, 0.19D)

                .add(Attributes.FOLLOW_RANGE, 25D)

                .add(Attributes.KNOCKBACK_RESISTANCE, 0.8D);
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (this.getTarget() instanceof Player) {
            Warden warden = this.level().getNearestEntity(
                    Warden.class,
                    TargetingConditions.forCombat(),
                    this,
                    this.getX(),
                    this.getY(),
                    this.getZ(),
                    this.getBoundingBox().inflate(32)
            );

            if (warden != null) {
                this.setTarget(warden);
            }
        }
    }

//    @Override
//    public void tick() {
//        super.tick();
//
//        if (this.getTarget() != null) {
//            this.getLookControl().setLookAt(
//                    this.getTarget(),
//                    30.0F, // max yaw change
//                    30.0F  // max pitch change
//            );
//        }
//    }

    @Override
    public void registerControllers(AnimatableManager.ControllerRegistrar controllers) {

        // Movement controller
        controllers.add(
                new AnimationController<>(this, "movement", 5, state -> {
                    if (state.isMoving()) {
                        state.setAnimation(WALK);
                    } else {
                        state.setAnimation(IDLE);
                    }

                    return PlayState.CONTINUE;
                })
        );

        // Attack controller
        controllers.add(
                new AnimationController<>(this, "attack", state -> PlayState.STOP)
                        .triggerableAnim("attack", ATTACK)
        );
    }

    @Override
    public boolean doHurtTarget(Entity target) {
        triggerAnim("attack", "attack");
        return super.doHurtTarget(target);
    }

    @Override
    public AnimatableInstanceCache getAnimatableInstanceCache() {
        return cache;
    }

}