package com.example.warlockgame;

import java.io.IOException;
import java.util.Collections;

import Game.GameObject;
import HUD.Button;
import Input.Finger;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * @author impaler
 *         <p/>
 *         The Main thread which contains the game loop. The thread must have
 *         access to the surface view and holder to trigger events every game
 *         tick.
 */
public class GameThread extends Thread {

    private static final String TAG = GameThread.class.getSimpleName();
    static final long FPS = 30;
    // Surface holder that can access the physical surface
    private final SurfaceHolder surfaceHolder;
    // The actual view that handles inputs
    // and draws to the surface
    private final RenderThread renderThread;
    // flag to hold game state
    private static boolean running;

    public static void setRunning(boolean _running) {
        running = _running;
    }

    public GameThread(SurfaceHolder surfaceHolder, RenderThread gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.renderThread = gamePanel;
    }


    public void Update() {

        // boolean f = false;
        int selectedSpell = -1;
        // Chekcs Which Buttons are Down, the last down one in order of left to
        // right becomes the selected spell
        for (Button b : this.renderThread.buttons) {
            b.Update();
            if (b.down)
                selectedSpell = this.renderThread.buttons.indexOf(b);
        }
        for(int f = 0; f<RenderThread.popupTexts.size();f++)
        {
            RenderThread.popupTexts.get(f).Update();
        }
        if (selectedSpell != -1)
            RenderThread.archie.Spells[selectedSpell].Cast(RenderThread.finger.pointers);

        Collision();


    //    Collections.sort(RenderThread.gameObjects);

    }

    public static int s = 0;

    public void Collision() {
        if (s++ != 100) {

        }
//
        //q.insert(RenderThread.archie.rect);
//q.clear();
        q.clear();
        for (int v = 0; v < RenderThread.gameObjects.size(); v++) {

//            q.insert(RenderThread.gameObjects.get(v));
            RenderThread.gameObjects.get(v).Update();
        }
        RenderThread.archie.FingerUpdate(RenderThread.finger);
        for (int x = 0; x < RenderThread.gameObjects.size(); x++) {
            GameObject g = RenderThread.gameObjects.get(x);
            for (int y = 0; y < RenderThread.gameObjects.size(); y++) {
                if (RenderThread.gameObjects.size() > y
                        && RenderThread.gameObjects.size() > x)
                    if (y != x)
                        if (RenderThread.gameObjects.get(x).owner == null
                                || RenderThread.gameObjects.get(y).owner == null)// no
                        // owner
                        // set
                        // ,
                        // collide
                        // with
                        // all.
                        {
                            // Log.d("GETME", "NOT LAME!");
                            if (RenderThread.gameObjects.get(x).Intersect(
                                    RenderThread.gameObjects.get(y).rect))
                                RenderThread.gameObjects.get(y).Collision(
                                        RenderThread.gameObjects.get(x));
                        } else if (RenderThread.gameObjects.get(x).owner.id != RenderThread.gameObjects
                                .get(y).id
                                && RenderThread.gameObjects.get(y).owner.id != RenderThread.gameObjects
                                .get(x).id)
                            if (RenderThread.gameObjects.get(x).Intersect(
                                    RenderThread.gameObjects.get(y).rect))
                                RenderThread.gameObjects.get(y).Collision(
                                        RenderThread.gameObjects.get(x));
            }


        }


    }

    public static Quadtree q = new Quadtree(0, new RectF(0, 0, Global.WORLD_BOUND_SIZE.x, Global.WORLD_BOUND_SIZE.y));
int i = 0;
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        Canvas canvas;

        Log.d(TAG, "Starting game loop");
        while (running) {
            canvas = null;
            startTime = System.currentTimeMillis();
            // try locki        n   g   th  e       c   an  vas for exclusive pixel editing
            // in the surface
            try {

                canvas = this.surfaceHolder.lockCanvas();
                synchronized (this.surfaceHolder) {
                    Update();
                   //     Log.d("INET",(String)ServerThread.getLocalIpAddress());
                    if(!Global.Server)
                    {
                    try {
//                            if(Finger.position.down)
                        i++;
                        if(i%2==0)
                        {
                            Log.d("INET","SENDING!");
                        ClientTask.Send("192.168.1.9",RenderThread.archie.position);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    }
                    // update game state
                    // render state to the screen
                    // draws the canvas on the panel
                    this.renderThread.onDraw(canvas);
                }
            } finally {
                // in case of an exception the surface is not left in
                // an inconsistent state
                if (canvas != null)
                    this.surfaceHolder.unlockCanvasAndPost(canvas);
            } // end finally
            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
            try {
                if (sleepTime > 0)
                    sleep(sleepTime);
                else
                    sleep(10);
            } catch (Exception e) {
            }
        }
    }

}
