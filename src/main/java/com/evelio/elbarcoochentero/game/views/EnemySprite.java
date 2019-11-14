package com.evelio.elbarcoochentero.game.views;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;

public class EnemySprite extends Sprite {


    private Bitmap image;
    private int x;
    private int y = 0;
    private int height;
    private int width;
    private int xVelocity;
    private int yVelocity;
    private int dt = 0;
    private double theta;

    public EnemySprite(Bitmap bmp, double theta){
        image = bmp;

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
