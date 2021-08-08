package BoardDesign;
import Core.*;
import TileAction.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExitTest {

    Tile exit;

    /**
     * Creates an exit object
     */
    @BeforeAll
    public void setup() {
        exit = new Exit();
    }

    /**
     * Checks to make sure that the exit is closed upon intialization
     */
    @Test
    public void testOpen() {
        assertFalse(exit.isOpen());
    }

    // on-point: 0
    // off-point: 1

    /**
     * Tests if the exit actually opens if there are no checkpoints left
     */
    @Test
    public void testNoCheckpointsLeft() {
        Checkpoint.setCheckpointsLeft(0);
        assertTrue(((Exit) exit).checkCheckpoints());
    }

    /**
     * Tests if the exit stays closed if there are checkpoints left
     */
    @RepeatedTest(10)
    public void testSomeCheckpointsLeft() {
        exit = new Exit();  // require new creation of exit each time to make sure that initialized as false
        int max = 1000;
        int min = 1;
        int randomInt = (int)Math.floor(Math.random()*(max-min+1)+min);
        Checkpoint.setCheckpointsLeft(randomInt);
        assertFalse(((Exit) exit).checkCheckpoints());
    }

    /**
     * Ensure that the tile that the exit is on has no reward
     * Note that this is because all tiles that are walls should never have a reward on them
     */
    @Test
    public void testNoReward() {
        assertFalse(exit.getHasReward());
    }
}
