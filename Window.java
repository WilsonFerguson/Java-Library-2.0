public class Window {

    Applet applet;

    public Window(Applet applet) {
        this.applet = applet;
        applet.setup();

        while (true) {
            applet.backendUpdate();
            applet.draw();
        }
    }

}
