package com.developmental.myapplication;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;

import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.multiplayer.realtime.Room;

import java.util.ArrayList;
import java.util.Collections;

import Game.GameObject;
import HUD.Button;
import HUD.PopupText;
import Input.Finger;
import Input.NetworkFinger;
import Tools.Serializer;

/**
 * @author impaler
 *         <p/>
 *         The Main thread which contains the game loop. The thread must have
 *         access to the surface view and holder to trigger events every game
 *         tick.
 */
public class GameThread extends Thread {
   public static int Gamestep = 0;
   public static ArrayList<Finger> fingers =new ArrayList<Finger>();
    private static final String TAG = GameThread.class.getSimpleName();
    static final long FPS = 15;
    // Surface holder that can access the physical surface
    private final SurfaceHolder surfaceHolder;
    // The actual view that handles inputs
    // and draws to the surface
    private final RenderThread renderThread;

    // flag to hold game state
    public static boolean running;

    public static void setRunning(boolean _running) {
        running = _running;
    }

    public GameThread(SurfaceHolder surfaceHolder, RenderThread gamePanel) {
        super();
        this.surfaceHolder = surfaceHolder;
        this.renderThread = gamePanel;
        RenderThread.popupTexts = new ArrayList<PopupText>();
    }


    public void Update() {
Gamestep+=1;

        // boolean f = false;
        int selectedSpell = -1;
        // Chekcs Which Buttons are Down, the last down one in order of left to
        // right becomes the selected spell
        for (Button b : this.renderThread.buttons) {
            b.Update();
            if (b.down)
            {
                selectedSpell = this.renderThread.buttons.indexOf(b);

                Log.d("INET","DOWN");
            }
        }
//        if (selectedSpell != -1)
//            RenderThread.archie.Spells[selectedSpell].Cast(RenderThread.finger.WorldPositions());
        if(Global.Multiplayer)
          //  if(i++%3 == 0)
        if(RenderThread.finger!=null)
                if(RenderThread.finger.down)
                RenderThread.c.getGamesClient().sendUnreliableRealTimeMessageToAll(Serializer.SerializetoBytes(new NetworkFinger(Gamestep,RenderThread.finger.WorldPositions(),Global.playerno,selectedSpell)), RenderThread.c.mRoom.getRoomId());
        for(int f = 0; f<RenderThread.popupTexts.size();f++)
        {
            RenderThread.popupTexts.get(f).Update();
        }
        if(RenderThread.finger!=null)
                RenderThread.archie.FingerUpdate(RenderThread.finger.WorldPositions(),selectedSpell);
        Collision();


        Collections.sort(RenderThread.gameObjects);

    }

    public static int s = 0;

    public void Collision() {

//
        //q.insert(RenderThread.archie.rect);
//q.clear();
        q.clear();
        for (int v = 0; v < RenderThread.gameObjects.size(); v++) {

//            q.insert(RenderThread.gameObjects.get(v));
            RenderThread.gameObjects.get(v).Update();
        }

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
public static GamesClient gamesClient;
    public static Room room;
    public static Quadtree q = new Quadtree(0, new RectF(0, 0, Global.WORLD_BOUND_SIZE.x, Global.WORLD_BOUND_SIZE.y));
int i = 0;
    @Override
    public void run() {
        long ticksPS = 1000 / FPS;
        long startTime;
        long sleepTime;
        Canvas canvas;
       // gamesClient.connect();
        Log.d("INET", "GAME THREAD STARTED");
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
                    // update game state
                    // render state to the screen
                    // draws the canvas on the panel
                  //  if(Global.Multiplayer)
                     //   if(RenderThread.finger!=null)gamesClient.sendUnreliableRealTimeMessageToAll(Serializer.SerializetoBytes(RenderThread.finger),room.getRoomId());
                    if(canvas!=null)
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
        Log.d("INET","CLOSED GAME THREAD");
    }

}
