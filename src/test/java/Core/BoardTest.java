package Core;
import java.util.ArrayList;
import java.util.Random;

import TileAction.Bonus;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BoardTest {

    static ArrayList<Board> boards;

    public Board allOneType(int type) {
        int[][] array = new int[dimensions][dimensions];
        for(int i = 0; i < dimensions; i++) {
            for(int j = 0; j < dimensions; j++) {
                array[j][i] = type;
            }
        }
        return new Board(array);
    }

    static int dimensions = 100;
    @BeforeAll
    public void setup() {
        boards = new ArrayList<>();
        int numBoards = 10;
        Random rand = new Random();
        for (int k = 0; k < numBoards; k++) {
            int[][] cur = new int[dimensions][dimensions];
            for (int i = 0; i < dimensions; i++) {
                for (int j = 0; j < dimensions; j++) {
                    cur[i][j] = rand.nextInt();
//                    System.out.println(cur[i][j]);

                }
            }
            boards.add(new Board(cur));
        }
    }
//    @Test
//    public void helper() {
////        setup();
//    }

    @Test
    public void testNullTiles(){
        /**
         * is every tile instantiated?
         */
        for(int k = 0; k < boards.size(); k++) {

            Board cur = boards.get(k);
            Tile[][] boardTile = cur.getBoard();

            for (int i = 0; i < cur.getDimY(); i++) {
                for (int j = 0; j < cur.getDimX(); j++) {
                    assert (boardTile[i][j] != null);
                    //more tests

                }
            }
        }
    }

    @Test
    public void bonusGen() {
        Board cur = boards.get(0);
        cur.generateBonus();

        boolean hasBonus;
        ArrayList<Bonus> curBonus = cur.getBonusArrayList();

        for(int i = 0; i < curBonus.size(); i++) {

            int xBonus = curBonus.get(i).getX();
            int yBonus = curBonus.get(i).getY();

            assertEquals(cur.getTile(xBonus, yBonus).getHasReward(),curBonus.get(i).getTicksRemaining() < 0);

        }

    }

    @Test
    public void testEntry() {
        /**
         * The last tile with a '5' or a '6' shoudl be the entrance and exit, respectively
         */
        Board entries = allOneType(5);
        Board exits = allOneType(6);
        assertEquals(dimensions - 1,entries.getExitXPos());
        assertEquals( dimensions - 1, entries.getExitYPos());
        assertEquals(0, entries.getEntranceXPos());
        assertEquals(0, entries.getEntranceYPos());

    }

    @Test
    public void testTileType() {

        String[] possible = {"Tile", "Wall","TileCheckpoint","TilePunishment",
        "TileBonus","Entrance","Exit"};

        int[][] id = new int[1][1];
        Board board;
        for(int i = 0; i < 7; i++) {
            id[0][0] = i;
            board = new Board(id);
            assertEquals(possible[i],board.getTileString());
        }
    }

}


