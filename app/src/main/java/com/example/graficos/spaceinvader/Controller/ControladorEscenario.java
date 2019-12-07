package com.example.graficos.spaceinvader.Controller;

import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Object3D;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.World;

import javax.microedition.khronos.opengles.GL10;

public class ControladorEscenario {
    private World escenario;
    private FrameBuffer fb=null;
    public ControladorEscenario(){
        escenario = new World();
        escenario.setAmbientLight(250, 250, 250);
    }
    public void surfaceChanged(GL10 gl, int width, int height){
        if (fb != null) {
            fb.dispose();
        }
        fb = new FrameBuffer(gl, width, height);
    }
    public void adicionarElementoEscenario(Object3D obj3D) {
        this.escenario.addObject(obj3D);
    }
    public Camera obtenerCamaraDeEscenario() {
        return escenario.getCamera();
    }
    public void limpiarFrameBuffer() {
        fb.clear();
    }
    public void renderScene() {
        escenario.renderScene(fb);
    }
    public void draw() {
        escenario.draw(fb);
    }

    public void displayFrameBuffer() {
        fb.display();
    }
    public Object3D getUsuarioObj3D() {
        return escenario.getObjectByName("usuario");
    }
    public void posicionarCamara() {
        Camera cam = escenario.getCamera();

        SimpleVector vectorDirection = cam.getDirection();
        SimpleVector vectorUp = cam.getUpVector();
        //vectorUp.x=0.0f;
        vectorUp.y-=100;
        vectorUp.z-=100;
        cam.setOrientation(vectorDirection, vectorUp);
        cam.setPosition(vectorUp);
        //cam.lookAt(thing.getTransformedCenter());
        cam.lookAt(new SimpleVector());
    }



}
