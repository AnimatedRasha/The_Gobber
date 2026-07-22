package net.animatedrasha.thegobber.item;

import net.animatedrasha.thegobber.TheGobber;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;

public class ModToolTiers {

    public static final Tier CRYSTAL = new ForgeTier(
            3,                      // Mining Level (Diamond)
            1561,                   // Durability
            8.0F,                   // Mining Speed
            3.5F,                   // Base Attack Damage
            12,                     // Enchantability
            BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(Tags.Items.GEMS_DIAMOND)
    );
}