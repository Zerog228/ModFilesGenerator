package com.zink.entity.battle_horse_statue;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import com.zink.entity.battle_horse_statue.BattleHorseStatueEntity;

public class BattleHorseStatueModel extends AnimatedGeoModel<BattleHorseStatueEntity> {
    @Override
    public Identifier getModelResource(BattleHorseStatueEntity object) {
        return new Identifier(tsm, "geo/battle_horse.geo.json");
    }

    @Override
    public Identifier getTextureResource(BattleHorseStatueEntity object) {
        return new Identifier(tsm, "textures/entity/battle_horse_statue/battle_horse_statue.png");
    }

    @Override
    public Identifier getAnimationResource(BattleHorseStatueEntity animatable) {
        return new Identifier(tsm, "animations/battle_horse.animations.json");
    }
}
