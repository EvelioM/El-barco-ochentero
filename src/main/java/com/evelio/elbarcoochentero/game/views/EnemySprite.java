package com.evelio.elbarcoochentero.game.views;

import android.graphics.Bitmap;
import android.graphics.Canvas;

import com.evelio.elbarcoochentero.game.util.Constants;

public class EnemySprite extends Sprite {


    private Bitmap image;
    private int x;
    private int y = 0;
    private int height;
    private int width;
    private int xVelocity;
    private int yVelocity;
    private double dt = 0;
    private double theta;

    public EnemySprite(double theta, int x){

        image = Constants.ENEMY_SPRITE;

        width = image.getWidth();
        height = image.getHeight();

        yVelocity = 4;
        xVelocity = 0;

        this.x = x;
        y = -height;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    @Override
    public void update() {
        xVelocity = (int) (7* Math.sin(0.03*dt + theta));

        y += yVelocity;
        x += xVelocity;

        dt++;
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
