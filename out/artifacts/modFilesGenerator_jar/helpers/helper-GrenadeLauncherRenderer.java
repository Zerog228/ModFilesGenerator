package com.zink.item.grenade_launcher;

import software.bernie.geckolib.renderer.GeoItemRenderer;

import com.zink.item.grenade_launcher.GrenadeLauncher;
public class GrenadeLauncherRenderer extends GeoItemRenderer<GrenadeLauncher> {
    public GrenadeLauncherRenderer() {
        super(new GrenadeLauncherModel());
    }
}
