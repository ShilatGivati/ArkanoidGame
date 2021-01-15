/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.util.ArrayList;
import java.util.List;

/**
 * The Rectangle class will contain its properties.
 * return values and return list of intersection points with the specified line.
 */
public class Rectangle {

    // members
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * Constructor function.
     *
     * @param upperLeft - point in upper-left of the rectangle.
     * @param width     - the width of the rectangle.
     * @param height    - the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor multiple function.
     *
     * @param rectangle - we want to multiple with.
     */
    public Rectangle(Rectangle rectangle) {
        this.upperLeft = rectangle.getUpperLeft();
        this.width = rectangle.getWidth();
        this.height = rectangle.getHeight();
    }

    /**
     * The function returns list of intersection points with the specified line.
     *
     * @param line - the line intersection with.
     * @return - a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> pointList = new ArrayList<Point>();

        //the lines that creates the rectangle
        //upperLeft-downLeft
        Line line1 = new Line(this.upperLeft, new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height));
        //upperLeft-right
        Line line2 = new Line(this.upperLeft, new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY()));
        //line1-right
        Line line3 = new Line(line1.end(), new Point(line1.end().getX() + this.width, line1.end().getY()));
        //line2-down
        Line line4 = new Line(line2.end(), line3.end());

        //add the points that intersecting the line
        if (line.isIntersecting(line1)) {
            pointList.add(line.intersectionWith(line1));
        }
        if (line.isIntersecting(line2)) {
            pointList.add(line.intersectionWith(line2));
        }
        if (line.isIntersecting(line3)) {
            pointList.add(line.intersectionWith(line3));
        }
        if (line.isIntersecting(line4)) {
            pointList.add(line.intersectionWith(line4));
        }
        return pointList;
    }

    /**
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Set function to the point - upper left.
     *
     * @param newUpperLeft - the upperLeft we want to set.
     */
    public void setUpperLeft(Point newUpperLeft) {
        this.upperLeft = newUpperLeft;
    }
}
