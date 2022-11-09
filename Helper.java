import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class Helper {

    /**
     * Returns a {@code float} distance between two points given {@code int} x1,
     * {@code int} y1, {@code int} x2, and {@code int} y2.
     * 
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return float
     */
    public static float getDistance(float x1, float y1, float x2, float y2) {
        return (float) Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

    /**
     * Returns a {@code float} distance between two points given {@code Point} p1
     * and {@code Point} p2.
     * 
     * @param p1
     * @param p2
     * @return float
     */
    public static float getDistance(Point p1, Point p2) {
        return getDistance(p1.x, p1.y, p2.x, p2.y);
    }

    /**
     * Returns a {@code float} random value between a {@code float} min and
     * {@code float} max.
     * 
     * @param min
     * @param max
     * @return float
     */
    public static float random(float min, float max) {
        return (float) (Math.random() * (max - min) + min);
    }

    /**
     * Returns a {@code float} random value between a {@code int} min and
     * {@code int} max.
     * 
     * @param min
     * @param max
     * @return int
     */
    public static int random(int min, int max) {
        return (int) random((float) min, (float) max);
    }

    /**
     * Returns a {@code float} random value between 0 and {@code float} max.
     * 
     * @param max
     * @return float
     */
    public static float random(float max) {
        return random(0, max);
    }

    /**
     * Returns a {@code float} random value between 0 and {@code int} max.
     * 
     * @param max
     * @return int
     */
    public static int random(int max) {
        return random(0, max);
    }

    /**
     * Returns a {@code float} random value between 0 and 1.
     * 
     * @return float
     */
    public static float random() {
        return random(1);
    }

    /**
     * Returns a {@code String} rounded number given a {@code float} number and
     * {@code int} decimal places.
     * 
     * @param num
     * @param places
     * @return String
     */
    public static String roundString(float num, int places) {
        float factor = (float) Math.pow(10, places);
        float rounded = Math.round(num * factor) / factor;
        if (rounded % 1 == 0)
            return Integer.toString((int) rounded);

        return Float.toString(rounded);
    }

    /**
     * Waits a given {@code float} milliseconds. {@code float} milliseconds are
     * converted to {@code long} milliseconds.
     * 
     * @param millis
     */
    public static void wait(float millis) {
        try {
            Thread.sleep((long) millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Prints every {@code Object} argument given with a space in between. Prints a
     * new line at the end.
     * 
     * @param args
     */
    public static void println(Object... args) {
        for (Object arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }

    /**
     * Prints a given {@code Object[]} with spaces in between each element. Prints a
     * new line at the end.
     * 
     * @param args
     */
    public static void printArray(Object[] args) {
        for (Object arg : args) {
            System.out.print(arg + " ");
        }
        System.out.println();
    }

    /**
     * Maps a given {@code float} value from one range to another.
     * 
     * @param value
     * @param start1
     * @param stop1
     * @param start2
     * @param stop2
     * @return float
     */
    public static float map(float value, float start1, float stop1, float start2, float stop2) {
        return start2 + (stop2 - start2) * ((value - start1) / (stop1 - start1));
    }

    /**
     * Maps a given {@code int} value from one range to another.
     * 
     * @param value
     * @param start1
     * @param stop1
     * @param start2
     * @param stop2
     * @return int
     */
    public static int map(int value, int start1, int stop1, int start2, int stop2) {
        return (int) map((float) value, (float) start1, (float) stop1, (float) start2, (float) stop2);
    }

    /**
     * Returns true if the given {@code String} is an integer.
     * 
     * @param str
     * @return boolean
     */
    public static boolean isInt(String str) {
        int decimalIndex = str.indexOf('.');
        if (decimalIndex == -1) {
            try {
                Integer.parseInt(str);
                return true;
            } catch (NumberFormatException e) {
                return false;
            }
        }
        String afterDecimal = str.substring(decimalIndex + 1);
        return afterDecimal.equals("0");
    }

    /**
     * Returns true if the given {@code Object} is an int. Based on
     * {@link #isInt(String)}.
     * 
     * @param obj
     * @return
     */
    public static boolean isInt(Object obj) {
        return isInt(obj.toString());
    }

    /**
     * Returns a String representation of the given {@code Object} array. The String
     * will not contain brackets, each element is separated by a space.
     * 
     * @param arr
     * @return String
     */
    public static String toString(Object[] arr) {
        String str = "";
        for (int i = 0; i < arr.length; i++) {
            str += arr[i];
            if (i < arr.length - 1)
                str += " ";
        }
        return str + "";
    }

    /**
     * Converts an {@code ArrayList<Integer>} to an {@code int[]}.
     * 
     * @param arr
     * @return int[]
     */
    public static int[] toIntArray(ArrayList<Integer> arr) {
        int[] intArr = new int[arr.size()];
        for (int i = 0; i < arr.size(); i++) {
            intArr[i] = arr.get(i);
        }
        return intArr;
    }

    /**
     * Returns true if the given {@code String[]} array contains the given
     * {@code String} element.
     * 
     * @param arr
     * @param element
     * @return boolean
     */
    public static boolean contains(String[] arr, String element) {
        for (String str : arr) {
            if (str.equals(element))
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given {@code int[]} array contains the given {@code int}
     * element.
     * 
     * @param arr
     * @param element
     * @return boolean
     */
    public static boolean contains(int[] arr, int element) {
        for (int i : arr) {
            if (i == element)
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given {@code float[]} array contains the given
     * {@code float} element.
     * 
     * @param arr
     * @param element
     * @return boolean
     */
    public static boolean contains(float[] arr, float element) {
        for (float d : arr) {
            if (d == element)
                return true;
        }
        return false;
    }

    /**
     * Returns true if the given {@code Object} classObject contains a
     * {@code String} method.
     * <p>
     * Note: This will only work with methods that do not have parameters.
     * </font>
     * 
     * @param classObject
     * @param methodName
     * @return boolean
     */
    public static boolean containsClass(Object classObject, String methodName) {
        Class<?> class1 = classObject.getClass();
        try {
            class1.getDeclaredMethod(methodName, (Class<?>[]) null);
            return true;
        } catch (NoSuchMethodException | SecurityException e) {
            // Doesn't have class
            return false;
        }
    }

    /**
     * Returns a {@code String} of the given {@code KeyEvent}. Examples of non-alpha
     * keys: "Backspace" "Control" "Space" "Up" "F5"
     * 
     * @param evt
     * @return String
     */
    public static String keyEventToString(KeyEvent evt) {
        String key = String.valueOf(evt.getKeyChar());
        int keyCode = evt.getKeyCode();
        int[] possibleKeyCodes = { 8, 10, 16, 17, 18, 27, 32, 37, 38, 39, 40, 112, 113, 114, 115, 116, 117, 118, 119,
                120, 121, 122, 123 };
        String[] possibleKeys = { "Backspace", "Enter", "Shift", "Control", "Alt", "Escape", "Space", "Left", "Up",
                "Right", "Down", "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12" };
        for (int i = 0; i < possibleKeyCodes.length; i++) {
            if (keyCode == possibleKeyCodes[i]) {
                key = possibleKeys[i];
                break;
            }
        }
        return key;
    }

    /**
     * Returns an {@code int} keycode of the given {@code KeyEvent}.
     * 
     * @param evt
     * @return int
     */
    public static int keyEventToKeyCode(KeyEvent evt) {
        return evt.getKeyCode();
    }

    /**
     * Returns a constrained {@code float} value.
     */
    public static float constrain(float value, float min, float max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    /**
     * Returns a constrained {@code int} value.
     */
    public static int constrain(int value, int min, int max) {
        if (value < min)
            return min;
        if (value > max)
            return max;
        return value;
    }

    /**
     * Lerps between two {@code float} values.
     * 
     * @param start
     * @param stop
     * @param amt
     * 
     * @return float
     */
    public static float lerp(float start, float stop, float amt) {
        return start + (stop - start) * amt;
    }

    /**
     * Normalizes a value from an arbitrary range to a value between 0 and 1.
     * 
     * @param value
     * @param min
     * @param max
     * 
     * @return float
     */
    public static float norm(float value, float min, float max) {
        return (value - min) / (max - min);
    }

    /**
     * Returns a smooth {@code float} value between -1 and 1 given a {@code float}
     * x and {@code float} y.
     * 
     * @param x
     * @param y
     * @return float
     */
    public static float perlinNoise(float x, float y) {
        return (float) PerlinNoise.noise(x, y);
    }

    /**
     * Returns a smooth {@code float} value between -1 and 1 given a {@code float}
     * x, {@code float} y, and {@code float} z.
     * 
     * @param x
     * @param y
     * @param z
     * @return float
     */
    public static float perlinNoise(float x, float y, float z) {
        return (float) PerlinNoise.noise(x, y, z);
    }

    /**
     * Returns a smooth {@code float} value between -1 and 1 given a {@code float}
     * x, {@code float} y, {@code float} z, and {@code float} w.
     * 
     * @param x
     * @param y
     * @param z
     * @param w
     * @return float
     */
    public static float perlinNoise(float x, float y, float z, float w) {
        return (float) PerlinNoise.noise(x, y, z, w);
    }

    // Math Functions
    /**
     * Returns the absolute value of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float abs(float value) {
        return Math.abs(value);
    }

    /**
     * Returns the absolute value of the given {@code int} value.
     * 
     * @param value
     * @return int
     */
    public static int abs(int value) {
        return Math.abs(value);
    }

    /**
     * Returns the cosine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float cos(float value) {
        return (float) Math.cos(value);
    }

    /**
     * Returns the sine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float sin(float value) {
        return (float) Math.sin(value);
    }

    /**
     * Returns the tangent of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float tan(float value) {
        return (float) Math.tan(value);
    }

    /**
     * Returns the arc cosine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float acos(float value) {
        return (float) Math.acos(value);
    }

    /**
     * Returns the arc sine of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float asin(float value) {
        return (float) Math.asin(value);
    }

    /**
     * Returns the arc tangent of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float atan(float value) {
        return (float) Math.atan(value);
    }

    /**
     * Returns the arc tangent of the given {@code float} y and {@code float} x.
     * 
     * @param y
     * @param x
     * @return float
     */
    public static float atan2(float y, float x) {
        return (float) Math.atan2(y, x);
    }

    /**
     * Returns the square root of the given {@code float} value.
     * 
     * @param value
     * @return float
     */
    public static float sqrt(float value) {
        return (float) Math.sqrt(value);
    }

    /**
     * Returns the given {@code float} value raised to the given {@code float}
     * 
     * @param value
     * @param power
     * @return float
     */
    public static float pow(float value, float power) {
        return (float) Math.pow(value, power);
    }
}
