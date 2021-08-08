package BoardDesign;
import Characters.MainCharacter;
import Core.*;
import TileAction.*;
//import sun.applet.Main;

/**
 * Exit class represents a Tile object
 * that the player must move onto in order
 * to trigger a win.
 *
 * @author Brendan
 * @version 1.0
 * @since 1.0
 */
public class Exit extends Wall {
    static MainCharacter mainCharacter = MainCharacter.getMainCharacter(0, 0);

    public Exit() {
        isOpen = false;
    }

    /**
     * Checks if all checkpoints have been collected
     * to open the exit.
     *
     * @return true if exit is opened, else false
     */
    public boolean checkCheckpoints() { // essentially call this whenever checkpoints are updated
        mainCharacter = MainCharacter.getMainCharacter(0, 0);
        if (Checkpoint.getCheckpointsLeft() == 0) {
            isOpen = true;
        }

        if (mainCharacter.getX() == Board.getExitXPos() && mainCharacter.getY() == Board.getExitYPos()) {
            Game.endGame(true);
        }

        return isOpen;
    }
}
