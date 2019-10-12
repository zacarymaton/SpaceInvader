package com.example.graficos.spaceinvader.Controller;


import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.graficos.spaceinvader.R;
import com.example.graficos.spaceinvader.View.OpengGLView;


public class MainActivity extends AppCompatActivity {
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


    }
    @Override
    protected  void onResume(){
        super.onResume();
        opengGLView.onResume();
    }
    @Override
    protected void onPause(){
        super.onPause();
        opengGLView.onPause();
    }
    @Override
    protected void onStop(){
        super.onStop();
        opengGLView.onResume();
    }

}
