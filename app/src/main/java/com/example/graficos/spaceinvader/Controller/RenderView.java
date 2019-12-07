package com.example.graficos.spaceinvader.Controller;

import android.annotation.SuppressLint;

import android.content.Context;
import android.content.res.AssetManager;
import android.hardware.SensorManager;
import android.opengl.GLSurfaceView;

import com.threed.jpct.Camera;


import com.threed.jpct.Light;

import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;



import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;




public class RenderView  implements GLSurfaceView.Renderer {
    private  ControladorEscenario escenario=null;
    private  ControladorUsuario usuario;


    public   RenderView(Context contexto){
        usuario = new ControladorUsuario(contexto);
        escenario = new ControladorEscenario();
        escenario.posicionarCamara();
        escenario.adicionarElementoEscenario(usuario.obtenerNave());
    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {

    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        escenario.surfaceChanged(gl, width, height);
    }

    @Override
    public void onDrawFrame(GL10 gl10) {


        escenario.limpiarFrameBuffer();

        escenario.renderScene();

        escenario.draw();

        escenario.displayFrameBuffer();


    }

}
