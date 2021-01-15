/**
 * @Author Shilat Givati
 * ID 206377038
 */

/**
 * The class determines the speed of the ball.
 */
public class Velocity {
    private double dx;
    private double dy;
    private static final int CHANGE_SIGN = -1;

    /**
     * constructor function.
     *
     * @param dx - delta by axis X.
     * @param dy - delta by axis Y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor function - duplicating.
     *
     * @param v - the velocity.
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * The function return object by angle and speed to dx,dy.
     *
     * @param angle - the angle of the ball.
     * @param speed - the speed of the ball.
     * @return ball object with the parameters.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        angle = Math.toRadians(angle);
        double dx = Math.sin(angle) * speed;
        double dy = CHANGE_SIGN * Math.cos(angle) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * @return the dx value.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * @return the dy value.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * @param newDx - set the delta X.
     */
    public void setDx(double newDx) {
        this.dx = newDx;
    }

    /**
     * @param newDy - set the delta Y.
     */
    public void setDy(double newDy) {
        this.dy = newDy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point we want to move.
     * @return - the new location of the point.
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }

    /**
     * @return the size of the vector.
     */
    public double fromVectorToSpeed() {
        return Math.sqrt(Math.pow(this.dx, 2) + Math.pow(this.dy, 2));
    }
}
