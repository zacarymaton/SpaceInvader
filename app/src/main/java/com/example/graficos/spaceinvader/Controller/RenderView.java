package com.example.graficos.spaceinvader.Controller;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.widget.Toast;

import com.example.graficos.spaceinvader.R;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.ITextureEffect;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Logger;
import com.threed.jpct.Matrix;
import com.threed.jpct.NPOTTexture;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;

import java.io.IOException;
import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.example.graficos.spaceinvader.R.raw.f_obj;
import static com.example.graficos.spaceinvader.R.raw.nave_obj;


public class RenderView extends Activity implements GLSurfaceView.Renderer, SensorEventListener {
    private MainActivity main;
    private World world= null;;
    private FrameBuffer fb;
    private float thingScale = 0.0010f;//end
    private static RenderView master = null;
    private RGBColor back = new RGBColor(50, 50, 100);
    private long time = System.currentTimeMillis();
    private float touchTurn = 0;
    private float touchTurnUp = 0;

    private float xpos = -1;
    private float ypos = -1;

    public Object3D thing = null;

    private int fps = 0;

    private Light sun = null;
    private Camera cam = null;
    private boolean stop = false;
    AssetManager assMan;
    InputStream is,mtl;
    //constructor
    private SensorManager miMananger;
    private Sensor miAcelorometro;
    private Context context;
    public   RenderView(Context context){
        this.context=context;


    }
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        miMananger=(SensorManager)getSystemService(SENSOR_SERVICE);
        miAcelorometro=   miMananger.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }
    @Override
    protected  void onResume(){
        super.onResume();
        miMananger.registerListener(this,miAcelorometro,SensorManager.SENSOR_DELAY_GAME);
    }
    @Override
    protected void onPause(){
        super.onPause();
        miMananger.unregisterListener(this);
    }

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig eglConfig) {


    }

    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {
        //fb=new FrameBuffer(width,height);
        if (fb != null) {
            fb.dispose();
        }
        fb = new FrameBuffer(gl, width, height);




        if (master == null) {

            world = new World();

            world.setAmbientLight(20, 20, 20);

            sun = new Light(world);
            sun.setIntensity(250, 250, 250);

            thing = loadModel("raw/nave_obj.obj", thingScale);

            thing.build();
            Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(context.getResources().getDrawable(R.drawable.nave_bomber_body_diffuse)), 64, 64));
            TextureManager.getInstance().addTexture("texture", texture);
            thing.calcTextureWrap();
            thing.setTexture("texture");
            thing.strip();


            world.addObject(thing);
            cam = world.getCamera();
            cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
            cam.lookAt(thing.getTransformedCenter());

            SimpleVector sv = new SimpleVector();

            sv.set(thing.getTransformedCenter());
          // sv.x -=70;
            sv.y -= 100;
            sv.z -= 100;
          //  posicion inicial de la nave
            thing.rotateX(3.0f);
            thing.rotateZ(3.15f);
            //traslacion del objeto
            thing.translate(0,10.0f,0);
            sun.setPosition(sv);
            //MemoryHelper.compact();

            if (master == null) {
                Logger.log("Saving master Activity!");
                master = RenderView.this;
            }
        }
        mover_nave(movete);

    }

    @SuppressLint("ResourceType")
    @Override
    public void onDrawFrame(GL10 gl10) {
/*
        InputStream inputStream = null;

        inputStream = context.getResources().openRawResource(R.drawable.invacionbackground);

        final Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
        final NPOTTexture npotTexture = new NPOTTexture(bitmap.getWidth(),
                bitmap.getHeight(), RGBColor.BLACK);
        npotTexture.setEffect (new ITextureEffect() {

            @Override
            public void init (Texture texture) {
            }
            @Override
            public void apply (int [] dest, int [ ] fuente) {
                // aplicamos el efecto, pero en realidad todo lo que hacemos es copiar el ARGB del mapa de bits a "dest"
                bitmap.getPixels (dest, 0, bitmap.getWidth (), 0, 0,
                        bitmap.getWidth (), bitmap.getHeight ());
            }

            @Override
            public boolean containsAlpha() {
                return false;
            }
        });
        npotTexture.applyEffect ();

*/
     mover_nave(movete);
        fb.clear();
        //fb.blit (npotTexture, 0, 0, 0, 0,bitmap.getWidth (), bitmap.getHeight (),FrameBuffer.OPAQUE_BLITTING);
        world.renderScene(fb);
        world.draw(fb);
        fb.display();

    }



//leemos el modelo del contexto del constructor
    private Object3D loadModel(String filename, float scale) {
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
    public void toastMsg(String msg) {
       Toast toast = Toast.makeText(main.getApplicationContext(), msg, Toast.LENGTH_LONG);
        toast.show();
    }
    private void copyMatrix(float[] src, com.threed.jpct.Matrix dest) {
        dest.setRow(0, src[0], src[1], src[2], 0);
        dest.setRow(1, src[3], src[4], src[5], 0);
        dest.setRow(2, src[6], src[7], src[8], 0);
        dest.setRow(3, 0f, 0f, 0f, 1f);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
       // String s=String.valueOf(event);
       // toastMsg(s);

        float[] result = new float[9];
       /* SensorManager.remapCoordinateSystem(
                rotationMatrix, SensorManager.AXIS_MINUS_Y,
                SensorManager.AXIS_MINUS_X, result);*/
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
    public float movete=0;
    public void mover_nave(float valor){
 float  cant=0.005f;
        if (valor<0 && valor>-50){
            //thing.translate(movete+cant,0.0f,0);
            thing.translate(0.05f,0.0f,0);
            //thing.rotateZ(0.55f);
            //thing.translate(0.05f,0.0f,0.55f); los objetos se alejan de z hacia x
        }
        if(valor>0 && valor<40)
        {
           // thing.translate(movete-cant,0.0f,0);
            thing.translate(-0.05f,0.0f,0);
            //thing.rotateZ(-0.55f);
            //thing.translate(-0.05f,0.0f,-0.55f); los objetos vienen de z hacia x
        }
    }

float constante=0.05f;
    public void mover_derecha() {
        thing.translate(movete+constante,0.0f,0);
    }

    public void mover_izquierda() {
        thing.translate(movete-constante,0.0f,0);

    }
}
