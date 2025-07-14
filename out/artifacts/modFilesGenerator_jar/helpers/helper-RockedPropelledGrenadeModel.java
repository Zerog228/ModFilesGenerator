package com.zink.item.rocked_propelled_grenade;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import com.zink.item.rocked_propelled_grenade.RockedPropelledGrenade;

public class RockedPropelledGrenadeModel extends GeoModel<RockedPropelledGrenade> {
    @Override
    public Identifier getModelResource(RockedPropelledGrenade animatable) {
        return new Identifier("tsm", "geo/grenade_launcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(RockedPropelledGrenade animatable) {
        return new Identifier("tsm", "textures/items/rocked_propelled_grenade.png");
    }

    @Override
    public Identifier getAnimationResource(RockedPropelledGrenade animatable) {
        return new Identifier("tsm", "animations/grenade_launcher.animation.json");
    }
}
