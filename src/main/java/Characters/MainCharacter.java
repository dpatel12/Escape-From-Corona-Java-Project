package Characters;

import javafx.scene.input.*;
import java.lang.Math.*;
import java.util.*;

import Core.*;
import TileAction.*;
import BoardDesign.Exit;

/**
 * MainCharacter class implements methods to update the
 * character. This includes character movement
 * and input handling for player controls.
 *
 * @author Brendan
 * @author Stephen Dao
 * @version 1.0
 * @since 1.0
 */
public class MainCharacter extends NonStationaryCharacter {
    private static MainCharacter mainCharacter = null;

//    boolean keyIsPressed; // if a key is held down or not

    private MainCharacter(int x, int y) { // private constructor
        this.x = x;
        this.y = y;
//        keyIsPressed = false;
    }

    /**
     * Creates an instance of MainCharacter and sets
     * the starting coordinates if there is currently no other
     * instance of this class.
     *
     * @param x starting x position
     * @param y starting y position
     * @return an instance of a MainCharacter object
     */
    public static MainCharacter getMainCharacter(int x, int y) { // x,y are starting coordinates
        if (mainCharacter == null) { // only create object if none exist
            mainCharacter = new MainCharacter(x, y);
        }

        return mainCharacter;
    }

    /**
     * Creates a new instance of MainCharacter and sets
     * the starting coordinates.
     *
     * @param x starting x position
     * @param y starting y position
     * @return an instance of a MainCharacter object
     */
    public static MainCharacter restartMainCharacter(int x, int y) { // only called when game is restarted
        mainCharacter = new MainCharacter(x, y);

        return mainCharacter;
    }

    /**
     * Handles game behaviour when player changes position.
     */
    protected void move() {
        Tile[][] board = Board.getBoard();
        int dimX = board[0].length;
        int dimY = board.length;
        Tile currentTile = board[y][x];
        Tile exit = board[Board.getExitYPos()][Board.getExitXPos()];
        if(Board.getExitXPos() != 0 || Board.getExitYPos() != 0) {
            ((Exit) exit).checkCheckpoints();
        }

        if(isColliding(currentTile)) {
            Reward reward = currentTile.getReward();
            reward.updatePlayerScore();
            currentTile.removeReward();
        }

        //Board.printBoard();
    }

    /**
     * Checks if the player is colliding with a reward tile.
     *
     * @param currentTile a Tile object corresponding to the player's current position
     * @return true if the player is on a reward tile, else false
     */
    protected boolean isColliding(Tile currentTile) {
        if(currentTile.getHasReward()) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     * Handles player movement in
     * response to a key input.
     *
     * @param e a KeyEvent key input
     */
    public void keyPressed(KeyEvent e) {
        KeyCode key = e.getCode();
        Tile[][] board = Board.getBoard();
        int dimX = board[0].length;
        int dimY = board.length;
        if ((key == KeyCode.UP) && (y-1 >= 0)) {
            if(board[y-1][x].isOpen()) {
                y += -1;
            }
        }
        if ((key == KeyCode.DOWN) && (y+1 < dimY)) {
            if(board[y+1][x].isOpen()) {
                y += 1;
            }
        }
        if ((key == KeyCode.LEFT) && (x-1 >= 0)) {
            if(board[y][x-1].isOpen()) {
                x += -1;
            }
        }
        if ((key == KeyCode.RIGHT) && (x+1 < dimX)) {
            if(board[y][x+1].isOpen()) {
                x += 1;
            }
        }
        //printPos();
        move();
    }

//    /**
//     * Resets the keyIsPressed condition
//     * when a key is released.
//     *
//     * @param e a KeyEvent that is the key being released
//     */
//    public void keyReleased(KeyEvent e) {
//        //int key = e.getKeyCode(); // currently unneeded
//
//        if (keyIsPressed) {
//            keyIsPressed = false;
//        }
//    }
}
