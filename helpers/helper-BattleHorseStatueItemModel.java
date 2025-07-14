package com.zink.item.BattleHorseStatue;

import com.zink.item.custom.FumoBlockItem;
import net.minecraft.util.Identifier;
import com.zink.item.BattleHorseStatueItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BattleHorseStatueItemModel extends AnimatedGeoModel<BattleHorseStatueItem> {
    @Override
    public Identifier getModelResource(BattleHorseStatueItem object) {
        return new Identifier(TestMod.MOD_ID, "geo/battle_horse.geo.json");
    }

    @Override
    public Identifier getTextureResource(BattleHorseStatueItem object) {
        return FumoLogic.getFumoId("textures/battle_horse_statue.png");
    }

    @Override
    public Identifier getAnimationResource(BattleHorseStatueItem animatable) {
        return new Identifier(TestMod.MOD_ID, "animations/battle_horse.animations.json");
    }
}
