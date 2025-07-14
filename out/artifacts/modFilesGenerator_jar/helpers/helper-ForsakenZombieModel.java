package com.zink.entity.forsaken_zombie;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import com.zink.entity.forsaken_zombie.ForsakenZombie;

public class ForsakenZombieModel extends AnimatedGeoModel<ForsakenZombie> {
    @Override
    public Identifier getModelResource(ForsakenZombie object) {
        return new Identifier("tsm", "geo/ForsakenZombie.geo.json");
    }

    @Override
    public Identifier getTextureResource(ForsakenZombie object) {
        return new Identifier("tsm", "textures/entity/forsaken_zombie.png");
    }

    @Override
    public Identifier getAnimationResource(ForsakenZombie animatable) {
        return new Identifier("tsm", "animations/forsaken.zombie.animation.json");
    }
}
