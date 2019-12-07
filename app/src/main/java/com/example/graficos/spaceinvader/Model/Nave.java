package com.example.graficos.spaceinvader.Model;


import android.graphics.drawable.Drawable;

import com.example.graficos.spaceinvader.R;
import com.threed.jpct.Loader;

import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;

import com.threed.jpct.util.BitmapHelper;

import java.io.InputStream;



public class Nave {
    private Object3D nave;


    private float posX;
    private float posY;
    private float posZ;
    private int vidas=7;


    public Nave(){
        posX=0.0f;
        posY=0.0f;
        posZ=0.0f;
    }
    public Object3D getNave() {
        return nave;
    }

    public void setNave(Object3D nave) {
        this.nave = nave;
    }
    public void adicionarTextura(Drawable drawable){
        Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(drawable), 1024, 1024));
        TextureManager.getInstance().addTexture("texture", texture);
    }


    //leemos el modelo del contexto del constructor
    public void leerModelo(InputStream inputStreamstream,float escala){
        Object3D[] model = Loader.loadOBJ(inputStreamstream, null, escala);
        this.nave = new Object3D(0);
        Object3D naveTemp=null;
        for (int i = 0; i < model.length; i++) {
            naveTemp = model[i];
            naveTemp.setCenter(SimpleVector.ORIGIN);

            this.nave = Object3D.mergeObjects(this.nave, naveTemp);

           this.nave.setTexture("texture");
            this.nave.build();
        }

        this.nave.setName("usuario");

       // this.nave.rotateX((float)( -1.0*Math.PI));
        nave.rotateX(3.0f);
        nave.rotateZ(3.15f);

        this.nave.translate(posX, posY, posZ);
    }

}
