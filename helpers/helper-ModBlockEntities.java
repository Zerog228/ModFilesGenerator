package com.zink.entity;

import com.zink.block.custom.BattleHorseStatue.BattleHorseStatueEntity;
import com.zink.block.custom.ArtilleryGunEntity;
import com.zink.block.custom.OilRigEntity;
import com.zink.registry.ModBlocks;
import com.zink.tsm.TestMod;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class ModBlockEntities {
    public static BlockEntityType<OilRigEntity> OIL_RIG_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            new Identifier(TestMod.MOD_ID, "oil_rig_block"),
            FabricBlockEntityTypeBuilder.create(OilRigEntity::new,
                    ModBlocks.ANIMATED_OIL_RIG).build(null));

    public static BlockEntityType<ArtilleryGunEntity> ARTILLERY_GUN_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE,
            new Identifier(TestMod.MOD_ID, "artillery_gun_entity"),
            FabricBlockEntityTypeBuilder.create(ArtilleryGunEntity::new,
                    ModBlocks.ARTILLERY_GUN).build(null));

    public static BlockEntityType<MechanicalBenchBlockEntity> MECHANICAL_BENCH_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(TestMod.MOD_ID, "mechanical_bench"),
                    FabricBlockEntityTypeBuilder.create(MechanicalBenchBlockEntity::new,
                            ModBlocks.MECHANICAL_BENCH_BLOCK).build(null));

    public static BlockEntityType<LightningChannelerBlockEntity> LIGHTNING_CHANNELER_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(TestMod.MOD_ID, "lightning_channeler"),
                    FabricBlockEntityTypeBuilder.create(LightningChannelerBlockEntity::new,
                            ModBlocks.LIGHTNING_CHANNELER_BLOCK).build(null));

    public static BlockEntityType<FumoBlockEntity> FUMO_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(TestMod.MOD_ID, "fumo_block"),
                    FabricBlockEntityTypeBuilder.create(FumoBlockEntity::new,
                            ModBlocks.FUMO_BLOCK).build(null));

        public static BlockEntityType<BattleHorseStatueEntity> BATTLE_HORSE_STATUE_ENTITY =
                Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(tsm, "battle_horse_statue"),
                        FabricBlockEntityTypeBuilder.create(BattleHorseStatueEntity::new,
                                ModBlocks.BATTLE_HORSE_STATUE).build(null));
    //For replace by auto-generation

    public static void RegisterBlockEntities(){
        System.out.println("Registered BlockEntities!");
    }
}
