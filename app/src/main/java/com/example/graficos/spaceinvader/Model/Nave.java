package com.example.graficos.spaceinvader.Model;

import android.content.Context;

import com.example.graficos.spaceinvader.R;
import com.threed.jpct.Loader;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;

import java.io.InputStream;

import static com.example.graficos.spaceinvader.R.raw.nave_obj;

public class Nave {
private int vidas=3;
int count;
    InputStream is,mtl;
private boolean moviendo;
private Object3D nave;
    float constante = 0.005f;
    public Nave(int vidas, int count, boolean moviendo) {
        this.vidas = vidas;
        this.count = count;
        this.moviendo = moviendo;
    }

    public Nave() {

    }
    public void moverIzquierda(Object3D object3D){

        nave.translate(constante,0,0);

    }
    public void moverDerecha(Object3D object3D){

        nave.translate(constante,0,0);
    }
    public void moverArriba(Object3D object3D){

        nave.translate(0,constante,0);
    }



    //leemos el modelo del contexto del constructor
    private Object3D loadModel(Context context, float scale) {
        is = context.getResources().openRawResource(nave_obj);
        mtl=context.getResources().openRawResource(R.raw.nave_mtl);

        Object3D[] model = Loader.loadOBJ(is, mtl, scale);
        Object3D o3d = new Object3D(0);
        Object3D temp = null;
        for (int i = 0; i < model.length; i++) {
            temp = model[i];
            temp.setCenter(SimpleVector.ORIGIN.ORIGIN);
            temp.rotateX((float)( -.5*Math.PI));
            temp.rotateMesh();
            temp.setRotationMatrix(new Matrix());
            o3d = Object3D.mergeObjects(o3d, temp);
            o3d.build();
        }
        return o3d;
    }

}
