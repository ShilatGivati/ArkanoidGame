/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * The point has x and y values, and it can measure the distance between it and other points,
 * and if it is equal to the other point.
 */
public class Point {
    private double x;
    private double y;


    /**
     * constructor function.
     *
     * @param x valid for point.
     * @param y valid for point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor function - duplicating.
     *
     * @param point - point we want to duplicate.
     */
    public Point(Point point) {
        this.x = point.getX();
        this.y = point.getY();
    }

    /**
     * function function.
     *
     * @param other - the point from which we want to calculate the distance from the current point.
     * @return the distance between the points.
     */
    public double distance(Point other) {
        /*
          subX - subtracts X values
          subY - subtracts Y values
         */
        double subX = other.x - this.x;
        double subY = other.y - this.y;
        return Math.sqrt(subX * subX + subY * subY);
    }

    /**
     * equals function - if the other point is the same from the current return true, else - false.
     *
     * @param other - the point from which we want to compare from the current point.
     * @return true or false
     */
    public boolean equals(Point other) {
        return ((other.x == this.x) && (other.y == this.y));
    }

    /**
     * get x function.
     *
     * @return - the x point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * get x function.
     *
     * @return - the y point.
     */
    public double getY() {
        return this.y;
    }

    /**
     * @param newX - new X.
     */
    public void setX(double newX) {
        this.x = newX;
    }

    /**
     * @param newY - new Y.
     */
    public void setY(double newY) {
        this.y = newY;
    }

}
