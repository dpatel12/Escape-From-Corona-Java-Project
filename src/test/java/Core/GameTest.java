package Core;

import Characters.Enemy;
import javafx.application.Application;
import javafx.scene.input.KeyCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class GameTest {
    Game game = new Game();

    @BeforeEach
    public void initGame() {
        Game.setScore(0);
        Game.setTime(0);
        Game.setTicksElapsed(0);
        Game.setTimeOfInput(0);
        Game.setCurrentStage("first");
        Game.setWinStatus("");
        Game.setPaused(false);
        Game.setGameOver(false);
        Game.getEnemyArrayList().clear();
    }

    boolean launched = false; // test condition for javafx

    @Test
    public void testLaunch() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                try {
                    Application.launch(Game.class); // Run JavaFX application.
                    launched = true;
                } catch(Throwable t) {
                    if(t.getCause() != null && t.getCause().getClass().equals(InterruptedException.class)) {
                        launched = true;
                        return;
                    }
                }
            }
        };
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(3000);  // wait 3 seconds before closing
        } catch(InterruptedException ex) {
            // do nothing
        }

        thread.interrupt();
        try {
            thread.join(1); // wait for thread to die
        } catch(InterruptedException ex) {
            // do nothing
        }
        assertTrue(launched, "should be true if application launched");
    }

    @Test
    public void testStartGame() {
        game.startGame();
        assertEquals(0, game.getScore(), "should be 0");
        assertEquals(0, game.getTime(), "should be 0");
    }

    @Test
    public void testEndGameWinFirst() {
        Game.endGame(true);
        assertEquals("second", Game.getCurrentStage(), "should be set to second");
        assertEquals("", Game.getWinStatus(), "should not have been set");
        assertFalse(Game.getPaused(), "should be false");
    }

    @Test
    public void testEndGameWinSecond() {
        Game.setCurrentStage("second");
        Game.endGame(true);
        assertEquals("third", Game.getCurrentStage(), "should be set to third");
        assertEquals("", Game.getWinStatus(), "should not have been set");
        assertFalse(Game.getPaused(), "should be false");
    }

    @Test
    public void testEndGameWinThird() {
        Game.setCurrentStage("third");
        Game.endGame(true);
        assertEquals("win", Game.getCurrentStage(), "should be set to win");
        assertEquals("You won!", Game.getWinStatus(), "should be set to You won!");
        assertTrue(Game.getPaused(), "should be true");
    }

    @Test
    public void testEndGameLose() {
        Game.endGame(false);
        assertEquals("lose", Game.getCurrentStage(), "should be set to lose");
        assertEquals("You lost. :(", Game.getWinStatus(), "should be set to You lost. :(");
        assertTrue(Game.getPaused(), "should be true");
    }

    @Test
    public void testRestartGame() {
        Game.setScore(1);
        Game.setTime(2);
        Game.setTicksElapsed(100);
        Game.setTimeOfInput(4);
        Game.setWinStatus("win");
        Game.setCurrentStage("second");
        Game.setPaused(true);
        Game.setGameOver(true);

        game.restartGame();

        assertEquals(0, Game.getScoreStatic(), "score should be 0");
        assertEquals(0, Game.getTime(), "time should be 0");
        assertTrue(Game.getTicksElapsed() < 100, "ticksElapsed should have been reset");
        assertEquals(0, Game.getTimeOfInput(), "timeOfInput should be 0");
        assertEquals("", Game.getWinStatus(), "winStatus should be empty");
        assertEquals("first", Game.getCurrentStage(), "currentStage should be first");
        assertFalse(Game.getPaused(), "paused should be false");
        assertFalse(Game.getGameOver(), "gameOver should be false");
    }

    @Test
    public void testUpdateScore() {
        Game.updateScore(76);
        assertEquals(76, Game.getScoreStatic(), "0 + 76 should be 76");
        Game.updateScore(24);
        assertEquals(100, Game.getScoreStatic(), "76 + 24 should be 100");
        Game.updateScore(-34);
        assertEquals(66, Game.getScoreStatic(), "100 + (-34) should be 66");
    }

    @Test
    public void testGenerateEnemies() {
        Game.generateEnemies();
        assertEquals(2, Game.getEnemyArrayList().size(), "should contain 2 objects");
        for (Enemy e: Game.getEnemyArrayList()) {
            assertNotNull(e, "should not be null");
        }
    }

    @Test
    public void testGenerateEnemies2() {
        Game.generateEnemies2();
        assertEquals(3, Game.getEnemyArrayList().size(), "should contain 3 objects");
        for (Enemy e: Game.getEnemyArrayList()) {
            assertNotNull(e, "should not be null");
        }
    }

    @Test
    public void testGenerateEnemies3() {
        Game.generateEnemies3();
        assertEquals(3, Game.getEnemyArrayList().size(), "should contain 3 objects");
        for (Enemy e: Game.getEnemyArrayList()) {
            assertNotNull(e, "should not be null");
        }
    }

    @Test
    public void testPauseGame() {
        Game.pauseGame(KeyCode.UP);
        assertFalse(Game.getPaused(), "should not have paused game");
        Game.pauseGame(KeyCode.ESCAPE);
        assertTrue(Game.getPaused(), "escape key should pause the game");
    }

    @Test
    public void testUnpauseGame() {
        Game.setPaused(true);
        Game.pauseGame(KeyCode.UP);
        assertTrue(Game.getPaused(), "should not have unpaused game");
        Game.pauseGame(KeyCode.ESCAPE);
        assertFalse(Game.getPaused(), "escape key should unpause the game");
    }
}
