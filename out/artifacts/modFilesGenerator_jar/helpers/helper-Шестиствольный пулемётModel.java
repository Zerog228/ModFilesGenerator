package com.zink.item;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import com.zink.item.Шестиствольный пулемёт;

public class Шестиствольный пулемётModel extends GeoModel<Шестиствольный пулемёт> {
    @Override
    public Identifier getModelResource(Шестиствольный пулемёт animatable) {
        return new Identifier("tsm", "geo/machine_gun_item.geo.json");
    }

    @Override
    public Identifier getTextureResource(Шестиствольный пулемёт animatable) {
        return new Identifier("tsm", "textures/items/Шестиствольный пулемёт.png");
    }

    @Override
    public Identifier getAnimationResource(Шестиствольный пулемёт animatable) {
        return new Identifier("tsm", "animations/machine.gun.animation.json");
    }
}
