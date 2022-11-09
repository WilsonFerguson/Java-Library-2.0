public class Point {

    public float x, y, z;

    /**
     * Creates a new point given {@code float} x, {@code float} y, and
     * {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     */
    public Point(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Creates a new point given {@code float} x and {@code float} y.
     * 
     * @param x
     * @param y
     */
    public Point(float x, float y) {
        this(x, y, 0);
    }

    /**
     * Creates a new (0, 0, 0) point.
     */
    public Point() {
        this(0, 0, 0);
    }

    /**
     * Subtracts a {@code Point} p.
     * 
     * @param point
     */
    public void sub(Point point) {
        this.x -= point.x;
        this.y -= point.y;
        this.z -= point.z;
    }

    /**
     * Subtracts a {@code float} x, {@code float} y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     */
    public void sub(float x, float y, float z) {
        this.x -= x;
        this.y -= y;
        this.z -= z;
    }

    /**
     * Subtracts a {@code float} x and {@code float} y.
     * 
     * @param x
     * @param y
     */
    public void sub(float x, float y) {
        this.x -= x;
        this.y -= y;
    }

    /**
     * Adds a {@code Point} p.
     * 
     * @param point
     */
    public void add(Point point) {
        this.x += point.x;
        this.y += point.y;
    }

    /**
     * Adds a {@code float} x, {@code float} y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     */
    public void add(float x, float y, float z) {
        this.x += x;
        this.y += y;
        this.z += z;
    }

    /**
     * Adds a {@code float} x and {@code float} y.
     * 
     * @param x
     * @param y
     */
    public void add(float x, float y) {
        this.x += x;
        this.y += y;
    }

    /**
     * Multiplies a {@code float} scalar.
     * 
     * @param scalar
     */
    public void mult(float scalar) {
        this.x *= scalar;
        this.y *= scalar;
        this.z *= scalar;
    }

    /**
     * Divides a {@code float} divisor.
     * 
     * @param divisor
     */
    public void div(float divisor) {
        this.x /= divisor;
        this.y /= divisor;
        this.z /= divisor;
    }

    /**
     * Normalizes the {@code Point} p.
     * 
     */
    public void normalize() {
        float length = Helper.sqrt(x * x + y * y + z * z);
        if (length != 0.0)
            div(length);
    }

    /**
     * Sets the magnitude to {@code float} magnitude.
     * 
     * @param magnitude
     */
    public void setMag(float magnitude) {
        normalize();
        mult(magnitude);
    }

    /**
     * Returns the magnitude of the point as a {@code float}.
     * 
     * @return float
     */
    public float mag() {
        return Helper.sqrt(x * x + y * y + z * z);
    }

    /**
     * Returns the squared magnitude of the point as a {@code float}.
     * 
     * @return float
     */
    public float magSq() {
        return (x * x + y * y + z * z);
    }

    /**
     * Returns the angle of the point as a {@code float}. (2D only)
     * 
     * @return float
     */
    public float heading() {
        // Returns the angle of the point
        return Helper.atan2(this.y, this.x);
    }

    /**
     * Rotate the point by {@code float} angle. (2D only)
     * 
     * @param angle
     */
    public void rotate(float angle) {
        float x = this.x;
        float y = this.y;

        this.x = (float) (x * Math.cos(angle) - y * Math.sin(angle));
        this.y = (float) (x * Math.sin(angle) + y * Math.cos(angle));
    }

    /**
     * Returns the distance between this point and {@code Point} p.
     * 
     * @param p
     * @return float
     */
    public float dist(Point point) {
        float dx = this.x - point.x;
        float dy = this.y - point.y;
        float dz = this.z - point.z;
        return Helper.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Returns the distance between this point and {@code float} x, {@code float}
     * y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     * @return float
     */
    public float dist(float x, float y, float z) {
        float dx = this.x - x;
        float dy = this.y - y;
        float dz = this.z - z;
        return Helper.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Returns the distance between this point and {@code float} x and
     * {@code float} y.
     * 
     * @param x
     * @param y
     * @return float
     */
    public float dist(float x, float y) {
        return (float) Math.sqrt(Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2));
    }

    /**
     * Limits the point to {@code float} max.
     */
    public void limit(float max) {
        this.x = Math.max(Math.min(this.x, max), -max);
        this.y = Math.max(Math.min(this.y, max), -max);
        this.z = Math.max(Math.min(this.z, max), -max);
    }

    /**
     * Returns the dot product of this point and {@code Point} p.
     * 
     * @param point
     * @return float
     */
    public float dot(Point point) {
        return this.x * point.x + this.y * point.y + this.z * point.z;
    }

    /**
     * Returns the dot product of this point and {@code float} x, {@code float}
     * y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     * @return float
     */
    public float dot(float x, float y, float z) {
        return this.x * x + this.y * y + this.z * z;
    }

    /**
     * Returns the dot product of this point and {@code float} x and {@code float}
     * y.
     * 
     * @param x
     * @param y
     * @return float
     */
    public float dot(float x, float y) {
        return this.x * x + this.y * y;
    }

    /**
     * Returns the cross product of this point and {@code Point} p.
     * 
     * @param point
     * @return
     */
    public float cross(Point point) {
        return this.x * point.y - this.y * point.x;
    }

    /**
     * Returns the cross product of this point and {@code float} x and
     * {@code float} y.
     * 
     * @param x
     * @param y
     * @return float
     */
    public float cross(float x, float y) {
        return this.x * y - this.y * x;
    }

    /**
     * Linear interpolation the point to {@code Point} p by {@code float} amount;
     * 
     * @param point
     * @param amount
     */
    public void lerp(Point point, float amount) {
        this.x += (point.x - this.x) * amount;
        this.y += (point.y - this.y) * amount;
        this.z += (point.z - this.z) * amount;
    }

    /**
     * Linear interpolation the point to {@code float} x, {@code float} y, and
     * {@code float} z by {@code float} amount;
     * 
     * @param x
     * @param y
     * @param z
     * @param amount
     */
    public void lerp(float x, float y, float z, float amount) {
        this.x += (x - this.x) * amount;
        this.y += (y - this.y) * amount;
        this.z += (z - this.z) * amount;
    }

    /**
     * Linear interpolation the point to {@code float} x and {@code float} y by
     * {@code float} amount;
     * 
     * @param x
     * @param y
     * @param amount
     */
    public void lerp(float x, float y, float amount) {
        this.x += (x - this.x) * amount;
        this.y += (y - this.y) * amount;
    }

    /**
     * Returns a {@code Point} copy of this point.
     * 
     * @return Point
     */
    public Point copy() {
        return new Point(this.x, this.y, this.z);
    }

    /**
     * Returns a {@code float[]} array of this point.
     * 
     * @return float[]
     */
    public float[] array() {
        return new float[] { (float) this.x, (float) this.y, (float) this.z };
    }

    /**
     * Sets the x, y, and z coordinates of the point to {@code float} x,
     * {@code float} y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     */
    public void set(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Sets the x and y of the point to {@code float} x and {@code float} y.
     * 
     * @param x
     * @param y
     */
    public void set(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Sets the x and y of the point to {@code Point} p.
     * 
     * @param point
     * @return
     */
    public void set(Point point) {
        this.x = point.copy().x;
        this.y = point.copy().y;
        this.z = point.copy().z;
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
    public static Point sub(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        p1.sub(p2);
        return p1;
    }

    /**
     * Static method to multiply {@code Point} point by {@code float} scalar
     * 
     * @param point
     * @param scalar
     * @return Point
     */
    public static Point mult(Point point, float scalar) {
        Point p = point.copy();
        p.mult(scalar);
        return p;
    }

    /**
     * Static method to divide {@code Point} point by {@code float} divisor.
     * 
     * @param point
     * @param divisor
     * @return Point
     */
    public static Point div(Point point, float divisor) {
        Point p = point.copy();
        p.div(divisor);
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
     * Static method to return the {@code float} distance between {@code Point}
     * point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return float
     */
    public static float dist(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        return p1.dist(p2);
    }

    /**
     * Returns a {@code Point} that is limited to the given {@code float} max. See
     * {@link Point#limit(float)}.
     * 
     * @param point
     * @param max
     */
    public static Point limit(Point point, float max) {
        Point p = point.copy();
        p.limit(max);
        return p;
    }

    /**
     * Static method to return the {@code float} distance between {@code float}
     * x1, {@code float} y1 and {@code float} x2, {@code float} y2.
     * {@link Point#dist(float, float)}.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return float
     */
    public static float dist(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Static method to return the {@code float} dot product of {@code Point}
     * point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return float
     */
    public static float dot(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        return p1.dot(p2);
    }

    /**
     * Static method to return the {@code float} cross product of {@code Point}
     * point1 and {@code Point} point2.
     * 
     * @param point1
     * @param point2
     * @return float
     */
    public static float cross(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        return p1.cross(p2);
    }

    /**
     * Linear interpolation between {@code Point} point1 and {@code Point} point2
     * 
     * @param point1
     * @param point2
     * @param amt
     * @return Point
     */
    public static Point lerp(Point point1, Point point2, float amt) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();
        p1.lerp(p2, amt);
        return p1;
    }

    /**
     * Calculates and returns the {@code float} angle (in radians) between two
     * points.
     * 
     * @param point1
     * @param point2
     * @return float
     */
    public static float angleBetween(Point point1, Point point2) {
        Point p1 = point1.copy();
        Point p2 = point2.copy();

        float dot = p1.dot(p2);
        float angle = Helper.acos(dot / (p1.mag() * p2.mag()));
        return angle;
    }

    /**
     * Returns a new Point(0, 0).
     * 
     * @return Point
     */
    public static Point zero() {
        return new Point(0, 0);
    }

    /**
     * Returns a new random 2D Point.
     * 
     * @return Point
     */
    public static Point random2D() {
        return new Point(Helper.random(-1.0f, 1.0f), Helper.random(-1.0f, 1.0f));
    }

    /**
     * Returns a new random 3D Point.
     * 
     * @return Point
     */
    public static Point random3D() {
        return new Point(Helper.random(-1.0f, 1.0f), Helper.random(-1.0f, 1.0f), Helper.random(-1.0f, 1.0f));
    }

    /**
     * Returns a new Point from the given {@code float} angle. (2D only)
     * 
     * @param angle
     * @return Point
     */
    public static Point fromAngle(float angle) {
        return new Point(Helper.cos(angle), Helper.sin(angle));
    }
}