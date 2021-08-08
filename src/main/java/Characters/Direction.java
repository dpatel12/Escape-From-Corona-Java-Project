package Characters;

/**
 * An enum class to assign values to directions.
 *
 * @author Stephen Dao
 * @version 1.0
 * @since 1.0
 */
public enum Direction {
    UP(-1),
    DOWN(1),
    LEFT(-1),
    RIGHT(1),
    STAY(0);

    private int val;

    Direction(int val){
        this.val = val;
    }

    public int getVal(){
        return val;
    }
}
