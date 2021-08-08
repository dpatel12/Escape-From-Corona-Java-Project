package Core;

import javax.sound.midi.SysexMessage;
import TileAction.*;

/**
 * Tile class represents the tiles that the make up the board.
 * Provides the methods and members for a regular tile.
 *
 * @author Brendan
 * @author Stephen Dao
 * @version 1.0
 * @since 1.0
 */
public class Tile
{
    private int x;
    private int y;
//    private boolean hasEnemy;
//    private boolean hasMain;
    private boolean hasReward = false;
    protected boolean isOpen = true;
    Reward reward;
    public String typeOfReward = "";

    public Tile() {
        hasReward = false;
    }

    public Tile(int inputType, int x, int y) { // constructor that also generates a Reward object
        hasReward = true;
        switch (inputType) {
            case 1:
                reward = new Checkpoint();
                break;
            case 2:
                reward = new Punishment();
                break;
            case 3:
                reward = new Bonus(x, y);
                break;
            default:
                System.out.println("Incorrect input");
                break;
        }
        typeOfReward = reward.getClass().getSimpleName();
    }

//    public boolean getHasEnemy()
//    {
//        return hasEnemy;
//    }

//    public boolean getHasMain()
//    {
//        return hasMain;
//    }

    /**
     * Getter for hasReward.
     *
     * @return true if the tile contains a Reward, else false
     */
    public boolean getHasReward(){
        return hasReward;
    }

    /**
     * Getter for reward.
     *
     * @return the Reward object at this tile
     */
    public Reward getReward() {
        return reward;
    }

    /**
     * Getter for isOpen.
     *
     * @return true if the tile is open (can be moved onto), else false
     */
    public boolean isOpen() {
        return isOpen;
    }

    /**
     * Sets whether or not the tile has a reward.
     *
     * @param hasReward true if the tile has a reward, else false
     */
    public void setHasReward(boolean hasReward) {
        this.hasReward = hasReward;
    }

    /**
     * Sets the reward object for the tile.
     *
     * @param reward a type of Reward object
     */
    public void setReward(Reward reward) {
        hasReward = true;
        this.reward = reward;
        typeOfReward = reward.getClass().getSimpleName();
    }

    /**
     * Removes the reward object for the tile.
     */
    public void removeReward() {
        hasReward = false;
        reward = null;
        typeOfReward = "";
    }

    /**
     * Creates and returns a string for the reward object, but deals with issue of null Reward by using an if statement.
     * @return either the actual reward if the Tile has a reward, or an empty string if it doesn't
     */
    public String getTypeReward(){
        String rewardType;
        if (hasReward == true){
            rewardType = typeOfReward;
        }
        else {
            rewardType = "";
        }
        return rewardType;
    }

   /* public int getXCoordinate(){
        return x;
    }
    public int getYCoordinate(){
        return y;
    }*/
}
