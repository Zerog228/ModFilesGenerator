package com.zink.entity.forsaken_zombie;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import com.zink.entity.forsaken_zombie.ForsakenZombie;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ForsakenZombieRenderer extends GeoEntityRenderer<ForsakenZombie> {
    public ForsakenZombieRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new ForsakenZombieModel());

    }

    @Override
    public Identifier getTextureResource(ForsakenZombie instance) {
        return new Identifier("tsm", "textures/entity/forsaken_zombie.png");
    }
}
