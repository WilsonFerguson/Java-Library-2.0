import java.awt.Color;

public class RectApplet extends AppletComponent {

    public float x, y, w, h;
    public Color strokeColor;
    public Color fillColor;
    public int alignment;
    public float strokeWeight;
    public float roundness;

    public RectApplet(float x, float y, float w, float h, Color strokeColor, Color fillColor, int alignment,
            float strokeWeight, float roundness, float rotate, Point translate, float scale) {
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
