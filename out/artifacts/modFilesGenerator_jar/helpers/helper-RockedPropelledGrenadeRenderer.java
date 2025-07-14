package com.zink.item.rocked_propelled_grenade;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import com.zink.item.rocked_propelled_grenade.RockedPropelledGrenade;
public class RockedPropelledGrenadeRenderer extends GeoItemRenderer<RockedPropelledGrenade> {
    public RockedPropelledGrenadeRenderer() {
        super(new RockedPropelledGrenadeModel());
    }
}
