package com.smartelephantapps.flappyandroid.input;

// TODO: Better Use Androids Motion Events Constants instead of redefining everything.
public class Input {

    public static final int MAX_STATES = 3;

    public static final int TOUCH_DOWN = 0;
    public static final int TOUCH_MOVE = 1;
    public static final int TOUCH_UP = 2;

    public static float x, y;

    private static boolean[] states = new boolean[MAX_STATES];

    static {
        states[TOUCH_DOWN] = false;
        states[TOUCH_MOVE] = false;
        states[TOUCH_UP] = false;
    }

    public static boolean getState(int event) {
        if (event < 0 || event > MAX_STATES - 1) return false;

        boolean temp = states[event];

        // Reset for next frame
        states[event] = false;

        return temp;
    }

    public static void setState(int event, boolean state) {
        if (event < 0 || event > MAX_STATES - 1) return;

        states[event] = state;
    }
}
