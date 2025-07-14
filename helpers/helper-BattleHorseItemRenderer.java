package com.zink.item.BattleHorse;

import software.bernie.geckolib3.renderers.geo.GeoItemRenderer;

import com.zink.item.BattleHorseItem;
public class BattleHorseItemRenderer extends GeoItemRenderer<BattleHorseItem> {
    public BattleHorseItemRenderer() {
        super(new BattleHorseItemModel());
    }
}
