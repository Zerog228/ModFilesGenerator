package zerog.modfilesgenerator.generators;

import zerog.modfilesgenerator.annotations.VersionIndependent;
import zerog.modfilesgenerator.annotations.WIP;
import zerog.modfilesgenerator.enums.ProgramVersion;
import zerog.modfilesgenerator.enums.WorkMode;
import zerog.modfilesgenerator.util.BINameUtils;
import zerog.modfilesgenerator.util.CopyFiles;
import zerog.modfilesgenerator.util.JavaFileUtils;

import java.io.*;
import java.util.*;

import static zerog.modfilesgenerator.Controller.helpersPath;
import static zerog.modfilesgenerator.Controller.sendToConsole;

@WIP(version = ProgramVersion.V1_19_2_GECKO_3, workmode =
        {
                WorkMode.GECKO_ITEM
        }
)
@WIP(version = ProgramVersion.V1_19_3_GECKO_4, workmode =
        {
                WorkMode.GECKO_ENTITY
        }
)

public class GeckoGenerator {

    private final String bi_name;
    private final String texture;
    private final File jsonFile;
    private final File animationFile;
    private final String pathToCustomEntityFolder;
    private final String pathToEntityTextureFolder;
    private final String pathToGeoFolder;
    private final String pathToAnimationsFolder;
    private final String modId;
    private final String moveAnimation;
    private final String idleAnimation;
    private final String pathToRegEntitiesClass;
    private final String pathToRegAttributesClass;
    private final boolean isHostile;
    private final String properName;
    private final String packageName;
    private final String itemModelFolder;
    private final HashMap<String, String> attributes;
    private final double width;
    private final double height;
    private final String pathToClientModClass;
    private final String pathToEntitiesFolder;

    private final String blockClassesFolder;
    private final String pathToItemsFolder;
    private final File displaySettingsFile;
    private final String pathToModBlockEntities;
    private final String pathToBlockTexture;
    private final String pathToBlockModelFolder;
    private final String blockStatePath;

    private final String pathToCustomEntityPackage;
    private final String pathToBlockClassesPackage;
    private final String pathToItemClassesPackage;
    private final String pathToItemTextureFolder;
    private final String pathToItemModelFolder;

    private final ProgramVersion version;
    private String geckoVer = "gecko4";

    WorkMode mode;

    private boolean shouldReturn;

    public GeckoGenerator(String bi_name, String texture, File jsonFile, File animationFile, String pathToEntitiesFolder,
                          String pathToEntityTextureFolder, String pathToGeoFolder, String pathToAnimationsFolder,
                          String pathToClientModClass, String modId, String moveAnimation, String idleAnimation,
                          String pathToRegEntitiesClass, String pathToRegAttributesClass, boolean isHostile, String itemModelFolder,
                          HashMap<String, String> attributes, double width, double height, File displaySettingsFile, String pathToModBlockEntities,
                          String blockClassesFolder, String pathToItemsFolder, String pathToBlockTexture, String pathToBlockModelFolder, String blockStatePath,
                          String pathToItemTextureFolder, String pathToItemModelFolder, ProgramVersion version, WorkMode mode){

        this.shouldReturn = false;

        if(jsonFile != null && animationFile != null){
            if (jsonFile.renameTo(new File(jsonFile.getAbsolutePath() + "\\" + jsonFile.getName().toLowerCase()))) {
                sendToConsole("Renamed geo model to " + jsonFile.getName() + "! Model must always be in lowercase!");
            }
            if (jsonFile.renameTo(new File(jsonFile.getAbsolutePath() + "\\" + jsonFile.getName().replace(" ", ".")))) {
                sendToConsole("Deleted space in geo model, be attentive! New name - " + jsonFile.getName() + "!");
            }

            if (animationFile.renameTo(new File(animationFile.getAbsolutePath() + "\\" + animationFile.getName().toLowerCase()))) {
                sendToConsole("Renamed animation file to " + animationFile.getName() + "! Animation must always be in lowercase!");
            }
            if (animationFile.renameTo(new File(animationFile.getAbsolutePath() + "\\" + animationFile.getName().replace(" ", ".")))) {
                sendToConsole("Deleted space in animation file name, be attentive! New name - " + animationFile.getName() + "!");
            }
        }else {
            sendToConsole("Animation file or json file is null!", new Exception());
            shouldReturn = true;
        }

        if(mode != WorkMode.GECKO_ENTITY){
            if(displaySettingsFile != null){
                if (displaySettingsFile.renameTo(new File(displaySettingsFile.getAbsolutePath() + "\\" + displaySettingsFile.getName().toLowerCase()))) {
                    sendToConsole("Renamed display settings file to " + displaySettingsFile.getName() + "! Animation must always be in lowercase!");
                }
                if (displaySettingsFile.renameTo(new File(displaySettingsFile.getAbsolutePath() + "\\" + displaySettingsFile.getName().replace(" ", "_")))) {
                    sendToConsole("Deleted space in display settings file name, be attentive! New name - " + displaySettingsFile.getName() + "!");
                }
            }else {
                sendToConsole("Display settings file is null!", new Exception());
                shouldReturn = true;
            }
        }

        this.bi_name = bi_name;
        this.texture = texture;
        this.jsonFile = jsonFile;
        this.animationFile = animationFile;
        this.pathToCustomEntityFolder = pathToEntitiesFolder+"\\"+bi_name;
        this.pathToEntityTextureFolder = pathToEntityTextureFolder;
        this.pathToGeoFolder = pathToGeoFolder;
        this.pathToAnimationsFolder = pathToAnimationsFolder;
        this.modId = modId;
        this.moveAnimation = moveAnimation;
        this.idleAnimation = idleAnimation;
        this.pathToRegEntitiesClass = pathToRegEntitiesClass;
        this.pathToRegAttributesClass = pathToRegAttributesClass;
        this.isHostile = isHostile;
        this.properName = BINameUtils.getProperName(bi_name);
        this.packageName = pathToEntitiesFolder.replace("\\", ".").split("java.")[1]+"."+bi_name;
        this.itemModelFolder = itemModelFolder;
        this.attributes = attributes;
        this.width = width;
        this.height = height;
        this.pathToCustomEntityPackage = BINameUtils.getPackagePath(pathToCustomEntityFolder);

        this.displaySettingsFile = displaySettingsFile;
        this.pathToModBlockEntities = pathToModBlockEntities;
        this.blockClassesFolder = blockClassesFolder;
        this.pathToItemsFolder = mode == WorkMode.GECKO_ITEM ? pathToItemsFolder+"\\"+bi_name : pathToItemsFolder;
        this.pathToBlockTexture = pathToBlockTexture;
        this.pathToBlockModelFolder = pathToBlockModelFolder;

        this.pathToBlockClassesPackage = BINameUtils.getPackagePath(blockClassesFolder);
        this.pathToItemClassesPackage = BINameUtils.getPackagePath(pathToItemsFolder);

        this.pathToEntitiesFolder = pathToEntitiesFolder;
        this.pathToClientModClass = pathToClientModClass;

        this.blockStatePath = blockStatePath;

        this.pathToItemTextureFolder = pathToItemTextureFolder;
        this.pathToItemModelFolder = pathToItemModelFolder;

        this.version = version;
        this.geckoVer = version.getGecko();
        this.mode = mode;
    }

    public boolean create(){
        if(shouldReturn){
            return !shouldReturn;
        }

        try{
            File clientModClass = new File(pathToClientModClass);
            File clientHelper = new File(helpersPath + "\\clientModHelper.txt");
            Map<String, List<String>> replacementMap = new HashMap<>();

            switch (mode) {
                case GECKO_ENTITY -> {
                    //GECKO ENTITY

                    //It's version independent
                    replacementMap.put("//For replace by auto-generation", List.of("        EntityRendererRegistry.register(RegisterEntities." + bi_name.toUpperCase() + ", " + properName + "Renderer::new);"));
                    JavaFileUtils.rewriteJavaFile(null, Collections.singletonList(pathToCustomEntityPackage + "." + properName + "Renderer"), replacementMap, clientHelper, new FileInputStream(clientModClass), clientModClass, "Exception on clientMod reading!");

                    createPackageAndEntity();
                    registerEntityAndAttributes();
                    createEntityClientModelAndRenderer();

                }
                case GECKO_BLOCK -> {
                    //GECKO BLOCK
                    switch (version) {
                        case V1_19_2_GECKO_3 -> {
                            replacementMap.put("//For replace by auto-generation", List.of("        GeoItemRenderer.registerItemRenderer(ModItems." + bi_name.toUpperCase() + ", new " + properName + "Renderer());",
                                    "        BlockEntityRendererFactories.register(ModBlockEntities." + bi_name.toUpperCase() + "_ENTITY, " + properName + "Renderer::new);"));

                            JavaFileUtils.rewriteJavaFile(null, Arrays.asList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Renderer",
                                    pathToItemClassesPackage + "." + properName + "Renderer"), replacementMap, clientHelper, new FileInputStream(clientModClass), clientModClass, "Exception on clientMod (block) reading!");

                        }
                        case V1_19_3_GECKO_4 -> {
                            replacementMap.put("//For replace by auto-generation", Collections.singletonList("        BlockEntityRendererFactories.register(ModBlockEntities."+bi_name.toUpperCase()+"_ENTITY, "+properName+"Renderer::new);"));

                            JavaFileUtils.rewriteJavaFile(null, Collections.singletonList(pathToCustomEntityPackage+"."+properName+"Renderer"), replacementMap, clientHelper, new FileInputStream(clientModClass), clientModClass, "Exception on clientMod (block) reading!");
                        }
                    }

                    registerModBlockEntity();
                    createObjectNModelNRenderer();
                }
                case GECKO_ITEM -> {
                    //GECKO ITEM

                    switch (version) {
                        case V1_19_2_GECKO_3 -> {
                            //TODO Gecko item generation with gecko 3.0
                        }
                        case V1_19_3_GECKO_4 -> {
                            /*replacementMap.put("//For replace by auto-generation",
                                    Collections.singletonList("        GeoItemRenderer.registerItemRenderer(ModItems." + bi_name.toUpperCase() + ", new " + properName + "Renderer());"));

                            JavaFileUtils.rewriteJavaFile(null, Arrays.asList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Renderer"),
                                    replacementMap, clientHelper, new FileInputStream(clientModClass), clientModClass, "Exception on clientMod (item) reading!");

                             */

                            //Unnecessary?
                        }
                    }
                    createObjectNModelNRenderer();
                }
            }
            copyNeededFiles();
            createItem();

        }catch (IOException e){
            sendToConsole("Failed to create gecko thing!", e);
        }
        return true;
    }

    //ONLY FOR BLOCK AND ITEM
    private void createObjectNModelNRenderer() throws IOException{
        switch (mode){
            case GECKO_BLOCK -> {
                //GECKO BLOCK

                //Version-independent section
                File modBlockEntitiesFile = new File(pathToModBlockEntities);
                File customBlockFile = new File(blockClassesFolder + "\\" + properName + ".java");

                File pathToCustomModelNRendererPackage = new File(pathToCustomEntityFolder);
                pathToCustomModelNRendererPackage.mkdirs();

                File customBlockEntity = new File(pathToCustomModelNRendererPackage + "\\" + properName + "Entity.java");
                File customBlockEntityModel = new File(pathToCustomModelNRendererPackage + "\\" + properName + "EntityModel.java");
                File customBlockEntityRenderer = new File(pathToCustomModelNRendererPackage + "\\" + properName + "EntityRenderer.java");

                Map<String, List<String>> replacementMap = new HashMap<>();
                replacementMap.put("ProperBlockName", Collections.singletonList(properName));
                replacementMap.put("animationFile", Collections.singletonList(animationFile.getName()));
                replacementMap.put("geoFile", Collections.singletonList(jsonFile.getName()));
                replacementMap.put("bi_name", Collections.singletonList(bi_name));
                replacementMap.put("BI_NAME", Collections.singletonList(bi_name.toUpperCase()));
                replacementMap.put("mod_id", Collections.singletonList(modId));
                replacementMap.put("texture_name", Collections.singletonList(bi_name + ".png"));

                //Block-Item registration
                File pathToCustomItem = new File(pathToItemsFolder + "\\" + bi_name);
                pathToCustomItem.mkdirs();

                File customBlockItem = new File(pathToCustomItem + "\\" + properName + "Item.java");
                File customBlockItemModel = new File(pathToCustomItem + "\\" + properName + "ItemModel.java");
                File customBlockItemRenderer = new File(pathToCustomItem + "\\" + properName + "ItemRenderer.java");

                //Version-dependent resources
                JavaFileUtils.rewriteJavaFile(pathToBlockClassesPackage, Collections.singletonList(pathToCustomEntityPackage + "." + properName + "Entity"), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/Block.txt"), customBlockFile, "Failed to create block class!");
                JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList(pathToCustomEntityPackage + "." + modBlockEntitiesFile.getName()), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/BlockEntity.txt"), customBlockEntity, "Failed to create BlockEntity class!");
                JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList((pathToCustomEntityPackage + "." + properName + "Entity")), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/BlockEntityModel.txt"), customBlockEntityModel, "Failed to create BlockEntityModel class!");
                JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList((pathToCustomEntityPackage + "." + properName + "Entity")), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/BlockEntityRenderer.txt"), customBlockEntityRenderer, "Failed to create BlockEntityModel class!");

                JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, null, replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/BlockItem.txt"), customBlockItem, "Failed to create BlockItem");
                JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Item"), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/BlockItemModel.txt"), customBlockItemModel, "Failed to create BlockItemModel");
                JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Item"), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoBlockFiles/BlockItemRenderer.txt"), customBlockItemRenderer, "Failed to create BlockItemRenderer");
                /*switch (version) {
                    case V1_19_2_GECKO_3 -> {
                    }
                    case V1_19_3_GECKO_4 -> {
                        JavaFileUtils.rewriteJavaFile(pathToBlockClassesPackage, Collections.singletonList(pathToCustomEntityPackage + "." + properName + "Entity"), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/Block.txt"), customBlockFile, "Failed to create block class!");
                        JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList(pathToCustomEntityPackage + "." + modBlockEntitiesFile.getName()), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/BlockEntity.txt"), customBlockEntity, "Failed to create BlockEntity class!");
                        JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList((pathToCustomEntityPackage + "." + properName + "Entity")), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/BlockEntityModel.txt"), customBlockEntityModel, "Failed to create BlockEntityModel class!");
                        JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList((pathToCustomEntityPackage + "." + properName + "Entity")), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/BlockEntityRenderer.txt"), customBlockEntityRenderer, "Failed to create BlockEntityModel class!");

                        JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, null, replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/BlockItem.txt"), customBlockItem, "Failed to create BlockItem");
                        JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Item"), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/BlockItemModel.txt"), customBlockItemModel, "Failed to create BlockItemModel");
                        JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Item"), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoBlockFiles/BlockItemRenderer.txt"), customBlockItemRenderer, "Failed to create BlockItemRenderer");
                    }
                }
                 */
            }
            case GECKO_ITEM -> {
                //GECKO ITEM
                switch (version) {
                    case V1_19_2_GECKO_3 -> {
                        //TODO Gecko item generation with gecko 3.0
                    }
                    case V1_19_3_GECKO_4 -> {
                        File animatedItemFile = new File(pathToItemsFolder + "\\" + properName + ".java");
                        File animatedItemModel = new File(pathToItemsFolder + "\\" + properName + "Model.java");
                        File animatedItemRenderer = new File(pathToItemsFolder + "\\" + properName + "Renderer.java");

                        File pathToCustomItem = new File(pathToItemsFolder);
                        pathToCustomItem.mkdirs();

                        Map<String, List<String>> replacementMap = new HashMap<>();
                        replacementMap.put("ProperName", Collections.singletonList(properName));
                        replacementMap.put("mod_id", Collections.singletonList(modId));
                        replacementMap.put("animationFile", Collections.singletonList(animationFile.getName()));
                        replacementMap.put("geoFile", Collections.singletonList(jsonFile.getName()));
                        replacementMap.put("texture_name", Collections.singletonList(bi_name + ".png"));

                        JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName + "Renderer"),
                                replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoItemFiles/AnimatedItem.txt"), animatedItemFile, "Failed to create animated item!");

                        JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName),
                                replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoItemFiles/AnimatedItemModel.txt"), animatedItemModel, "Failed to create animated item model!");

                        JavaFileUtils.rewriteJavaFile(pathToItemClassesPackage + "." + bi_name, Collections.singletonList(pathToItemClassesPackage + "." + bi_name + "." + properName),
                                replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko4/geckoItemFiles/AnimatedItemRenderer.txt"), animatedItemRenderer, "Failed to create animated item renderer!");
                    }
                }
            }
        }
    }

    private void registerModBlockEntity() throws IOException {
        //Version-independent stuff
        File modBlockEntitiesFile = new File(pathToModBlockEntities);

        Map<String, List<String>> replacementMap = new HashMap<>();
        List<String> forReplace = new ArrayList<>();

        switch (version){
            case V1_19_2_GECKO_3 -> {
                forReplace.add("        public static BlockEntityType<" + properName + "Entity> " + bi_name.toUpperCase() + "_ENTITY =");
                forReplace.add("                Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(\"" + modId + "\", \"" + bi_name + "\"),");
                forReplace.add("                        FabricBlockEntityTypeBuilder.create(" + properName + "Entity::new,");
                forReplace.add("                                ModBlocks." + bi_name.toUpperCase() + ").build(null));");
                replacementMap.put("//For replace by auto-generation", forReplace);
            }
            case V1_19_3_GECKO_4 -> {
                forReplace.add("        public static BlockEntityType<" + properName + "Entity> " + bi_name.toUpperCase() + "_ENTITY =");
                forReplace.add("                Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(\"" + modId + "\", \"" + bi_name + "\"),");
                forReplace.add("                        FabricBlockEntityTypeBuilder.create(" + properName + "Entity::new,");
                forReplace.add("                                ModBlocks." + bi_name.toUpperCase() + ").build());");
                replacementMap.put("//For replace by auto-generation", forReplace);

            }
        }

        JavaFileUtils.rewriteJavaFile(null, Collections.singletonList(pathToCustomEntityPackage + "." + properName + "Entity"), replacementMap, null, new FileInputStream(modBlockEntitiesFile), modBlockEntitiesFile, "Failed to add ModBlockEntities");
    }

    private void createPackageAndEntity() throws IOException {
        //Entity logic
        if(mode == WorkMode.GECKO_ENTITY){
            sendToConsole("Created new folder for entity: " + new File(pathToCustomEntityFolder).mkdir());

            File entityFile = new File(pathToCustomEntityFolder + "\\" + properName + ".java");
            sendToConsole("Created new file for entity: " + entityFile.createNewFile());

            Map<String, List<String>> replacementMap = new HashMap<>();
            replacementMap.put("ProperEntityName", List.of(properName));
            replacementMap.put("ProperEntityAnimationMovement", List.of(moveAnimation));
            replacementMap.put("ProperEntityAnimationIdle", List.of(idleAnimation));
            replacementMap.put("HostileOrAnimal", List.of(isHostile ? "HostileEntity" : "AnimalEntity"));

            List<String> hostileList = new ArrayList<>();
                    /*if (!isHostile) {
                        hostileList.add("    @Nullable");
                        hostileList.add("    @Override");
                        hostileList.add("    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {");
                        hostileList.add("        return null;");
                        hostileList.add("    }");
                    } else {
                    }*/
            hostileList.add("");
            replacementMap.put("OnlyIfPassiveEntity", hostileList);

            for (String key : attributes.keySet()) {
                replacementMap.put(key, Collections.singletonList(attributes.get(key)));
            }

            List<String> goalsList = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(GeckoGenerator.class.getResourceAsStream(isHostile ? "/gecko/"+geckoVer+"/geckoEntityFiles/hostileGoals" : "/gecko/"+geckoVer+"/geckoEntityFiles/passiveGoals")))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    goalsList.add(line);
                }
            } catch (Exception ignored) {
                sendToConsole("Exception while reading goals file!");
            }
            replacementMap.put("InitGoals", goalsList);

            JavaFileUtils.rewriteJavaFile(packageName, null, replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/"+geckoVer+"/geckoEntityFiles/Entity.txt"), entityFile, "Error on entity creation!");
        }
    }

    private void registerEntityAndAttributes() throws IOException {
        //Entity logic
        if(mode == WorkMode.GECKO_ENTITY){
            switch (version){
                case V1_19_2_GECKO_3 -> {
                    File registrationHelper = new File(helpersPath + "\\entityRegistrationHelper.txt");
                    File entityRegistrationClass = new File(pathToRegEntitiesClass);

                    Map<String, List<String>> replacementMap = new HashMap<>();
                    List<String> autoGenerationReplacement = new ArrayList<>();
                    autoGenerationReplacement.add("    public static final EntityType<" + properName + "> " + bi_name.toUpperCase() + " = Registry.register(");
                    autoGenerationReplacement.add("            Registry.ENTITY_TYPE,");
                    autoGenerationReplacement.add("            new Identifier(\"" + modId + "\", \"" + bi_name + "\"),");
                    autoGenerationReplacement.add("            FabricEntityTypeBuilder.create(SpawnGroup." + (isHostile ? "MONSTER" : "CREATURE") + ", " + properName + "::new).dimensions(EntityDimensions.fixed(" + width + "f, " + height + "f)).build()");
                    autoGenerationReplacement.add("    );");
                    replacementMap.put("//For replace by auto-generation", autoGenerationReplacement);

                    replacementMap.put("//Initialize new entity", List.of("        FabricDefaultAttributeRegistry.register(" + bi_name.toUpperCase() + ", " + properName + ".createMobAttributes());"));

                    JavaFileUtils.rewriteJavaFile(null, Collections.singletonList(pathToCustomEntityPackage + "." + properName), replacementMap, registrationHelper, new FileInputStream(entityRegistrationClass), entityRegistrationClass, "Failed to edit entity registration class!");


                    //Attributes
                    File attributeHelper = new File(helpersPath + "\\attributeRegistrationHelper.txt");
                    File attributeRegistrationClass = new File(pathToRegAttributesClass);

                    replacementMap.replace("//For replace by auto-generation", List.of("        FabricDefaultAttributeRegistry.register(" + bi_name.toUpperCase() + ", " + properName + ".setAttributes());"));

                    JavaFileUtils.rewriteJavaFile(null, Collections.singletonList(pathToCustomEntityPackage + "." + properName), replacementMap, attributeHelper, new FileInputStream(attributeRegistrationClass), attributeRegistrationClass, "Failed to edit entity attribute class!");

                    sendToConsole("Registered entity and attributes!");
                }
                case V1_19_3_GECKO_4 -> {
                    //TODO Gecko entity generation with gecko 4.0
                }
            }
        }
    }

    @VersionIndependent
    private void copyNeededFiles() throws IOException {
        switch (mode){
            case GECKO_ENTITY -> {
                CopyFiles.copyFileUsingChannel(new File(texture), new File(pathToEntityTextureFolder + "\\" + bi_name + ".png"));
            }
            case GECKO_BLOCK -> {
                CopyFiles.copyFileUsingChannel(new File(texture), new File(pathToBlockTexture + "\\" + bi_name + ".png"));
                CopyFiles.copyFileUsingChannel(displaySettingsFile, new File(pathToBlockModelFolder + "\\" + displaySettingsFile.getName()));
            }
            case GECKO_ITEM -> {
                CopyFiles.copyFileUsingChannel(new File(texture), new File(pathToItemTextureFolder + "\\" + bi_name + ".png"));
                CopyFiles.copyFileUsingChannel(displaySettingsFile, new File(pathToItemModelFolder + "\\" + displaySettingsFile.getName()));
            }
        }
        CopyFiles.copyFileUsingChannel(jsonFile, new File(pathToGeoFolder + "\\" + jsonFile.getName()));
        CopyFiles.copyFileUsingChannel(animationFile, new File(pathToAnimationsFolder + "\\" + animationFile.getName()));
    }

    private void createEntityClientModelAndRenderer() throws IOException {
        //Entity logic
        if(mode == WorkMode.GECKO_ENTITY){
            switch (version){
                case V1_19_2_GECKO_3 -> {
                    File clientModel = new File(pathToCustomEntityFolder + "\\" + properName + "Model.java");
                    sendToConsole("Created new file for entity model: " + clientModel.createNewFile());
                    File clientModelRenderer = new File(pathToCustomEntityFolder + "\\" + properName + "Renderer.java");
                    sendToConsole("Created new file for entity renderer: " + clientModelRenderer.createNewFile());

                    Map<String, List<String>> replacementMap = new HashMap<>();

                    replacementMap.put("EntityModelName", List.of(properName + "Model"));
                    replacementMap.put("ProperEntityRenderer", List.of(properName + "Renderer"));
                    replacementMap.put("ProperEntityName", List.of(properName));
                    replacementMap.put("ModId", List.of(modId));
                    replacementMap.put("GeoFile", List.of(jsonFile.getName()));
                    replacementMap.put("TextureFile", List.of(bi_name + ".png"));
                    replacementMap.put("AnimationFile", List.of(animationFile.getName()));

                    JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList(pathToCustomEntityPackage + "." + properName), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko3/geckoEntityFiles/EntityModel.txt"), clientModel, "Failed to edit entity registration class!");
                    JavaFileUtils.rewriteJavaFile(pathToCustomEntityPackage, Collections.singletonList(pathToCustomEntityPackage + "." + properName), replacementMap, null, GeckoGenerator.class.getResourceAsStream("/gecko/gecko3/geckoEntityFiles/EntityRenderer.txt"), clientModelRenderer, "Failed to edit entity registration class!");
                    sendToConsole("Created client model and renderer!");
                }
                case V1_19_3_GECKO_4 -> {
                    //TODO Gecko entity generation with gecko 4.0
                }
            }
        }
    }

    @VersionIndependent
    private void createItem() throws IOException {
        if(mode == WorkMode.GECKO_ENTITY){
            File spawnEggFile = new File(itemModelFolder + "\\" + bi_name + "_spawn_egg.json");
            spawnEggFile.createNewFile();
            try (PrintWriter pw = new PrintWriter(spawnEggFile)) {
                pw.println("{");
                pw.println("  \"parent\": \"minecraft:item/template_spawn_egg\"");
                pw.println("}");
            } catch (Exception e) {
                sendToConsole("Failed to create spawn egg item file!");
            }
        }
        if(mode == WorkMode.GECKO_BLOCK){
            File itemFile = new File(itemModelFolder + "\\" + bi_name+".json");
            itemFile.createNewFile();
            try (PrintWriter pw = new PrintWriter(itemFile)) {
                pw.println("{");
                pw.println("  \"parent\": \""+modId+":block/"+displaySettingsFile.getName().split(".json")[0]+"\"");
                pw.println("}");
            } catch (Exception e) {
                sendToConsole("Failed to create new item file!");
            }


            File blockStateFile = new File(blockStatePath + "\\" + bi_name+".json");
            blockStateFile.createNewFile();
            try (PrintWriter pw = new PrintWriter(blockStateFile)) {
                pw.println("{");
                pw.println("    \"facing=north\": { \"model\": \""+modId+":block/"+bi_name+"\", \"uvlock\": true },");
                pw.println("    \"facing=east\":  { \"model\": \""+modId+":block/"+bi_name+"\", \"y\":  90, \"uvlock\": false },");
                pw.println("    \"facing=south\":  { \"model\": \""+modId+":block/"+bi_name+"\", \"y\":  180, \"uvlock\": false },");
                pw.println("    \"facing=west\":  { \"model\": \""+modId+":block/"+bi_name+"\", \"y\":  270, \"uvlock\": false }");
                pw.println("}");
            } catch (Exception e) {
                sendToConsole("Failed to create new blockstate file!");
            }
        }
    }
}