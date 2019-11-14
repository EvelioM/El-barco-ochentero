package com.evelio.elbarcoochentero.game.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.evelio.elbarcoochentero.game.util.Constants;

public class ObstacleSprite extends Sprite {

    private Bitmap image;
    private int x;
    private int y = 0;
    private int height;
    private int width;

    public ObstacleSprite(Bitmap bmp) {
        image = bmp;
        x = Constants.SCREEN_WIDTH / 2;
        y = (int)(Constants.SCREEN_HEIGHT * 0.8);
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void update() {

    }

    @Override
    public int getRightX() {
        return x + width;
    }

    @Override
    public int getTopY() {
        return y;
    }

    @Override
    public int getLeftX() {
        return x;
    }

    @Override
    public int getBotY() {
        return y+height;
    }


}
