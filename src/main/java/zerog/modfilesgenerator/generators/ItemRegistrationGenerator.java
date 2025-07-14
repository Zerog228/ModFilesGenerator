package zerog.modfilesgenerator.generators;

import javafx.scene.paint.Color;
import zerog.modfilesgenerator.annotations.WIP;
import zerog.modfilesgenerator.enums.ProgramVersion;
import zerog.modfilesgenerator.enums.WorkMode;
import zerog.modfilesgenerator.util.BINameUtils;

import static zerog.modfilesgenerator.Controller.sendToConsole;

@WIP(version = ProgramVersion.V1_19_3_GECKO_4, workmode =
        {

        }
)
public class ItemRegistrationGenerator {
    public static void registerItem(String bi_name, Color innerColor, Color outerColor, WorkMode mode, GeckoGenerator generator, ProgramVersion version){

        sendToConsole("Register item:");
        switch (version){
            case V1_19_2_GECKO_3 -> {
                switch (mode) {
                    case ITEM, ITEM_WGM -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + " = registerItem(\"" + bi_name + "\",\n" +
                                "            new Item(new FabricItemSettings().group(TestMod.ITEM_GROUP)));");
                    }
                    case ALL_SIDES, PILLAR, ONE_SIDE_BLOCK, BLOCK_ENTITY -> {
                        sendToConsole("public static final Block " + bi_name.toUpperCase() + " = registerBlock(\"" + bi_name + "\", new Block(FabricBlockSettings\n" +
                                "            .of(Material.METAL)\n" +
                                "            .strength(2, 10)\n" +
                                "            .nonOpaque()\n" +
                                "            .sounds(BlockSoundGroup.WOOL)\n" +
                                "            .requiresTool()));");
                    }
                    case BLOCK_WGM -> {
                        sendToConsole("public static final Block " + bi_name.toUpperCase() + " = registerBlock(\"" + bi_name + "\", new " + BINameUtils.getProperName(bi_name) + "(FabricBlockSettings\n" +
                                "            .of(Material.METAL)\n" +
                                "            .strength(2, 10)\n" +
                                "            .nonOpaque()\n" +
                                "            .sounds(BlockSoundGroup.WOOL)\n" +
                                "            .requiresTool()));");
                    }
                    case GECKO_ENTITY -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + "_SPAWN_EGG = registerItem(\"" + bi_name + "_spawn_egg\",\n" +
                                " new SpawnEggItem(RegisterEntities." + bi_name.toUpperCase() + ", " + innerColor.toString().substring(0, innerColor.toString().length() - 2) + ", " + outerColor.toString().substring(0, outerColor.toString().length() - 2) + ", new FabricItemSettings().group(TestMod.ITEM_GROUP)));");
                    }
                    case GECKO_BLOCK -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + "_ITEM = registerItem(\"" + bi_name + "\",\n" +
                                "        new " + BINameUtils.getProperName(bi_name) + "Item(ModBlocks." + bi_name.toUpperCase() + ", new FabricItemSettings().group(TestMod.ITEM_GROUP).maxCount(64)));");

                        sendToConsole("public static final Block " + bi_name.toUpperCase() + " = registerBlockWithoutBlockItem(\"" + bi_name + "\", new " + BINameUtils.getProperName(bi_name) + "(FabricBlockSettings\n" +
                                "            .of(Material.STONE)\n" +
                                "            .nonOpaque()\n" +
                                "            .strength(1, 1)\n" +
                                "            .sounds(BlockSoundGroup.WOOL)));");
                    }
                    case GECKO_ITEM -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + " = registerItem(\"" + bi_name + "\",\n" +
                                "            new " + BINameUtils.getProperName(bi_name) + "(new FabricItemSettings().group(TestMod.ITEM_GROUP)));");
                    }
                }
            }

            case V1_19_3_GECKO_4 -> {
                switch (mode){
                    case ITEM, ITEM_WGM -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + " = registerItem(\"" + bi_name + "\",\n" +
                                "            new Item(new FabricItemSettings()));");
                    }
                    case ALL_SIDES, PILLAR, ONE_SIDE_BLOCK, BLOCK_ENTITY -> {
                        sendToConsole("public static final Block " + bi_name.toUpperCase() + " = registerBlock(\"" + bi_name + "\", new Block(FabricBlockSettings\n" +
                                "               .copyOf(Blocks.IRON_BLOCK)\n" +
                                "               .strength(2, 10)\n" +
                                "               .sounds(BlockSoundGroup.WOOL)\n" +
                                "               .requiresTool()));");
                    }
                    case BLOCK_WGM -> {
                        sendToConsole("public static final Block " + bi_name.toUpperCase() + " = registerBlock(\"" + bi_name + "\", new " + BINameUtils.getProperName(bi_name) + "(FabricBlockSettings\n" +
                                "               .copyOf(Blocks.IRON_BLOCK)\n" +
                                "               .strength(2, 10)\n" +
                                "               .sounds(BlockSoundGroup.WOOL)\n" +
                                "               .requiresTool()));");
                    }
                    case GECKO_ENTITY -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + " = registerItem(\"" + bi_name + "\",\n" +
                                "            new SpawnEggItem(RegisterEntities." + bi_name.toUpperCase() + ", " + innerColor.toString().substring(0, innerColor.toString().length() - 2) + ", " + outerColor.toString().substring(0, outerColor.toString().length() - 2) + ", new FabricItemSettings()));");
                    }
                    case GECKO_BLOCK -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase()+ "_ITEM = registerItem(\"" + bi_name + "\",\n" +
                                "            new " + BINameUtils.getProperName(bi_name) + "Item(ModBlocks." + bi_name.toUpperCase() + ", new FabricItemSettings()));");

                        sendToConsole("public static final Block " + bi_name.toUpperCase() + " = registerBlockWithoutBlockItem(\"" + bi_name + "\", new " + BINameUtils.getProperName(bi_name) + "(FabricBlockSettings\n" +
                                "            .copyOf(Blocks.WHITE_WOOL)\n" +
                                "            .nonOpaque()\n" +
                                "            .strength(1, 1)\n" +
                                "            .sounds(BlockSoundGroup.WOOL)));");
                    }
                    case GECKO_ITEM -> {
                        sendToConsole("public static final Item " + bi_name.toUpperCase() + " = registerItem(\"" + bi_name + "\",\n" +
                                "            new " + BINameUtils.getProperName(bi_name) + "(new FabricItemSettings()));");
                    }
                }
                //TODO Item registration generator for Geckolib 4.0
            }
        }

    }
}
