package TileAction;
import Core.Game;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.TestInstance;


import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BonusTest {
    Bonus bonus;
    int x;
    int y;

    /**
     * Create random number between 1 and 1000 for the bonus coordinates
     */
    @BeforeEach
    public void setup() {
        int max = 1000;
        int min = 1;
        x = (int)Math.floor(Math.random()*(max-min+1)+min);
        y = (int)Math.floor(Math.random()*(max-min+1)+min);
        bonus = new Bonus(x, y);
        assertNotNull(bonus);
    }

    /**
     * Check that the random numbers match for X from initialization to the getter
     */
    @RepeatedTest(10)
    public void testX() {
        assertEquals(bonus.getX(), x);
    }

    /**
     * Check that the random numbers match for Y from initialization to the getter
     */
    @RepeatedTest(10)
    public void testY() {
        assertEquals(bonus.getY(), y);
    }

    /**
     * Check that tick decrementing works as intended
     */
    @RepeatedTest(10)
    public void testDecrement() {
        int init = bonus.getTicksRemaining();
        for (int i = 0; i < 5; i++) {
            bonus.decrementTicksRemaining();
            init--;
            assertEquals(bonus.getTicksRemaining(), init);
        }
    }

    /**
     * Ensure that the score is being increased by a positive value
     */
    @Test
    public void testPositiveScoreIncrease() {
        assertTrue(bonus.getScoreIncreaseValue() >= 0);
    }

    /**
     * Test that the updatePlayerScore is actually updating the game score
     */
    @Test
    public void testScoreIncreaseValid() {
        int tempScore = Game.getScoreStatic();
        bonus.updatePlayerScore();
        assertEquals(Game.getScoreStatic(), tempScore + bonus.getScoreIncreaseValue());
    }
}
