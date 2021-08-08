package Core;
import TileAction.*;

import org.junit.Rule;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.function.Executable;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;


public class TileTest {

    /*@Rule
    public ExpectedException thrown = ExpectedException.none();*/
    @Test
    public void testFalseInput()
    {
        /*thrown.expect(NullPointerException.class);*/
        assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Tile myTile = new Tile(4, 5, 8);
                assertNull(myTile.reward);
                assertFalse(myTile.getHasReward());
            }
        });
        //assertTrue(myTile.getHasReward());
    }
    @Test
    public void testCheckpoint()
    {
        Tile myTile2 = new Tile(1, 5, 8);
        Reward Tile2reward = myTile2.getReward();
        Boolean isRewardPresent = myTile2.getHasReward();
        String rewardType = Tile2reward.getClass().getSimpleName();
        assertEquals("Checkpoint", rewardType);
        assertEquals("Checkpoint", myTile2.getTypeReward());
        assertNotNull(Tile2reward);
        assertTrue(isRewardPresent);
        //System.out.println(rewardType);
    }
    @Test
    public void testPunishment()
    {
        Tile myTile2 = new Tile(2, 5, 8);
        Reward Tile2reward = myTile2.getReward();
        Boolean isRewardPresent = myTile2.getHasReward();
        String rewardType = Tile2reward.getClass().getSimpleName();
        assertEquals("Punishment", rewardType);
        assertEquals("Punishment", myTile2.getTypeReward());
        assertNotNull(Tile2reward);
        assertTrue(isRewardPresent);
        //System.out.println(rewardType);
    }
    @Test
    public void testBonus()
    {
        Tile myTile2 = new Tile(3, 5, 8);
        Reward Tile2reward = myTile2.getReward();
        Boolean isRewardPresent = myTile2.getHasReward();
        String rewardType = Tile2reward.getClass().getSimpleName();
        assertEquals("Bonus", rewardType);
        assertEquals("Bonus", myTile2.getTypeReward());
        assertNotNull(Tile2reward);
        assertTrue(isRewardPresent);
        //System.out.println(rewardType);
    }

    @Test
    public void removeRewardTest()
    {
        Tile myTile2 = new Tile(3, 5, 8);
        myTile2.removeReward();
        assertNull(myTile2.getReward());
        assertFalse(myTile2.getHasReward());
        assertEquals("", myTile2.getTypeReward());

    }
    /*@Test
    public void xandyCoordinateTest()
    {
        Tile myTile2 = new Tile(3, 5, 8);
        assertEquals(5, myTile2.getXCoordinate());
        assertEquals(8, myTile2.getYCoordinate());
        Tile myTile3 = new Tile(1, 60, 50);
        Tile myTile1 = new Tile(2, 40, 41);
        assertEquals(60, myTile3.getXCoordinate());
        assertEquals(50, myTile3.getYCoordinate());
        assertEquals(40, myTile1.getXCoordinate());
        assertEquals(41, myTile1.getYCoordinate());
    }*/
    @Test
    public void testOpen(){
        Tile myTile2 = new Tile(3,5,8);
        assertTrue(myTile2.isOpen());
    }

}


