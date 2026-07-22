package net.animatedrasha.thegobber.effect;

import net.animatedrasha.thegobber.TheGobber;
import net.animatedrasha.thegobber.effect.custom.SplinteredEffect;
import net.minecraft.world.effect.MobEffect;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModEffects {

    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(ForgeRegistries.MOB_EFFECTS, TheGobber.MOD_ID);

    public static final RegistryObject<MobEffect> SPLINTERED =
            MOB_EFFECTS.register("splintered", SplinteredEffect::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}