package com.zink.item.BattleHorse;

import com.zink.item.custom.FumoBlockItem;
import net.minecraft.util.Identifier;
import com.zink.item.BattleHorseItem;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class BattleHorseItemModel extends AnimatedGeoModel<BattleHorseItem> {
    @Override
    public Identifier getModelResource(BattleHorseItem object) {
        return new Identifier(TestMod.MOD_ID, "geo/battle_horse.geo.json");
    }

    @Override
    public Identifier getTextureResource(BattleHorseItem object) {
        return FumoLogic.getFumoId("textures/battle_horse.png");
    }

    @Override
    public Identifier getAnimationResource(BattleHorseItem animatable) {
        return new Identifier(TestMod.MOD_ID, "animations/battle_horse.animations.json");
    }
}
