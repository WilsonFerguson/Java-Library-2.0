import java.awt.Color;

public class LineApplet extends AppletComponent {

    public double x1, y1, x2, y2;
    public Color color;
    public double strokeWeight;

    public LineApplet(double x1, double y1, double x2, double y2, Color color, double strokeWeight, double rotate,
            Point translate, double scale) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.color = color;
        this.strokeWeight = strokeWeight;
        this.rotate = rotate;
        this.translate = translate;
        this.scale = scale;
    }

}
