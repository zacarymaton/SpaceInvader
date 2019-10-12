package com.example.graficos.spaceinvader.View;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

import com.example.graficos.spaceinvader.Controller.MainActivity;
import com.example.graficos.spaceinvader.Controller.RenderView;
import com.example.graficos.spaceinvader.R;

public class OpengGLView extends GLSurfaceView {
    public RenderView escenario;;
    private OpengGLView view;
    public OpengGLView(Context context) {
        super(context);
        setRenderMode(GLSurfaceView.RENDERMODE_WHEN_DIRTY);
        init(context);
    }

    public OpengGLView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void  init(Context context){
        escenario=new RenderView(context);
        setEGLContextClientVersion(1);
        setPreserveEGLContextOnPause(true);
        setRenderer(escenario);
    }





}
