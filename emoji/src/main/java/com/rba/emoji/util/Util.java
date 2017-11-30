package com.rba.emoji.util;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.view.Display;
import android.view.WindowManager;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Ricardo Bravo on 29/11/2017.
 */

public final class Util {

    private static int keyboardHeight = 200;

    private Util() {
    }

    private static final AtomicInteger sNextGeneratedId = new AtomicInteger(1);

    public static int generateViewId() {
        for (; ; ) {
            final int result = sNextGeneratedId.get();
            // aapt-generated IDs have the high byte nonzero; clamp to the range under that.
            int newValue = result + 1;
            if (newValue > 0x00FFFFFF) newValue = 1; // Roll over to 1, not 0.
            if (sNextGeneratedId.compareAndSet(result, newValue)) {
                return result;
            }
        }
    }

    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.y;
    }

    public static int getScreenWidth(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }

    public static boolean isKeyboardShowing(Context context) {
        return context.getResources().getConfiguration().keyboardHidden == Configuration.KEYBOARDHIDDEN_NO;
    }

    public static void setKeyboardHeight(int keyboardHeight) {
        Util.keyboardHeight = keyboardHeight;
    }

    public static int getKeyboardHeight() {
        return keyboardHeight;
    }
}