public class Point {

    public double x, y;

    /**
     * Creates a new point given {@code double} x and {@code double} y.
     * 
     * @param x
     * @param y
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Subtracts a {@code Point} p. <br>
     * <br>
     * {@code this.x -= point.x;} <br>
     * <br>
     * {@code this.y -= point.y;}
     * 
     * @param point
     */
    public void subtract(Point point) {
        this.x -= point.x;
        this.y -= point.y;
    }

    /**
     * Subtracts a {@code double} x and {@code double} y. <br>
     * <br>
     * {@code this.x -= x;} <br>
     * <br>
     * {@code this.y -= y;}
     * 
     * @param x
     * @param y
     */
    public void subtract(double x, double y) {
        this.x -= x;
        this.y -= y;
    }

    /**
     * Adds a {@code Point} p. <br>
     * <br>
     * {@code this.x += point.x;} <br>
     * <br>
     * {@code this.y += point.y;}
     * 
     * @param point
     */
    public void add(Point point) {
        this.x += point.x;
        this.y += point.y;
    }

    /**
     * Adds a {@code double} x and {@code double} y. <br>
     * <br>
     * {@code this.x += x;} <br>
     * <br>
     * {@code this.y += y;}
     * 
     * @param x
     * @param y
     */
    public void add(double x, double y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Multiplies a {@code Point} p. <br>
     * <br>
     * {@code this.x *= point.x;} <br>
     * <br>
     * {@code this.y *= point.y;}
     * 
     * @param point
     */
    public void multiply(Point point) {
        this.x *= point.x;
        this.y *= point.y;
    }

    /**
     * Multiplies a {@code double} x and {@code double} y. <br>
     * <br>
     * {@code this.x *= x;} <br>
     * <br>
     * {@code this.y *= y;}
     * 
     * @param x
     * @param y
     */
    public void multiply(double x, double y) {
        this.x *= x;
        this.y *= y;
    }

    /**
     * Multiplies a {@code double} scalar. <br>
     * <br>
     * {@code this.x *= scalar;} <br>
     * <br>
     * {@code this.y *= scalar;}
     * 
     * @param scalar
     */
    public void multiply(double scalar) {
        this.x *= scalar;
        this.y *= scalar;
    }

    /**
     * Divides a {@code Point} p. <br>
     * <br>
     * {@code this.x /= point.x;} <br>
     * <br>
     * {@code this.y /= point.y;}
     * 
     * @param point
     */
    public void divide(Point point) {
        this.x /= point.x;
        this.y /= point.y;
    }

    /**
     * Divides a {@code double} x and {@code double} y. <br>
     * <br>
     * {@code this.x /= x;} <br>
     * <br>
     * {@code this.y /= y;}
     * 
     * @param x
     * @param y
     */
    public void divide(double x, double y) {
        this.x /= x;
        this.y /= y;
    }

    /**
     * Divides a {@code double} divisor. <br>
     * <br>
     * {@code this.x /= divisor;} <br>
     * <br>
     * {@code this.y /= divisor;}
     * 
     * @param divisor
     */
    public void divide(double divisor) {
        this.x /= divisor;
        this.y /= divisor;
    }

    /**
     * Normalizes the {@code Point} p.
     * 
     */
    public void normalize() {
        double length = Math.sqrt(x * x + y * y);
        if (length != 0.0)
            divide(length);
    }

    /**
     * Sets the magnitude to {@code double} magnitude.
     * 
     * @param magnitude
     */
    public void setMag(double magnitude) {
        // Sets the length of the point to the given value
        double angle = this.getAngle();
        this.x = (double) (magnitude * Math.cos(angle));
        this.y = (double) (magnitude * Math.sin(angle));
    }

    /**
     * Returns the magnitude of the point as a {@code double}.
     * 
     * @return double
     */
    public double getMag() {
        // Returns the length of the point
        return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
    }

    /**
     * Returns the angle of the point as a {@code double}.
     * 
     * @return double
     */
    public double getAngle() {
        // Returns the angle of the point
        return Math.atan2(this.y, this.x);
    }

    /**
     * Rotate the point by {@code double} angle.
     * 
     * @param angle
     */
    public void rotate(double angle) {
        double x = this.x;
        double y = this.y;

        this.x = (double) (x * Math.cos(angle) - y * Math.sin(angle));
        this.y = (double) (x * Math.sin(angle) + y * Math.cos(angle));
    }

    /**
     * Returns the distance between this point and {@code Point} p.
     * 
     * @param p
     * @return double
     */
    public double dist(Point point) {
        return Math.sqrt(Math.pow(point.x - this.x, 2) + Math.pow(point.y - this.y, 2));
    }

    /**
     * Returns the distance between this point and {@code double} x and
     * {@code double} y.
     * 
     * @param x
     * @param y
     * @return double
     */
    public double dist(double x, double y) {
        return Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    /**
     * Constrains the point from {@code double} min to {@code double} max.
     * 
     * @param min
     * @param max
     */
    public void constrain(double min, double max) {
        this.x = Helper.constrain(this.x, min, max);
        this.y = Helper.constrain(this.y, min, max);
    }

    /**
     * Limits the point to {@code double} max.
     */
    public void limit(double max) {
        this.x = Math.max(Math.min(this.x, max), -max);
        this.y = Math.max(Math.min(this.y, max), -max);
    }

    /**
     * Returns the dot product of this point and {@code Point} p.
     * 
     * @param point
     * @return double
     */
    public double dot(Point point) {
        return this.x * point.x + this.y * point.y;
    }

    /**
     * Returns the dot product of this point and {@code double} x and {@code double}
     * y.
     * 
     * @param x
     * @param y
     * @return double
     */
    public double dot(double x, double y) {
        return this.x * x + this.y * y;
    }

    /**
     * Returns the cross product of this point and {@code Point} p.
     * 
     * @param point
     * @return
     */
    public double cross(Point point) {
        return this.x * point.y - this.y * point.x;
    }

    /**
     * Returns the cross product of this point and {@code double} x and
     * {@code double} y.
     * 
     * @param x
     * @param y
     * @return double
     */
    public double cross(double x, double y) {
        return this.x * y - this.y * x;
    }

    /**
     * Returns a {@code Point} copy of this point.
     * 
     * @return Point
     */
    public Point copy() {
        return new Point(this.x, this.y);
    }

    /**
     * Returns {@code double} x.
     * 
     * @return double
     */
    public double getX() {
        return this.x;
    }

    /**
     * Returns {@code double} y.
     * 
     * @return double
     */
    public double getY() {
        return this.y;
    }

    /**
     * Sets {@code double} x.
     * 
     * @param x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Sets {@code double} y.
     * 
     * @param y
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Returns {@code String} representation of this point.
     * 
     * @return String
     */
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    /**
     * Static method to add {@code Point} point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return Point
     */
    public static Point add(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        p1.add(p2);
        return p1;

    }

    /**
     * Static method to subtract {@code Point} point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return Point
     */
    public static Point subtract(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        p1.subtract(p2);
        return p1;
    }

    /**
     * Static method to multiply {@code Point} point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return Point
     */
    public static Point multiply(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        p1.multiply(p2);
        return p1;
    }

    /**
     * Static method to divide {@code Point} point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return Point
     */
    public static Point divide(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        p1.divide(p2);
        return p1;
    }

    /**
     * Static method to multiply {@code Point} point by {@code double} scalar
     * 
     * @param point
     * @param scalar
     * @return Point
     */
    public static Point multiply(Point point, double scalar) {
        Point p = point.copy();
        p.multiply(scalar);
        return p;
    }

    /**
     * Static method to divide {@code Point} point by {@code double} divisor.
     * 
     * @param point
     * @param divisor
     * @return Point
     */
    public static Point divide(Point point, double divisor) {
        Point p = point.copy();
        p.divide(divisor);
        return p;
    }

    /**
     * Static method to normalize a given {@code Point} point.
     * 
     * @param point
     * @return Point
     */
    public static Point normalize(Point point) {
        Point p = point.copy();
        p.normalize();
        return p;
    }

    /**
     * Static method to return the {@code double} distance between {@code Point}
     * point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return double
     */
    public static double dist(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        return p1.dist(p2);
    }

    /**
     * Returns a constrained {@code Point} point from the given {@code Point} point,
     * {@code double} min, and {@code double} max. See
     * {@link Point#constrain(double, double)}.
     * 
     * @param point
     * @param min
     * @param max
     * @return Point
     */
    public static Point constrain(Point point, double min, double max) {
        Point p = point.copy();
        p.constrain(min, max);
        return p;
    }

    /**
     * Returns a {@code Point} that is limited to the given {@code double} max. See
     * {@link Point#limit(double)}.
     * 
     * @param point
     * @param max
     */
    public static Point limit(Point point, double max) {
        Point p = point.copy();
        p.limit(max);
        return p;
    }

    /**
     * Static method to return the {@code double} distance between {@code double}
     * x1, {@code double} y1 and {@code double} x2, {@code double} y2.
     * {@link Point#dist(double, double)}.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return double
     */
    public static double dist(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Static method to return the {@code double} dot product of {@code Point}
     * point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return double
     */
    public static double dot(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        return p1.dot(p2);
    }

    /**
     * Static method to return the {@code double} cross product of {@code Point}
     * point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return double
     */
    public static double cross(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        return p1.cross(p2);
    }

    /**
     * Returns a new Point(0, 0).
     * 
     * @return Point
     */
    public static Point zero() {
        return new Point(0, 0);
    }
}