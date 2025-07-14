package com.zink.item.grenade_launcher;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import com.zink.item.grenade_launcher.GrenadeLauncher;

public class GrenadeLauncherModel extends GeoModel<GrenadeLauncher> {
    @Override
    public Identifier getModelResource(GrenadeLauncher animatable) {
        return new Identifier("tsm", "geo/grenade_launcher.geo.json");
    }

    @Override
    public Identifier getTextureResource(GrenadeLauncher animatable) {
        return new Identifier("tsm", "textures/items/grenade_launcher.png");
    }

    @Override
    public Identifier getAnimationResource(GrenadeLauncher animatable) {
        return new Identifier("tsm", "animations/grenade_launcher.animation.json");
    }
}
