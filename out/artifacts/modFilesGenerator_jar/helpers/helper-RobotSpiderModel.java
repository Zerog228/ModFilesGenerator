package com.zink.entity.robot_spider;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import com.zink.entity.robot_spider.RobotSpider;

public class RobotSpiderModel extends AnimatedGeoModel<RobotSpider> {
    @Override
    public Identifier getModelResource(RobotSpider object) {
        return new Identifier("tsm", "geo/robot_spider.geo.json");
    }

    @Override
    public Identifier getTextureResource(RobotSpider object) {
        return new Identifier("tsm", "textures/entity/robot_spider.png");
    }

    @Override
    public Identifier getAnimationResource(RobotSpider animatable) {
        return new Identifier("tsm", "animations/robot.spider.animation.json");
    }
}
