package com.zink.item;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import com.zink.item.�������������� ������;

public class �������������� ������Model extends GeoModel<�������������� ������> {
    @Override
    public Identifier getModelResource(�������������� ������ animatable) {
        return new Identifier("tsm", "geo/machine_gun_item.geo.json");
    }

    @Override
    public Identifier getTextureResource(�������������� ������ animatable) {
        return new Identifier("tsm", "textures/items/�������������� ������.png");
    }

    @Override
    public Identifier getAnimationResource(�������������� ������ animatable) {
        return new Identifier("tsm", "animations/machine.gun.animation.json");
    }
}
