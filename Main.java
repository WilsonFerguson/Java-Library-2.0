import java.awt.Color;
import java.awt.*;

class Main extends Applet {

    public static double x = 0;

    public static void main(String[] args) {
        new Window(new Main());
    }

    public void setup() {
        fullScreen(); // No flickering on laptop
        frameRate(60);
    }

    public void draw() {
        background(100);
        displayFrameRate();

        fill(255, 0, 0);
        stroke(255);
        strokeWeight(1);

        x += 1;
        // translate(x, 100);
        // rotate(x);
        rectMode(CORNER);
        rect(1920 - 100, 100, 100, 100, 20); // 0, 0

        // translate(0, 0);
        // rotate(0);
        strokeWeight(10);
        stroke(Color.blue);
        point(mouseX, mouseY);

        stroke(255);
        strokeWeight(10);
        beginShape(SMOOTH);
        vertex(336, 364);
        vertex(336, 364);
        vertex(272, 76);
        vertex(84, 68);
        vertex(128, 400);
        vertex(128, 400);
        endShape();
    }

}
