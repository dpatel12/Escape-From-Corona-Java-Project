package Core;
import BoardDesign.*;
import Characters.*;
import TileAction.*;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.BarChart;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;

import javafx.scene.*;

import javafx.scene.layout.*;
import javafx.scene.input.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.scene.text.*;
import javafx.scene.image.*;

import java.io.*;
import java.lang.*;


import javafx.util.Duration;
//import org.graalvm.compiler.nodeinfo.StructuralInput;

import java.util.ArrayList;
import java.util.Locale;

import javafx.scene.Node.*;
import javafx.scene.control.Button;

import javax.sound.sampled.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Game class implements the JavaFX Application class.
 * This class handles game rendering as well as game data
 * including the player score and time elapsed. The game
 * is started and ended from this class. Input listening
 * is also done from this class using EventHandler.
 *
 * @author Danyaal
 * @author Peter
 * @author Brendan
 * @author Stephen
 * @version 1.0
 * @since 1.0
 */
public class Game extends Application{
    private static int score;
    private static int time;

    private static TimerTask gameTicksTask;
    private static Timer gameTicks;
    private static int ticksElapsed = 0; // a tick is 0.25 seconds, enemies move per 2 ticks
    private static int timeOfInput = 0;
    private static boolean paused = false;

    private int xTileSize = 96;
    private int yTileSize = 96;
    private boolean onFullSecondNextRound = false;

    private static String winStatus = "";

    private static MainCharacter mainCharacter = MainCharacter.getMainCharacter(0, 0);
    private static ArrayList<Enemy> enemyArrayList = new ArrayList<>();

    private GameMenu gameMenu;

    private static String currentStage = "first"; // can be "first", "second", "third", "win", "lose"
    private static Board boardGame;

    private static boolean gameOver = false;
    private static AssetLoad assetLoad;

    private Clip clip;



    // no constructor needed since this will contain the main for now

    // launch automatically calls start

    private static int[][] lvl1 = new int[][]{
        {1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
        {1, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 0, 1},
        {1, 0, 1, 3, 1, 0, 1, 1, 0, 0, 1, 1, 0, 1, 0, 1},
        {1, 2, 0, 0, 0, 2, 0, 0, 0, 0, 1, 1, 0, 1, 0, 1},
        {1, 1, 1, 0, 1, 0, 1, 1, 0, 3, 0, 3, 0, 0, 0, 1},
        {1, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 1},
        {1, 0, 1, 1, 1, 0, 1, 1, 3, 0, 0, 3, 0, 1, 0, 1},
        {1, 3, 0, 0, 1, 0, 1, 2, 0, 0, 1, 1, 0, 0, 0, 1},
        {1, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 2, 0, 1, 0, 1},
        {1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 1}
    };
    private static int[][] lvl2 = new int[][]{
            {1, 5, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 0, 0, 0, 0, 0, 0, 1, 3, 0, 0, 0, 0, 0, 2, 1},
            {1, 0, 1, 3, 1, 0, 1, 1, 0, 0, 2, 0, 0, 1, 0, 1},
            {1, 2, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 1, 1, 0, 1, 0, 1, 1, 0, 3, 0, 3, 0, 0, 0, 1},
            {1, 0, 0, 0, 2, 0, 0, 2, 0, 0, 0, 0, 0, 1, 0, 1},
            {1, 0, 1, 1, 1, 0, 1, 1, 3, 0, 0, 3, 0, 1, 0, 1},
            {1, 3, 0, 0, 1, 0, 1, 2, 0, 0, 1, 1, 0, 0, 0, 1},
            {1, 0, 1, 0, 0, 0, 3, 0, 0, 0, 1, 2, 0, 1, 0, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 6, 1, 1, 1, 1, 1, 1, 1}
    };
    private static int[][] lvl3 = new int[][]{
            {1, 5, 6, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 1},
            {1, 0, 1, 3, 1, 1, 3, 0, 0, 3, 1, 1, 3, 1, 0, 1},
            {1, 0, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 2, 2, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 0, 0, 1, 0, 0, 2, 2, 0, 0, 1, 0, 0, 0, 1},
            {1, 0, 3, 0, 0, 0, 3, 0, 0, 3, 0, 0, 0, 3, 0, 1},
            {1, 0, 1, 3, 1, 1, 3, 0, 0, 3, 1, 1, 3, 1, 0, 1},
            {1, 2, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 2, 1},
            {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1}
    };

    /**
     * Creates the first level of the game
     */
    private static Board firstStage() {
        enemyArrayList.clear();
        Board temp = createBoard(lvl1);
        Game.generateEnemies();
        return temp;
    }

    /**
     * Creates the second level of the game
     * Ensures that all enemies that were in the first level are cleared
     */
    private static Board secondStage() {
        enemyArrayList.clear(); // delete old enemies
        Board temp = createBoard(lvl2);
        Game.generateEnemies2();
        return temp;
    }

    /**
     * Creates the third and final level of the game
     */
    private static Board thirdStage() {
        enemyArrayList.clear(); // delete old enemies
        Board temp = createBoard(lvl3);
        Game.generateEnemies3();
        return temp;
    }

   /* private class gameSoundEffect {
        public  Clip playSoundeff(String musicLocation) {
            try {
                File musicPath = new File(musicLocation);

                if (musicPath.exists()) {
                    AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                    Clip clip = AudioSystem.getClip();
                    clip.open(audioInput);
                } else {
                    System.out.println("Cant find file");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            return clip;
        }
    }*/

    public void start(Stage mainGame)throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        mainGame.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent t) {
                Platform.exit();
                System.exit(0);
            }
        });

        assetLoad = new AssetLoad();


        mainGame.setTitle("Escape From Corona: The Adventures of Dr. Honnie Benry");
        mainGame.getIcons().add(assetLoad.getCheckpointImage());

        //coronatime audio
        Clip coronat = assetLoad.getCoronaClip();
        /*File file = new File("src/main/resources/assets/coronatimelower.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip coronat = AudioSystem.getClip();
        coronat.open(audioStream);*/

        //menuhover audio
        Clip menuhover = assetLoad.getClickClip();
        /*File file2 = new File("src/main/resources/assets/click.wav");
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
        Clip menuhover = AudioSystem.getClip();
        menuhover.open(audioStream2);*/

        //swooshselect audio
        Clip swooshselect = assetLoad.getSwooshClip();
        /*File file3 = new File("src/main/resources/assets/swoosh.wav");
        AudioInputStream audioStream3 = AudioSystem.getAudioInputStream(file3);
        Clip swooshselect = AudioSystem.getClip();
        swooshselect.open(audioStream3);*/

        //gamover audio
        Clip gameOverAudio = assetLoad.getGameOverClip();
        /*File file4 = new File("src/main/resources/assets/gameover.wav");
        AudioInputStream audioStream4 = AudioSystem.getAudioInputStream(file4);
        Clip gameOverAudio = AudioSystem.getClip();
        gameOverAudio.open(audioStream4);*/

        //victory audio
        Clip victoryAudio = assetLoad.getVictoryClip();
        /*File file5 = new File("src/main/resources/assets/victory.wav");
        AudioInputStream audioStream5 = AudioSystem.getAudioInputStream(file5);
        Clip victoryAudio = AudioSystem.getClip();
        victoryAudio.open(audioStream5);*/

        //themesongaudio
        Clip homeDepotAudio = assetLoad.getThemeSongClip();
        /*File file6 = new File("src/main/resources/assets/themesonglow.wav");
        AudioInputStream audioStream6 = AudioSystem.getAudioInputStream(file6);
        Clip homeDepotAudio = AudioSystem.getClip();
        homeDepotAudio.open(audioStream6);*/
        homeDepotAudio.loop(15);
        homeDepotAudio.stop();

        boardGame = firstStage();
        int squaredBoard = 10;


        AnchorPane root = new AnchorPane();
        BorderPane positions = new BorderPane();

        Group rootGroup = new Group(root);
        positions.setCenter(rootGroup);
        positions.setAlignment(rootGroup, Pos.CENTER);

        //positions.setCenter(root);

        //mainGame.setFullScreen(true);

        Scene scene = new Scene(positions);
        scene.setRoot(positions);

        //Lose Gameover Scene
        Pane gameOverRoot = new Pane();
        /*gameOverRoot.setPrefSize(800,600);
        InputStream inputOverBackground;
        try {
            inputOverBackground = new FileInputStream("src/main/resources/assets/YouLostFinal.jpg");

        } catch(FileNotFoundException e) { inputOverBackground = null;}

        Image inputBackgroundOver = new Image(inputOverBackground);

        ImageView imgView2 = new ImageView(inputBackgroundOver);
        imgView2.setFitHeight(600);
        imgView2.setFitWidth(800);*/
        Image loseGameOverImageLoaded = assetLoad.getGameOverLoseImage();
        ImageView imgView2 = sceneImageBuilder(loseGameOverImageLoaded, gameOverRoot);

        GameOverMenu gameOverMenu = new GameOverMenu(mainGame, scene, score, time, coronat, menuhover, swooshselect, "lose", homeDepotAudio);

        gameOverRoot.getChildren().addAll(imgView2, gameOverMenu);
        Group gameRoot2 = new Group(gameOverRoot);
        BorderPane gameOverBorder = sceneBuilder(gameRoot2);
       /* BorderPane gameOverBorder = new BorderPane();
        gameOverBorder.setCenter(gameRoot2);
        gameOverBorder.setAlignment(gameRoot2, Pos.CENTER);*/
        Scene gameIsOver = new Scene(gameOverBorder);
        //Lose Gameover scene end

        //Win Gameover Scene
        Pane wgameOverRoot = new Pane();
        /*wgameOverRoot.setPrefSize(800,600);
        InputStream winputOverBackground;
        try {
            winputOverBackground = new FileInputStream("src/main/resources/assets/YouWonFinal.jpg");

        } catch(FileNotFoundException e) { winputOverBackground = null;}

        Image winputBackgroundOver = new Image(winputOverBackground);

        ImageView wimgView2 = new ImageView(winputBackgroundOver);
        wimgView2.setFitHeight(600);
        wimgView2.setFitWidth(800);*/
        Image winGameOverImageLoaded = assetLoad.getGameOverWinImage();
        ImageView wimgView2 = sceneImageBuilder(winGameOverImageLoaded, wgameOverRoot);

        GameOverMenu wgameOverMenu = new GameOverMenu(mainGame, scene, score, time, coronat, menuhover, swooshselect, "win", homeDepotAudio);

        wgameOverRoot.getChildren().addAll(wimgView2, wgameOverMenu);
        Group wgameRoot2 = new Group(wgameOverRoot);
        BorderPane wgameOverBorder = sceneBuilder(wgameRoot2);
       /* BorderPane wgameOverBorder = new BorderPane();
        wgameOverBorder.setCenter(wgameRoot2);
        wgameOverBorder.setAlignment(wgameRoot2, Pos.CENTER);*/
        Scene wgameIsOver = new Scene(wgameOverBorder);
        //Win Gameover scene end

        //INstructions scene
        Pane instructionsRoot = new Pane();
       /* instructionsRoot.setPrefSize(800,600);
        InputStream inputInstructionsBackground;
        try {
            inputInstructionsBackground = new FileInputStream("src/main/resources/assets/mainmenufinal.jpg");

        } catch(FileNotFoundException e) { inputInstructionsBackground = null;}

        Image inputBackgroundInstructions = new Image(inputInstructionsBackground);

        ImageView imgView3 = new ImageView(inputBackgroundInstructions);
        imgView3.setFitHeight(600);
        imgView3.setFitWidth(800);*/
        Image instructionsScreenImageLoaded = assetLoad.getMainMenuImage();
        ImageView imgView3 = sceneImageBuilder(instructionsScreenImageLoaded, instructionsRoot);

        InstructionsScreen instructionsMenu = new InstructionsScreen(mainGame, scene, coronat, menuhover, swooshselect, homeDepotAudio);

        instructionsRoot.getChildren().addAll(imgView3, instructionsMenu);
        Group instructionsGroup = new Group(instructionsRoot);
        BorderPane instructionsBorder = sceneBuilder(instructionsGroup);
       /* BorderPane instructionsBorder = new BorderPane();
        instructionsBorder.setCenter(instructionsGroup);
        instructionsBorder.setAlignment(instructionsGroup, Pos.CENTER);*/
        Scene instructionScene = new Scene(instructionsBorder);
        //Instuctions scene end

        //MenuStart Scene
        Pane paneRoot = new Pane();
        /*paneRoot.setPrefSize(800,600);
        InputStream inputBackground;

        try {
            inputBackground = new FileInputStream("src/main/resources/assets/mainmenufinal.jpg");

        } catch(FileNotFoundException e) { inputBackground = null;}

        Image inputBackgroundImage = new Image(inputBackground);

        ImageView imgView = new ImageView(inputBackgroundImage);
        imgView.setFitHeight(600);
        imgView.setFitWidth(800);*/
        Image mainGameScreenImageLoaded = assetLoad.getMainMenuImage();
        ImageView imgView1 = sceneImageBuilder(mainGameScreenImageLoaded, paneRoot);

        gameMenu = new GameMenu(mainGame, scene, wgameIsOver, instructionScene, coronat, menuhover, swooshselect, homeDepotAudio);

        paneRoot.getChildren().addAll(imgView1, gameMenu);
        Group menuRoot = new Group(paneRoot);
        BorderPane menuBorder = sceneBuilder(menuRoot);
        /*BorderPane menuBorder = new BorderPane();
        menuBorder.setCenter(menuRoot);
        menuBorder.setAlignment(menuRoot, Pos.CENTER);*/
        Scene menuStart = new Scene(menuBorder);
        //End of MenuStart

        //Pause Screen
        Pane pauseRoot = new Pane();
        /*pauseRoot.setPrefSize(800,600);
        InputStream pauseBackground;

        try {
            pauseBackground = new FileInputStream("src/main/resources/assets/pauseFinal.jpg");

        } catch(FileNotFoundException e) { pauseBackground = null;}

        Image pauseImage = new Image(pauseBackground);

        ImageView pauseView = new ImageView(pauseImage);
        pauseView.setFitHeight(600);
        pauseView.setFitWidth(800);
*/
        Image pauseScreenImageLoaded = assetLoad.getPauseScreenImage();
        ImageView pauseView = sceneImageBuilder(pauseScreenImageLoaded, pauseRoot);
        //InstructionsScreen pauseMenu = new InstructionsScreen(mainGame, scene);

        pauseRoot.getChildren().addAll(pauseView);
        Group rootPause = new Group(pauseRoot);
        BorderPane pauseBorder = sceneBuilder(rootPause);

        /*BorderPane pauseBorder = new BorderPane();
        pauseBorder.setCenter(rootPause);
        pauseBorder.setAlignment(rootPause, Pos.CENTER);*/
        Scene pauseScene = new Scene(pauseBorder);
        //End of PauseScreen

        Group g2 = new Group();
        //Scene mainmenu = new Scene(g2, 150, 100);

        Group g1 = new Group();

        scene.setRoot(positions);
        mainGame.setScene(menuStart);

        drawRectangles(root, boardGame, mainCharacter);

        Timeline everySecond = new Timeline(
                new KeyFrame(Duration.millis(500),
                        new EventHandler<ActionEvent>() {

                            @Override
                            public void handle(ActionEvent event) {
                                //int currentScore = getScore();
                                if(winStatus.equals("You lost. :(") && !(gameOver)) {
                                    GameOverMenu GameOverMenu2 = new GameOverMenu(mainGame, scene, score, time, coronat, menuhover, swooshselect, "lose", homeDepotAudio);
                                    Pane gameOverRoot2 = new Pane();
                                    gameOverRoot2.setPrefSize(800,600);
                                    gameOverRoot2.getChildren().addAll(imgView2, GameOverMenu2);
                                    BorderPane gameOverBorder2 = new BorderPane();
                                    Group gameOver2 = new Group(gameOverRoot2);

                                    gameOverBorder2.setCenter(gameOver2);
                                    gameOverBorder2.setAlignment(gameOver2, Pos.CENTER);

                                    Scene gameIsOver2 = new Scene(gameOverBorder2);
                                    mainGame.setScene(gameIsOver2);
                                    gameOverAudio.setMicrosecondPosition(0);
                                    gameOverAudio.start();
                                    homeDepotAudio.stop();
                                    gameOver = true;
                                }
                                else if(winStatus.equals("You won!") && !(gameOver)) {
                                    GameOverMenu WinGameOverMenu = new GameOverMenu(mainGame, scene, score, time, coronat, menuhover, swooshselect, "win", homeDepotAudio);
                                    Pane wingameOverRoot = new Pane();
                                    wingameOverRoot.setPrefSize(800, 600);
                                    wingameOverRoot.getChildren().addAll(wimgView2, WinGameOverMenu);
                                    BorderPane wingameOverBorder = new BorderPane();
                                    Group wingameOver = new Group(wingameOverRoot);

                                    wingameOverBorder.setCenter(wingameOver);
                                    wingameOverBorder.setAlignment(wingameOver, Pos.CENTER);

                                    Scene winGameIsOver = new Scene(wingameOverBorder);
                                    homeDepotAudio.stop();
                                    mainGame.setScene(winGameIsOver);
                                    victoryAudio.setMicrosecondPosition(0);
                                    victoryAudio.start();

                                    gameOver = true;
                                }
                                Integer getScoreInt = new Integer(getScore());
                                Integer getTimeInt = new Integer(time);
                                Integer getCheckpointsRemainingInt = new Integer(Checkpoint.getCheckpointsLeft());
                                HBox statistics = new HBox();
                                VBox firstChild = new VBox();
                                VBox secondChild = new VBox();
                                VBox thirdChild = new VBox();
                                VBox fourthChild = new VBox();
                                int numberOfChildren = 4;
                                Text scoreDisplay = new Text("Current Protection: " + getScoreInt.toString());
                                Text timeDisplay = new Text("Time Elapsed: " + getTimeInt.toString());
                                Text checkpointDisplay = new Text("Checkpoints Remaining: " + getCheckpointsRemainingInt.toString());
                                Text winDisplay = new Text("Win Status: " + winStatus);
                                firstChild.getChildren().add(scoreDisplay);
                                secondChild.getChildren().add(timeDisplay);
                                thirdChild.getChildren().add(checkpointDisplay);
                                fourthChild.getChildren().add(winDisplay);
                                statistics.setPrefWidth(mainGame.getWidth());
                                firstChild.setPrefWidth(mainGame.getWidth()/numberOfChildren);
                                secondChild.setPrefWidth(mainGame.getWidth()/numberOfChildren);
                                thirdChild.setPrefWidth(mainGame.getWidth()/numberOfChildren);
                                fourthChild.setPrefWidth(mainGame.getWidth()/numberOfChildren);
                                // maybe use escape to pause game or something?
                                if (!paused) {
                                    if (onFullSecondNextRound) {
                                        time = time + 1;
                                        onFullSecondNextRound = false;
                                    } else {
                                        onFullSecondNextRound = true;
                                    }
                                }

                                statistics.getChildren().add(firstChild);
                                statistics.getChildren().add(secondChild);
                                statistics.getChildren().add(thirdChild);
                                statistics.getChildren().add(fourthChild);

                                xTileSize = (int)(mainGame.getHeight()/boardGame.getDimY())-6-(int)Math.ceil(statistics.getHeight()/boardGame.getDimY());
                                yTileSize = (int)(mainGame.getHeight()/boardGame.getDimY())-6-(int)Math.ceil(statistics.getHeight()/boardGame.getDimY());
                                drawRectangles(root, boardGame, mainCharacter);

                                positions.setTop(statistics);
                            }
                        }));
        everySecond.setCycleCount(Timeline.INDEFINITE);
        everySecond.play();

        //game timer
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.ESCAPE) {
                    if (!paused) {
                        everySecond.pause();
                        //mainGame.setScene(pauseScene);
                        //OLD CODE THAT WORKS
//                        Label t2 = new Label("This is the main menu. Press ESCAPE to resume");
//                        Button b2 = new Button("Go to the maingame");
//                        t2.setTranslateY(15);

                        //OLD CODE THAT WORKS ENDING

                        //b2.setTranslateY(50);
                        //b2.setOnMouseClicked(f -> { positions.setCenter(rootGroup);});

                        //Old code that works part2
                        g2.getChildren().clear();
                        g2.getChildren().addAll(pauseView);
                        positions.setCenter(g2);
                        //stops audio
                        homeDepotAudio.stop();
                        //Old Code that works partending
                    } else {
                        everySecond.play();
                        //old code that works
                        positions.setCenter(rootGroup);
                        //old code that works ending
                       /* mainGame.setScene(scene);*/
                        homeDepotAudio.start();
                    }
                    pauseGame(e.getCode());

                } else if (!paused && ((ticksElapsed-timeOfInput) >= 1)) {
                    timeOfInput = ticksElapsed;
                    mainCharacter.keyPressed(e);
                    drawRectangles(root, boardGame, mainCharacter);
                }
            }
        });

        mainGame.show();
    }

    public ImageView sceneImageBuilder (Image image, Pane paneRoot1){
        paneRoot1.setPrefSize(800, 600);
        ImageView imgview = new ImageView(image);
        imgview.setFitWidth(800);
        imgview.setFitHeight(600);
        return imgview;
    }

    public BorderPane sceneBuilder (Group group1){
        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(group1);
        borderPane.setAlignment(group1, Pos.CENTER);
        return borderPane;
    }

    private class GameMenu extends Parent{
        //scene2 is passed for the gameendscreen DEBUG
        public GameMenu(Stage mainGame, Scene scene, Scene scene2, Scene scene3, Clip clip1, Clip clip2, Clip clip3, Clip clip4) {
            VBox menuOrig = new VBox(40);

            menuOrig.setTranslateX(250);
            menuOrig.setTranslateY(250);

            MenuButton resumeBtn = new MenuButton("START GAME", clip2, clip3, Color.SEAGREEN);

            resumeBtn.setOnMouseClicked(event -> {
                clip1.setMicrosecondPosition(0);
                clip1.start();
                mainGame.setScene(scene);
                //mp.play();
                startGame();
                clip4.loop(15);
                clip4.start();

            });
            MenuButton instructionsBtn = new MenuButton("INSTRUCTIONS", clip2, clip3, Color.SEAGREEN);
            instructionsBtn.setOnMouseClicked(event -> {
                clip2.stop();
                clip3.setMicrosecondPosition(0);
                clip3.start();
                mainGame.setScene(scene3);

            });

            MenuButton exitBtn = new MenuButton("EXIT", clip2, clip3, Color.SEAGREEN);
            exitBtn.setOnMouseClicked(event ->{
                clip2.stop();
                clip3.setMicrosecondPosition(0);
                clip3.start();
                System.exit(0);

            });

            /*MenuButton debugOverBtn = new MenuButton("DEBUG WONGAME", clip2, clip3, Color.SEAGREEN);
            debugOverBtn.setOnMouseClicked(event ->{
                clip2.stop();
                clip3.setMicrosecondPosition(0);
                clip3.start();
                mainGame.setScene(scene2);
            });
*/

            menuOrig.getChildren().addAll(resumeBtn, instructionsBtn, exitBtn);

            Rectangle background = new Rectangle(800,600);
            background.setFill(Color.GREY);
            background.setOpacity(0.4);

            getChildren().addAll(background, menuOrig);

        }
    }
    private class InstructionsScreen extends Parent{
        private Text text;
        //scene2 is passed for the gameendscreen DEBUG
        public InstructionsScreen(Stage mainGame, Scene scene, Clip clip1, Clip clip2, Clip clip3, Clip clip4) {
            VBox menuOrig = new VBox(40);

            menuOrig.setTranslateX(250);
            menuOrig.setTranslateY(100);

            MenuButton resumeBtn = new MenuButton("START GAME", clip2, clip3, Color.SEAGREEN);
            resumeBtn.setOnMouseClicked(event -> {
                clip1.setMicrosecondPosition(0);
                clip1.start();
                mainGame.setScene(scene);
                startGame();
                clip4.loop(15);
                clip4.start();

            });

            MenuButton exitBtn = new MenuButton("EXIT", clip2, clip3, Color.SEAGREEN);
            exitBtn.setOnMouseClicked(event ->{
                System.exit(0);
            });

            //Label label1= new Label("This is the instructions");
            //label1.setPrefSize(1000,250);
            text = new Text("Instructions: Play as Dr. Honnie Benry in her mission to Escape the 3 phases of Coronavirus: " +
                    "Your objective is to pick up all Vaccine checkpoints, and then proceed to the podium exit to advance " +
                    "to the next level. Move the character with arrow keys, and avoid tiles with Geese to avoid losing protection. " +
                    "Pick up Masks to improve your protection! If protection is negative, you die. Make sure to dodge CoronEnemies to keep from dying as well!");
            text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,22));
            text.setFill(Color.BLACK);
            text.setTextAlignment(TextAlignment.CENTER);
            text.setWrappingWidth(550);
            text.setTranslateX(-125);

            menuOrig.getChildren().addAll(resumeBtn, exitBtn, text);

            Rectangle background = new Rectangle(800,600);
            background.setFill(Color.GREY);
            background.setOpacity(0.4);

            getChildren().addAll(background, menuOrig);


        }
    }

    private class GameOverMenu extends Parent{
        public GameOverMenu(Stage mainGame, Scene scene, int endScore, int endTime, Clip clip1, Clip clip2, Clip clip3, String winorlose, Clip clip4) {
            Paint colour = Color.FIREBRICK;
            if (winorlose == "win"){
                colour = Color.MEDIUMPURPLE;
            }
            VBox menuOrig = new VBox(40);

            menuOrig.setTranslateX(250);
            menuOrig.setTranslateY(150);

            MenuButton resumeBtn = new MenuButton("RESTART", clip2, clip3, colour );
            resumeBtn.setOnMouseClicked(event -> {
                restartGame();
                clip1.setMicrosecondPosition(0);
                clip1.start();
                mainGame.setScene(scene);
                clip4.start();
                clip4.loop(15);

            });
            //int endScore = getScore();
            //System.out.println("score:");
            //System.out.println(endScore);

            MenuButton instructionsBtn = new MenuButton("PROTECTION: " + endScore, clip2, clip3, colour);
            MenuButton timeBtn = new MenuButton("TIME: " + endTime + "s", clip2, clip3, colour);

            MenuButton exitBtn = new MenuButton("EXIT", clip2, clip3, colour);
            exitBtn.setOnMouseClicked(event ->{
                clip3.setMicrosecondPosition(0);
                clip3.start();
                System.exit(0);
            });

            menuOrig.getChildren().addAll(resumeBtn, instructionsBtn, timeBtn, exitBtn);

            Rectangle background = new Rectangle(800,600);
            background.setFill(Color.GREY);
            background.setOpacity(0.4);

            getChildren().addAll(background, menuOrig);
        }
    }



    //MenuButton template for buttons
    private static class MenuButton extends StackPane{
        private Text text;

       /* String coronapath = "assets/coronatime.wav";

        public void createMusic(){
            gameSoundEffect musicObject = new gameSoundEffect();
            musicObject.playSoundeff(coronapath);
        }*/

        /*Media coronamedia = new Media(new File(coronapath).toURI().toString());
        MediaPlayer coronaPlayer = new MediaPlayer(coronamedia);*/
        public MenuButton(String name, Clip audio, Clip audio2, Paint paint){
            audio2.setMicrosecondPosition(0);
            text = new Text(name);
            text.setFont(Font.font("Comic Sans MS", FontWeight.BOLD,25));;
            //text.setFont(Font.font("Dubai", FontWeight.BOLD,20));
            //text.setFont(text.getFont().font(20));
            text.setFill(paint);


            Rectangle menuR = new Rectangle(250, 50);
            menuR.setOpacity(0.6);
            menuR.setFill(Color.BLACK);
            menuR.setEffect(new GaussianBlur(3.5));

            setAlignment(Pos.CENTER_LEFT);
            setRotate(-0.5);
            getChildren().addAll(menuR, text);

            setOnMouseEntered(event -> {
                menuR.setTranslateX(30);
                text.setTranslateX(30);
                menuR.setFill(paint);
                text.setFill(Color.BLACK);
                audio.setMicrosecondPosition(0);
                audio.start();
                //createMusic();
                /*coronaPlayer.setAutoPlay(true);*/
                //clickPlayer.play();
            });
            setOnMouseExited(event -> {
                menuR.setTranslateX(0);
                text.setTranslateX(0);
                menuR.setFill(Color.BLACK);
                text.setFill(paint);
                audio.stop();

                //clickPlayer.play();
            });

            DropShadow drop = new DropShadow(50, Color.WHITE);
            drop.setInput(new Glow());

            setOnMousePressed(event -> {
                setEffect(drop);
                audio.stop();
                audio2.setMicrosecondPosition(0);
                audio2.start();
                //createMusic();
            });
            setOnMouseReleased(event -> {
                audio.stop();
                setEffect(null);
                /*coronaPlayer.play();*/
            });


        }
    }

    /**
     * Generates enemies on the in a static manner.
     */
    public static void generateEnemies() { //GENERATING ENEMIES
        Enemy e1 = new Enemy(8, 8);
        Enemy e2 = new Enemy(3, 8);
        enemyArrayList.add(e1);
        enemyArrayList.add(e2);
    }

    public static void generateEnemies2() {
        Enemy e1 = new Enemy(8, 8);
        Enemy e2 = new Enemy(3, 8);
        Enemy e3 = new Enemy(10, 3);
        enemyArrayList.add(e1);
        enemyArrayList.add(e2);
        enemyArrayList.add(e3);
    }

    public static void generateEnemies3() {
        //Enemy e1 = new Enemy(4, 3);
        Enemy e2 = new Enemy(13, 3);
        Enemy e3 = new Enemy(4, 6);
        Enemy e4 = new Enemy(13, 6);
        //enemyArrayList.add(e1);
        enemyArrayList.add(e2);
        enemyArrayList.add(e3);
        enemyArrayList.add(e4);
    }

    /**
     * Calls method to update all Enemy positions.
     */
    public void moveEnemies() {
        for(Enemy e : enemyArrayList) {
            e.move(e.checkBestMovement(enemyArrayList));
            //e.printPos();
        }
    }

    /**
     * Called every tick. Handles game updates including
     * Bonus reward generation.
     */
    public static void updateGame() {
        Board.generateBonus();
    }

    /**
     * Draws the mainCharacter.
     *
     * @param root a JavaFX Pane
     * @param boardGame Board used in current game
     * @param mainCharacter the player controlled MainCharacter
     */
    void drawMainCharacter(AnchorPane root, Board boardGame, MainCharacter mainCharacter) {

        Rectangle rect = null;
        InputStream inputStream;
        Image image = assetLoad.getSpriteImage();

        int x = mainCharacter.getX();
        int y = mainCharacter.getY();
        int width = xTileSize;
        int height = yTileSize;
        rect = new Rectangle(width*x, height*y, width, height);
        if(image != null)
            rect.setFill(new ImagePattern(image));
        else
            rect.setFill(Color.BLACK);
        root.getChildren().add(rect);

    }

    void drawReward(AnchorPane root, Tile tile, int x, int y) {
        //Rectangle rect = null;
        int height = yTileSize;
        int width = xTileSize;
        Rectangle rect = new Rectangle(width*x, height*y, width, height);
        rect.toFront();
        if (tile.typeOfReward.equals("Checkpoint")) {
            rect.setFill(new ImagePattern(assetLoad.getCheckpointImage()));

        }
        else if (tile.typeOfReward.equals("Punishment")) {
            if(assetLoad.getPunishImage() != null) {
            rect.setFill(new ImagePattern(assetLoad.getPunishImage()));
            }
            else
                rect.setFill(Color.PINK);
        }
        else if (tile.typeOfReward.equals("Bonus")) {
            rect.setFill(new ImagePattern(assetLoad.getBonusImage()));
        }
//        rect.setFill(new ImagePattern(image));
        root.getChildren().add(rect);

    }

    void drawEntryExit(AnchorPane root, Image image, int x, int y) {
        int height = yTileSize;
        int width = xTileSize;
        Rectangle rect = new Rectangle(width*x, height*y, width, height);
        rect.toFront();
        rect.toFront();
        rect.setFill(new ImagePattern(image));
        root.getChildren().add(rect);
    }

    /**
     * Draws the enemies.
     *
     * @param root a JavaFX Pane
     * @param boardGame Board used in current game
     */
    void drawEnemies(AnchorPane root, Board boardGame) {
        Rectangle rect = null;
        Image image = assetLoad.getEnemyImage();

        int width = xTileSize;
        int height = yTileSize;
        for (int i = 0; i < enemyArrayList.size(); i++) {
            int x = enemyArrayList.get(i).getX();
            int y = enemyArrayList.get(i).getY();
            rect = new Rectangle(width*x, height*y, width, height);
            if(image != null)
                rect.setFill(new ImagePattern(image));
            else
                rect.setFill(Color.BLACK);
            root.getChildren().add(rect);
        }
    }

    /**
     * Draws the board Tiles.
     *
     * @param root a JavaFX Pane
     * @param boardGame Board used in current game
     * @param mainCharacter the player controlled MainCharacter
     */
    void drawRectangles(AnchorPane root, Board boardGame, MainCharacter mainCharacter) {
        root.getChildren().clear();
        int width = boardGame.getDimX();
        int height = boardGame.getDimY();
        int horizontal = xTileSize, vertical = yTileSize;
        Rectangle rect = null;
        Rectangle rectSupp = null;
        for(int i = 0; i < height; ++i)
        {//Iterate through columns
            for(int j = 0; j < width; ++j)
            {//Iterate through rows
//              Color choice = chooseColor(rectColors);
                //Method that chooses a color
                //Create a new rectangle(PosY,PosX,width,height)
                rect = new Rectangle(horizontal*j, vertical*i, horizontal, vertical);
                //temporary asset loading for textures; could eventually be done from one file and be more elegant

                Image imageTile = assetLoad.getGroundImage();
                Image imageWall = assetLoad.getWallImage();

                Tile currentTile = boardGame.getTile(i,j);
                String currentTileString = currentTile.getClass().getSimpleName();
                int currentTileInt = 0;
                if (currentTileString.equals("Tile")) {
                    currentTileInt = 0;
                } else if (currentTileString.equals("Wall")) {
                    currentTileInt = 1;
                }
                else if (currentTileString.equals("Entrance")) {
                    currentTileInt = 2;
                }
                else if (currentTileString.equals("Exit")) {
                    currentTileInt = 3;
                }
//                rect.setStrokeWidth(1);
                //rect.setStroke(Color.BLACK);
                rect.setFill(Color.WHITE);
                rect.toBack();

                switch(currentTileInt)
                {
                    case 0: // empty tile
                        rect.setFill(new ImagePattern(imageTile));
                        break;
                    case 1: // wall
                        rect.setFill(new ImagePattern(imageWall));
                        break;
                    case 2: // entrance
                        rect.setFill(new ImagePattern(imageTile));
                        break;
                    case 3: // exit
                        rect.setFill(new ImagePattern(imageTile));
                        break;
                }

                root.getChildren().add(rect);
                //Add Rectangle to board

                boolean tileHasReward = currentTile.getHasReward();
                if (tileHasReward) {
                    drawReward(root, currentTile, j, i);
                }
                switch(currentTileInt) {
                    case 2:
                        drawEntryExit(root, assetLoad.getEntryImage(), j, i);
                        break;
                    case 3:
                        drawEntryExit(root, assetLoad.getExitImage(), j, i);
                        break;
                }
            }
        }

        drawMainCharacter(root, boardGame, mainCharacter);
        drawEnemies(root, boardGame);
    }

    /**
     * Starts the gameTicks timer.
     */
    protected void startTimer() {
        gameTicksTask = new TimerTask() {
            int prevTicksElapsed = 0;
            @Override
            public void run() {
                if(!paused) {
                    ticksElapsed += 1;
                    if((ticksElapsed - prevTicksElapsed) >= 2) {
                        prevTicksElapsed = ticksElapsed;
                        moveEnemies();
                    }
                    updateGame();
                }
            }
        };
        gameTicks = new Timer();

        gameTicks.scheduleAtFixedRate(gameTicksTask, 20, 250);
    }

    /**
     * Initializes the starting variables score and time.
     * Also start the gameTicks timer.
     */
    public void startGame(){
        score = 0;
        time = 0;
        startTimer();
    }

    /**
     * Ends the current game.
     *
     * @param win true if player wins, else false
     */
    public static void endGame(boolean win){

        if (currentStage.equals("win") || currentStage.equals("lose")) {
            // do nothing
            gameTicks.cancel();
            paused = true;
        }
        else {
            if (win) {
                if (currentStage.equals("first")) {
                    currentStage = "second";
                    boardGame = secondStage();
                } else if (currentStage.equals("second")) {
                    currentStage = "third";
                    boardGame = thirdStage();
                } else if (currentStage.equals("third")) {
                    currentStage = "win";
                    gameTicks.cancel();
                    paused = true;
                    winStatus = "You won!";
                } else if (currentStage.equals("win")) {
                    // do nothing
                } else {
                    System.out.println("Not a valid current stage");
                }
            }
            else {
                currentStage = "lose";
                winStatus = "You lost. :(";
                gameTicks.cancel();
                paused = true;
            }
        }
    }

    /**
     * Resets values and restarts the game to first stage.
     */
    public void restartGame() {
        score = 0;
        time = 0;
        ticksElapsed = 0;
        timeOfInput = 0;
        winStatus = "";
        currentStage = "first";
        paused = false;
        gameOver = false;

        Checkpoint.setCheckpointsLeft(0);
        
        boardGame = firstStage();

        startTimer();
    }

    public static void pauseGame(KeyCode key) {
        if(key == KeyCode.ESCAPE) {
            if (winStatus == "") {
                paused = !paused;
            }
        }
    }

    /**
     * Getter for score.
     *
     * @return the current game score
     */
    public int getScore(){
        return score;
    }

    /**
     * Static getter for score.
     * @return the current game score
     */
    public static int getScoreStatic() {
        return score;
    }

    public static void setScore(int x) {
        score = x;
    }

    /**
     * Adds to the game score by specified amount.
     *
     * @param amount the value to add to the total score
     */
    public static void updateScore(int amount){
        score += amount;
        //System.out.println("Score:" + score);
        if(score < 0) {
            endGame(false);
        }
    }

    /**
     * Getter for time.
     *
     * @return the current game time
     */
    public static int getTime(){
        return time;
    }

    public static void setTime(int x) {
        time = x;
    }

    public static String getCurrentStage() {
        return currentStage;
    }

    public static void setCurrentStage(String stage) {
        currentStage = stage;
    }

    public static String getWinStatus() {
        return winStatus;
    }

    public static void setWinStatus(String status) {
        winStatus = status;
    }

    public static boolean getPaused() {
        return paused;
    }

    public static void setPaused(boolean pause) {
        paused = pause;
    }

    public static int getTicksElapsed() {
        return ticksElapsed;
    }

    public static void setTicksElapsed(int x) {
        ticksElapsed = x;
    }

    public static int getTimeOfInput() {
        return timeOfInput;
    }

    public static void setTimeOfInput(int x) {
        timeOfInput = x;
    }

    public static boolean getGameOver() {
        return gameOver;
    }

    public static void setGameOver(boolean x) {
        gameOver = x;
    }

    public static ArrayList<Enemy> getEnemyArrayList() {
        return enemyArrayList;
    }

    private static Board createBoard(int[][] lvl) {

        Board boardGame = new Board(lvl);
//        for (int i = 0; i < boardGame.getDimY(); i++) {
//            for (int j = 0; j < boardGame.getDimX(); j++) {
//                System.out.print(Board.getBoard()[i][j].getClass().getSimpleName() + Board.getBoard()[i][j].typeOfReward + " ");
//            }
//            System.out.println("");
//        }
        return boardGame;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
