package Characters;

/**
 * NonStationaryCharacter class provides base methods
 * and members for subclasses.
 *
 * @author Brendan
 * @author Stephen Dao
 * @version 1.0
 * @since 1.0
 */

public class NonStationaryCharacter {
    protected int x = 0; // x coord
    protected int y = 0; // y coord

    /**
     * Getter for the x coordinate of object.
     *
     * @return an int corresponding to an x coordinate
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the y coordinate of object.
     *
     * @return an int corresponding to a y coordinate
     */
    public int getY() {
        return y;
    }

    /**
     * Setter to set the x and y coordinate of the object.
     *
     * @param x an x coordinate
     * @param y a y coordinate
     */
    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public String printPos() {
        System.out.println(getClass().getSimpleName() +":[" + x + ", " + y + "]");
        return "[" + x + ", " + y + "]";
    }
}
