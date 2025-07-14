package com.zink.entity.robot_spider;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.util.Identifier;
import com.zink.entity.robot_spider.RobotSpider;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class RobotSpiderRenderer extends GeoEntityRenderer<RobotSpider> {
    public RobotSpiderRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new RobotSpiderModel());

    }

    @Override
    public Identifier getTextureResource(RobotSpider instance) {
        return new Identifier("tsm", "textures/entity/robot_spider.png");
    }
}
