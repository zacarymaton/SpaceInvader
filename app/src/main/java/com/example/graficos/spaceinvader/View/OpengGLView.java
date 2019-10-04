package com.example.graficos.spaceinvader.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.example.graficos.spaceinvader.Controller.RenderView;

public class OpengGLView extends GLSurfaceView {
    public RenderView escenario;;
    public OpengGLView(Context context) {
        super(context);

        init(context);
    }

    public OpengGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void  init(Context context){
        escenario=new RenderView(context);
        setEGLContextClientVersion(2);
        setPreserveEGLContextOnPause(true);
        setRenderer(escenario);
    }





}
