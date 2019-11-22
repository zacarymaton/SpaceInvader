package com.example.graficos.spaceinvader.Controller;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Jugar implements SensorEventListener {
    private SensorManager miMananger;
    private Sensor miSensor;

    public Jugar(Context context) {
       // miMananger=(SensorManager)get
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
