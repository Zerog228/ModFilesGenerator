package com.zink.item;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import com.zink.item.MachineGunItem;
public class MachineGunItemRenderer extends GeoItemRenderer<MachineGunItem> {
    public MachineGunItemRenderer() {
        super(new MachineGunItemModel());
    }
}
