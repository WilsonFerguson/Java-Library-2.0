import java.util.ArrayList;
import java.awt.Color;

public class ShapeApplet {

    public ArrayList<Point> points = new ArrayList<Point>();
    public Color color;
    public double rotate, scale;
    public Point translate;
    public double strokeWeight;
    public int mode;

    public ShapeApplet(Color color, double rotate, Point translate, double scale, double strokeWeight, int mode) {
        this.color = color;
        this.rotate = rotate;
        this.translate = translate;
        this.scale = scale;
        this.strokeWeight = strokeWeight;
        this.mode = mode;
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public void addPoint(double x, double y) {
        points.add(new Point(x, y));
    }

}
