package com.zink.item.BattleHorseStatue;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import com.zink.item.BattleHorseStatueItem;
public class BattleHorseStatueItemRenderer extends GeoItemRenderer<BattleHorseStatueItem> {
    public BattleHorseStatueItemRenderer() {
        super(new BattleHorseStatueItemModel());
    }
}
