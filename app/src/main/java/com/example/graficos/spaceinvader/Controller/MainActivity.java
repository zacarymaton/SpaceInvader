package com.example.graficos.spaceinvader.Controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graficos.spaceinvader.R;
import com.example.graficos.spaceinvader.View.OpengGLView;


public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager miMananger;
    private Sensor miAcelorometro;
    TextView txt;
private OpengGLView opengGLView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //hechamos la pantalla
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        //instancio el SurfaceView en la Interfaz
        opengGLView=(OpengGLView)findViewById(R.id.openGLView);
        opengGLView.setBackgroundResource(R.drawable.invacionbackground);
       opengGLView.setZOrderOnTop(true);
      //sensor de acelerometro
        miMananger=(SensorManager)getSystemService(SENSOR_SERVICE);
        miAcelorometro=   miMananger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
     //

        txt=(TextView)findViewById(R.id.idText);

    }
    @Override
    protected  void onResume(){
        super.onResume();
        opengGLView.onResume();
        miMananger.registerListener(this,miAcelorometro,SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause(){
        super.onPause();
        opengGLView.onPause();
        miMananger.unregisterListener(this);
    }
    @Override
    protected void onStop(){
        super.onStop();
        opengGLView.onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        String x=String.valueOf(event.values[1]);
        String y=String.valueOf(event.values[1]);
        String z=String.valueOf(event.values[2]);


       // toastMsg(x);
      //  txt.setText(x);

        Float datox=Float.parseFloat(x);
        Float datoy=Float.parseFloat(y);
        Float datoz=Float.parseFloat(z);


       /* if(datox>0) {

                 opengGLView.escenario.mover_derecha();
        }else {
           opengGLView.escenario.mover_izquierda();
           }
        */

     opengGLView.escenario.movete=datox*0.0095f;

    }
    public void toastMsg(String msg) {
        Toast toast = Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT);
        toast.show();

    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
