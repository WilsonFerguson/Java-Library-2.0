import java.util.ArrayList;
import java.awt.Color;

public class ShapeApplet extends AppletComponent {

    public ArrayList<Point> points = new ArrayList<Point>();
    public Color strokeColor;
    public Color fillColor;
    public double strokeWeight;
    public int mode;

    public ShapeApplet(Color strokeColor, Color fillColor, double rotate, Point translate, double scale,
            double strokeWeight, int mode) {
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
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
