package Characters;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {
    @Test
    public void testGetValUP() {
        assertEquals(-1, Direction.UP.getVal(), "should return -1");
    }

    @Test
    public void testGetValDOWN() {
        assertEquals(1, Direction.DOWN.getVal(), "should return 1");
    }

    @Test
    public void testGetValLEFT() {
        assertEquals(-1, Direction.LEFT.getVal(), "should return -1");
    }

    @Test
    public void testGetValRIGHT() {
        assertEquals(1, Direction.RIGHT.getVal(), "should return 1");
    }

    @Test
    public void testGetValSTAY() {
        assertEquals(0, Direction.STAY.getVal(), "should return 0");
    }
}
