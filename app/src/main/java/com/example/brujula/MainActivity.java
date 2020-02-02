package com.example.brujula;

import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements SensorListener {

    private static final int sensor = SensorManager.SENSOR_ORIENTATION;

    SensorManager sensorManager;
    Rose rose;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rose = new Rose(this);
        setContentView(rose);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    public void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor);
    }

    @Override
    public void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(int sensor, int accuracy) {
        //Empty
    }

    @Override
    public void onSensorChanged(int sensor, float[] values) {
        if (rose != null) {
            int orientation = (int) values[0];
            rose.setDirection(orientation);
        }
    }
}
