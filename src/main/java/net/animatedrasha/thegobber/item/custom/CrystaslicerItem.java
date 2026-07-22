package net.animatedrasha.thegobber.item.custom;

import net.animatedrasha.thegobber.item.ModToolTiers;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SwordItem;

public class CrystaslicerItem extends SwordItem {

    public CrystaslicerItem(Item.Properties properties) {
        super(ModToolTiers.CRYSTAL,
                2,
                -2.1F,
                properties);
    }

}