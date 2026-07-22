package net.animatedrasha.thegobber.event;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.effect.ModEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = TheGobber.MOD_ID)
public class SplinteredEvents {

    private static final Map<UUID, Double> LAST_X = new HashMap<>();
    private static final Map<UUID, Double> LAST_Y = new HashMap<>();
    private static final Map<UUID, Double> LAST_Z = new HashMap<>();

    @SubscribeEvent
    public static void livingTick(LivingEvent.LivingTickEvent event) {

        LivingEntity entity = event.getEntity();
        UUID id = entity.getUUID();

        // Store initial position
        if (!LAST_X.containsKey(id)) {
            LAST_X.put(id, entity.getX());
            LAST_Y.put(id, entity.getY());
            LAST_Z.put(id, entity.getZ());
            return;
        }

        double dx = entity.getX() - LAST_X.get(id);
        double dy = entity.getY() - LAST_Y.get(id);
        double dz = entity.getZ() - LAST_Z.get(id);

        LAST_X.put(id, entity.getX());
        LAST_Y.put(id, entity.getY());
        LAST_Z.put(id, entity.getZ());

        if (!entity.hasEffect(ModEffects.SPLINTERED.get()))
            return;

        double distance = Math.sqrt(dx * dx + dy * dy + dz * dz);

        // Completely stationary
        if (distance <= 0.000001D)
            return;

        // Sprinting is about 0.28 blocks/tick
        float damage = (float)(distance * 5.35D);

        entity.hurt(entity.damageSources().magic(), damage);
    }
}