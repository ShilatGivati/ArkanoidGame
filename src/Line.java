/**
 * @Author Shilat Givati
 * ID 206377038
 */

import java.util.List;

/**
 * A section connects two points: a starting point and a final point. Sections have length, and they can be cut with
 * Other sections. In addition, a section can check if it is equal to another section.
 */
public class Line {
    private static final double EP = Math.pow(10, -10);
    private Point start;
    private Point end;

    /**
     * constructor function.
     *
     * @param start - starting point.
     * @param end   - end point.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
    }

    /**
     * constructor function.
     *
     * @param x1 - x valid for starting point.
     * @param y1 - y valid for starting point.
     * @param x2 - x valid for end point.
     * @param y2 - y valid for end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * length function.
     *
     * @return the distance between the start and the end
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * middle function.
     *
     * @return the middle point in the line.
     */
    public Point middle() {
        double midX = (this.start.getX() + this.end.getX()) / 2;
        double midY = (this.start.getY() + this.end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * @return the start point in the line.
     */
    public Point start() {
        return this.start;
    }

    /**
     * @return the start point in the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * @return true is the line is actually point' else - false
     */
    public boolean lineIsPoint() {
        return this.start.equals(this.end);
    }

    /**
     * @return true is vertical line to X axis else - false
     */
    public boolean isVerticalLineToX() {
        return (this.end.getX() == this.start.getX());
    }

    /**
     * isDomainX function - check the X domain.
     *
     * @param x - the value of the point we want to check if in the domain in line
     * @return true if in domain and else - false
     */
    public boolean isDomainX(Double x) {
        if (this.start.getX() > this.end.getX()) {
            return (this.start.getX() + EP >= x && this.end.getX() - EP <= x);
        }
        return (this.start.getX() - EP <= x && this.end.getX() + EP >= x);
    }

    /**
     * isDomainY function - check the Y domain.
     *
     * @param y - the value of the point we want to check if in the domain in line
     * @return true if in domain and else - false
     */
    public boolean isDomainY(Double y) {
        if (this.start.getY() > this.end.getY()) {
            return (this.start.getY() + EP >= y && this.end.getY() - EP <= y);
        }
        return (this.start.getY() - EP <= y && this.end.getY() + EP >= y);
    }

    /**
     * @return the slope of the current line.
     */
    public double findSlope() {
        return (this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX());
    }

    /**
     * @return the Y intercept of the current line.
     */
    public double findYIntercept() {
        return (this.start.getY() - this.findSlope() * this.start.getX());
    }

    /**
     * The function checks whether one line is a continuation of the other.
     *
     * @param other - the other line we want to compare.
     * @return the shared point
     */
    public Point isContinuesLines(Line other) {

        boolean startEnd = this.start.equals(other.end);
        boolean endStart = this.end.equals(other.start);
        boolean startStart = this.start.equals(other.start);
        boolean endEnd = this.end.equals(other.end);

        //check which with line is continues lines
        if (startEnd) {
            if ((this.isDomainX(other.start.getX()) && this.isDomainY(other.start.getY()))
                    || (other.isDomainX(this.end.getX()) && other.isDomainY(this.end.getY()))) {
                return null;
            }
            return this.start;
        }
        if (endStart) {
            if ((this.isDomainX(other.end.getX()) && this.isDomainY(other.end.getY()))
                    || (other.isDomainX(this.start.getX()) && other.isDomainY(this.start.getY()))) {
                return null;
            }
            return this.end;
        }
        if (startStart) {
            if ((this.isDomainX(other.end.getX()) && this.isDomainY(other.end.getY()))
                    || (other.isDomainX(this.end.getX()) && other.isDomainY(this.end.getY()))) {
                return null;
            }
            return this.start;
        }
        if (endEnd) {
            if ((this.isDomainX(other.start.getX()) && this.isDomainY(other.start.getY()))
                    || (other.isDomainX(this.start.getX()) && other.isDomainY(this.start.getY()))) {
                return null;
            }
            return this.end;
        }
        return null;
    }

    /**
     * @param x - the value we want to check if exist in the line.
     * @return the value of Y.
     */
    public double findYIntersection(double x) {
        return this.findSlope() * x + this.findYIntercept();
    }

    /**
     * @param other - the other line we check.
     * @return the intersecting point.
     */
    public Point isIntersectingHelper(Line other) {
        double m1 = this.findSlope();
        double m2 = other.findSlope();
        double b1 = this.findYIntercept();
        double b2 = other.findYIntercept();
        if (m1 == m2) {
            return this.isContinuesLines(other);
        }
        return new Point(((b2 - b1) / (m1 - m2)), this.findYIntersection(((b2 - b1) / (m1 - m2))));
    }

    /**
     * @param other - the other line we compare with.
     * @return true if equals and else return false (equal points respectively).
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end);
    }

    /**
     * @param other - the other line we compare with.
     * @return true - if the lines have the same two points.
     */
    public boolean equalsLines(Line other) {
        return this.start.equals(other.end) && this.end.equals(other.start) || this.equals(other);
    }

    /**
     * @param other - the other line we check.
     * @return true if the line is intersecting, else - false.
     */
    public boolean isIntersecting(Line other) {
        if (this.equalsLines(other)) {
            return false;
        }

        //check if the line is a point
        if (this.lineIsPoint()) {
            return other.isDomainX(this.start.getX()) && other.isDomainY(this.start.getY());
        }
        if (other.lineIsPoint()) {
            return this.isDomainX(other.start.getX()) && this.isDomainY(other.start.getY());
        }

        //check if the lines art verticals
        if (this.isVerticalLineToX() && other.isVerticalLineToX()) {
            //if the lines is vertical to axis x and the x are different the lines not intersecting
            if (this.start.getX() != other.start.getX()) {
                return false;
            }
            return (this.isContinuesLines(other) != null);
        }
        if (this.isVerticalLineToX()) {
            if (other.isDomainX(this.start.getX())) {
                return this.isDomainY(other.findYIntersection(this.start.getX()));
            }
            return false;
        }
        if (other.isVerticalLineToX()) {
            if (this.isDomainX(other.start.getX())) {
                return other.isDomainY(this.findYIntersection(other.start.getX()));
            }
            return false;
        }

        //call to helper function
        Point point = this.isIntersectingHelper(other);
        if (point != null) {
            return (this.isDomainY(point.getY()) && this.isDomainX(point.getX()) && other.isDomainY(point.getY())
                    && other.isDomainX(point.getX()));
        }
        return false;
    }

    /**
     * The function returns the intersection point between two lines.
     *
     * @param other - the other line we check with.
     * @return the common point for the two lines
     */
    public Point intersectionWith(Line other) {
        //The realization of the function is divided into lines-state cases and calls auxiliary functions accordingly
        if (!this.isIntersecting(other)) {
            return null;
        }
        if (this.lineIsPoint()) {
            return this.start;
        }
        if (other.lineIsPoint()) {
            return other.start;
        }
        if (this.isVerticalLineToX() && other.isVerticalLineToX()) {
            return this.isContinuesLines(other);
        }
        if (this.isVerticalLineToX()) {
            return new Point(this.start.getX(), other.findYIntersection(this.start.getX()));
        }
        if (other.isVerticalLineToX()) {
            return new Point(other.start.getX(), this.findYIntersection(other.start.getX()));
        }
        return this.isIntersectingHelper(other);
    }

    /**
     * The function return closest intersection to the start of line if there is.
     *
     * @param rect - the rectangle.
     * @return if this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //If this line does not intersect with the rectangle
        if (rect.intersectionPoints(this).isEmpty()) {
            return null;
        }

        //find closest intersection point to the start of the line.
        List<Point> pointList = rect.intersectionPoints(this);
        double closestDistance = Double.MAX_VALUE;
        Point tempPoint = null;
        //go over all the lines that intersection
        for (Point point : pointList) {
            if (point.distance(this.start()) < closestDistance) {
                tempPoint = point;
                closestDistance = point.distance(this.start());
            }
        }
        return tempPoint;
    }
}
