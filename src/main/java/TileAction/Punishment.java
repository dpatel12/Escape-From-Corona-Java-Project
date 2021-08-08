package TileAction;
import Characters.*;
import Core.Game;

/**
 * Punishment class implements the Reward interface.
 * Contains method to update player score by the
 * value of the object.
 *
 * @author Brendan
 * @author Stephen Dao
 * @version 1.0
 * @since 1.0
 */
public class Punishment implements Reward {
    int scoreIncreaseValue = -100;

    public Punishment() {
    }

    /**
     * Updates player score by value of reward.
     */
    @Override
    public void updatePlayerScore() {
        Game.updateScore(scoreIncreaseValue);
        //System.out.println(Game.score);
        /*
        mainCharacter.increasePlayerScore(scoreIncreaseValue);
         */
    }

    /**
     * Getter for the score increase value of the object
     * @return an int corresponding to how much an interaction with the object will increase the player score
     */
    public int getScoreIncreaseValue() {
        return scoreIncreaseValue;
    }
}
