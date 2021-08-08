package BoardDesign;
import Core.*;

/**
 * Entrance class represents a Tile object
 * where the player character starts the game on.
 *
 * @author Brendan
 * @version 1.0
 * @since 1.0
 */
public class Entrance extends Wall {
    public Entrance() {
        isOpen = false;
    }

//   public static void closeEntrance() { // call this when the MC moves // keep it open at all times for now
//        /*
//        if (gameBoard.mainCharacter.locationX != xLocation || gameBoard.mainCharacter.locationY != yLocation) {
//            isOpen = false;
//        }
//        */
//    }
}
