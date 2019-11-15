package com.evelio.elbarcoochentero.game.util;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;

public class Constants {

    public static Context CURRENT_CONTEXT;
    public static int SCREEN_HEIGHT = Resources.getSystem().getDisplayMetrics().heightPixels;
    public static int SCREEN_WIDTH = Resources.getSystem().getDisplayMetrics().widthPixels;
    public static long INIT_TIME;

    public static Bitmap ENEMY_SPRITE;
    public static Bitmap OBSTACLE_SPRITE;
    public static Bitmap HEART_SPRITE;
}
