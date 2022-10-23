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

    private JFrame frame;
    private Graphics2D g2d;

    public final int CENTER = 0;
    public final int CORNER = 4;
    public final int LEFT = 1;
    public final int RIGHT = 3;

    private int rectMode = CORNER;
    private int ellipseMode = CORNER;
    private int textAlign = LEFT;

    private Color fillColor = Color.BLACK;
    private Color strokeColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;

    private double strokeWeight = 1;
    private double textSize = 12;
    private String textFont = "Arial";

    private double rotation = 0;
    private Point translation = Point.zero();
    private double scale = 1;

    public double mouseX = 0;
    public double mouseY = 0;
    public Point mouse = Point.zero();
    public double pmouseX = 0;
    public double pmouseY = 0;
    public Point pmouse = Point.zero();

    public int width;
    public int height;
    public int displayWidth;
    public int displayHeight;
    private double universalScale = 1;

    public boolean mousePressed = false;
    public int mouseButton = CENTER;
    public String key = "";
    public int keyCode = 0;

    // Drawing
    private ArrayList<AppletComponent> components = new ArrayList<AppletComponent>();
    private boolean drawBackground = false;

    // Constants
    public final double PI = Math.PI;
    public final double HALF_PI = Math.PI / 2;
    public final double QUARTER_PI = Math.PI / 4;
    public final double TWO_PI = Math.PI * 2;
    public final double TAU = Math.PI * 2;

    // Shape
    private ShapeApplet shape;
    public final int SMOOTH = 1;
    public final int RIGID = 2;
    public final int CLOSE = 0;

    // FPS
    private double targetFrameRate = 60;
    private ArrayList<Double> frameRates = new ArrayList<Double>();
    public double frameRate = 0;
    private double lastTime = 0;
    public int frameCount = 0;
    private boolean displayFrameRate = false;
    private double startTime = 0;

    // Random noise offsets
    private double noiseXOffset = 0;
    private double noiseYOffset = 0;
    private double noiseZOffset = 0;
    private double noiseWOffset = 0;

    private boolean aliasing = false;

    private boolean exitOnEscape = true;

    public void size(double width, double height) {
        if (displayWidth == 0) {
            Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
            displayWidth = (int) displaySize.getWidth();
            displayHeight = (int) displaySize.getHeight();
        }
        universalScale = displayWidth / width;

        setDoubleBuffered(true);

        this.width = (int) width;
        this.height = (int) height;

        Dimension size = new Dimension(this.width, this.height);
        setMinimumSize(size);

        frame = new JFrame("Sketch");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(this);

        frame.pack(); // completely changes the size of this
        frame.setVisible(true);

        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();

        addListeners();

        noiseXOffset = Math.random() * 1000;
        noiseYOffset = Math.random() * 1000;
        noiseZOffset = Math.random() * 1000;
        noiseWOffset = Math.random() * 1000;
    }

    /**
     * Note: Automatically sets width to 1920, and height to the same ratio as your
     * display.
     */
    public void fullScreen() {
        Dimension displaySize = Toolkit.getDefaultToolkit().getScreenSize();
        displayWidth = (int) displaySize.getWidth();
        displayHeight = (int) displaySize.getHeight();

        this.width = 1920;
        this.height = (int) (displayHeight * (1920.0 / displayWidth));

        size(width, height);

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // Slightly changes the size
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
        });

        frame.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseMoved(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
                mouseX /= universalScale;
                mouseY /= universalScale;
                mouseX -= 8 / universalScale;
                mouseY -= 31 / universalScale;
                mouse = new Point(mouseX, mouseY);
                mouseMove();
            }

            public void mouseDragged(MouseEvent evt) {
                mouseX = evt.getX();
                mouseY = evt.getY();
                mouseX /= universalScale;
                mouseY /= universalScale;
                mouseX -= 8 / universalScale;
                mouseY -= 31 / universalScale;
                mouseDrag();
            }
        });

        frame.addMouseWheelListener(new MouseWheelListener() {
            public void mouseWheelMoved(MouseWheelEvent evt) {
                int notches = evt.getWheelRotation();
                mouseScroll(notches);
            }
        });

        frame.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent evt) {
                keyCode = evt.getKeyCode();
                String keyText = KeyEvent.getKeyText(evt.getKeyCode());
                key = keyText;

                keyPress();

                if (evt.getKeyCode() == KeyEvent.VK_ESCAPE && exitOnEscape) {
                    exit();
                }
            }

            public void keyReleased(KeyEvent evt) {
                keyCode = evt.getKeyCode();
                String keyText = KeyEvent.getKeyText(evt.getKeyCode());
                key = keyText;

                keyRelease();
            }

            public void keyTyped(KeyEvent evt) {
                keyType();
            }
        });
    }

    // Mouse inputs (protected so that they can be overridden)
    protected void mousePress() {
        mousePressed();
    }

    protected void mousePressed() {

    }

    protected void mouseRelease() {
        mouseReleased();
    }

    protected void mouseReleased() {

    }

    protected void mouseClick() {
        mouseClicked();
    }

    protected void mouseClicked() {

    }

    protected void mouseDrag() {
        mouseDragged();
    }

    protected void mouseDragged() {

    }

    protected void mouseScroll(int amount) {
        mouseScrolled(amount);
    }

    protected void mouseScrolled(int amount) {

    }

    protected void mouseMove() {
        mouseMoved();
    }

    protected void mouseMoved() {

    }

    // Keyboard inputs
    protected void keyPress() {
        keyPressed();
    }

    protected void keyPressed() {

    }

    protected void keyRelease() {
        keyReleased();
    }

    protected void keyReleased() {

    }

    protected void keyType() {
        keyTyped();
    }

    /**
     * Warning: This method is not called when the user presses any modifier (e.g.
     * shift, ctrl, alt, backspace).
     * 
     * @param key
     */
    protected void keyTyped() {

    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMinimumSize() {
        return new Dimension(width, height);
    }

    @Override
    public Dimension getMaximumSize() {
        return new Dimension(width, height);
    }

    public void setup() {
        // Empty
    }

    public void draw() {
        // Empty
    }

    public void aliasing() {
        aliasing = true;
    }

    public void backendUpdate() {
        double nextFrameRate = 1000.0 / (millis() - lastTime);
        if (frameRates.size() < 10) {
            frameRates.add(nextFrameRate);
        } else {
            frameRates.remove(0);
            frameRates.add(nextFrameRate);
        }
        frameRate = 0;
        for (double frameRate : frameRates) {
            this.frameRate += frameRate;
        }
        this.frameRate /= frameRates.size();
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

        repaint(); // Calling repaint() causes a lot of lag, so we'll just call
        // paint() instead
        // paintComponent(getGraphics()); // Kinda hacky, but it works

        rotation = 0;
        translation = Point.zero();
        scale = 1;

        displayFrameRate = false;
        drawBackground = false;
    }

    public void exitOnEscape(boolean exitOnEscape) {
        this.exitOnEscape = exitOnEscape;
    }

    public void frameRate(double targetFrameRate) {
        this.targetFrameRate = targetFrameRate;
    }

    public void displayFrameRate() {
        displayFrameRate = true;
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
        components.add(ellipse);
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
        components.add(line);
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
        components.add(quad);
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
        g2d.fill(new RoundRectangle2D.Double(x, y, w, h, rect.roundness, rect.roundness));
        // Stroke
        g2d.setColor(rect.strokeColor);
        g2d.setStroke(new BasicStroke((float) rect.strokeWeight));
        g2d.draw(new RoundRectangle2D.Double(x, y, w, h, rect.roundness, rect.roundness));
    }

    public void rect(double x, double y, double w, double h, double r) {
        RectApplet rect = new RectApplet(x, y, w, h, strokeColor, fillColor, rectMode, strokeWeight, r, rotation,
                translation, scale);
        components.add(rect);
    }

    public void rect(double x, double y, double w, double h) {
        RectApplet rect = new RectApplet(x, y, w, h, strokeColor, fillColor, rectMode, strokeWeight, 1, rotation,
                translation, scale);
        components.add(rect);
    }

    public void rect(Point p, double w, double h, double r) {
        rect(p.x, p.y, w, h, r);
    }

    public void rect(Point p, double w, double h) {
        rect(p.x, p.y, w, h);
    }

    public void rect(Point p, Point s, double r) {
        rect(p.x, p.y, s.x, s.y, r);
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
        components.add(triangle);
    }

    public void triangle(Point p1, Point p2, Point p3) {
        triangle(p1.x, p1.y, p2.x, p2.y, p3.x, p3.y);
    }

    // Text
    public void drawText(TextApplet text) {
        String t = text.text;
        double x = text.x;
        double y = text.y;

        g2d.setFont(new Font(text.font, Font.PLAIN, (int) text.size));
        double w = g2d.getFontMetrics().stringWidth(t);
        double h = g2d.getFontMetrics().getHeight();

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
        g2d.drawString(t, (int) x, (int) y);
    }

    public void text(String text, double x, double y) {
        TextApplet textApplet = new TextApplet(text, x, y, fillColor, textSize, textAlign, textFont, rotation,
                translation, scale);
        components.add(textApplet);
    }

    public void text(Object text, double x, double y) {
        text(text.toString(), x, y);
    }

    public void text(String text, Point p) {
        text(text, p.x, p.y);
    }

    public void text(Object text, Point p) {
        text(text.toString(), p.x, p.y);
    }

    // Shape
    public void drawShape(ShapeApplet shape) {
        g2d.translate(shape.translate.x, shape.translate.y);
        g2d.scale(shape.scale, shape.scale);
        g2d.rotate(shape.rotate);

        if (shape.mode == RIGID) {
            int[] xPoints = new int[shape.points.size()];
            int[] yPoints = new int[shape.points.size()];

            g2d.setColor(shape.strokeColor);
            g2d.setStroke(new BasicStroke((float) shape.strokeWeight));
            for (int i = 0; i < shape.points.size() - 1; i++) {
                Point p1 = shape.points.get(i);
                Point p2 = shape.points.get(i + 1);
                g2d.drawLine((int) p1.x, (int) p1.y, (int) p2.x, (int) p2.y);

                xPoints[i] = (int) p1.x;
                yPoints[i] = (int) p1.y;
            }
            xPoints[shape.points.size() - 1] = (int) shape.points.get(shape.points.size()
                    - 1).x;
            yPoints[shape.points.size() - 1] = (int) shape.points.get(shape.points.size()
                    - 1).y;
            // Draw the filled in shape
            g2d.setColor(shape.fillColor);
            g2d.fillPolygon(xPoints, yPoints, shape.points.size());
        } else if (shape.mode == SMOOTH) {
            // Catmull-Rom splines smoothing algorithm

            g2d.setColor(shape.strokeColor);
            g2d.setStroke(new BasicStroke((float) shape.strokeWeight));

            ArrayList<Integer> xPoints = new ArrayList<Integer>();
            ArrayList<Integer> yPoints = new ArrayList<Integer>();

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
                    int xInt = (int) x;
                    int yInt = (int) y;
                    g2d.drawLine(xInt, yInt, xInt, yInt);
                    xPoints.add(xInt);
                    yPoints.add(yInt);
                    t += 0.01;
                }
            }

            // Draw the filled in shape
            g2d.setColor(shape.fillColor);
            g2d.fillPolygon(Helper.toIntArray(xPoints), Helper.toIntArray(yPoints), xPoints.size());
        }
    }

    public void beginShape() {
        shape = new ShapeApplet(strokeColor, fillColor, rotation, translation, scale, strokeWeight, RIGID);
    }

    public void beginShape(int mode) {
        shape = new ShapeApplet(strokeColor, fillColor, rotation, translation, scale, strokeWeight, mode);
    }

    public void endShape() {
        components.add(shape);
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

    private void drawSpecificShape(AppletComponent component) {
        if (component instanceof EllipseApplet) {
            drawEllipse((EllipseApplet) component);
        } else if (component instanceof LineApplet) {
            drawLine((LineApplet) component);
        } else if (component instanceof QuadApplet) {
            drawQuad((QuadApplet) component);
        } else if (component instanceof RectApplet) {
            drawRect((RectApplet) component);
        } else if (component instanceof TriangleApplet) {
            drawTriangle((TriangleApplet) component);
        } else if (component instanceof TextApplet) {
            drawText((TextApplet) component);
        } else if (component instanceof ShapeApplet) {
            drawShape((ShapeApplet) component);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g2d = (Graphics2D) g;

        g2d.scale(universalScale, universalScale);

        if (aliasing)
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

        if (drawBackground) {
            Color prevColor = g2d.getColor();
            g2d.setColor(backgroundColor);
            g2d.fillRect(0, 0, width, height);
            g2d.setColor(prevColor);
        }
        for (int i = 0; i < components.size(); i++) {
            drawSpecificShape(components.get(i));
        }
        components.clear();

        if (displayFrameRate) {
            // Set drawing modes to default but save values to restore later
            int rectM = rectMode;
            rectMode(CORNER);
            int textA = textAlign;
            textAlign(LEFT);
            double textS = textSize;
            g2d.setFont(new Font("Arial", Font.PLAIN, 12));

            Color prevColor = g2d.getColor();

            String fps = "FPS: " + Helper.roundString(frameRate, 0);

            double wid = textWidth(fps);

            g2d.setColor(new Color(0, 0, 0, 100));
            g2d.fillRect(width - (int) wid - 10, 0, (int) wid + 10, 20);

            g2d.setColor(new Color(255, 255, 255));
            g2d.drawString(fps, width - (int) wid - 5, 15);

            g2d.setColor(prevColor);

            rectMode(rectM);
            textAlign(textA);
            textSize(textS);
        }
    }

    public void delay(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void exit() {
        frame.dispose();
        System.exit(0);
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
        return Helper.perlinNoise(x + noiseXOffset, y + noiseYOffset, z + noiseZOffset, w + noiseWOffset);
    }

    public double noise(double x, double y, double z) {
        return Helper.perlinNoise(x + noiseXOffset, y + noiseYOffset, z + noiseZOffset);
    }

    public double noise(double x, double y) {
        return Helper.perlinNoise(x + noiseXOffset, y + noiseYOffset);
    }

    public double noise(double x) {
        return noise(x + noiseXOffset, 0);
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
