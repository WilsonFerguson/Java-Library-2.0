import java.awt.Color;

public class RectApplet extends AppletComponent {

    public double x, y, w, h;
    public Color strokeColor;
    public Color fillColor;
    public int alignment;
    public double strokeWeight;
    public double roundness;

    public RectApplet(double x, double y, double w, double h, Color strokeColor, Color fillColor, int alignment,
            double strokeWeight, double roundness, double rotate, Point translate, double scale) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.alignment = alignment;
        this.strokeWeight = strokeWeight;
        this.roundness = roundness;
        this.rotate = rotate;
        this.translate = translate;
        this.scale = scale;
    }

}
