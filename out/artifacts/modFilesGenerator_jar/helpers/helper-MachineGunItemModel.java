package com.zink.item;

import net.minecraft.util.Identifier;
import software.bernie.geckolib.model.GeoModel;
import com.zink.item.MachineGunItem;

public class MachineGunItemModel extends GeoModel<MachineGunItem> {
    @Override
    public Identifier getModelResource(MachineGunItem animatable) {
        return new Identifier("tsm", "geo/machine_gun_item.geo.json");
    }

    @Override
    public Identifier getTextureResource(MachineGunItem animatable) {
        return new Identifier("tsm", "textures/items/machine_gun_item.png");
    }

    @Override
    public Identifier getAnimationResource(MachineGunItem animatable) {
        return new Identifier("tsm", "animations/machine.gun.animation.json");
    }
}
