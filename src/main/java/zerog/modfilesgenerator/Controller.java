package zerog.modfilesgenerator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.Initializable;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.util.Pair;
import zerog.modfilesgenerator.annotations.WIP;
import zerog.modfilesgenerator.enums.ProgramVersion;
import zerog.modfilesgenerator.enums.WorkMode;
import zerog.modfilesgenerator.generators.*;
import zerog.modfilesgenerator.test.TestClass;
import zerog.modfilesgenerator.util.CheckAllClasses;
import zerog.modfilesgenerator.util.Config;
import zerog.modfilesgenerator.util.SelectFolderOrFile;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.*;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;


import static java.lang.System.out;
import static zerog.modfilesgenerator.util.Config.*;

public class Controller implements Initializable {

    public Button startButton;
    public ChoiceBox<String> choiceGeneratorType;
    public ImageView generatorImage;
    public TextField translation;
    public ImageView sidePicture1;
    public ImageView sidePicture2;
    public ImageView sidePicture3;
    public ImageView sidePicture4;
    public ImageView sidePicture5;
    public ImageView sidePicture6;
    public TextField modName;
    public TextField modId;
    public TextField itemModelFolder;
    private static TextField staticItemModelFolder;
    public TextField blockModelFolder;
    private static TextField staticBlockModelFolder;
    public TextField blockstateFolder;
    private static TextField staticBlockstateFolder;
    public TextField translationFile;
    private static TextField staticTranslationFile;
    public TextField blockTextureFolder;
    private static TextField staticBlockTextureFolder;
    public TextField itemTextureFolder;
    private static TextField staticItemTextureFolder;
    public TextField entityTextureFolder;
    private static TextField staticEntityTextureFolder;
    public TextField entityFolder;
    private static TextField staticEntityFolder;
    public TextArea consoleArea;
    private static TextArea staticConsoleArea;
    public TextField geoFolder;
    private static TextField staticGeoFolder;
    public TextField animationFolder;
    private static TextField staticAnimationFolder;
    public TextField entityClass;
    private static TextField staticEntityClass;
    public TextField clientModClass;
    private static TextField staticClientModClass;
    public TextField entityClassText;
    public TextField clientModClassText;
    private static TextField staticClientModClassText;
    public TextField animationFolderText;
    public TextField geoFolderText;
    public TextField jsonDnD;
    public AnchorPane anchorPane;
    public CheckBox createNewLangFile;
    public TextField resourceFolder;
    public CheckBox createNewResourceFolder;
    public TextField languageName;
    public Rectangle javaBg;
    public Rectangle resourceBg;
    public TextField itemModelFolderText;
    public TextField blockModelFolderText;
    public TextField blockstateFolderText;
    public TextField blockTextureText;
    public TextField itemTextureText;
    public TextField entityFolderText;
    public TextField entityTextureText;
    public TextField resourceFolderText;
    public GridPane resourceGrid;
    public AnchorPane mainPane;
    public GridPane javaGrid;
    public TextField meFolder;
    public TextField meFolderText;
    public TextField animationDnD;
    public GridPane textureGrid;
    public AnchorPane optionsAnchorPane;
    public ScrollPane languageScrollPane;
    public GridPane languageGridPane;
    public Text animationText;
    public Text dddText;
    public Text javaText;
    public Text textureText;
    public Text resourceText;
    public Text eastText;
    public Text westText;
    public Text southText;
    public Text northText;
    public Text downText;
    public Text upText;
    public Tab blockAndItemTab;
    public TextField languageSelectionText;
    public Tab settingsTab;
    public Tooltip modelDnDTooltip;
    public Tooltip modidTooltip;
    public Tooltip nameTooltip;
    public Tooltip animationDnDTooltip;
    public Text animationListText;
    public Button confirmClearLangFolder;
    public CheckBox clearLangFolder;
    public Tooltip translationTooltip;
    public Tooltip clearLangFolderTooltip;
    public Tooltip mePathTooltip;
    public Tooltip createNewPathTooltip;
    public Tooltip createNewTranslationTooltip;
    public Tooltip resourcePathTooltip;
    public AnchorPane dndAnchorPane;
    public TextField idleAnimationText;
    public TextField movementAnimationText;
    public ChoiceBox<String> idleAnimationChoicebox;
    public ChoiceBox<String> movementAnimationChoicebox;
    public AnchorPane animationsAnchorPane;
    public CheckBox isHostile;
    public TextField regAttributesClass;
    public TextField regAttributesClassText;
    public Tab entityTab;
    public Tab directionsTab;
    public AnchorPane mainFieldsPane;
    public AnchorPane consolePane;
    public AnchorPane texturesPane;
    public Text entityPictureTextureText;
    public ImageView entityPicture;
    public AnchorPane buttonPane;
    public ColorPicker innerColor;
    public ColorPicker outerColor;
    public AnchorPane entityPane;
    public Text spawnEggText;
    public Pagination geckoEntityPagination;
    public Tooltip attackKbAttTooltip;
    public TextField attackKbAtt;
    public Text attackKbAttText;
    public Tooltip armorAttTooltip;
    public Text armorAttText;
    public TextField armorAtt;
    public Tooltip damageAttTooltip;
    public TextField damageAtt;
    public Text damageAttText;
    public Tooltip speedAttTooltip;
    public TextField speedAtt;
    public Text speedAttText;
    public Tooltip kbResAttTooltip;
    public TextField kbResAtt;
    public Text kbResAttText;
    public Tooltip followRangeAttTooltip;
    public TextField followRangeAtt;
    public Text followRangeAttText;
    public Tooltip maxHealthAttTooltip;
    public TextField maxHealthAtt;
    public Text maxHealthAttText;
    public TextField entitySizeZ;
    public TextField entitySizeY;
    public TextField entitySizeX;
    public Text entitySizeText;
    public AnchorPane entitySettingsPane;
    public GridPane attributesPane;
    public GridPane allPathesPane;
    public AnchorPane pathPane;
    public CheckBox failedPathNotification;
    private static CheckBox staticFailedToLoadPathNotification;
    public CheckBox startupNotification;
    private static CheckBox staticStartupNotification;
    public CheckBox newFolderNotification;
    private static CheckBox staticNewFolderNotification;
    public CheckBox incorrectEntitySizeNotification;
    private static CheckBox staticIncorrectEntitySizeNotification;
    public CheckBox newLangFileNotification;
    private static CheckBox staticNewLangFileNotification;
    public Text notificationsText;
    public CheckBox onlyTranslation;
    public Tooltip onlyTranslationTooltip;
    public Text attributesText;
    public Tab tutorialsTab;
    public Text tutorialForBIText;
    public Text tutorialChooseBIText;
    public ImageView tutorialChooseBIImage;
    public ImageView tutorialChooseBIImage2;
    public Text tutorialFillFieldsText;
    public ImageView tutorialFillFieldsImage;
    public Text tutorialDnDText;
    public ImageView tutorialDnDImage;
    public ImageView tutorialFillFieldsImage2;
    public Text tutorialStartButtonText;
    public ImageView tutorialStartButtonImage;
    public Text tutorialRegisterItemInCodeText;
    public ImageView tutorialRegisterItemInCodeImage;
    public AnchorPane youtubersPane;
    public AnchorPane tutorialsPane;
    public Text tutorialForEntityText;
    public Tab youtubersTab;
    public Text tutorialForBlockEntityText;
    public Text tutorialOpenEntityTabText;
    public Text tutorialFillEntityFieldsText;
    public ImageView tutorialFillEntityFieldsImage;
    public ImageView tutorialFillEntityFieldsImage2;
    public Text tutorialEntityDnDText;
    public ImageView tutorialEntityDnDImage;
    public ImageView tutorialEntityDnDImage2;
    public Text tutorialSelectEntityValuesText;
    public ImageView tutorialSelectEntityValuesImage;
    public ImageView tutorialSelectEntityValuesImage2;
    public Text tutorialEntityStartButtonText;
    public Text tutorialEntityRegisteredInCodeText;
    public ImageView tutorialEntityRegisteredInCodeImage;
    public ImageView tutorialEntityRegisteredInCodeImage2;
    public Text tutorialEntityAddedText;
    public ImageView tutorialEntityAddedImage;
    public Text tutorialEntityWarningText;
    public Pagination tutorialsPagination;
    public AnchorPane biEntityBlockTutorialPane;
    public CheckBox isRotatable;
    public TextField blockFolder;
    public TextField blockFolderText;
    public CheckBox savedToConfigNotification;
    private static CheckBox staticSavedToConfigNotification;
    public AnchorPane appTutorialsPane;
    public Text youtuberErnieBernieText;
    public Text youtuberTurtyWurtyText;
    public Text youtuberCy4Text;
    public Text youtuberKaupenjoeText;
    public Button checkClassesButton;
    public Text closeWelcomeScreenText;
    public Text plansText;
    public Text infoText;
    public Text versionText;
    public Text changelogText;
    public CheckBox showWelcomeScreenCheckBox;
    public Text welcomeText;
    public AnchorPane welcomeScreenPane;
    public Button showWelcomeScreenButton;
    public AnchorPane addTagsPane;
    public ChoiceBox toolTagBox;
    public ChoiceBox miningLevelBox;
    public CheckBox geckoEntityCheckbox;
    public CheckBox geckoBlockCheckbox;
    public Text tagsText;
    public Text toolText;
    public Text miningLevelText;
    public TextField translationText;
    public AnchorPane spawnEggPane;
    public Text gecko_display_settings_text;
    public TextField displaySettingsDnD;
    public Tooltip displayDnDTooltip;
    public TextField modBlockEntitiesClass;
    private static TextField staticModBlockEntitiesClass;
    public TextField modBlockEntitiesClassText;
    public TextField itemFolder;
    private static TextField staticItemFolder;
    public TextField itemFolderText;
    public Circle testCircle;
    public CheckBox geckoItemCheckbox;
    public ChoiceBox programVersionChooseBox;
    private static ChoiceBox staticProgramVersionChooseBox;
    private ImageView innerImageView;
    public ImageView outerImageView;
    private WorkMode mode = WorkMode.NONE;
    public static Path currentWorkingDir = Paths.get("").toAbsolutePath();
    public static String lastOpenedPath = "";
    public static Path helpersPath = Path.of(currentWorkingDir + "\\helpers");
    private static String side1 = "";
    private static String side2 = "";
    private static String side3 = "";
    private static String side4 = "";
    private static String side5 = "";
    private static String side6 = "";
    public List<ImageView> views = new ArrayList<>();
    private static String langName = "enEN";
    private File jsonFile, displayFile, animationFile;
    private boolean createNewResourceLocations = false;
    private boolean createNewTranslation = false;
    private List<File> translations;
    private HashMap<String, String> WorkModeTranslationMap;
    private HashMap<String, String> reverseTranslationMap;
    private List<TextField> foldersAndFiles;
    private WorkMode previousMode;
    private static boolean isEnabled = false;
    private static boolean extremeTranslationMode = false;


    //TODO 0/38
    //App
    //TODO Ability to create/load backup, undo actions

    //TODO При генерации блока в режиме column нет выбора вращаемости
    //TODO Режим вращаемости по горизонтали/всем осям
    //TODO При генерации блока в режиме отдельных текстур в коде не предлагается подставление нового типа блока, а используется ванильный "Block"
    //TODO Для BlockWGM не предлагается натсройка .nonOpaque()
    //TODO Path for tags (needs_iron_tool...) and auto-generation folders
    //TODO Tags and instruments (needs...tool.json)
    //TODO (mb. +Tags autocomplete like needs_iron_tool.json etc. (similar to translation, mb. Jackson would help)) Settings for block and item (What kind of tool, what level, rarity, damage (Advanced item generation)).
    //TODO Tab with constructor for item generation with ability to set item group
    //TODO Try to use Jackson for translation/tags adding
    //TODO Block-entity creation
    //TODO Block-entity tutorial
    //TODO Block-entity tab/complete entity tab
    //TODO CheckBox in settings to automatically create item in block/item class if replacement-tag is present
    //TODO WorkMode for doors
    //TODO On creating something check if files already exists and checkbox for replace / ability to disable this notification in console
    //TODO Forge/Fabric switch in settings tab;
    //Todo Create item if... checkbox in settings tab??
    //TODO Instrument for uploading/downloading models and textures from server
    //TODO Auto-updating/site for downloading updates
    //TODO Block-multipart generation (tab for advanced things like this?)?
    //TODO Reset translation files on program version change

    //TODO DnD texture for particles on block break

    //Revamp
    //TODO Brand new interface (When selecting something new corresponding fields appears)

    //Entity
    //TODO Choose targets for attack goal editor, attribute editor?..
    //TODO RendererType and methods editor (Scale...);

    //TODO check if entity works correctly

    private void connectStaticWithNonStatic() {
        staticItemModelFolder = itemModelFolder;
        staticBlockModelFolder = blockModelFolder;
        staticBlockstateFolder = blockstateFolder;
        staticBlockTextureFolder = blockTextureFolder;
        staticItemTextureFolder = itemTextureFolder;
        staticEntityTextureFolder = entityTextureFolder;
        staticAnimationFolder = animationFolder;
        staticGeoFolder = geoFolder;
        staticTranslationFile = translationFile;
        staticEntityClass = entityClass;
        staticClientModClass = clientModClass;
        staticEntityFolder = entityFolder;
        staticConsoleArea = consoleArea;

        staticModBlockEntitiesClass = modBlockEntitiesClass;
        staticItemFolder = itemFolder;

        staticFailedToLoadPathNotification = failedPathNotification;
        staticStartupNotification = startupNotification;
        staticNewFolderNotification = newFolderNotification;
        staticIncorrectEntitySizeNotification = incorrectEntitySizeNotification;
        staticNewLangFileNotification = newLangFileNotification;
        staticSavedToConfigNotification = savedToConfigNotification;

        staticProgramVersionChooseBox = programVersionChooseBox;
    }

    private void putConfigTextFields(){
        foldersAndFiles = new ArrayList<>();
        foldersAndFiles.add(modId);
        foldersAndFiles.add(translationFile);
        foldersAndFiles.add(resourceFolder);
        foldersAndFiles.add(meFolder);
        foldersAndFiles.add(itemModelFolder);
        foldersAndFiles.add(blockModelFolder);
        foldersAndFiles.add(blockstateFolder);
        foldersAndFiles.add(animationFolder);
        foldersAndFiles.add(geoFolder);

        foldersAndFiles.add(clientModClass);
        foldersAndFiles.add(entityFolder);
        foldersAndFiles.add(entityClass);

        foldersAndFiles.add(itemTextureFolder);
        foldersAndFiles.add(blockTextureFolder);
        foldersAndFiles.add(entityTextureFolder);

        foldersAndFiles.add(regAttributesClass);
        foldersAndFiles.add(blockFolder);

        foldersAndFiles.add(modBlockEntitiesClass);
        foldersAndFiles.add(itemFolder);
    }

    public static void connectPathWithFolder(String pathName, String finalPath, boolean created) throws IOException{
        switch (pathName){
            case "blockstates" -> {
                if(created){
                    staticBlockstateFolder.setText(finalPath);
                    saveInConfig("blockstateFolder", finalPath);
                }
            }
            case "models\\block" -> {
                if(created){
                    staticBlockModelFolder.setText(finalPath);
                    saveInConfig("blockModelsFolder", finalPath);
                }
            }
            case "models\\item" -> {
                if(created){
                    staticItemModelFolder.setText(finalPath);
                    saveInConfig("itemModelsFolder", finalPath);
                }
            }
            case "textures\\block" -> {
                if(created){
                    staticBlockTextureFolder.setText(finalPath);
                    saveInConfig("blockTextureFolder", finalPath);
                }
            }
            case "textures\\item" -> {
                if(created){
                    staticItemTextureFolder.setText(finalPath);
                    saveInConfig("itemTextureFolder", finalPath);
                }
            }
            case "textures\\entity" -> {
                if(created){
                    staticEntityTextureFolder.setText(finalPath);
                    saveInConfig("entityTextureFolder", finalPath);
                }
            }
            case "geo" -> {
                if(created){
                    staticGeoFolder.setText(finalPath);
                    saveInConfig("geoFolder", finalPath);
                }
            }
            case "animations" -> {
                if(created){
                    staticAnimationFolder.setText(finalPath);
                    saveInConfig("animationFolder", finalPath);
                }
            }
        }
    }

    @Override
    public void initialize(URL args0, ResourceBundle args1) {
        //Always must be first!
        connectStaticWithNonStatic();
        checkTranslations();
        //Always must be first!

        helpersPath.toFile().mkdir();
        views = (Arrays.asList(sidePicture1, sidePicture2, sidePicture3, sidePicture4, sidePicture5, sidePicture6, entityPicture));
        views.forEach(view -> view.setImage(new Image(Controller.class.getResourceAsStream("/texture.png"))));

        anchorPane.setBackground(new Background(createBgFromResource("backgrounds/anchorBg.png")));
        optionsAnchorPane.setBackground(new Background(createBgFromResource("backgrounds/configBg.png")));
        entityPane.setBackground(new Background(createBgFromResource("backgrounds/entityBg.png")));
        pathPane.setBackground(new Background(createBgFromResource("backgrounds/pathBg.png")));
        tutorialsPane.setBackground(new Background(createBgFromResource("backgrounds/tutorialBg.png")));
        youtubersPane.setBackground(new Background(createBgFromResource("backgrounds/youtuberBg.png")));

        initializeTutorialsImages();

        putConfigTextFields();
        loadNotifications();

        Image innerImage = new Image(Controller.class.getResourceAsStream("/entityFiles/eggInner.png"), 100, 100, true, false);
        innerImageView = new ImageView(innerImage);
        innerImageView.setClip(new ImageView(innerImage));
        innerImageView.setLayoutX(400);
        innerImageView.setLayoutY(50);
        entityPane.getChildren().add(innerImageView);

        Image outerImage = new Image(Controller.class.getResourceAsStream("/entityFiles/eggOuter.png"), 100, 100, true, false);
        outerImageView = new ImageView(outerImage);
        outerImageView.setClip(new ImageView(outerImage));
        outerImageView.setLayoutX(400);
        outerImageView.setLayoutY(50);
        entityPane.getChildren().add(outerImageView);

        for(TextField folderOrFile : foldersAndFiles){
            folderOrFile.setText(getOrCreateDefault(folderOrFile.getId(), ""));
        }

        langName = getOrCreateDefault("translation", "");
        lastOpenedPath = getOrCreateDefault("lastOpenedPath", "");

        parseTranslation();
        languageName.setVisible(false);
        languageName.setEditable(false);

        geckoEntityPagination.setPageFactory(integer -> {
            Node node;
            switch (integer){
                case 0 -> {
                    node = animationsAnchorPane;
                }
                case 1 -> {
                    node = entitySettingsPane;
                    entitySettingsPane.setVisible(true);
                }
                case 2 -> node = entitySettingsPane;
                default -> node = animationsAnchorPane;
            }
            return node;
        });

        tutorialsPagination.setPageFactory(integer -> {
            Node node;
            switch (integer){
                case 0 -> {
                    node = appTutorialsPane;
                }
                default -> {
                    node = biEntityBlockTutorialPane;
                    biEntityBlockTutorialPane.setVisible(true);
                }
            }
            return node;
        });
        geckoEntityCheckbox.setSelected(true);

        programVersionChooseBox.getItems().addAll(ProgramVersion.values());
        programVersionChooseBox.setValue(ProgramVersion.valueOf(Config.getOrCreateDefault("programVersion", "V1_19_2_GECKO_3")));

        welcomeScreenLogic();
        isEnabled = true;
        consoleArea.backgroundProperty().setValue(Background.fill(Paint.valueOf("red")));
        sendToConsole(ConsoleNotification.STARTED.getNotification());

        testCircle.setVisible(false);
        //ClampCursor cursor = new ClampCursor(testCircle, anchorPane);
        //cursor.start();
    }

    private void welcomeScreenLogic(){
        try{
            welcomeScreenPane.setVisible(Boolean.parseBoolean(Config.getOrCreateDefault("welcome", "true")));
        }catch (Exception ignored){}
        versionText.setText(versionText.getText()+" "+ version);
    }

    private BackgroundImage createBgFromResource(String pathToRes){
        return new BackgroundImage(new Image(Controller.class.getResourceAsStream("/"+pathToRes),
                250, 500, false, true),
                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT, new BackgroundSize(1, 1, true, true, false, false));
    }

    private void initializeTutorialsImages(){
        tutorialChooseBIImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/bi_image.JPG")));
        tutorialChooseBIImage2.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/bi_image2.JPG")));
        tutorialFillFieldsImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/fields.JPG")));
        tutorialFillFieldsImage2.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/fields2.JPG")));
        tutorialDnDImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/dnd_image.JPG")));
        tutorialStartButtonImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/start_button.JPG")));
        tutorialRegisterItemInCodeImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/block_item/register_in_code.JPG")));

        tutorialFillEntityFieldsImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/fillFields.JPG")));
        tutorialFillEntityFieldsImage2.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/spawnEgg.JPG")));
        tutorialEntityDnDImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/dNd.JPG")));
        //tutorialEntityDnDImage2.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/selectAnimation.JPG")));
        tutorialSelectEntityValuesImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/selectAnimation.JPG")));
        tutorialSelectEntityValuesImage2.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/selectAttributes.JPG")));
        tutorialEntityRegisteredInCodeImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/consoleNotification.JPG")));
        tutorialEntityRegisteredInCodeImage2.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/incodeRegistration.JPG")));
        tutorialEntityAddedImage.setImage(new Image(Controller.class.getResourceAsStream("/tutorials/entity/voala.png")));
    }

    public static void sendToConsole(String message){
        if(message != null) staticConsoleArea.appendText(message+"\n");
    }

    public static void sendToConsole(String message, Exception e){
        staticConsoleArea.appendText(message+"\n");
        out.println(message);
        e.printStackTrace();
    }

    public void onStartButtonClick(ActionEvent e) throws IOException{
        mode = WorkMode.valueOf(reverseTranslationMap.get(choiceGeneratorType.getValue()));

        if(mode != WorkMode.NEW_PATH && mode != WorkMode.NEW_TRANSLATION && mode != WorkMode.TEST &&
                (modId.getText().isEmpty() || translation.getText().isEmpty() || modName.getText().isEmpty())){
            sendToConsole("Fill modId, translation and bi_name fields first!");
            return;
        }

        if(checkForWIPAnnotations(ItemRegistrationGenerator.class)){
            return;
        }

        //TODO Check if needed field is filled or not
        //TODO Highlight background of field on start if field is empty
        if(createNewResourceLocations && mode == WorkMode.NEW_PATH){
            ResourcePathGenerator generator = new ResourcePathGenerator(resourceFolder.getText(), modId.getText());
        }else{
            GeckoGenerator geckoGenerator = null;
            if(!onlyTranslation.isSelected()){
                if(!checkIfPathFilled() && mode != WorkMode.TEST){
                    sendToConsole("Pathes are not set!");
                    return;
                }
                ArrayList<String> imagePath = new ArrayList<>(Arrays.asList(side1, side2, side3, side4, side5, side6));


                switch (mode) {
                    case ITEM, ITEM_WGM -> {
                        if(checkForWIPAnnotations(ItemGenerator.class)){
                            return;
                        }

                        ItemGenerator itemGenerator = new ItemGenerator(modName.getText(), itemModelFolder.getText(), itemTextureFolder.getText(), side1, modId.getText(), mode, jsonFile);
                    }
                    case PILLAR, ALL_SIDES, ONE_SIDE_BLOCK, BLOCK_WGM -> {
                        if(checkForWIPAnnotations(BlockGenerator.class, BlockstateGenerator.class)){
                            return;
                        }

                        BlockGenerator blockGenerator = new BlockGenerator(modName.getText(), jsonFile, blockModelFolder.getText(), itemModelFolder.getText(), modId.getText(), mode, imagePath, blockTextureFolder.getText());
                        BlockstateGenerator blockstateGenerator = new BlockstateGenerator(modName.getText(), jsonFile, blockstateFolder.getText(), modId.getText(), mode, isRotatable.isSelected(), blockFolder.getText(), (ProgramVersion) programVersionChooseBox.getValue());
                    }
                    case GECKO_ENTITY, GECKO_ITEM, GECKO_BLOCK -> {
                        if(checkForWIPAnnotations(GeckoGenerator.class)){
                            return;
                        }

                        HashMap<String, String> attributes = new HashMap<>();
                        attributes.put("MaxHealthAtt", getOrCreateDefaultFromPrompt(maxHealthAtt));
                        attributes.put("FollowRangeAtt", getOrCreateDefaultFromPrompt(followRangeAtt));
                        attributes.put("KbResAtt", getOrCreateDefaultFromPrompt(kbResAtt));
                        attributes.put("MovementSpeedAtt", getOrCreateDefaultFromPrompt(speedAtt));
                        attributes.put("AttackDamageAtt", getOrCreateDefaultFromPrompt(damageAtt));
                        attributes.put("ArmorAtt", getOrCreateDefaultFromPrompt(armorAtt));
                        attributes.put("AttackKbAtt", getOrCreateDefaultFromPrompt(attackKbAtt));

                        double width = 1;
                        double height = 1;
                        try {
                            width = Double.parseDouble(entitySizeX.getText());
                        } catch (Exception ignored) {
                            sendToConsole(ConsoleNotification.SIZE_UNSELECTED.completeNotification("Size x was 0!"));
                        }
                        try {
                            height = Double.parseDouble(entitySizeY.getText());
                        } catch (Exception ignored) {
                            sendToConsole(ConsoleNotification.SIZE_UNSELECTED.completeNotification("Size Y was 0!"));
                        }
                        geckoGenerator = new GeckoGenerator(modName.getText(), side1, jsonFile, animationFile,
                                entityFolder.getText(), entityTextureFolder.getText(), geoFolder.getText(), animationFolder.getText(),
                                clientModClass.getText(), modId.getText(), movementAnimationChoicebox.getValue(),
                                idleAnimationChoicebox.getValue(), entityClass.getText(),
                                regAttributesClass.getText(), isHostile.isSelected(), itemModelFolder.getText(), attributes, width, height,
                                displayFile, modBlockEntitiesClass.getText(), blockFolder.getText(), itemFolder.getText(),
                                blockTextureFolder.getText(), blockModelFolder.getText(), blockstateFolder.getText(),
                                itemTextureFolder.getText(), itemModelFolder.getText(), (ProgramVersion) programVersionChooseBox.getValue(), mode);

                        if(!geckoGenerator.create()){
                            sendToConsole("Failed to create gecko block/entity!");
                            return;
                        }
                    }
                    case BLOCK_ENTITY -> {
                        if (movementAnimationChoicebox.getValue() != null && idleAnimationChoicebox.getValue() != null) {

                        } else {
                            sendToConsole("Select animations first!");
                            return;
                        }
                    }
                    case TEST -> {

                        //sendToConsole(((ProgramVersion)staticProgramVersionChooseBox.getValue()).name());
                        /*
                        File test_file = new File("D:\\java projects\\modFilesGenerator\\helpers\\test_model.json");
                        Map<String, String> texturesMap = new HashMap<>();
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(test_file);
                        jsonNode = jsonNode.get("textures");
                        Iterator<String> iterator = jsonNode.fieldNames();
                        JsonNode finalJsonNode = jsonNode;
                        iterator.forEachRemaining(animationName -> {
                            texturesMap.put(animationName, String.valueOf(finalJsonNode.get(animationName)));
                            out.println(animationName+" "+String.valueOf(finalJsonNode.get(animationName)));
                        });*/

                        //TODO Auto-replace texture path
                    }
                }
            }
            if (mode != WorkMode.TEST && mode != WorkMode.NONE) {
                TranslationGenerator translationGenerator = new TranslationGenerator(langName, modName.getText(), translation.getText(), translationFile.getText(), resourceFolder.getText(), modId.getText(), mode, createNewTranslation, geckoGenerator);
                ItemRegistrationGenerator.registerItem(modName.getText(), innerColor.getValue(), outerColor.getValue(), mode, geckoGenerator, (ProgramVersion) programVersionChooseBox.getValue());
            }
        }
        sendToConsole("Finished operation!");
    }

    private boolean checkForWIPAnnotations(Class ... targets){
        for(Class target : targets){
            for (Annotation annotation : target.getAnnotations()) {
                if (annotation instanceof WIP wip && wip.version() == programVersionChooseBox.getValue() && wip.workmode() != null) {
                    for(WorkMode mode : wip.workmode()){
                        if(mode == this.mode){
                            sendToConsole("Mode " + mode.name() + " in version " + wip.version() + " is unavailable!\n" + wip.message());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void onChoice(Event e){
        InputStream is;
        isRotatable.setVisible(false);
        isRotatable.setSelected(false);
        if(extremeTranslationMode){
            mode = WorkMode.valueOf(choiceGeneratorType.getValue());
        }else {
            mode = WorkMode.valueOf(reverseTranslationMap.get(choiceGeneratorType.getValue()));
        }
        try{
            is = Controller.class.getResourceAsStream("/workmodes/"+mode.name() + ".png");
        }catch (Exception ignored){
            is = Controller.class.getResourceAsStream("/workmodes/FAILED.png");
            sendToConsole("Failed to load img for "+mode+"!");
        }

        generatorImage.setImage(new Image(is));
        switch (mode){
            case ITEM, ITEM_WGM -> {
                highlightItemCreation();
            }
            case ONE_SIDE_BLOCK, PILLAR, ALL_SIDES, BLOCK_WGM -> {
                highlightBlockCreation();
                if(mode == WorkMode.ALL_SIDES || mode == WorkMode.BLOCK_WGM){
                    isRotatable.setVisible(true);
                    isRotatable.setSelected(true);
                }
            }
            case GECKO_ENTITY, GECKO_BLOCK, GECKO_ITEM -> {
                highlightBlockEntityCreation();
            }
            case BLOCK_ENTITY -> {
                highlightBlockEntityCreation();
            }
            default -> {
                highlightAll();
            }
        }
    }

    private void highlightItemCreation(){
        resourceGrid.getChildren().clear();
        addToGrid(resourceGrid, itemModelFolder, itemModelFolderText);

        javaGrid.getChildren().clear();

        textureGrid.getChildren().clear();
        addToGrid(textureGrid, itemTextureFolder, itemTextureText);
    }

    private void highlightBlockCreation(){
        resourceGrid.getChildren().clear();
        addToGrid(resourceGrid, itemModelFolder, itemModelFolderText);
        addToGrid(resourceGrid, blockModelFolder, blockModelFolderText);
        addToGrid(resourceGrid, blockstateFolder, blockstateFolderText);

        javaGrid.getChildren().clear();
        addToGrid(javaGrid, blockFolder, blockFolderText);

        textureGrid.getChildren().clear();
        addToGrid(textureGrid, blockTextureFolder, blockTextureText);
    }

    private void highlightEntityCreation(){
        resourceGrid.getChildren().clear();
        addToGrid(resourceGrid, animationFolder, animationFolderText);
        addToGrid(resourceGrid, geoFolder, geoFolderText);

        javaGrid.getChildren().clear();
        addToGrid(javaGrid, clientModClass, clientModClassText);
        addToGrid(javaGrid, entityFolder, entityFolderText);
        addToGrid(javaGrid, entityClass, entityClassText);
        addToGrid(javaGrid, regAttributesClass, regAttributesClassText);

        textureGrid.getChildren().clear();
        addToGrid(textureGrid, entityTextureFolder, entityTextureText);
    }

    private void highlightBlockEntityCreation(){
        resourceGrid.getChildren().clear();
        addToGrid(resourceGrid, itemModelFolder, itemModelFolderText);
        addToGrid(resourceGrid, blockModelFolder, blockModelFolderText);
        addToGrid(resourceGrid, blockstateFolder, blockstateFolderText);
        addToGrid(resourceGrid, animationFolder, animationFolderText);
        addToGrid(resourceGrid, geoFolder, geoFolderText);

        javaGrid.getChildren().clear();
        addToGrid(javaGrid, entityFolder, entityFolderText);
        addToGrid(javaGrid, blockFolder, blockFolderText);
        addToGrid(javaGrid, itemFolder, itemFolderText);

        textureGrid.getChildren().clear();
        addToGrid(textureGrid, itemTextureFolder, itemTextureText);
        addToGrid(textureGrid, blockTextureFolder, blockTextureText);
        addToGrid(textureGrid, entityTextureFolder, entityTextureText);
    }

    private void highlightAll(){
        resourceGrid.getChildren().clear();
        addToGrid(resourceGrid, itemModelFolder, itemModelFolderText);
        addToGrid(resourceGrid, blockModelFolder, blockModelFolderText);
        addToGrid(resourceGrid, blockstateFolder, blockstateFolderText);
        addToGrid(resourceGrid, animationFolder, animationFolderText);
        addToGrid(resourceGrid, geoFolder, geoFolderText);

        javaGrid.getChildren().clear();
        addToGrid(javaGrid, clientModClass, clientModClassText);
        addToGrid(javaGrid, entityFolder, entityFolderText);
        addToGrid(javaGrid, entityClass, entityClassText);
        addToGrid(javaGrid, regAttributesClass, regAttributesClassText);
        addToGrid(javaGrid, blockFolder, blockFolderText);
        addToGrid(javaGrid, itemFolder, itemFolderText);

        textureGrid.getChildren().clear();
        addToGrid(textureGrid, itemTextureFolder, itemTextureText);
        addToGrid(textureGrid, blockTextureFolder, blockTextureText);
        addToGrid(textureGrid, entityTextureFolder, entityTextureText);
    }

    private void addAllToPathGrid(){
        addToGrid(allPathesPane, itemModelFolder, itemModelFolderText);
        addToGrid(allPathesPane, blockModelFolder, blockModelFolderText);
        addToGrid(allPathesPane, blockstateFolder, blockstateFolderText);
        addToGrid(allPathesPane, animationFolder, animationFolderText);
        addToGrid(allPathesPane, geoFolder, geoFolderText);
        addToGrid(allPathesPane, clientModClass, clientModClassText);
        addToGrid(allPathesPane, entityFolder, entityFolderText);
        addToGrid(allPathesPane, entityClass, entityClassText);
        addToGrid(allPathesPane, regAttributesClass, regAttributesClassText);
        addToGrid(allPathesPane, blockFolder, blockFolderText);
        addToGrid(allPathesPane, itemTextureFolder, itemTextureText);
        addToGrid(allPathesPane, blockTextureFolder, blockTextureText);
        addToGrid(allPathesPane, itemFolder, itemFolderText);
        addToGrid(allPathesPane, entityTextureFolder, entityTextureText);
    }

    private void addToGrid(GridPane grid, TextField t1, TextField t2){
        int i = grid.getRowCount();
        grid.add(t1, 0, i);
        grid.add(t2, 1, i);
    }

    public void onTextureChoice(MouseEvent e) {
        JFileChooser fileChooser = new JFileChooser(lastOpenedPath);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Png filter", "png"));
        int response = fileChooser.showOpenDialog(null);
        if (response == JFileChooser.APPROVE_OPTION) {
            File filePath = fileChooser.getSelectedFile().getAbsoluteFile();
            setRelatedTextures(filePath, (ImageView) e.getSource());
        }
    }

    public void selectFolderOrFile(MouseEvent e){
        Thread selector = new SelectFolderOrFile("SelectorThread", e, lastOpenedPath);

        selector.start();
    }

    public void mouseDragReleased(DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasFiles()) {
            File filePAth = new File((db.getFiles().get(0).getAbsolutePath()));
            setRelatedTextures(filePAth, (ImageView) e.getSource());
        }
    }

    public void setRelatedTextures(File filePath, ImageView target){
        switch (mode) {
            case ITEM, ONE_SIDE_BLOCK, ITEM_WGM, BLOCK_WGM, GECKO_ENTITY, GECKO_BLOCK, GECKO_ITEM, BLOCK_ENTITY -> {
                sidePicture1.setImage(new Image(filePath.getAbsolutePath()));
                sidePicture2.setImage(new Image(filePath.getAbsolutePath()));
                sidePicture3.setImage(new Image(filePath.getAbsolutePath()));
                sidePicture4.setImage(new Image(filePath.getAbsolutePath()));
                sidePicture5.setImage(new Image(filePath.getAbsolutePath()));
                sidePicture6.setImage(new Image(filePath.getAbsolutePath()));
                entityPicture.setImage(new Image(filePath.getAbsolutePath()));
                side1 = filePath.getAbsolutePath();
                lastOpenedPath = filePath.getAbsolutePath();
            }
            case PILLAR -> {
                if (target.equals(sidePicture1) || target.equals(sidePicture6)) {
                    sidePicture1.setImage(new Image(filePath.getAbsolutePath()));
                    sidePicture6.setImage(new Image(filePath.getAbsolutePath()));
                    side1 = filePath.getAbsolutePath();
                }else {
                    sidePicture2.setImage(new Image(filePath.getAbsolutePath()));
                    sidePicture3.setImage(new Image(filePath.getAbsolutePath()));
                    sidePicture4.setImage(new Image(filePath.getAbsolutePath()));
                    sidePicture5.setImage(new Image(filePath.getAbsolutePath()));
                    side2 = filePath.getAbsolutePath();
                }
                lastOpenedPath = filePath.getAbsolutePath();
            }
            case ALL_SIDES -> {
                ImageView targetedView = target;
                targetedView.setImage(new Image(filePath.getAbsolutePath()));
                switch (targetedView.getId()){
                    case "sidePicture1" -> side1 = filePath.getAbsolutePath();
                    case "sidePicture2" -> side2 = filePath.getAbsolutePath();
                    case "sidePicture3" -> side3 = filePath.getAbsolutePath();
                    case "sidePicture4" -> side4 = filePath.getAbsolutePath();
                    case "sidePicture5" -> side5 = filePath.getAbsolutePath();
                    case "sidePicture6" -> side6 = filePath.getAbsolutePath();
                }
                lastOpenedPath = filePath.getAbsolutePath();
            }
        }
    }

    public void jsonDragDropped(DragEvent e) {
        Dragboard db = e.getDragboard();
        if (db.hasFiles()) {
            TextField filed = (TextField) e.getSource();
            filed.setText(e.getDragboard().getFiles().get(0).getName());
            switch (((TextField) e.getSource()).getId()){
                case "jsonDnD" -> {
                    jsonFile = new File((db.getFiles().get(0).getAbsolutePath()));
                }
                case "animationDnD" -> {
                    try {
                        idleAnimationChoicebox.getItems().clear();
                        movementAnimationChoicebox.getItems().clear();
                        animationFile = new File((db.getFiles().get(0).getAbsolutePath()));
                        ObjectMapper mapper = new ObjectMapper();
                        JsonNode jsonNode = mapper.readTree(animationFile);
                        jsonNode = jsonNode.get("animations");
                        Iterator<String> iterator = jsonNode.fieldNames();
                        iterator.forEachRemaining(animationName -> {
                            idleAnimationChoicebox.getItems().add(animationName);
                            movementAnimationChoicebox.getItems().add(animationName);
                        });
                    }catch (Exception ignored){
                        sendToConsole("Wrong animation type!");
                    }
                }
                case "displaySettingsDnD" -> {
                    displayFile = new File((db.getFiles().get(0).getAbsolutePath()));
                }
            }
        }
    }

    public void switchLangFileCreation(ActionEvent actionEvent) {
        createNewTranslation = createNewLangFile.isSelected();
        languageName.setVisible(createNewLangFile.isSelected());
        languageName.setEditable(createNewLangFile.isSelected());
    }

    public void switchFolderCreation(ActionEvent actionEvent) {
        createNewResourceLocations = createNewResourceFolder.isSelected();
    }

    private void parseTranslation(){
        try{
            WorkModeTranslationMap = Config.getWorkmodeTranslationMap(getOrCreateDefault("translation", ""));
        }catch (Exception ignored){
            out.println("Failed to load workmode translation map!");
            extremeTranslationMode = true;
            try{
                Config.checkTranslations();
                WorkModeTranslationMap = Config.getWorkmodeTranslationMap(getOrCreateDefault("translation", ""));
                out.println(getOrCreateDefault("translation", ""));
            }catch (Exception ignored2){
                out.println("Failed to get translation config!");
            }
        }
        try {
            translations = Config.searchLanguages();
        }catch (Exception ignored){
            sendToConsole("Failed to load translations!");
            sendToConsole("Path: "+Controller.class.getResourceAsStream("/languages/"));
            out.println("Failed to load translations!");
        }
        for(File translation_file : translations){
            Button langButton = new Button(translation_file.getName());
            if(langButton.getText().equals(langName)){
                langButton.setStyle("-fx-background-color: #ccffd6;");
            }
            langButton.setPrefWidth(150);
            langButton.setOnAction(actionEvent -> {
                if(Objects.equals(langButton.getText(), langName)) return;

                languageGridPane.getChildren().clear();
                try {
                    WorkModeTranslationMap = Config.getWorkmodeTranslationMap(langButton.getText());
                    saveInConfig("translation", langButton.getText());
                    sendToConsole("New language - "+langButton.getText());
                    langName = langButton.getText();
                } catch (IOException e) {
                    sendToConsole("Failed to get translationMap! Lang: "+langButton.getText());
                    out.println("Failed to load translation map!");
                }
                parseTranslation();
            });
            languageGridPane.add(langButton, 0, languageGridPane.getRowCount());
        }

        iterateForAnchorPane(mainPane);

        List<String> workModes = new ArrayList<>();
        if(!extremeTranslationMode){
            reverseTranslationMap = getReverseWorkmodeTranslationMap(WorkModeTranslationMap);

            choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.NONE.name()));
            choiceGeneratorType.getItems().clear();
            generatorImage.setImage(new Image(Controller.class.getResourceAsStream("/workmodes/NONE.png")));

            for (WorkMode mode : WorkMode.values()) {
                workModes.add(WorkModeTranslationMap.get(mode.name()));
            }
            choiceGeneratorType.getItems().addAll(workModes);
        }else {
            for (WorkMode mode : WorkMode.values()) {
                workModes.add(mode.name());
            }
            choiceGeneratorType.getItems().addAll(workModes);
            sendToConsole("Warning! Extreme translation mode enabled!");
            sendToConsole("No translation files are found. Translations will be messed up!");
            sendToConsole("Try to reload app and add translations manually");
        }
        choiceGeneratorType.setOnAction(this::onChoice);
    }

    private String getTranslationFromConfig(String key){
        return Config.getTranslation(key, langName);
    }

    private void iterateForTabPane(TabPane pane){
        for(Tab tab : pane.getTabs()){
            tab.setText(getTranslationFromConfig(tab.getId()));
            Node node = tab.getContent();
            if(node instanceof AnchorPane anchorPane){
                iterateForAnchorPane(anchorPane);
            }
            if(node instanceof TabPane tabPane){
                iterateForTabPane(tabPane);
            }

            String elementText = getTranslationFromConfig(node.getId());
            String elementPrompt = getTranslationFromConfig(node.getId()+"Prompt");

            if(node instanceof Text text && elementText != null){
                text.setText(elementText);
                continue;
            }

            if(node instanceof Button bt && elementText != null){
                bt.setText(elementText);
                continue;
            }

            if(node instanceof CheckBox box && elementText != null){
                box.setText(elementText);
                if(box.getTooltip() != null){
                    box.getTooltip().setText(getTranslationFromConfig(box.getTooltip().getId()));
                }
                continue;
            }

            if(node instanceof TextField field){
                if(elementText != null){
                    field.setText(getTranslationFromConfig(field.getId()));
                }
                if(elementPrompt != null){
                    field.setPromptText(getTranslationFromConfig(field.getId()+"Prompt"));
                }
                if(field.getTooltip() != null){
                    field.getTooltip().setText(getTranslationFromConfig(field.getTooltip().getId()));
                }
            }
        }
    }

    private void iterateForAnchorPane(AnchorPane pane){
        for(Node node : pane.getChildren()){
            if(node instanceof AnchorPane anchorPane){
                iterateForAnchorPane(anchorPane);
            }
            if(node instanceof TabPane tabPane){
                iterateForTabPane(tabPane);
            }

            String elementText = getTranslationFromConfig(node.getId());
            String elementPrompt = getTranslationFromConfig(node.getId()+"Prompt");

            if(node instanceof TabPane tabPane){
                for(Tab tab : tabPane.getTabs()){
                    if (elementText != null) {
                        tab.setText(elementText);
                    }
                }
                continue;
            }

            if(node instanceof Text text && elementText != null){
                text.setText(elementText);
                continue;
            }

            if(node instanceof Button bt && elementText != null){
                bt.setText(elementText);
                continue;
            }

            if(node instanceof CheckBox box && elementText != null){
                box.setText(elementText);
                if(box.getTooltip() != null){
                    box.getTooltip().setText(getTranslationFromConfig(box.getTooltip().getId()));
                }
                continue;
            }

            if(node instanceof TextField field){
                if(elementText != null){
                    field.setText(getTranslationFromConfig(field.getId()));
                }
                if(elementPrompt != null){
                    field.setPromptText(getTranslationFromConfig(field.getId()+"Prompt"));
                }
                if(field.getTooltip() != null){
                    field.getTooltip().setText(getTranslationFromConfig(field.getTooltip().getId()));
                }
            }
        }
    }

    public void clearLangFolder(ActionEvent actionEvent) {
        if(clearLangFolder.isSelected()){
            if(new File(currentWorkingDir.toAbsolutePath() + "\\languages").listFiles() != null){
                for(File file : new File(currentWorkingDir.toAbsolutePath() + "\\languages").listFiles()){
                    file.delete();
                }
            }
            sendToConsole("Lang folder cleared successfully");
            sendToConsole("You need to restart this app to lang files reappear!");
        }else {
            sendToConsole("You need to select checkbox first!");
        }
    }

    private boolean checkIfPathFilled() {
        boolean b = true;
        List<GridPane> pathPanes = Arrays.asList(resourceGrid, javaGrid, textureGrid);

        for(GridPane gridPane : pathPanes){
            for (Node node : gridPane.getChildren()) {
                if (node instanceof TextField text) {
                    if (text.getText().length() == 0) {
                        b = false;
                        text.setStyle("-fx-background-color:ffbebe");
                    } else if (text.getStyle().equals("-fx-background-color:ffbebe")) {
                        text.setStyle("");
                    }
                }
                if (!b) {
                    sendToConsole("Fill path fields first!");
                }
            }
        }
        return b;
    }

    public void ItemAndBlockTabChosen(Event event) {
        if(event.getSource() instanceof Tab tab && tab == blockAndItemTab && tab.isSelected() && isEnabled){
            consolePane.setVisible(true);
            dndAnchorPane.setVisible(true);
            mainFieldsPane.setVisible(true);
            buttonPane.setVisible(true);
            consolePane.setLayoutY(192);
            dndAnchorPane.setLayoutX(473);
        }
    }

    public void geckoTabChosen(Event event) {
        if(event.getSource() instanceof Tab tab && tab == entityTab){
            if(tab.isSelected()){
                mainFieldsPane.setVisible(true);
                dndAnchorPane.setVisible(true);
                consolePane.setVisible(true);
                buttonPane.setVisible(true);
                dndAnchorPane.setLayoutX(557);
                consolePane.setLayoutY(24);
                previousMode = WorkMode.valueOf(reverseTranslationMap.get(choiceGeneratorType.getValue()));
                choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_ENTITY.name()));
                dndAnchorPane.setLayoutY(235);
            }else {
                choiceGeneratorType.setValue(WorkModeTranslationMap.get(previousMode.name()));
                dndAnchorPane.setLayoutY(473);
            }
        }
    }

    public void settingsTabChosen(Event event) {
        if(event.getSource() instanceof Tab tab && tab == settingsTab && tab.isSelected()){
            consolePane.setVisible(true);
            mainFieldsPane.setVisible(false);
            dndAnchorPane.setVisible(false);
            buttonPane.setVisible(false);
            consolePane.setLayoutY(24);
        }
    }

    public void onDirectionsTabChosen(Event event) {
        if(event.getSource() instanceof Tab tab && tab == directionsTab){
            if(tab.isSelected()){
                mainFieldsPane.setVisible(false);
                dndAnchorPane.setVisible(false);
                buttonPane.setVisible(false);
                consolePane.setLayoutY(24);
                addAllToPathGrid();
            }else {
                allPathesPane.getChildren().clear();
                onChoice(event);
            }
        }
    }

    public void tutorialsTabChosen(Event event) {
        if(event.getSource() instanceof Tab tab && tab == tutorialsTab && tab.isSelected()){
            mainFieldsPane.setVisible(false);
            dndAnchorPane.setVisible(false);
            buttonPane.setVisible(false);
            consolePane.setVisible(false);
        }
    }

    public void onYoutubersTabChosen(Event event) {
        if(event.getSource() instanceof Tab tab && tab == youtubersTab && tab.isSelected()){
            mainFieldsPane.setVisible(false);
            dndAnchorPane.setVisible(false);
            buttonPane.setVisible(false);
            consolePane.setVisible(false);
        }
    }

    public void onInnerColorChanged(ActionEvent actionEvent) {setColor(innerImageView, innerColor.getValue());}

    public void onOuterColorChanged(ActionEvent actionEvent) {setColor(outerImageView, outerColor.getValue());}

    private void setColor(ImageView imageView, Color color){
        ColorAdjust monochrome = new ColorAdjust();
        monochrome.setSaturation(-1.0);

        Blend blush = new Blend(
                BlendMode.MULTIPLY,
                monochrome,
                new ColorInput(
                        0,
                        0,
                        imageView.getImage().getWidth(),
                        imageView.getImage().getHeight(),
                        color
                )
        );
        imageView.effectProperty().setValue(blush);

        imageView.setCache(true);
        imageView.setCacheHint(CacheHint.SPEED);
    }

    private String getOrCreateDefaultFromPrompt(TextField textField){
        return textField.getText().length() > 0 ? textField.getText() : textField.getPromptText();
    }

    public static boolean isNotificationDisabled(ConsoleNotification notification){
        switch (notification){
            case STARTED -> {
                if(!Controller.staticStartupNotification.isSelected()) return true;
            }
            case SIZE_UNSELECTED -> {
                if(!Controller.staticIncorrectEntitySizeNotification.isSelected()) return true;
            }
            case FAILED_TO_LOAD_PATH -> {
                if(!Controller.staticFailedToLoadPathNotification.isSelected()) return true;
            }
            case CREATED_NEW_PATH -> {
                if(!Controller.staticNewFolderNotification.isSelected()) return true;
            }
            case CREATED_NEW_LANG_FILE -> {
                if(!Controller.staticNewLangFileNotification.isSelected()) return true;
            }
            case SAVED_TO_CONFIG -> {
                if(!Controller.staticSavedToConfigNotification.isSelected()) return true;
            }
        }
        return false;
    }

    public static void saveConfigs() throws IOException {
        Config.saveInConfig("newLangFileNotification", String.valueOf(staticNewLangFileNotification.isSelected()));
        Config.saveInConfig("entitySizeUnselectedNotification", String.valueOf(staticIncorrectEntitySizeNotification.isSelected()));
        Config.saveInConfig("newFolderNotification", String.valueOf(staticNewFolderNotification.isSelected()));
        Config.saveInConfig("startupNotification", String.valueOf(staticStartupNotification.isSelected()));
        Config.saveInConfig("failedToLoadPathNotification", String.valueOf(staticFailedToLoadPathNotification.isSelected()));

        Config.saveInConfig("programVersion", ((ProgramVersion)staticProgramVersionChooseBox.getValue()).name());
    }

    private void loadNotifications(){
        try {
            staticNewLangFileNotification.setSelected(Boolean.parseBoolean(Config.getOrCreateDefault("newLangFileNotification", "true")));
            staticIncorrectEntitySizeNotification.setSelected(Boolean.parseBoolean(Config.getOrCreateDefault("entitySizeUnselectedNotification", "true")));
            staticNewFolderNotification.setSelected(Boolean.parseBoolean(Config.getOrCreateDefault("newFolderNotification", "true")));
            staticStartupNotification.setSelected(Boolean.parseBoolean(Config.getOrCreateDefault("startupNotification", "true")));
            staticFailedToLoadPathNotification.setSelected(Boolean.parseBoolean(Config.getOrCreateDefault("failedToLoadPathNotification", "true")));
        }catch (Exception e){
            sendToConsole("Failed to load notification data!");
        }
    }

    public void onYoutuberClicked(MouseEvent event) {
        Text youtuberName = (Text) event.getSource();
        if(youtuberName == youtuberCy4Text){
            openWebpage(URI.create("https://www.youtube.com/@cy4/featured"));
        }
        if(youtuberName == youtuberTurtyWurtyText){
            openWebpage(URI.create("https://www.youtube.com/@TurtyWurty/featured"));
        }
        if(youtuberName == youtuberErnieBernieText){
            openWebpage(URI.create("https://www.youtube.com/@ErnieBernie10/featured"));
        }
        if(youtuberName == youtuberKaupenjoeText){
            openWebpage(URI.create("https://www.youtube.com/@ModdingByKaupenjoe"));
        }
    }

    private static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    private static boolean openWebpage(URL url) {
        try {
            return openWebpage(url.toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void checkAllClasses(ActionEvent actionEvent) {
        CheckAllClasses check = new CheckAllClasses();
        Pair<Boolean, String> pair = check.check(entityClass.getText(), clientModClass.getText(), regAttributesClass.getText(), modBlockEntitiesClass.getText());
        if(!pair.getKey()){
            sendToConsole("Class "+pair.getValue()+" lacks of \"//For replace by auto-generation\"");
            sendToConsole("If it's RegisterEntity.class it could lack \"//Initialize new entity\" in \"public void onInitialize() {}\"");
        }else {
            sendToConsole("All classes set properly!");
        }
    }

    public void closeWelcomeScreen(MouseEvent event) throws IOException {
        Config.saveInConfig("welcome", String.valueOf(!showWelcomeScreenCheckBox.isSelected()));
        welcomeScreenPane.setVisible(false);
    }

    public void showWelcomeScreen(ActionEvent actionEvent) {
        welcomeScreenPane.setVisible(true);
    }

    public void geckoEntityCheckboxChoose(ActionEvent actionEvent) {
        CheckBox box = (CheckBox) actionEvent.getSource();
        if(box.isSelected()){
            geckoBlockCheckbox.setSelected(false);
            geckoItemCheckbox.setSelected(false);
            choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_ENTITY.name()));
        }else {
            geckoBlockCheckbox.setSelected(true);
            choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_BLOCK.name()));
        }
    }

    public void geckoBlockCheckboxChoose(ActionEvent actionEvent) {
        CheckBox box = (CheckBox) actionEvent.getSource();
        if(box.isSelected()){
            geckoEntityCheckbox.setSelected(false);
            geckoItemCheckbox.setSelected(false);
            choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_BLOCK.name()));
        }else {
            geckoEntityCheckbox.setSelected(true);
            choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_ENTITY.name()));
        }
    }


    public void geckoItemCheckboxChoose(ActionEvent actionEvent) {
        if(programVersionChooseBox.getValue() == ProgramVersion.V1_19_2_GECKO_3){
            sendToConsole("Gecko item creation is not supported in program version 1.19.2! Change version to 1.19.3!");
            ((CheckBox) actionEvent.getSource()).setSelected(false);
        }else {
            CheckBox box = (CheckBox) actionEvent.getSource();
            if (box.isSelected()) {
                geckoEntityCheckbox.setSelected(false);
                geckoBlockCheckbox.setSelected(false);
                choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_ITEM.name()));
            }else {
                geckoEntityCheckbox.setSelected(true);
                choiceGeneratorType.setValue(WorkModeTranslationMap.get(WorkMode.GECKO_ENTITY.name()));
            }
        }
    }
}