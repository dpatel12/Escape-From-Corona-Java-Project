package Core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AssetLoadImageTest {
    AssetLoad assetLoad = new AssetLoad();

    @BeforeEach
    public void reset() {
        assetLoad.reloadAssets();
    }

    @Test
    public void notNullValidMCTest() {
        assertNotNull(assetLoad.getSpriteImage());
    }

    @Test
    public void notNullValidEnemyTest() {
        assertNotNull(assetLoad.getEnemyImage());
    }

    @Test
    public void notNullValidGroundTest() {
        assertNotNull(assetLoad.getGroundImage());
    }

    @Test
    public void notNullValidWallTest() {
        assertNotNull(assetLoad.getWallImage());
    }

    @Test
    public void notNullValidCheckpointTest() {
        assertNotNull(assetLoad.getCheckpointImage());
    }

    @Test
    public void notNullValidPunishmentTest() {
        assertNotNull(assetLoad.getPunishImage());
    }

    @Test
    public void notNullValidExitTest() {
        assertNotNull(assetLoad.getExitImage());
    }

    @Test
    public void notNullValidEntryTest() {
        assertNotNull(assetLoad.getEntryImage());
    }

    @Test
    public void notNullValidBonusTest() {
        assertNotNull(assetLoad.getBonusImage());
    }

    @Test
    public void notNullValidMainMenuTest(){
        assertNotNull(assetLoad.getMainMenuImage());
    }

    @Test
    public void notNullValidGameOverLoseTest(){
        assertNotNull(assetLoad.getGameOverLoseImage());
    }

    @Test
    public void notNullValidGameOverWinTest(){
        assertNotNull(assetLoad.getGameOverWinImage());
    }

    @Test
    public void notNullValidPauseScreenTest(){
        assertNotNull(assetLoad.getPauseScreenImage());
    }

    @Test
    public void nullValidMCTest() {
        assetLoad.loadSpriteImage(null);
        assertNull(assetLoad.getSpriteImage());
    }

    @Test
    public void nullValidEnemyTest() {
        assetLoad.loadEnemyImage(null);
        assertNull(assetLoad.getEnemyImage());
    }

    @Test
    public void nullValidGroundTest() {
        assetLoad.loadGroundImage(null);
        assertNull(assetLoad.getGroundImage());
    }

    @Test
    public void nullValidWallTest() {
        assetLoad.loadWallImage(null);
        assertNull(assetLoad.getWallImage());
    }

    @Test
    public void nullValidCheckpointTest() {
        assetLoad.loadCheckpointImage(null);
        assertNull(assetLoad.getCheckpointImage());
    }

    @Test
    public void nullValidPunishmentTest() {
        assetLoad.loadPunishmentImage(null);
        assertNull(assetLoad.getPunishImage());
    }

    @Test
    public void nullValidExitTest() {
        assetLoad.loadExitImage(null);
        assertNull(assetLoad.getExitImage());
    }

    @Test
    public void nullValidEntryTest() {
        assetLoad.loadEntranceImage(null);
        assertNull(assetLoad.getEntryImage());
    }

    @Test
    public void nullValidBonusTest() {
        assetLoad.loadBonusImage(null);
        assertNull(assetLoad.getBonusImage());
    }

    @Test
    public void nullValidMainMenuTest() {
        assetLoad.loadMainMenuImage(null);
        assertNull(assetLoad.getMainMenuImage());
    }

    @Test
    public void nullValidGameOverLoseTest() {
        assetLoad.loadGameOverLoseImage(null);
        assertNull(assetLoad.getGameOverLoseImage());
    }

    @Test
    public void nullValidGameOverWinTest() {
        assetLoad.loadGameOverWinImage(null);
        assertNull(assetLoad.getGameOverWinImage());
    }

    @Test
    public void nullValidPauseScreenTest() {
        assetLoad.loadPauseScreenImage(null);
        assertNull(assetLoad.getPauseScreenImage());
    }
}
