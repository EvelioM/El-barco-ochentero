package com.evelio.elbarcoochentero.game.data;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.evelio.elbarcoochentero.game.util.Constants;

public class OrientationData implements SensorEventListener {

    private SensorManager manager;
    private Sensor accelerometer;
    private Sensor magnometer;

    private float[] accelOut;
    private float[] magOut;
    private float[] orientation = new float[3];
    private float[] startOrientation = null;

    public float[] getOrientation(){
        return orientation;
    }

    public float[] getStartOrientation() {
        return startOrientation;
    }

    public void newGame(){
        startOrientation = null;
    }

    public OrientationData(){
        manager = (SensorManager) Constants.CURRENT_CONTEXT.getSystemService(Context.SENSOR_SERVICE);
        accelerometer = manager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        magnometer = manager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void register(){
        manager.registerListener(this,accelerometer,SensorManager.SENSOR_DELAY_GAME);
        manager.registerListener(this, magnometer, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if(event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            accelOut = event.values;
        }else if(event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            magOut = event.values;
        }

        if(accelOut != null && magOut != null){
            float[] R = new float[9];
            float[] I = new float[9];

            boolean success = SensorManager.getRotationMatrix(R,I,accelOut,magOut);

            if(success){
                SensorManager.getOrientation(R, orientation);

                if(startOrientation == null){
                    startOrientation = new float[orientation.length];
                    System.arraycopy(orientation, 0, startOrientation, 0, orientation.length);
                }
            }

        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
