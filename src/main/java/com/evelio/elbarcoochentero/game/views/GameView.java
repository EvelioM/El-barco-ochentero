package com.evelio.elbarcoochentero.game.views;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.evelio.elbarcoochentero.game.threads.MainThread;

import com.evelio.elbarcoochentero.game.util.Constants;

public class GameView extends SurfaceView implements SurfaceHolder.Callback{

    public MainThread thread;
    private Sprite characterSprite;

    public GameView(Context context) {
        super(context);

        getHolder().addCallback(this);

        Constants.CURRENT_CONTEXT = context;

        thread = new MainThread(getHolder(), this);
        setFocusable(true);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {

        Constants.INIT_TIME = System.currentTimeMillis();

        characterSprite = new CharacterSprite();
        thread.setRunning(true);
        thread.start();

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        while (retry) {
            try {
                thread.setRunning(false);
                thread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = false;
        }
    }

    public void update() {
        characterSprite.update();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        characterSprite.draw(canvas);
    }

}
