import java.awt.Color;

public class LineApplet extends AppletComponent {

    public float x1, y1, x2, y2;
    public Color color;
    public float strokeWeight;

    public LineApplet(float x1, float y1, float x2, float y2, Color color, float strokeWeight, float rotate,
            Point translate, float scale) {
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
