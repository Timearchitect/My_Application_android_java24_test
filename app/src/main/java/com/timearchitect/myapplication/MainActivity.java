package com.timearchitect.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.timearchitect.myapplication.ui.login.LoginActivity;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements  SensorEventListener{

    final String TAG = "ALRIK";

    SensorManager mSensorManager,sensorManager;
    Sensor mLightSensor,sensor;

    @Override
    protected void onStart() {
        Log.i("ALRIK", "onStart: ");
        super.onStart();
    }

    @Override
    protected void onStop() {
        Log.i("ALRIK", "onStop: ");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.i("ALRIK", "onDestroy: ");
        super.onDestroy();
    }

    @Override
    protected void onResume() {
        Log.i("ALRIK", "onResume: ");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.i("ALRIK", "onPause: ");
        super.onPause();
    }


    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("ALRIK", "onCreate: ");
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

         iv = findViewById(R.id.imageView);


/*        mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mLightSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);*/
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        //sensorManager.registerListener(SensorEventListener, Sensor, int)
        //sensorManager.registerListener(,Sensor.TYPE_GYROSCOPE_UNCALIBRATED);


        sensorManager.unregisterListener((SensorEventListener) MainActivity.this);
        //sensorManager.registerListener((SensorEventListener) MainActivity.this, sensor, SensorManager.SENSOR_DELAY_NORMAL);
//Toast.makeText(this, String.valueOf( )  ,Toast.LENGTH_LONG).show();
// Implement a listener to receive updates



        SensorEventListener lister = new SensorEventListener(){
            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }

            @Override
            public void onSensorChanged(SensorEvent event) {
                Log.i("Alrik",event.values[0]+" Gyro");
                iv.setRotationX(event.values[0]);

            }
        };


        sensorManager.registerListener(lister, sensor, SensorManager.SENSOR_DELAY_NORMAL);


        TextView tv = findViewById(R.id.appNameId);
        tv.setTextColor(getResources().getColor(R.color.red));
        tv.setTextColor(getColor(R.color.red));   // denna är den nya metoden

        tv.setTextSize(100f);
        Typeface typeface = ResourcesCompat.getFont(this, R.font.gritpress_tb);
        tv.setTypeface(typeface);

        Log.i("ALRIK", "author: " + getResources().getString(R.string.author));
        String[] lista = getResources().getStringArray(R.array.system);

        Log.i("ALRIK", "array: " + Arrays.toString(lista));


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

            int score = 1;

            score++;

            Log.i("Alrik", "HELLO WORLD");
            Log.d(TAG, "onCreate: " + score);

            Toast.makeText(this, "HEJSAN", Toast.LENGTH_LONG).show();

            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button b  = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MainActivity.this, "NEXT!!!!", Toast.LENGTH_SHORT).show();
                /*Intent in = new Intent(  this, LoginActivity.class);
                in.putExtra("message", "HEJSAN");
                startActivity( in );*/
            }
        });


  /*      new Handler(Looper.getMainLooper()).postDelayed( ()-> {
            Intent in = new Intent(  this, LoginActivity.class);
            in.putExtra("message", "HEJSAN");

            startActivity( in );
    } , 3000);*/



    }


    @Override
    public void onSensorChanged(SensorEvent event) {

        Log.i("Alrik",String.valueOf(event.values[0]));

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}