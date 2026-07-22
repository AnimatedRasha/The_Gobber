package net.animatedrasha.thegobber.item.custom;

import net.animatedrasha.thegobber.effect.ModEffects;
import net.animatedrasha.thegobber.item.ModToolTiers;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.server.level.ServerLevel;

public class CrystaslicerItem extends SwordItem {

    public CrystaslicerItem(Item.Properties properties) {
        super(ModToolTiers.CRYSTAL,
                2,
                -2.1F,
                properties);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {

        // Only run on the server, 20% chance
        if (!target.level().isClientSide && target.getRandom().nextFloat() < 0.20F) {

            MobEffectInstance current = target.getEffect(ModEffects.SPLINTERED.get());

            int duration = 40;

            if (current != null) {
                duration = Math.min(current.getDuration() + 40, 100);
            }

            target.addEffect(new MobEffectInstance(
                    ModEffects.SPLINTERED.get(),
                    duration,
                    0
            ));

            // Loud crystal break
            target.level().playSound(
                    null,
                    target.blockPosition(),
                    SoundEvents.AMETHYST_CLUSTER_BREAK,
                    SoundSource.PLAYERS,
                    2.0F,   // Volume (1.0 = normal)
                    1.0F    // Pitch
            );

// Glass shatter layered on top
            target.level().playSound(
                    null,
                    target.blockPosition(),
                    SoundEvents.GLASS_BREAK,
                    SoundSource.PLAYERS,
                    1.2F,
                    1.2F
            );

            // Amethyst particles
            if (target.level() instanceof ServerLevel serverLevel) {

                serverLevel.sendParticles(
                        new BlockParticleOption(
                                ParticleTypes.BLOCK,
                                Blocks.AMETHYST_BLOCK.defaultBlockState()
                        ),
                        target.getX(),
                        target.getY() + target.getBbHeight() * 0.5,
                        target.getZ(),
                        50,         // particle count
                        0.35,
                        0.45,
                        0.35,
                        0.12
                );
            }
        }

        return super.hurtEnemy(stack, target, attacker);
    }
}