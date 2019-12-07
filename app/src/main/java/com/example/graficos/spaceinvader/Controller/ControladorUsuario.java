package com.example.graficos.spaceinvader.Controller;

import android.content.Context;

import com.example.graficos.spaceinvader.Model.Nave;

import android.graphics.drawable.Drawable;

import com.example.graficos.spaceinvader.R;
import com.threed.jpct.Object3D;

import androidx.annotation.NonNull;

public class ControladorUsuario {
    private Nave nave;
    public  ControladorUsuario(){
        nave=new Nave();
    }

    public ControladorUsuario(Context context){
        nave=new Nave();
        nave.adicionarTextura(context.getDrawable(R.drawable.nave_bomber_body_diffuse));
        nave.leerModelo(context.getResources().openRawResource(R.raw.nave_obj),0.0020f);
    }
    public Object3D obtenerNave(){
        return  nave.getNave();
    }




}
