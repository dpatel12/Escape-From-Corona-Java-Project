package BoardDesign;
import Core.*;
import TileAction.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class EntranceTest {
    Tile entrance;

    /**
     * Creates an entrance object
     */
    @BeforeAll
    public void setup() {
        entrance = new Entrance();
    }

    /**
     * Checks to make sure that the entrance is closed upon intialization
     * The way that we've implemented entrance, is such that the entrance should never be opened
     * The entrance is only the means for the program to identify where to the put the main character
     * when they are spawned into the maze
     */
    @Test
    public void testOpen() {
        assertFalse(entrance.isOpen());
    }

    /**
     * Ensure that the tile that the entrance is on has no reward
     * Note that this is because all tiles that are walls should never have a reward on them
     */
    @Test
    public void testNoReward() {
        assertFalse(entrance.getHasReward());
    }
}
