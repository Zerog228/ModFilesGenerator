package com.zink.entity.battle_horse.BattleHorse;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import com.zink.entity.battle_horse.BattleHorse.BattleHorseEntity;

public class BattleHorseModel extends AnimatedGeoModel<BattleHorseEntity> {
    @Override
    public Identifier getModelResource(BattleHorseEntity object) {
        return new Identifier(mod_id, "geo/battle_horse.geo.json");
    }

    @Override
    public Identifier getTextureResource(BattleHorseEntity object) {
        return new Identifier(mod_id, "textures/entity/battle_horse/battle_horse.png");
    }

    @Override
    public Identifier getAnimationResource(BattleHorseEntity animatable) {
        return new Identifier(mod_id, "animations/battle_horse.animations.json");
    }
}
