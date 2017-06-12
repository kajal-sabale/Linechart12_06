package studytutorial.in.linechart;

/**
 * Created by a622890 on 27-03-2017.
 * This class is used by arraylist for setting values for x and y co-ordinate values.
 */

public class Axis {
    int x;
    int y;

    /**
     *
     * @param x- x co-ordinate
     * @param y- y co-ordinate
     */
    public Axis(int x, int y) {
        super();
        this.x = x;
        this.y = y;
    }

    /**
     *
     * @return int
     */
    public int getX() {
        return x;
    }

    /**
     *
     * @param x sets x co-ordinate
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     *
     * @return int
     */
    public int getY() {
        return y;
    }

    /**
     *
     * @param y - sets y co-ordinate
     */

    public void setY(int y) {
        this.y = y;
    }




}
