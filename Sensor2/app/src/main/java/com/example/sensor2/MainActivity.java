package com.example.sensor2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;

    private Sensor mSensorAccelerometer;
    private Sensor mSensorMagnetometer;

    private TextView mTextSensorAzimuth;
    private TextView mTextSensorPitch;
    private TextView mTextSensorRoll;

    private float[] mAccelerometerData = new float[3];
    private float[] mMagnetometerData = new float[3];

    private static final float VALUE_DRIFT = 0.05f;

    private ImageView nSpotTop;
    private ImageView nSpotBottom;
    private ImageView nSpotRight;
    private ImageView nSpotLeft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); // setting orientasi potrait secara deault.
        mTextSensorAzimuth = findViewById(R.id.value_azimuth);
        mTextSensorPitch = findViewById(R.id.value_pitch);
        mTextSensorRoll = findViewById(R.id.value_roll);

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensorAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mSensorMagnetometer = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        nSpotTop = findViewById(R.id.spot_top);
        nSpotBottom = findViewById(R.id.spot_bottom);
        nSpotLeft = findViewById(R.id.spot_left);
        nSpotRight = findViewById(R.id.spot_right);


    }

    @Override
    protected void onStart() {
        super.onStart();
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorAccelerometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (mSensorAccelerometer != null) {
            mSensorManager.registerListener(this, mSensorMagnetometer,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        mSensorManager.unregisterListener(this);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int sensorType = event.sensor.getType();
        switch (sensorType) {
            case Sensor.TYPE_ACCELEROMETER:
                mAccelerometerData = event.values.clone(); //secara otomatis akan mengisi array dgn value dari accelerometer
                break;
            case Sensor.TYPE_MAGNETIC_FIELD:
                mMagnetometerData = event.values.clone(); //secara otomatis akan mengisi array dgn value dari magnetometer
                break;
            default:
                return;
        }

        float[] rotationMatrix = new float[9];
        boolean rotationOK = SensorManager.getRotationMatrix(rotationMatrix, null,
                mAccelerometerData, mMagnetometerData);

        float[] orientationValues = new float[3];
        if (rotationOK) {
            SensorManager.getOrientation(rotationMatrix, orientationValues);
        }
        float azimuth = orientationValues[0];
        float pitch = orientationValues[1];
        float roll = orientationValues[2];

        mTextSensorRoll.setText(getResources().getString(R.string.value_format, roll));
        mTextSensorPitch.setText(getResources().getString(R.string.value_format, pitch));
        mTextSensorAzimuth.setText(getResources().getString(R.string.value_format, azimuth));

        if (Math.abs(pitch) < VALUE_DRIFT){
            pitch = 0;
        }
        if (Math.abs(roll) < VALUE_DRIFT){
            roll = 0;
        }
        if (pitch > 0){
            nSpotTop.setAlpha(pitch);
        } else {
            nSpotBottom.setAlpha(Math.abs(pitch));
        }
        if (roll > 0){
            nSpotRight.setAlpha(roll);
        } else {
            nSpotLeft.setAlpha(Math.abs(roll));
        }
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}