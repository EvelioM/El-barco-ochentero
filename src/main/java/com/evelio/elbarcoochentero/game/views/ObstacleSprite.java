package com.evelio.elbarcoochentero.game.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.evelio.elbarcoochentero.game.util.Constants;

public class ObstacleSprite extends Sprite {

    private final int yVelocity;
    private Bitmap image;
    private int x;
    private int y = 0;
    private int height;
    private int width;

    public ObstacleSprite(int x) {

        image = Constants.OBSTACLE_SPRITE;

        width = image.getWidth();
        height = image.getHeight();

        yVelocity = 4;

        this.x = x;
        y = -height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update() {
        y += yVelocity;
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
