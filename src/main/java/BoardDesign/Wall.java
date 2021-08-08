package BoardDesign;
import Core.*;

/**
 * Wall class represents a Tile object that
 * cannot be moved onto by any entity.
 *
 * @author Brendan
 * @version 1.0
 * @since 1.0
 */
public class Wall extends Tile {
    public Wall() {
        isOpen = false;
    }

    public boolean getIsOpen() {
        return isOpen;
    }
}
