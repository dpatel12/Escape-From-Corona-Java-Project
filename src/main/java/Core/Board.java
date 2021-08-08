package Core;

import BoardDesign.*;
import Characters.*;
import TileAction.*;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Board class represents the game board.
 * This class generates and stores the tile
 * composition.
 *
 * @author Peter
 * @version 1.0
 * @since 1.0
 */
public class Board
{
    private static int dimX;
    private static int dimY;
    private static int[][] boardID;
    private static Tile[][] board;
    private static int exitXPos;
    private static int exitYPos;
    private static int entranceXPos;
    private static int entranceYPos;

    private static ArrayList<Bonus> bonusArrayList = new ArrayList<>(); // list of bonus rewards currently on board

    public static Tile[][] getBoard() {
        return board.clone(); // return a copy that they cannot edit
    }

    public Board(int[][] lvl) {
        MainCharacter mainCharacter = MainCharacter.getMainCharacter(0, 0);

        boardID = lvl;

        dimX = boardID[0].length; // number of columns
        dimY = boardID.length; // number of rows
        board = new Tile[dimY][dimX]; // y has to go first
        bonusArrayList.clear();
        for(int i = 0; i < dimY; i++) {
            for(int j = 0; j < dimX; j++) {
                int cur = boardID[i][j];

                switch(cur) {
                    case 1://Wall
                        board[i][j] = new Wall();
                        //System.out.println("Wall");
                        break;
                    case 2: //Tile with checkpoint
                        board[i][j] = new Tile(1, j, i); // checkpoint is input 1
                        break;
                    case 3: //Tile with punishment
                        board[i][j] = new Tile(2, j, i); // punishment is input 2
                        break;
                    case 4:
                        board[i][j] = new Tile(3, j, i); // bonus is input 3
                        break;
                    case 5:
                        board[i][j] = new Entrance();
                        mainCharacter.setPos(j, i);
                        break;
                    case 6:
                        board[i][j] = new Exit();
                        exitYPos = i;
                        exitXPos = j;
                        break;
                    default: //Tile/invalid case
                        board[i][j] = new Tile();
                        //System.out.println("Tile");
                        break;
                }
            }
        }
    }

    public ArrayList<Bonus> getBonusArrayList() {
        return bonusArrayList;
    }

    public int getDimX(){
        return dimX;
    }

    public int getDimY(){
        return dimY;
    }

    public static int getExitXPos() {
        return exitXPos;
    }

    public static int getExitYPos() {
        return exitYPos;
    }

    public static int getEntranceXPos() {
        return entranceXPos;
    }
    public static int getEntranceYPos() {
        return entranceYPos;
    }

    public Tile getTile(int i, int j)
    {
        return board[i][j];
    }

    public static void setBoard(int[][] id) {
        MainCharacter mainCharacter = MainCharacter.getMainCharacter(0, 0);

        boardID = id;
        dimX = id[0].length; // number of columns
        dimY = id.length; // number of rows
        board = new Tile[dimY][dimX]; // y has to go first
        exitXPos = 0;
        exitYPos = 0;
        for(int i = 0; i < dimY; i++) {
            for(int j = 0; j < dimX; j++) {
                //System.out.println();
                int cur = id[i][j];
                switch(cur) {
                    case 0://Tile
                        board[i][j] = new Tile();
                        break;
                    //System.out.println("Tile");
                    case 1://Wall
                        board[i][j] = new Wall();
                        break;
                    //System.out.println("Wall");
                    case 2: //Tile with checkpoint
                        board[i][j] = new Tile(1, j, i); // checkpoint is input 1
                        break;
                    case 3: //Tile with punishment
                        board[i][j] = new Tile(2, j, i); // punishment is input 2
                        break;
                    case 4:
                        board[i][j] = new Tile(3, j, i); // bonus is input 3
                        break;
                    case 5:
                        board[i][j] = new Entrance();
                        mainCharacter.setPos(j, i);
                        break;
                    case 6:
                        board[i][j] = new Exit();
                        exitYPos = i;
                        exitXPos = j;
                        break;

                }
            }
        }
    }


    public static void printBoard() { // print board to console
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                System.out.print(board[i][j].getClass().getSimpleName() + board[i][j].typeOfReward + "  ");
            }
            System.out.println("");
        }
    }

    public static String getTileString() { // primarily for testing purposes
        String ret = "";
        for (int i = 0; i < dimY; i++) {
            for (int j = 0; j < dimX; j++) {
                ret += board[i][j].getClass().getSimpleName() + board[i][j].typeOfReward;
            }
            System.out.println("");
        }
        return ret;
    }

    /**
     * Method to attempt to generate a bonus reward on the board.
     * Also updates the remaining lifetime of currently generated
     * bonus rewards.
     */
    public static void generateBonus() {
        int bonusChance = 20; // chance to attempt to generate bonus
        int chance = (int)(Math.random() * 100);

        if(chance <= bonusChance) { // attempt to generate a bonus reward
            int xPos = (int) (Math.random() * (dimX - 2) + 1); // generate coordinate between 1 and dim-2
            int yPos = (int) (Math.random() * (dimY - 2) + 1); // ^^^
            Tile bonusTile = board[yPos][xPos];
            if (!bonusTile.getHasReward() && bonusTile.isOpen()) {
                Bonus bonus = new Bonus(xPos, yPos);
                bonusTile.setReward(bonus);
                bonusArrayList.add(bonus);
            }
        }
        Iterator<Bonus> itr = bonusArrayList.iterator();
        while(itr.hasNext()) {
            Bonus b = itr.next();
            b.decrementTicksRemaining();
            if(b.getTicksRemaining() <= 0) {
                board[b.getY()][b.getX()].removeReward();
                itr.remove();
            }
        }
    }

}


