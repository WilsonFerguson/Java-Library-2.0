import java.awt.Color;

public class QuadApplet extends AppletComponent {

    public double x1, y1, x2, y2, x3, y3, x4, y4;
    public Color strokeColor;
    public Color fillColor;
    public double strokeWeight;

    public QuadApplet(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4,
            Color strokeColor, Color fillColor, double strokeWeight, double rotate, Point translate, double scale) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.x4 = x4;
        this.y4 = y4;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWeight = strokeWeight;
        this.rotate = rotate;
        this.translate = translate;
        this.scale = scale;
    }

}
