import java.awt.Color;

public class TextApplet extends AppletComponent {

    public String text;
    public float x, y;
    public Color color;
    public float size;
    public int alignment;
    public String font;

    public TextApplet(String text, float x, float y, Color color, float size, int alignment, String font,
            float rotate, Point translate, float scale) {
        this.text = text;
        this.x = x;
        this.y = y;
        this.color = color;
        this.size = size;
        this.alignment = alignment;
        this.font = font;
        this.rotate = rotate;
        this.translate = translate;
        this.scale = scale;
    }

}
