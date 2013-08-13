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
    static final long FPS = 20;
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
                RenderThread.c.getGamesClient().sendUnreliableRealTimeMessageToAll(Serializer.SerializetoBytes(new NetworkFinger(Gamestep+3,RenderThread.finger.WorldPositions(),Global.playerno,selectedSpell)), RenderThread.c.mRoom.getRoomId());
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
            for (int y = x+1; y < RenderThread.gameObjects.size(); y++) {
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




    private boolean paused = false;
    private int fps = 30;
    private int frameCount = 0;
    @Override
    public void run()
    {
        Canvas canvas=null;
        //This value would probably be stored elsewhere.
        final double GAME_HERTZ = 30.0;
        //Calculate how many ns each frame should take for our target game hertz.
        final double TIME_BETWEEN_UPDATES = 1000000000 / GAME_HERTZ;
        //At the very most we will update the game this many times before a new render.
        //If you're worried about visual hitches more than perfect timing, set this to 1.
        final int MAX_UPDATES_BEFORE_RENDER = 5;
        //We will need the last update time.
        double lastUpdateTime = System.nanoTime();
        //Store the last time we rendered.
        double lastRenderTime = System.nanoTime();

        //If we are able to get as high as this FPS, don't render again.
        final double TARGET_FPS = 60;
        final double TARGET_TIME_BETWEEN_RENDERS = 1000000000 / TARGET_FPS;

        //Simple way of finding FPS.
        int lastSecondTime = (int) (lastUpdateTime / 1000000000);

        while (running)
        {
            double now = System.nanoTime();
            int updateCount = 0;

            if (!paused)
            {
                try {
                    canvas = this.surfaceHolder.lockCanvas();

                //Do as many game updates as we need to, potentially playing catchup.
                while( now - lastUpdateTime > TIME_BETWEEN_UPDATES && updateCount < MAX_UPDATES_BEFORE_RENDER )
                {
                    Update();
                    lastUpdateTime += TIME_BETWEEN_UPDATES;
                    updateCount++;
                }

                //If for some reason an update takes forever, we don't want to do an insane number of catchups.
                //If you were doing some sort of game that needed to keep EXACT time, you would get rid of this.
                if ( now - lastUpdateTime > TIME_BETWEEN_UPDATES)
                {
                    lastUpdateTime = now - TIME_BETWEEN_UPDATES;
                }

                //Render. To do so, we need to calculate interpolation for a smooth render.
                float interpolation = Math.min(1.0f, (float) ((now - lastUpdateTime) / TIME_BETWEEN_UPDATES) );
                renderThread.onDraw(canvas);
                lastRenderTime = now;
                }
                finally {
                    if (canvas != null)
                        this.surfaceHolder.unlockCanvasAndPost(canvas);
                }
                //Update the frames we got.
                int thisSecond = (int) (lastUpdateTime / 1000000000);
                if (thisSecond > lastSecondTime)
                {
                    System.out.println("NEW SECOND " + thisSecond + " " + frameCount);
                    fps = frameCount;
                    frameCount = 0;
                    lastSecondTime = thisSecond;
                }

                //Yield until it has been at least the target time between renders. This saves the CPU from hogging.
                while ( now - lastRenderTime < TARGET_TIME_BETWEEN_RENDERS && now - lastUpdateTime < TIME_BETWEEN_UPDATES)
                {
                    Thread.yield();

                    //This stops the app from consuming all your CPU. It makes this slightly less accurate, but is worth it.
                    //You can remove this line and it will still work (better), your CPU just climbs on certain OSes.
                    //FYI on some OS's this can cause pretty bad stuttering. Scroll down and have a look at different peoples' solutions to this.
                    try {Thread.sleep(1);} catch(Exception e) {}

                    now = System.nanoTime();
                }
            }
        }
    }


//
//    @Override
//    public void run() {
//        long ticksPS = 1000 / FPS;
//        long startTime;
//        long sleepTime;
//        Canvas canvas;
//       // gamesClient.connect();
//        Log.d("INET", "GAME THREAD STARTED");
//        while (running) {
//            canvas = null;
//            startTime = System.currentTimeMillis();
//            // try locki        n   g   th  e       c   an  vas for exclusive pixel editing
//            // in the surface
//            try {
//
//                canvas = this.surfaceHolder.lockCanvas();
//                synchronized (this.surfaceHolder) {
//                    Update();
//                   //     Log.d("INET",(String)ServerThread.getLocalIpAddress());
//                    // update game state
//                    // render state to the screen
//                    // draws the canvas on the panel
//                  //  if(Global.Multiplayer)
//                     //   if(RenderThread.finger!=null)gamesClient.sendUnreliableRealTimeMessageToAll(Serializer.SerializetoBytes(RenderThread.finger),room.getRoomId());
//                    if(canvas!=null)
//                    this.renderThread.onDraw(canvas);
//                }
//            } finally {
//                // in case of an exception the surface is not left in
//                // an inconsistent state
//                if (canvas != null)
//                    this.surfaceHolder.unlockCanvasAndPost(canvas);
//            } // end finally
//
//            sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
//            try {
//                if (sleepTime > 0)
//                    sleep(sleepTime);
//                else
//                    sleep(10);
//            } catch (Exception e) {
//            }
//        }
//        Log.d("INET","CLOSED GAME THREAD");
//    }

}
