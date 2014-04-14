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
import android.view.Window;

import com.developmental.myapplication.GL.NewHeirachy.Moveable;
import com.developmental.myapplication.GL.NewHeirachy.Renderable;
import com.developmental.myapplication.GL.NewHeirachy.glButton;
import com.developmental.myapplication.GL.NewHeirachy.glHealthBar;
import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;
import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;

import Actors.EllipseMovingAI;
import Actors.Player;
import Tools.Vector;

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

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        RenderThread.renderThread = new RenderThread(this, Global.size);
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
        Renderable[] spriteArray = new Renderable[robotCount + 13];

        // We need to know the width and height of the display pretty soon,
        // so grab the information now.
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        GameObject background = new GameObject(R.drawable.backgroundlava2);
        BitmapDrawable backgroundImage = (BitmapDrawable)getResources().getDrawable(R.drawable.charsheetedit);
        Bitmap backgoundBitmap = backgroundImage.getBitmap();
      background.size = new Vector(Global.WORLD_BOUND_SIZE.x,Global.WORLD_BOUND_SIZE.y);

        ArrayList<Grid> g = new ArrayList<Grid>();
        if (useVerts) {
            // Setup the background grid.  This is just a quad.
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0,  0.0f, -Global.WORLD_BOUND_SIZE.y, 0.0f, 0.0f, 1.0f, null);
            backgroundGrid.set(1, 0,  Global.WORLD_BOUND_SIZE.x, -Global.WORLD_BOUND_SIZE.y, 0.0f, 1.0f, 1.0f, null);
            backgroundGrid.set(0, 1, 0.0f, 0, 0.0f, 0.0f, 0.0f, null);
            backgroundGrid.set(1, 1, Global.WORLD_BOUND_SIZE.x,0, 0.0f,1.0f, 0.0f, null );
            g.add(backgroundGrid);
            background.setGrid(g);

        }
        spriteArray[0] = background;
        spriteArray[1] = RenderThread.l.platform;
        spriteArray[2]= RenderThread.l.iceplatform;
        RenderThread.l.platform.setGrid();
        RenderThread.l.iceplatform.setGrid();
RenderThread.gameObjects.clear();

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
           for( int j = 0;j<d.size();j++)
           {

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

        // This list of things to move. It points to the same content as the
        // sprite list except for the background.
        Renderable[] renderableArray = new Renderable[robotCount];
        final int robotBucketSize = robotCount / 4;
        for (int x = 0; x < robotCount; x++) {
            GameObject robot;
            Vector v = GameObject.PositiononEllipse((float) (Math.random()*360)).add(new Vector(Global.WORLD_BOUND_SIZE.x/2,Global.WORLD_BOUND_SIZE.y/2));
            // Our robots come in three flavors.  Split them up accordingly.
            if (x < robotBucketSize) {
                robot = new Player(R.drawable.charsheet,Global.spellList,v);
            } else if (x < robotBucketSize * 2) {
                robot = new EllipseMovingAI(R.drawable.charsheetedit,Global.spellList,v);
            }
                else if (x < robotBucketSize * 3) {
                robot = new EllipseMovingAI(R.drawable.charsheetedit4,Global.spellList,v);
            } else {
                robot = new EllipseMovingAI(R.drawable.charsheetedit2,Global.spellList,v);
            }

            robot.size = new Vector(SPRITE_WIDTH,SPRITE_HEIGHT);
            // Pick a random location for this sprite.


            // All sprites can reuse the same grid.  If we're running the
            // DrawTexture extension test, this is null.
            robot.setGrid(Global.SpritesLeft);

            // Add this robot to the spriteArray so it gets drawn and to the
            // renderableArray so that it gets moved.
            RenderThread.addObject(robot);
            RenderThread.players.add(robot);
            spriteArray[x + 13] = robot;
            renderableArray[x] = robot;
        }
        ArrayList<Grid> buttonGrid= new ArrayList<Grid>();
        Grid bG = new Grid(2,2,false);
        bG.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0f, 1.0f, null);
        bG.set(1, 0,  Global.size.x/10, 0.0f, 0.0f, 1.0f*1f/3f, 1.0f, null);
        bG.set(0, 1, 0.0f, Global.size.x / 10, 0.0f, 0.0f, 0.0f, null);
        bG.set(1, 1, Global.size.x / 10, Global.size.x / 10, 0.0f, 1.0f*1f/3f, 0.0f, null);
        buttonGrid.add(bG);
        bG = new Grid(2,2,false);
        bG.set(0, 0,  0.0f, 0.0f, 0.0f, 1.0f*1f/3f, 1.0f, null);
        bG.set(1, 0,  Global.size.x/10, 0.0f, 0.0f, 2.0f*1f/3f, 1.0f, null);
        bG.set(0, 1, 0.0f, Global.size.x / 10, 0.0f, 1.0f*1f/3f, 0.0f, null);
        bG.set(1, 1, Global.size.x / 10, Global.size.x / 10, 0.0f, 2.0f*1f/3f, 0.0f, null);
        buttonGrid.add(bG);

        bG = new Grid(2,2,false);
        bG.set(0, 0,  0.0f, 0.0f, 0.0f, 2.0f*1f/3f, 1.0f, null);
        bG.set(1, 0,  Global.size.x/10, 0.0f, 0.0f, 3.0f*1f/3f, 1.0f, null);
        bG.set(0, 1, 0.0f, Global.size.x / 10, 0.0f, 2.0f*1f/3f, 0.0f, null);
        bG.set(1, 1, Global.size.x / 10, Global.size.x / 10, 0.0f, 3.0f*1f/3f, 0.0f, null);
        buttonGrid.add(bG);
        SimpleGLRenderer.buttons.clear();

Global.playerno = 0;
        // Now's a good time to run the GC.  Since we won't do any explicit
        // allocation during the test, the GC should stay dormant and not
        // influence our results.
        Runtime r = Runtime.getRuntime();
        Grid bG2 = new Grid(2,2,false);
        bG2.set(0, 0,  0.0f, 0.0f, 0.0f, 0.0f, 1.0f, null);
        bG2.set(1, 0,  Global.size.x/10, 0.0f, 0.0f, 1.0f, 1.0f, null);
        bG2.set(0, 1, 0.0f, Global.size.x / 10, 0.0f, 0.0f, 0.0f, null);
        bG2.set(1, 1, Global.size.x / 10, Global.size.x / 10, 0.0f, 1.0f, 0.0f, null);
        r.gc();
RenderThread.archie = RenderThread.gameObjects.get(0);
        SimpleGLRenderer.archieHealthBar = new glHealthBar(R.drawable.healthbar,new Vector(Global.size.x,Global.healthBarHeight),new Vector(0,Global.size.x/10+Global.healthBarHeight),RenderThread.archie,glHealthBar.type.Health);
        SimpleGLRenderer.archieManaBar = new glHealthBar(R.drawable.healthbar,new Vector(Global.size.x,Global.healthBarHeight),new Vector(0,Global.size.x/10),RenderThread.archie, glHealthBar.type.Mana);
        for(int i =0; i<10;i++)
        {
            glButton qe = new glButton(R.drawable.buttons2,RenderThread.archie.Spells[i].texture,i*Global.size.x/10,Global.size.x/10,Global.size.x/10,Global.size.x/10,bG2);
            qe.setGrid(buttonGrid);
            qe.position.x= i*Global.size.x/10;
            //  qe.position.y= Global.size.x/10;
            SimpleGLRenderer.buttons.add(qe);
            spriteArray[3+i] = qe;

        }
       Global.ButtonSize =  Global.size.x/10;
//Global.size.y-=Global.size.x/10;
        //boundingCircle.boundsz= true;
        spriteRenderer.setSprites(spriteArray);
        spriteRenderer.setVertMode(useVerts, useHardwareBuffers);

        mGLSurfaceView.setRenderer(spriteRenderer);

        if (animate) {
            Mover simulationRuntime = new Mover();
            simulationRuntime.setRenderables(renderableArray);

            simulationRuntime.setViewSize(dm.widthPixels, dm.heightPixels);
          //  simulationRuntime. = mGLSurfaceView;

            //mGLSurfaceView.queueEvent(simulationRuntime);
            mGLSurfaceView.setEvent(simulationRuntime);
        }

        setContentView(mGLSurfaceView);
    }
//    public static GameObject boundingCircle = new GameObject(R.drawable.boundscircle);
}
