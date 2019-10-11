package com.example.graficos.spaceinvader.Controller;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;
import android.opengl.GLSurfaceView;
import android.widget.Toast;

import com.example.graficos.spaceinvader.R;
import com.threed.jpct.Camera;
import com.threed.jpct.FrameBuffer;
import com.threed.jpct.Light;
import com.threed.jpct.Loader;
import com.threed.jpct.Logger;
import com.threed.jpct.Matrix;
import com.threed.jpct.Object3D;
import com.threed.jpct.RGBColor;
import com.threed.jpct.SimpleVector;
import com.threed.jpct.Texture;
import com.threed.jpct.TextureManager;
import com.threed.jpct.World;
import com.threed.jpct.util.BitmapHelper;

import java.io.InputStream;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import static com.example.graficos.spaceinvader.R.raw.f_obj;
import static com.example.graficos.spaceinvader.R.raw.nave_obj;


public class RenderView implements GLSurfaceView.Renderer {
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

    private Object3D thing = null;
    private int fps = 0;

    private Light sun = null;
    private Camera cam = null;
    private boolean stop = false;
    AssetManager assMan;
    InputStream is,mtl;
    //constructor
    private Context context;
    public   RenderView(Context context){
        this.context=context;
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
            sun.setIntensity(200, 200, 200);
            // Create a texture out of the icon...:-)
           // Texture texture = new Texture(BitmapHelper.rescale(BitmapHelper.convert(main.getResources().getDrawable(R.drawable.ic_launcher_background)), 64, 64));
           // TextureManager.getInstance().addTexture("texture", texture);

            thing = loadModel("raw/nave_obj.obj", thingScale);
            thing.build();

            world.addObject(thing);
            cam = world.getCamera();
            cam.moveCamera(Camera.CAMERA_MOVEOUT, 50);
            cam.lookAt(thing.getTransformedCenter());
            //cam.setBack(cam.getZAxis());

            SimpleVector sv = new SimpleVector();
            sv.set(thing.getTransformedCenter());
          // sv.x -=70;
            sv.y -= 100;
            sv.z -= 100;
            sun.setPosition(sv);
            //MemoryHelper.compact();

            if (master == null) {
                Logger.log("Saving master Activity!");
                master = RenderView.this;
            }
        }

    }

    @Override
    public void onDrawFrame(GL10 gl10) {
        fb.clear();
        world.renderScene(fb);
       world.draw(fb);
       fb.display();

    }




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

}
