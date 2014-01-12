package com.developmental.myapplication.GL;

/**
 * Created by Scott on 5/01/14.
 */

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;

import java.util.ArrayList;

/**
 * Activity for testing OpenGL ES drawing speed.  This activity sets up sprites
 * and passes them off to an OpenGLSurfaceView for rendering and movement.
 */
public class OpenGLTestActivity extends Activity {
    private final static int SPRITE_WIDTH = 100;
    private final static int SPRITE_HEIGHT = 100;


    private mGLSurfaceView mGLSurfaceView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mGLSurfaceView = new mGLSurfaceView(this);
        SimpleGLRenderer spriteRenderer = new SimpleGLRenderer(this);
        // Clear out any old profile results.
        ProfileRecorder.sSingleton.resetAll();

        final Intent callingIntent = getIntent();
        // Allocate our sprites and add them to an array.
        final int robotCount = callingIntent.getIntExtra("spriteCount", 10);
        final boolean animate = callingIntent.getBooleanExtra("animate", true);
        final boolean useVerts =
                callingIntent.getBooleanExtra("useVerts", false);
        final boolean useHardwareBuffers =
                callingIntent.getBooleanExtra("useHardwareBuffers", false);

        // Allocate space for the robot sprites + one background sprite.
        GLSprite[] spriteArray = new GLSprite[robotCount + 1];

        // We need to know the width and height of the display pretty soon,
        // so grab the information now.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        GLSprite background = new GLSprite(R.drawable.charsheet);
        BitmapDrawable backgroundImage = (BitmapDrawable)getResources().getDrawable(R.drawable.charsheet);
        Bitmap backgoundBitmap = backgroundImage.getBitmap();
        background.width = Global.size.x;// backgoundBitmap.getWidth();
        background.height =Global.size.y;// backgoundBitmap.getHeight();
        ArrayList<Grid> g = new ArrayList<Grid>();
        if (useVerts) {
            // Setup the background grid.  This is just a quad.
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0f, 1.0f, null);
            backgroundGrid.set(1, 0, background.width, 0.0f, 0.0f, 1.0f, 1.0f, null);
            backgroundGrid.set(0, 1, 0.0f, background.height, 0.0f, 0.0f, 0.0f, null);
            backgroundGrid.set(1, 1, background.width, background.height, 0.0f,
                    1.0f, 0.0f, null );
            g.add(backgroundGrid);
            background.setGrid(g);
        }
        spriteArray[0] = background;


        Grid spriteGrid = null;
        ArrayList<ArrayList<Grid>> d = new ArrayList<ArrayList<Grid>>();
        ArrayList<ArrayList<Grid>> c = new ArrayList<ArrayList<Grid>>();
        ArrayList<ArrayList<Grid>> c2 = new ArrayList<ArrayList<Grid>>();
        d.add(Global.SpritesLeft);
        d.add(Global.SpritesLeftUp);
        d.add(Global.SpritesUp);
        d.add(Global.SpritesRightUp);
        d.add(Global.SpritesRight);
        d.add(Global.SpritesRightDown);
        d.add(Global.SpritesDown);
        d.add(Global.SpritesLeftDown);
        c.add(Global.SpritesLeftCast1);
        c.add(Global.SpritesLeftUpCast1);
        c.add(Global.SpritesUpCast1);
        c.add(Global.SpritesRightUpCast1);
        c.add(Global.SpritesRightCast1);
        c.add(Global.SpritesRightDownCast1);
        c.add(Global.SpritesDownCast1);
        c.add(Global.SpritesLeftDownCast1);
        c2.add(Global.SpritesLeftCast2);
        c2.add(Global.SpritesLeftUpCast2);
        c2.add(Global.SpritesUpCast2);
        c2.add(Global.SpritesRightUpCast2);
        c2.add(Global.SpritesRightCast2);
        c2.add(Global.SpritesRightDownCast2);
        c2.add(Global.SpritesDownCast2);
        c2.add(Global.SpritesLeftDownCast2);

        if (useVerts) {
            Log.e("BEGINNING LOADING","BEGINNING LOADING");
           for( int j = 0;j<d.size();j++)
           {

               Log.e("BEGINNING LOADING",""+j);
            for(int i = 0; i<8;i++)
            {

                spriteGrid = new Grid(2, 2, false);
                spriteGrid.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0625f*i , 0.125f+0.125f*j, null);
                spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 0.0625f+0.0625f*i, 0.125f+0.125f*j, null);
                spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f,0.0625f*i, 0.125f*j, null);
                spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, 0.0625f+0.0625f*i, 0.125f*j, null);
                d.get(j).add(spriteGrid);
            }
               for(int i = 8; i<13;i++)
               {

                   spriteGrid = new Grid(2, 2, false);
                   spriteGrid.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0625f*i , 0.125f+0.125f*j, null);
                   spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 0.0625f+0.0625f*i, 0.125f+0.125f*j, null);
                   spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f,0.0625f*i, 0.125f*j, null);
                   spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, 0.0625f+0.0625f*i, 0.125f*j, null);
                   c.get(j).add(spriteGrid);
               }
               for(int i = 13; i<16;i++)
               {

                   spriteGrid = new Grid(2, 2, false);
                   spriteGrid.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0625f*i , 0.125f+0.125f*j, null);
                   spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 0.0625f+0.0625f*i, 0.125f+0.125f*j, null);
                   spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f,0.0625f*i, 0.125f*j, null);
                   spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, 0.0625f+0.0625f*i, 0.125f*j, null);
                   c2.get(j).add(spriteGrid);
               }
           }


            // Setup a quad for the sprites to use.  All sprites will use the
            // same sprite grid intance.

        }

        ArrayList<Grid> e = new ArrayList<Grid>();
        Grid bounds = new Grid(2, 2, false);
        bounds.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0f, 1.0f, null);
        bounds.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, 1.0f, 1.0f, null);
        bounds.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f, 0.0f, 0.0f, null);
        bounds.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f,
                1.0f, 0.0f, null );
        e.add(bounds);
        OpenGLTestActivity.boundingCircle.setGrid(e);
        OpenGLTestActivity.boundingCircle.bounds=true;
        // This list of things to move. It points to the same content as the
        // sprite list except for the background.
        GLSprite[] renderableArray = new GLSprite[robotCount];
        final int robotBucketSize = robotCount / 4;
        for (int x = 0; x < robotCount; x++) {
            GLSprite robot;
            // Our robots come in three flavors.  Split them up accordingly.
            if (x < robotBucketSize) {
                robot = new GLSprite(R.drawable.charsheet);
            } else if (x < robotBucketSize * 2) {
                robot = new GLSprite(R.drawable.charsheetedit);
            }
                else if (x < robotBucketSize * 3) {
                robot = new GLSprite(R.drawable.charsheetedit4);
            } else {
                robot = new GLSprite(R.drawable.charsheetedit2);
            }

            robot.width = SPRITE_WIDTH;
            robot.height = SPRITE_HEIGHT;

            // Pick a random location for this sprite.
            robot.position.x = (float)(Math.random() * dm.widthPixels);
            robot.position.y = (float)(Math.random() * dm.heightPixels);

            // All sprites can reuse the same grid.  If we're running the
            // DrawTexture extension test, this is null.
            robot.setGrid(Global.SpritesLeft);

            // Add this robot to the spriteArray so it gets drawn and to the
            // renderableArray so that it gets moved.
            spriteArray[x + 1] = robot;
            renderableArray[x] = robot;
        }


        // Now's a good time to run the GC.  Since we won't do any explicit
        // allocation during the test, the GC should stay dormant and not
        // influence our results.
        Runtime r = Runtime.getRuntime();
        r.gc();


        spriteRenderer.setSprites(spriteArray);
        spriteRenderer.setVertMode(useVerts, useHardwareBuffers);

        mGLSurfaceView.setRenderer(spriteRenderer);

        if (animate) {
            Mover simulationRuntime = new Mover();
            simulationRuntime.setRenderables(renderableArray);

            simulationRuntime.setViewSize(dm.widthPixels, dm.heightPixels);
            //mGLSurfaceView.queueEvent(simulationRuntime);
            mGLSurfaceView.setEvent(simulationRuntime);
        }
        setContentView(mGLSurfaceView);
    }
    public static GLSprite boundingCircle = new GLSprite(R.drawable.boundscircle);
}
