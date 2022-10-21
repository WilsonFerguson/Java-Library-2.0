import java.awt.Color;

public class TextApplet extends AppletComponent {

    public String text;
    public double x, y;
    public Color color;
    public double size;
    public int alignment;
    public String font;

    public TextApplet(String text, double x, double y, Color color, double size, int alignment, String font,
            double rotate, Point translate, double scale) {
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
