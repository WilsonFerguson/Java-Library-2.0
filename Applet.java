import java.awt.*;
import java.awt.event.*;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.geom.*;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.*;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Applet extends JPanel {

    public JFrame frame;
    public Graphics g;
    public Graphics2D g2d;

    public final int CENTER = 0;
    public final int CORNER = 1;
    public final int LEFT = 1;
    public final int RIGHT = 3;

    public int rectMode = CORNER;
    public int ellipseMode = CORNER;
    public int textAlign = LEFT;

    public Color fillColor = Color.BLACK;
    public Color strokeColor = Color.BLACK;
    public Color backgroundColor = Color.WHITE;

    public double strokeWeight = 1;
    public double textSize = 12;
    public String textFont = "Arial";

    public double rotation = 0;
    public Point translation = Point.zero();
    public double scale = 1;

    public double mouseX = 0;
    public double mouseY = 0;
    public Point mouse = Point.zero();
    public double pmouseX = 0;
    public double pmouseY = 0;
    public Point pmouse = Point.zero();

    public double startTime = 0;

    public double targetFrameRate = 60;
    public double frameRate = 0;
    public double lastTime = 0;
    public int frameCount = 0;

    public int width;
    public int height;

    public boolean mousePressed = false;
    public int mouseButton = CENTER;
    public char key = ' ';
    public int keyCode = 0;

    // Drawing
    public ArrayList<EllipseApplet> ellipses = new ArrayList<EllipseApplet>();
    public ArrayList<LineApplet> lines = new ArrayList<LineApplet>();
    public ArrayList<QuadApplet> quads = new ArrayList<QuadApplet>();
    public ArrayList<RectApplet> rects = new ArrayList<RectApplet>();
    public ArrayList<TriangleApplet> triangles = new ArrayList<TriangleApplet>();
    public ArrayList<TextApplet> texts = new ArrayList<TextApplet>();
    public ArrayList<ShapeApplet> shapes = new ArrayList<ShapeApplet>();
    public boolean drawBackground = false;

    // Constants
    public final double PI = Math.PI;
    public final double HALF_PI = Math.PI / 2;
    public final double QUARTER_PI = Math.PI / 4;
    public final double TWO_PI = Math.PI * 2;
    public final double TAU = Math.PI * 2;

    // Shape
    public ShapeApplet shape;
    public final int SMOOTH = 1;
    public final int RIGID = 2;
    public final int CLOSE = 0;

    public void size(double width, double height) {
        frame = new JFrame();
        frame.setSize((int) width, (int) height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.add(this);

        this.width = (int) width;
        this.height = (int) height;

        g = frame.getGraphics();
        g2d = (Graphics2D) g;

        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();

        addListeners();
    }

    private void addListeners() {
        frame.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                mousePressed = true;
                mouseButton = evt.getButton();
                mousePress();
            }

            public void mouseReleased(MouseEvent evt) {
                mousePressed = false;
                mouseButton = CENTER;
                mouseRelease();
            }

            public void mouseClicked(MouseEvent evt) {
                mouseButton = evt.getButton();
                mouseClick();
            }

            public void mouseDragged(MouseEvent evt) {
                mouseDrag();
            }

            public void mouseWheelMoved(MouseWheelEvent evt) {
                int notches = evt.getWheelRotation();
                println("Mouse Wheel Moved: " + notches);
                mouseScroll(notches);
            }
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                mouseX = evt.getX() - 8;
                mouseY = evt.getY() - 31;
                mouse = new Point(mouseX, mouseY);
                mouseMove();
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                key = evt.getKeyChar();
                keyCode = evt.getKeyCode();

                String keyText = KeyEvent.getKeyText(evt.getKeyCode());
                keyPress(keyText);
            }

            public void keyReleased(KeyEvent evt) {
                key = evt.getKeyChar();
                keyCode = evt.getKeyCode();

                String keyText = KeyEvent.getKeyText(evt.getKeyCode());
                keyRelease(keyText);
            }

            public void keyTyped(KeyEvent evt) {
                String keyText = String.valueOf(evt.getKeyChar());
                keyType(keyText);
            }
        });
    }

    public void setup() {
        // Empty
    }

    public void draw() {
        // Empty
    }

    public void backendUpdate() {
        frameRate = 1000.0 / (millis() - lastTime);
        lastTime = millis();
        frameCount++;

        // Cap frame rate
        try {
            Thread.sleep((long) (1000.0 / targetFrameRate));
        } catch (Exception e) {
            // Empty
        }

        // Update mouse
        pmouseX = mouseX;
        pmouseY = mouseY;
        pmouse = mouse.copy();

        frame.repaint();

        rotation = 0;
        translation = Point.zero();
        scale = 1;
    }

    public void frameRate(double targetFrameRate) {
        this.targetFrameRate = targetFrameRate;
    }

    public void println(Object... args) {
        Helper.println(args);
    }

    public void background(Color color) {
        backgroundColor = color;
        drawBackground = true;
        frame.setBackground(color);
    }

    public void background(double r, double g, double b, double a) {
        r = Helper.constrain(r, 0, 255);
        g = Helper.constrain(g, 0, 255);
        b = Helper.constrain(b, 0, 255);
        a = Helper.constrain(a, 0, 255);
        background(new Color((int) r, (int) g, (int) b, (int) a));
    }

    public void background(double r, double g, double b) {
        background(r, g, b, 255);
    }

    public void background(double gray, double a) {
        background(gray, gray, gray, a);
    }

    public void background(double gray) {
        background(gray, 255);
    }

    // Mouse inputs
    public void mousePress() {
    }

    public void mouseRelease() {
    }

    public void mouseClick() {
    }

    public void mouseDrag() {
    }

    public void mouseScroll(int amount) {
    }

    public void mouseMove() {
    }

    // Keyboard inputs
    public void keyPress(String key) {
    }

    public void keyRelease(String key) {
    }

    /**
     * Warning: This method is not called when the user presses any modifier (e.g.
     * shift, ctrl, alt, backspace).
     * 
     * @param key
     */
    public void keyType(String key) {
    }

    // Drawing Modes
    public void rectMode(int mode) {
        rectMode = mode;
    }

    public void ellipseMode(int mode) {
        ellipseMode = mode;
    }

    // Fill
    public void fill(Color color) {
        fillColor = color;
    }

    public void fill(double r, double g, double b, double a) {
        r = Helper.constrain(r, 0, 255);
        g = Helper.constrain(g, 0, 255);
        b = Helper.constrain(b, 0, 255);
        a = Helper.constrain(a, 0, 255);
        fill(new Color((int) r, (int) g, (int) b, (int) a));
    }

    public void fill(double r, double g, double b) {
        fill(r, g, b, 255);
    }

    public void fill(double gray, double a) {
        fill(gray, gray, gray, a);
    }

    public void fill(double gray) {
        fill(gray, gray, gray);
    }

    public void noFill() {
        fill(0, 0);
    }

    // Stroke
    public void stroke(Color color) {
        strokeColor = color;
    }

    public void stroke(double r, double g, double b, double a) {
        r = Helper.constrain(r, 0, 255);
        g = Helper.constrain(g, 0, 255);
        b = Helper.constrain(b, 0, 255);
        a = Helper.constrain(a, 0, 255);
        stroke(new Color((int) r, (int) g, (int) b, (int) a));
    }

    public void stroke(double r, double g, double b) {
        stroke(r, g, b, 255);
    }

    public void stroke(double gray, double a) {
        stroke(gray, gray, gray, a);
    }

    public void stroke(double gray) {
        stroke(gray, gray, gray);
    }

    public void noStroke() {
        stroke(0, 0);
    }

    // Stroke Weight
    public void strokeWeight(double weight) {
        strokeWeight = Helper.constrain(weight, 0, weight);
    }

    // Text Size
    public void textSize(double size) {
        textSize = Helper.constrain(size, 0, size);
    }

    // Text Align
    public void textAlign(int align) {
        textAlign = align;
    }

    // Text Font
    public void textFont(String font) {
        textFont = font;
    }

    // Transformations
    public void translate(double x, double y) {
        translation = new Point(x, y);
    }

    public void translate(Point p) {
        translation = p.copy();
    }

    public void rotate(double angle) {
        rotation = angle;
    }

    public void scale(double scalar) {
        scale = scalar;
    }

    // Returns the width of the text
    public double textWidth(String text) {
        return frame.getGraphics().getFontMetrics().stringWidth(text);
    }

    // Returns the height of the text
    public double textHeight(String text) {
        return frame.getGraphics().getFontMetrics().getHeight();
    }

    // Ellipse
    public void drawEllipse(EllipseApplet ellipse) {
        double x = ellipse.x;
        double y = ellipse.y;
        double w = ellipse.w;
        double h = ellipse.h;
        if (ellipse.alignment == CENTER) {
            x -= w / 2;
            y -= h / 2;
        }

        g2d.translate(ellipse.translate.x, ellipse.translate.y);
        g2d.scale(ellipse.scale, ellipse.scale);
        g2d.rotate(ellipse.rotate);

        // Fill
        g2d.setColor(ellipse.fillColor);
        g2d.fill(new Ellipse2D.Double(x, y, w, h));
        // Stroke
        g2d.setColor(ellipse.strokeColor);
        g2d.setStroke(new BasicStroke((float) ellipse.strokeWeight));
        g2d.draw(new Ellipse2D.Double(x, y, w, h));
    }

    public void ellipse(double x, double y, double w, double h) {
        EllipseApplet ellipse = new EllipseApplet(x, y, w, h, strokeColor, fillColor, ellipseMode, strokeWeight,
                rotation,
                translation, scale);
        ellipses.add(ellipse);

    }

    public void ellipse(Point p, double w, double h) {
        ellipse(p.x, p.y, w, h);
    }

    public void ellipse(Point p, Point s) {
        ellipse(p.x, p.y, s.x, s.y);
    }

    // Circle
    public void circle(double x, double y, double r) {
        ellipse(x, y, r, r);
    }

    public void circle(Point p, double r) {
        ellipse(p.x, p.y, r, r);
    }

    // Line
    public void drawLine(LineApplet line) {
        double x1 = line.x1;
        double y1 = line.y1;
        double x2 = line.x2;
        double y2 = line.y2;

        g2d.translate(line.translate.x, line.translate.y);
        g2d.scale(line.scale, line.scale);
        g2d.rotate(line.rotate);

        g2d.setColor(line.color);
        g2d.setStroke(new BasicStroke((float) line.strokeWeight));
        g2d.draw(new Line2D.Double(x1, y1, x2, y2));
    }

    public void line(double x1, double y1, double x2, double y2) {
        LineApplet line = new LineApplet(x1, y1, x2, y2, strokeColor, strokeWeight, rotation, translation, scale);
        lines.add(line);
    }

    public void line(Point p1, Point p2) {
        line(p1.x, p1.y, p2.x, p2.y);
    }

    // Point
    public void point(double x, double y) {
        line(x, y, x, y);
    }

    public void point(Point p) {
        point(p.x, p.y);
    }

    // Quad
    public void drawQuad(QuadApplet quad) {
        double x1 = quad.x1;
        double y1 = quad.y1;
        double x2 = quad.x2;
        double y2 = quad.y2;
        double x3 = quad.x3;
        double y3 = quad.y3;
        double x4 = quad.x4;
        double y4 = quad.y4;

        g2d.translate(quad.translate.x, quad.translate.y);
        g2d.scale(quad.scale, quad.scale);
        g2d.rotate(quad.rotate);

        // Fill
        g2d.setColor(quad.fillColor);
        g2d.fillPolygon(new int[] { (int) x1, (int) x2, (int) x3, (int) x4 },
                new int[] { (int) y1, (int) y2, (int) y3, (int) y4 }, 4);
        // Stroke
        g2d.setColor(quad.strokeColor);
        g2d.setStroke(new BasicStroke((float) quad.strokeWeight));
        g2d.drawPolygon(new int[] { (int) x1, (int) x2, (int) x3, (int) x4 },
                new int[] { (int) y1, (int) y2, (int) y3, (int) y4 }, 4);
    }

    public void quad(double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4) {
        QuadApplet quad = new QuadApplet(x1, y1, x2, y2, x3, y3, x4, y4, strokeColor, fillColor, strokeWeight, rotation,
                translation, scale);
        quads.add(quad);
    }

    public void quad(Point p1, Point p2, Point p3, Point p4) {
        quad(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y, p4.x, p4.y);
    }

    // Rect
    public void drawRect(RectApplet rect) {
        double x = rect.x;
        double y = rect.y;
        double w = rect.w;
        double h = rect.h;

        if (rect.alignment == CENTER) {
            x -= w / 2;
            y -= h / 2;
        }

        g2d.translate(rect.translate.x, rect.translate.y);
        g2d.scale(rect.scale, rect.scale);
        g2d.rotate(rect.rotate);

        // Fill
        g2d.setColor(rect.fillColor);
        g2d.fill(new Rectangle2D.Double(x, y, w, h));
        // Stroke
        g2d.setColor(rect.strokeColor);
        g2d.setStroke(new BasicStroke((float) rect.strokeWeight));
        g2d.draw(new Rectangle2D.Double(x, y, w, h));
    }

    public void rect(double x, double y, double w, double h) {
        RectApplet rect = new RectApplet(x, y, w, h, strokeColor, fillColor, rectMode, strokeWeight, rotation,
                translation, scale);
        rects.add(rect);
    }

    public void rect(Point p, double w, double h) {
        rect(p.x, p.y, w, h);
    }

    public void rect(Point p, Point s) {
        rect(p.x, p.y, s.x, s.y);
    }

    // Square
    public void square(double x, double y, double s) {
        rect(x, y, s, s);
    }

    public void square(Point p, double s) {
        square(p.x, p.y, s);
    }

    // Triangle
    public void drawTriangle(TriangleApplet triangle) {
        double x1 = triangle.x1;
        double y1 = triangle.y1;
        double x2 = triangle.x2;
        double y2 = triangle.y2;
        double x3 = triangle.x3;
        double y3 = triangle.y3;

        g2d.translate(triangle.translate.x, triangle.translate.y);
        g2d.scale(triangle.scale, triangle.scale);
        g2d.rotate(triangle.rotate);

        // Fill
        g2d.setColor(triangle.fillColor);
        g2d.fillPolygon(new int[] { (int) x1, (int) x2, (int) x3 },
                new int[] { (int) y1, (int) y2, (int) y3 }, 3);
        // Stroke
        g2d.setColor(triangle.strokeColor);
        g2d.setStroke(new BasicStroke((float) triangle.strokeWeight));
        g2d.drawPolygon(new int[] { (int) x1, (int) x2, (int) x3 },
                new int[] { (int) y1, (int) y2, (int) y3 }, 3);
    }

    public void triangle(double x1, double y1, double x2, double y2, double x3, double y3) {
        TriangleApplet triangle = new TriangleApplet(x1, y1, x2, y2, x3, y3, strokeColor, fillColor, strokeWeight,
                rotation, translation, scale);
        triangles.add(triangle);
    }

    public void triangle(Point p1, Point p2, Point p3) {
        triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
    }

    // Text
    public void drawText(TextApplet text) {
        String t = text.text;
        double x = text.x;
        double y = text.y;
        double w = textWidth(t);
        double h = textHeight(t);

        if (text.alignment == CENTER) {
            x -= w / 2;
        } else if (text.alignment == RIGHT) {
            x -= w;
        }
        y += h / 2;

        g2d.translate(text.translate.x, text.translate.y);
        g2d.scale(text.scale, text.scale);
        g2d.rotate(text.rotate);

        g2d.setColor(text.color);
        g2d.setFont(new Font(text.font, Font.PLAIN, (int) text.size));
        g2d.drawString(t, (int) x, (int) y);
    }

    public void text(String text, double x, double y) {
        TextApplet textApplet = new TextApplet(text, x, y, fillColor, textSize, textAlign, textFont, rotation,
                translation, scale);
        texts.add(textApplet);
    }

    public void text(String text, Point p) {
        text(text, p.x, p.y);
    }

    // Shape
    public void drawShape(ShapeApplet shape) {
        g2d.translate(shape.translate.x, shape.translate.y);
        g2d.scale(shape.scale, shape.scale);
        g2d.rotate(shape.rotate);

        if (shape.mode == RIGID) {
            g2d.setColor(shape.color);
            g2d.setStroke(new BasicStroke((float) shape.strokeWeight));
            for (int i = 0; i < shape.points.size() - 1; i++) {
                Point p1 = shape.points.get(i);
                Point p2 = shape.points.get(i + 1);
                g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);
            }
        } else if (shape.mode == SMOOTH) {
            // Catmull-Rom splines
            for (int i = 0; i < shape.points.size() - 3; i++) {
                Point p1 = shape.points.get(i);
                Point p2 = shape.points.get(i + 1);
                Point p3 = shape.points.get(i + 2);
                Point p4 = shape.points.get(i + 3);

                double x1 = p1.x;
                double y1 = p1.y;
                double x2 = p2.x;
                double y2 = p2.y;
                double x3 = p3.x;
                double y3 = p3.y;
                double x4 = p4.x;
                double y4 = p4.y;

                double t = 0;
                while (t <= 1) {
                    double x = 0.5 * ((2 * x2) + (-x1 + x3) * t + (2 * x1 - 5 * x2 + 4 * x3 - x4) * t * t
                            + (-x1 + 3 * x2 - 3 * x3 + x4) * t * t * t);
                    double y = 0.5 * ((2 * y2) + (-y1 + y3) * t + (2 * y1 - 5 * y2 + 4 * y3 - y4) * t * t
                            + (-y1 + 3 * y2 - 3 * y3 + y4) * t * t * t);
                    g2d.setColor(shape.color);
                    g2d.setStroke(new BasicStroke((float) shape.strokeWeight));
                    g2d.drawLine((int) x, (int) y, (int) x, (int) y);
                    t += 0.01;
                }
            }
        }
    }

    public void beginShape() {
        shape = new ShapeApplet(strokeColor, rotation, translation, scale, strokeWeight, 0);
    }

    public void beginShape(int mode) {
        shape = new ShapeApplet(strokeColor, rotation, translation, scale, strokeWeight, mode);
    }

    public void endShape() {
        shapes.add(shape);
        shape = null;
    }

    public void endShape(int endBehavior) {
        if (endBehavior == CLOSE) {
            shape.points.add(shape.points.get(0));
        }
        endShape();
    }

    public void vertex(double x, double y) {
        if (shape != null)
            shape.addPoint(x, y);
    }

    public void vertex(Point p) {
        vertex(p.x, p.y);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        this.g = g;

        if (drawBackground) {
            Color prevColor = g2d.getColor();
            g2d.setColor(backgroundColor);
            g2d.fillRect(0, 0, getWidth(), getHeight());
            g2d.setColor(prevColor);
        }
        for (EllipseApplet e : ellipses) {
            drawEllipse(e);
        }
        for (LineApplet l : lines) {
            drawLine(l);
        }
        for (QuadApplet q : quads) {
            drawQuad(q);
        }
        for (RectApplet r : rects) {
            drawRect(r);
        }
        for (TriangleApplet t : triangles) {
            drawTriangle(t);
        }
        for (TextApplet t : texts) {
            drawText(t);
        }
        for (ShapeApplet s : shapes) {
            drawShape(s);
        }
        delay(5);
        ellipses.clear();
        lines.clear();
        quads.clear();
        rects.clear();
        triangles.clear();
        texts.clear();
        shapes.clear();
    }

    public void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // ------------------------------------------------------------
    // ------------------------------------------------------------
    // ------------------------------------------------------------
    // PROCESSING FUNCTIONS

    // Conversion Functions
    public String binary(int n) {
        return Integer.toBinaryString(n);
    }

    public String binary(byte n) {
        return Integer.toBinaryString(n);
    }

    public String binary(char n) {
        return Integer.toBinaryString(n);
    }

    public String binary(Color c) {
        return Integer.toBinaryString(c.getRGB());
    }

    public boolean bool(int n) {
        return n != 0;
    }

    public boolean bool(String s) {
        return s.equals("true");
    }

    // Ignoring byte

    // Ignoring char

    // Ignoring float

    public String hex(byte n) {
        return Integer.toHexString(n);
    }

    public String hex(char n) {
        return Integer.toHexString(n);
    }

    public String hex(int n) {
        return Integer.toHexString(n);
    }

    public String hex(Color c) {
        return Integer.toHexString(c.getRGB());
    }

    // Ignoring int

    public String str(boolean b) {
        return Boolean.toString(b);
    }

    public String str(byte n) {
        return Byte.toString(n);
    }

    public String str(char n) {
        return Character.toString(n);
    }

    public String str(int n) {
        return Integer.toString(n);
    }

    public String str(float n) {
        return Float.toString(n);
    }

    public String str(Color c) {
        return c.toString();
    }

    public String str(Object o) {
        return o.toString();
    }

    public int unbinary(String s) {
        return Integer.parseInt(s, 2);
    }

    public int unhex(String s) {
        return Integer.parseInt(s, 16);
    }

    // String Functions
    public String join(String[] list, String separator) {
        String result = "";
        for (int i = 0; i < list.length; i++) {
            result += list[i];
            if (i < list.length - 1) {
                result += separator;
            }
        }
        return result;
    }

    // Ignoring matchAll and match (regex)

    // Ignoring nf, nfc, nfp, nfs, and splitTokens

    public String[] split(String str, char separator) {
        return str.split(Character.toString(separator));
    }

    public String[] split(String str, String separator) {
        return str.split(separator);
    }

    public String trim(String str) {
        return str.trim();
    }

    // Time and Date Functions
    public int day() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    public int hour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }

    public int minute() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }

    public int month() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }

    public int second() {
        return Calendar.getInstance().get(Calendar.SECOND);
    }

    public int year() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    public int millis() {
        return (int) (System.currentTimeMillis() - startTime);
    }

    // Math Functions
    public int abs(int n) {
        return Math.abs(n);
    }

    public float abs(float n) {
        return Math.abs(n);
    }

    public double abs(double n) {
        return Math.abs(n);
    }

    public int ceil(float n) {
        return (int) Math.ceil(n);
    }

    public int ceil(double n) {
        return (int) Math.ceil(n);
    }

    public double constrain(double value, double min, double max) {
        return Helper.constrain(value, min, max);
    }

    public double dist(double x1, double y1, double x2, double y2) {
        return Point.dist(x1, y1, x2, y2);
    }

    public double dist(Point p1, Point p2) {
        return Point.dist(p1, p2);
    }

    public double exp(double n) {
        return Math.exp(n);
    }

    public int floor(double n) {
        return (int) Math.floor(n);
    }

    public double lerp(double start, double stop, double amt) {
        return Helper.lerp(start, stop, amt);
    }

    public double ln(double n) {
        return Math.log(n);
    }

    public double log(double n) {
        return Math.log10(n);
    }

    public double mag(double a, double b) {
        return Math.sqrt(a * a + b * b);
    }

    public double map(double value, double min1, double max1, double min2, double max2) {
        return Helper.map(value, min1, max1, min2, max2);
    }

    public double max(double n1, double n2) {
        return Math.max(n1, n2);
    }

    public double min(double n1, double n2) {
        return Math.min(n1, n2);
    }

    public double norm(double value, double min, double max) {
        return Helper.norm(value, min, max);
    }

    public double pow(double n, double e) {
        return Math.pow(n, e);
    }

    public int round(double n) {
        return (int) Math.round(n);
    }

    public double sq(double n) {
        return n * n;
    }

    public double sqrt(double n) {
        return Math.sqrt(n);
    }

    // Trigonometry Functions
    public double acos(double n) {
        return Math.acos(n);
    }

    public double asin(double n) {
        return Math.asin(n);
    }

    public double atan(double n) {
        return Math.atan(n);
    }

    public double atan2(double y, double x) {
        return Math.atan2(y, x);
    }

    public double cos(double angle) {
        return Math.cos(angle);
    }

    public double sin(double angle) {
        return Math.sin(angle);
    }

    public double tan(double angle) {
        return Math.tan(angle);
    }

    public double degrees(double radians) {
        return Math.toDegrees(radians);
    }

    public double radians(double degrees) {
        return Math.toRadians(degrees);
    }

    // Random Functions
    public double random(double high) {
        return Math.random() * high;
    }

    public double random(double low, double high) {
        return low + Math.random() * (high - low);
    }

    public double random() {
        return Math.random();
    }

    public double randomGaussian() {
        return new Random().nextGaussian();
    }

    public double noise(double x, double y, double z, double w) {
        return Helper.perlinNoise(x, y, z, w);
    }

    public double noise(double x, double y, double z) {
        return Helper.perlinNoise(x, y, z);
    }

    public double noise(double x, double y) {
        return Helper.perlinNoise(x, y);
    }

    public double noise(double x) {
        return noise(x, 0);
    }

    // Color
    public int alpha(Color color) {
        return color.getAlpha();
    }

    public int blue(Color color) {
        return color.getBlue();
    }

    public int green(Color color) {
        return color.getGreen();
    }

    public int red(Color color) {
        return color.getRed();
    }

    public double brightness(Color color) {
        return (color.getRed() + color.getGreen() + color.getBlue()) / 3;
    }

    public Color lerpColor(Color c1, Color c2, double amt) {
        return new Color(
                (int) Helper.lerp(c1.getRed(), c2.getRed(), amt),
                (int) Helper.lerp(c1.getGreen(), c2.getGreen(), amt),
                (int) Helper.lerp(c1.getBlue(), c2.getBlue(), amt),
                (int) Helper.lerp(c1.getAlpha(), c2.getAlpha(), amt));
    }

    public double saturation(Color color) {
        float[] hsb = Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), null);
        return hsb[1];
    }

    /**
     * WARNING: Calls the draw method of the sketch to load everything
     * 
     * @param filename
     */
    public void save(String filename) {
        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = image.createGraphics();
        draw();
        frame.paint(g);

        try {
            ImageIO.write(image, "png", new File(filename));
        } catch (IOException e) {
        }
        g.dispose();
    }

    public void saveStrings(String[] lines, String filename) {
        try {
            Files.write(Paths.get(filename), Arrays.asList(lines));
        } catch (IOException e) {
        }
    }
}
