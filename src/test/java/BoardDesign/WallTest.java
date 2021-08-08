package BoardDesign;
import Core.*;
import TileAction.*;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.Assert.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class WallTest {
    Tile wall;

    /**
     * Creates a wall object
     */
    @BeforeAll
    public void setup() {
        wall = new Wall();
    }

    /**
     * A wall should never be open
     */
    @Test
    public void testOpen() {
        assertFalse(wall.isOpen());
    }

    /**
     * Ensure that the tile that the wall is on has no reward
     * Note that this is because all tiles that are walls should never have a reward on them
     */
    @Test
    public void testNoReward() {
        assertFalse(wall.getHasReward());
    }
}
