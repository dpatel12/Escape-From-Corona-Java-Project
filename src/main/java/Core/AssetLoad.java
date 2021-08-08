package Core;

import javafx.scene.image.Image;

import javax.sound.sampled.*;
import java.io.*;
import java.net.URL;
import java.util.function.IntUnaryOperator;

public class AssetLoad {
    private InputStream spriteStream;
    private InputStream enemyStream;
    private InputStream groundStream;
    private InputStream wallStream;
    private InputStream checkpointStream;
    private InputStream punishStream;
    private InputStream exitStream;
    private InputStream entryStream;
    private InputStream bonusStream;
    private InputStream mainMenuStream;
    private InputStream gameOverLoseStream;
    private InputStream gameOverWinStream;
    private InputStream pauseScreenStream;

    private URL coronaFile;
    private URL clickFile;
    private URL swooshFile;
    private URL gameOverFile;
    private URL victoryFile;
    private URL themeSongFile;

    private Clip coronaClip = null;
    private Clip clickClip = null;
    private Clip swooshClip = null;
    private Clip gameOverClip = null;
    private Clip victoryClip = null;
    private Clip themeSongClip = null;


    private Image spriteImage = null;
    private Image enemyImage = null;
    private Image groundImage = null;
    private Image wallImage = null;
    private Image checkpointImage = null;
    private Image punishImage = null;
    private Image exitImage = null;
    private Image entryImage = null;
    private Image bonusImage = null;
    private Image mainMenuImage = null;
    private Image gameOverLoseImage = null;
    private Image gameOverWinImage = null;
    private Image pauseScreenImage = null;

    public AssetLoad() {
        reloadAssets();
        reloadAudioAssets();
    }

    public void reloadAssets() {
        loadSpriteImage("/assets/bonnie.png");
        loadEnemyImage("/assets/enemy.png");
        loadGroundImage("/assets/grass.png");
        loadWallImage("/assets/wall.png");
        loadCheckpointImage("/assets/chkpt.png");
        loadPunishmentImage("/assets/punish.png");
        loadExitImage("/assets/exit.png");
        loadEntranceImage("/assets/entry.png");
        loadBonusImage("/assets/bonusimage.png");
        loadMainMenuImage("/assets/mainmenufinal.jpg");
        loadGameOverLoseImage("/assets/YouLostFinal.jpg");
        loadGameOverWinImage("/assets/YouWonFinal.jpg");
        loadPauseScreenImage("/assets/pauseFinal.jpg");
    }

    public void reloadAudioAssets() {
        loadCoronaTimeAudio("/assets/coronatimelower.wav");
        loadClickAudio("/assets/click.wav");
        loadSwooshAudio("/assets/swoosh.wav");
        loadGameOverAudio("/assets/gameover.wav");
        loadVictoryAudio("/assets/victory.wav");
        loadThemeSongAudio("/assets/themesonglow.wav");

    }

    public Clip loadCoronaTimeAudio(String path){
        try {
            coronaFile = getClass().getResource(path);
            AudioInputStream coronaInput = AudioSystem.getAudioInputStream(coronaFile);
            coronaClip = AudioSystem.getClip();
            coronaClip.open(coronaInput);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e){
            coronaFile = null;
            coronaClip = null;
            return coronaClip;
        }
            return coronaClip;
    }
    public Clip loadClickAudio(String path){
        try {
            clickFile = getClass().getResource(path);
            AudioInputStream clickInput = AudioSystem.getAudioInputStream(clickFile);
            clickClip = AudioSystem.getClip();
            clickClip.open(clickInput);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e){
            clickFile = null;
            clickClip = null;
        }
            return clickClip;
    }

    public Clip loadSwooshAudio(String path){
        try {
            swooshFile = getClass().getResource(path);
            AudioInputStream swooshInput = AudioSystem.getAudioInputStream(swooshFile);
            swooshClip = AudioSystem.getClip();
            swooshClip.open(swooshInput);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e){
            swooshFile = null;
            swooshClip = null;
        }
        return swooshClip;
    }
    public Clip loadGameOverAudio(String path){
        try {
            gameOverFile = getClass().getResource(path);
            AudioInputStream gameOverInput = AudioSystem.getAudioInputStream(gameOverFile);
            gameOverClip = AudioSystem.getClip();
            gameOverClip.open(gameOverInput);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e){
            gameOverFile = null;
            gameOverClip = null;
        }
        return gameOverClip;
    }

    public Clip loadVictoryAudio(String path){
        try {
            victoryFile = getClass().getResource(path);
            AudioInputStream victoryInput = AudioSystem.getAudioInputStream(victoryFile);
            victoryClip = AudioSystem.getClip();
            victoryClip.open(victoryInput);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e){
            victoryFile = null;
            victoryClip = null;
        }
        return victoryClip;
    }
    public Clip loadThemeSongAudio(String path){
        try {
            themeSongFile = getClass().getResource(path);
            AudioInputStream themeSongInput = AudioSystem.getAudioInputStream(themeSongFile);
            themeSongClip = AudioSystem.getClip();
            themeSongClip.open(themeSongInput);
        }
        catch (UnsupportedAudioFileException | IOException | LineUnavailableException | NullPointerException e){
            themeSongFile = null;
            themeSongClip = null;
        }
        return themeSongClip;
    }



    public Image loadSpriteImage(String path) {
        try {
            spriteStream = getClass().getResourceAsStream(path);
            spriteImage = new Image(spriteStream);
        }
        catch (NullPointerException e) {
            spriteStream = null;
            spriteImage = null;
            return spriteImage;
        }
        return spriteImage;
    }

    public Image loadEnemyImage(String path) {
        try {
            enemyStream = getClass().getResourceAsStream(path);
            enemyImage = new Image(enemyStream);
        }
        catch (NullPointerException e) {
            enemyStream = null;
            enemyImage = null;
            return enemyImage;
        }
        return enemyImage;
    }

    public Image loadGroundImage(String path) {
        try {
            groundStream = getClass().getResourceAsStream(path);
            groundImage = new Image(groundStream);
        }
        catch (NullPointerException e) {
            groundStream = null;
            groundImage = null;
            return groundImage;
        }
        return groundImage;
    }

    public Image loadWallImage(String path) {
        try {
            wallStream = getClass().getResourceAsStream(path);
            wallImage = new Image(wallStream);
        }
        catch (NullPointerException e) {
            wallStream = null;
            wallImage = null;
            return wallImage;
        }
        return wallImage;
    }

    public Image loadCheckpointImage(String path) {
        try {
            checkpointStream = getClass().getResourceAsStream(path);
            checkpointImage = new Image(checkpointStream);
        }
        catch (NullPointerException e) {
            checkpointStream = null;
            checkpointImage = null;
            return checkpointImage;
        }
        return checkpointImage;
    }

    public Image loadPunishmentImage(String path) {
        try {
            punishStream = getClass().getResourceAsStream(path);
            punishImage = new Image(punishStream);
        }
        catch (NullPointerException e) {
            punishStream = null;
            punishImage = null;
            return punishImage;
        }
        return punishImage;
    }

    public Image loadExitImage(String path) {
        try {
            exitStream = getClass().getResourceAsStream(path);
            exitImage = new Image(exitStream);
        }
        catch (NullPointerException e) {
            exitStream = null;
            exitImage = null;
            return exitImage;
        }
        return exitImage;
    }

    public Image loadEntranceImage(String path) {
        try {
            entryStream = getClass().getResourceAsStream(path);
            entryImage = new Image(entryStream);
        }
        catch (NullPointerException e) {
            entryStream = null;
            entryImage = null;
            return entryImage;
        }
        return entryImage;
    }

    public Image loadBonusImage(String path) {
        try {
            bonusStream = getClass().getResourceAsStream(path);
            bonusImage = new Image(bonusStream);
        }
        catch (NullPointerException e) {
            bonusStream = null;
            bonusImage = null;
            return bonusImage;
        }
        return bonusImage;

    }
    public Image loadMainMenuImage(String path) {
        try {
            mainMenuStream = getClass().getResourceAsStream(path);
            mainMenuImage = new Image(mainMenuStream);
        }
        catch (NullPointerException e) {
            mainMenuStream = null;
            mainMenuImage = null;
            return mainMenuImage;
        }
        return mainMenuImage;
    }

    public Image loadGameOverLoseImage(String path) {
        try {
            gameOverLoseStream = getClass().getResourceAsStream(path);
            gameOverLoseImage = new Image(gameOverLoseStream);
        }
        catch (NullPointerException e) {
            gameOverLoseStream = null;
            gameOverLoseImage = null;
            return gameOverLoseImage;
        }
        return gameOverLoseImage;
    }

    public Image loadGameOverWinImage(String path) {
        try {
            gameOverWinStream = getClass().getResourceAsStream(path);
            gameOverWinImage = new Image(gameOverWinStream);
        }
        catch (NullPointerException e) {
            gameOverWinStream = null;
            gameOverWinImage = null;
            return gameOverWinImage;
        }
        return gameOverWinImage;
    }

    public Image loadPauseScreenImage(String path) {
        try {
            pauseScreenStream = getClass().getResourceAsStream(path);
            pauseScreenImage = new Image(pauseScreenStream);
        }
        catch (NullPointerException e) {
            pauseScreenStream = null;
            pauseScreenImage = null;
            return pauseScreenImage;
        }
        return pauseScreenImage;
    }


    public Image getSpriteImage() {
        return spriteImage;
    }

    public Image getEnemyImage() {
        return enemyImage;
    }

    public Image getGroundImage() {
        return groundImage;
    }

    public Image getWallImage() {
        return wallImage;
    }

    public Image getCheckpointImage() {
        return checkpointImage;
    }

    public Image getPunishImage() {
        return punishImage;
    }

    public Image getEntryImage() {
        return entryImage;
    }

    public Image getExitImage() {
        return exitImage;
    }

    public Image getBonusImage() {
        return bonusImage;
    }

    public Image getMainMenuImage()
    {
        return mainMenuImage;
    }

    public Image getGameOverLoseImage()
    {
        return gameOverLoseImage;
    }

    public Image getGameOverWinImage()
    {
        return gameOverWinImage;
    }

    public Image getPauseScreenImage()
    {
        return pauseScreenImage;
    }


    public Clip getCoronaClip() {
        return  coronaClip;
    }
    public Clip getClickClip() {
        return  clickClip;
    }
    public Clip getSwooshClip() {
        return  swooshClip;
    }
    public Clip getGameOverClip() {
        return  gameOverClip;
    }
    public Clip getVictoryClip() {
        return  victoryClip;
    }
    public Clip getThemeSongClip() {
        return  themeSongClip;
    }


}


