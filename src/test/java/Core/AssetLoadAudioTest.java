package Core;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.*;

public class AssetLoadAudioTest {
    AssetLoad assetLoad = new AssetLoad();

    @BeforeEach
    public void reset() {
        assetLoad.reloadAudioAssets();
    }

    @Test
    public void notNullValidCoronaTimeTest(){
        assertNotNull(assetLoad.getCoronaClip());
    }

    @Test
    public void notNullClickTest(){
        assertNotNull(assetLoad.getClickClip());
    }

    @Test
    public void notNullSwooshTest(){
        assertNotNull(assetLoad.getSwooshClip());
    }

    @Test
    public void notNullGameOverAudioTest(){
        assertNotNull(assetLoad.getGameOverClip());
    }

    @Test
    public void notNullVictoryTest(){
        assertNotNull(assetLoad.getVictoryClip());
    }

    @Test
    public void notNullThemeSongTest(){
        assertNotNull(assetLoad.getThemeSongClip());
    }

    @Test
    public void nullValidCoronaTest() {
        assetLoad.loadCoronaTimeAudio("");
        assertNull(assetLoad.getCoronaClip());
    }

    @Test
    public void nullValidClickTest() {
        assetLoad.loadClickAudio("");
        assertNull(assetLoad.getClickClip());
    }

    @Test
    public void nullValidSwooshTest() {
        assetLoad.loadSwooshAudio("");
        assertNull(assetLoad.getSwooshClip());
    }

    @Test
    public void nullValidGameOverAudioTest() {
        assetLoad.loadGameOverAudio("");
        assertNull(assetLoad.getGameOverClip());
    }

    @Test
    public void nullValidVictoryTest() {
        assetLoad.loadVictoryAudio("");
        assertNull(assetLoad.getVictoryClip());
    }

    @Test
    public void nullValidThemeSongTest() {
        assetLoad.loadThemeSongAudio("");
        assertNull(assetLoad.getThemeSongClip());
    }
}
