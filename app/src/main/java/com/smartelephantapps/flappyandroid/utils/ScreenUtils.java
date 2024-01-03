package com.smartelephantapps.flappyandroid.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public final class ScreenUtils {

    private ScreenUtils() {}

    public static float getAspectRatio(Context context, boolean inverse) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);

        if (windowManager == null) return 0f;

        DisplayMetrics metrics = new DisplayMetrics();

        windowManager.getDefaultDisplay().getMetrics(metrics);

        int width = metrics.widthPixels;
        int height = metrics.heightPixels;

        return inverse == false ?  (float) width / height : (float) height / width;
    }
}
