package Characters;

import Core.Board;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class EnemyTest {
    Enemy enemy = new Enemy(1, 1);

    MainCharacter mainCharacter = MainCharacter.getMainCharacter(0, 0);

    int[][] id = {
            {0, 0, 0},
            {0, 0, 0},
            {0, 0, 0}
    };

    @Mock
    Board board;

    @BeforeEach
    public void initEnemy() {
        board.setBoard(id);
        enemy.setPos(1, 1);
    }

    @Test
    public void testMoveUp() {
        enemy.move(Direction.UP);
        assertEquals(1, enemy.getX(), "x coordinate should not change");
        assertEquals(0, enemy.getY(), "y coordinate should decrease by 1");
    }

    @Test
    public void testMoveDown() {
        enemy.move(Direction.DOWN);
        assertEquals(1, enemy.getX(), "x coordinate should not change");
        assertEquals(2, enemy.getY(), "y coordinate should increase by 1");
    }

    @Test
    public void testMoveLeft() {
        enemy.move(Direction.LEFT);
        assertEquals(0, enemy.getX(), "x coordinate should decrease by 1");
        assertEquals(1, enemy.getY(), "y coordinate should not change");
    }

    @Test
    public void testMoveRight() {
        enemy.move(Direction.RIGHT);
        assertEquals(2, enemy.getX(), "x coordinate should increase by 1");
        assertEquals(1, enemy.getY(), "y coordinate should not change");
    }

    @Test
    public void testCheckBestMovementUp() {
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);

        mainCharacter.setPos(0, 0);

        int[][] testId = {
                {0, 0, 0},
                {1, 0, 0},
                {1, 1, 0}
        };
        board.setBoard(testId);

        assertEquals(Direction.UP, enemy.checkBestMovement(enemyArrayList), "should return Direction.UP");
    }

    @Test
    public void testCheckBestMovementDown() {
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);

        mainCharacter.setPos(2, 2);

        int[][] testId = {
                {0, 0, 0},
                {0, 0, 1},
                {0, 0, 0}
        };
        board.setBoard(testId);

        assertEquals(Direction.DOWN, enemy.checkBestMovement(enemyArrayList), "should return Direction.DOWN");
    }

    @Test
    public void testCheckBestMovementLeft() {
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);

        mainCharacter.setPos(0, 0);

        int[][] testId = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        board.setBoard(testId);

        assertEquals(Direction.LEFT, enemy.checkBestMovement(enemyArrayList), "should return Direction.LEFT");
    }

    @Test
    public void testCheckBestMovementRight() {
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);

        mainCharacter.setPos(2, 2);

        int[][] testId = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        board.setBoard(testId);

        assertEquals(Direction.RIGHT, enemy.checkBestMovement(enemyArrayList), "should return Direction.RIGHT");
    }

    @Test
    public void testCheckBestMovementUpEnemyCollide() {
        ArrayList<Enemy> enemyArrayList = new ArrayList<>();
        enemyArrayList.add(enemy);

        Enemy enemy2 = new Enemy(0, 1);
        enemy2.setPos(0, 1);
        enemyArrayList.add(enemy2);

        mainCharacter.setPos(0, 0);

        int[][] testId = {
                {0, 1, 1},
                {0, 0, 1},
                {1, 1, 1}
        };
        board.setBoard(testId);

        assertEquals(Direction.STAY, enemy.checkBestMovement(enemyArrayList), "should return Direction.STAY");
    }

    @Test
    public void testIsCollidingTrue() {
        mainCharacter.setPos(enemy.getX(), enemy.getY());
        assertTrue(enemy.isColliding(), "should return true");
    }

    @Test
    public void testIsCollidingFalse() {
        mainCharacter.setPos(0, 0);
        assertFalse(enemy.isColliding(), "should return false");
    }
}
