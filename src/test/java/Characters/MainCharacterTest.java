package Characters;

import BoardDesign.Exit;
import Core.Board;
import Core.Tile;
import TileAction.Checkpoint;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static javafx.scene.input.KeyEvent.KEY_PRESSED;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MainCharacterTest {
    MainCharacter mainCharacter = MainCharacter.getMainCharacter(0, 0);

    int[][] id = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    @Mock
    Board board;

    @BeforeEach
    public void initMainCharacter() {
        board.setBoard(id);
        mainCharacter.setPos(0, 0);
    }

    @Test
    public void testGetMainCharacter() {
        mainCharacter = MainCharacter.getMainCharacter(3, 7);
        assertEquals(0, mainCharacter.getX(), "x coordinate should still be 0");
        assertEquals(0, mainCharacter.getY(), "y coordinate should still be 0");
    }

    @Test
    public void testRestartMainCharacter() {
        mainCharacter = MainCharacter.restartMainCharacter(3, 7);
        assertEquals(3, mainCharacter.getX(), "x coordinate should be reset to 3");
        assertEquals(7, mainCharacter.getY(), "y coordinate should be reset to 7");
    }

    @Test
    public void testIsColliding() {
        Tile mockTile = mock(Tile.class);
        when(mockTile.getHasReward()).thenReturn(true);
        assertTrue(mainCharacter.isColliding(mockTile), "should return true");
        when(mockTile.getHasReward()).thenReturn(false);
        assertFalse(mainCharacter.isColliding(mockTile), "should return false");
    }

    @Test
    public void testKeyPressedInvalidInput() {
        mainCharacter.setPos(0, 0);

        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.S, false, false, false, false));
        assertEquals(0, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(0, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedUpLowerBounds() {
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false));
        assertEquals(0, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(0, mainCharacter.getY(), "y coordinate should not go out of bounds");
    }

    @Test
    public void testKeyPressedDownLowerBounds() {
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false));
        assertEquals(0, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(1, mainCharacter.getY(), "y coordinate should increase by 1");
    }

    @Test
    public void testKeyPressedLeftLowerBounds() {
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false));
        assertEquals(0, mainCharacter.getX(), "x coordinate should not go out of bounds");
        assertEquals(0, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedRightLowerBounds() {
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false));
        assertEquals(1, mainCharacter.getX(), "x coordinate should increase by 1");
        assertEquals(0, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedUpUpperBounds() {
        mainCharacter.setPos(2, 2);
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false));
        assertEquals(2, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(1, mainCharacter.getY(), "y coordinate should decrease by 1");
    }

    @Test
    public void testKeyPressedDownUpperBounds() {
        mainCharacter.setPos(2, 2);
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false));
        assertEquals(2, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(2, mainCharacter.getY(), "y coordinate should not go out of bounds");
    }

    @Test
    public void testKeyPressedLeftUpperBounds() {
        mainCharacter.setPos(2, 2);
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false));
        assertEquals(1, mainCharacter.getX(), "x coordinate should decrease by 1");
        assertEquals(2, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedRightUpperBounds() {
        mainCharacter.setPos(2, 2);
        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false));
        assertEquals(2, mainCharacter.getX(), "x coordinate should not go out of bounds");
        assertEquals(2, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedUpIntoWall() {
        int[][] tiles = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        board.setBoard(tiles);
        mainCharacter.setPos(1, 1);

        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.UP, false, false, false, false));
        assertEquals(1, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(1, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedDownIntoWall() {
        int[][] tiles = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        board.setBoard(tiles);
        mainCharacter.setPos(1, 1);

        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.DOWN, false, false, false, false));
        assertEquals(1, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(1, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedLeftIntoWall() {
        int[][] tiles = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        board.setBoard(tiles);
        mainCharacter.setPos(1, 1);

        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.LEFT, false, false, false, false));
        assertEquals(1, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(1, mainCharacter.getY(), "y coordinate should not change");
    }

    @Test
    public void testKeyPressedRightIntoWall() {
        int[][] tiles = {
                {1, 1, 1},
                {1, 0, 1},
                {1, 1, 1}
        };
        board.setBoard(tiles);
        mainCharacter.setPos(1, 1);

        mainCharacter.keyPressed(new KeyEvent(KEY_PRESSED, "", "", KeyCode.RIGHT, false, false, false, false));
        assertEquals(1, mainCharacter.getX(), "x coordinate should not change");
        assertEquals(1, mainCharacter.getY(), "y coordinate should not change");
    }
}
