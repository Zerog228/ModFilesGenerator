package com.zink.entity.battle_horse.BattleHorse;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import com.zink.entity.battle_horse.BattleHorse.BattleHorseEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.renderers.geo.GeoBlockRenderer;

public class BattleHorseRenderer extends GeoBlockRenderer<BattleHorseEntity> {
    public BattleHorseRenderer(BlockEntityRendererFactory.Context context) {
        super(new BattleHorseModel());
    }

    @Override
    public RenderLayer getRenderType(BattleHorseEntity animatable, float partialTicks, MatrixStack stack, VertexConsumerProvider renderTypeBuffer, VertexConsumer vertexBuilder, int packedLightIn, Identifier textureLocation) {
        return RenderLayer.getEntityTranslucent(getTextureResource(animatable));
    }
}
