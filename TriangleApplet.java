import java.awt.Color;

public class TriangleApplet extends AppletComponent {

    public float x1, y1, x2, y2, x3, y3;
    public Color strokeColor;
    public Color fillColor;
    public float strokeWeight;

    public TriangleApplet(float x1, float y1, float x2, float y2, float x3, float y3, Color strokeColor,
            Color fillColor, float strokeWeight, float rotate, Point translate, float scale) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
        this.strokeColor = strokeColor;
        this.fillColor = fillColor;
        this.strokeWeight = strokeWeight;
        this.rotate = rotate;
        this.translate = translate;
        this.scale = scale;
    }

}
