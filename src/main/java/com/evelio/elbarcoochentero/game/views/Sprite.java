package com.evelio.elbarcoochentero.game.views;

import android.content.res.Resources;
import android.graphics.Canvas;

public abstract class Sprite {

    public abstract void draw(Canvas canvas);
    public abstract void update();
    public abstract int getRightX();
    public abstract int getTopY();
    public abstract int getLeftX();
    public abstract int getBotY();


}
