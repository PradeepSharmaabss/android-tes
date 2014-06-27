package developmental.warlocks.GL;

/**
 * Created by Scott on 5/01/14.
 */

import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import Game.ObjectType;
import Input.NetworkFinger;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.GL.NewHeirarchy.glButton;
import developmental.warlocks.Global;

/**
 * A simple runnable that updates the position of each sprite on the screen
 * every frame by applying a very simple gravity and bounce simulation.  The
 * sprites are jumbled with random velocities every once and a while.
 */
public class Mover implements Runnable {
    private Renderable[] mRenderables;
    private long mLastTime;
    private long mLastJumbleTime;
    private int mViewWidth;
    private int mViewHeight;

    static final float COEFFICIENT_OF_RESTITUTION = 0.75f;
    static final float SPEED_OF_GRAVITY = 150.0f;
    static final long JUMBLE_EVERYTHING_DELAY = 15 * 1000;
    static final float MAX_VELOCITY = 8000.0f;

    public static NetworkFinger k;
    public static int Gamestep = 0;

    public static ArrayList<NetworkFinger> fingers = new ArrayList<NetworkFinger>();
    public void run() {
        Gamestep+=1;
        // Perform a single simulation step.
        if (mRenderables != null) {
            final long time = SystemClock.uptimeMillis();
            final long timeDelta = time - mLastTime;
            final float timeDeltaSeconds =
                    mLastTime > 0.0f ? timeDelta / 1000.0f : 0.0f;
            mLastTime = time;

            // Check to see if it's time to jumble again.
Update();
    }
    }
    void Update()
    {
        Gamestep += 1;
//    if(Global.Multiplayer)
//        for(Player p :SimpleGLRenderer.players)
//        {
//            boolean found = false;
//            for(NetworkFinger f : fingers)
//            {
//                if(f.id==p.id&&f.Step==Gamestep)
//                {
//                    found= true;
//                }
//            }
//            if(!found)
//            {
//                Gamestep-=1;
//                break;
//            }
//        }
        // boolean f = false;
        int selectedSpell = -1;
        // Chekcs Which Buttons are Down, the last down one in order of left to
        // right becomes the selected spell
        for (int d = 0; d<SimpleGLRenderer.buttons.size(); d++)
        {
            glButton b= SimpleGLRenderer.buttons.get(d);
            b.Update(d);
            if (b.down) {
                selectedSpell =d;

                Log.d("INET", "DOWN");
            }
        }
        for (int f = 0; f < SimpleGLRenderer.popupTexts.size(); f++) {
            SimpleGLRenderer.popupTexts.get(f).Update();
        }
        for (int f = 0; f < SimpleGLRenderer.Particles.size(); f++) {
            SimpleGLRenderer.Particles.get(f).Update();
        }


        Collision();

        SimpleGLRenderer.l.platform.Shrink();

        Collections.sort(SimpleGLRenderer.gameObjects);


        int i = 0;
        if (i < fingers.size())

            while (i < fingers.size()) {
                // Log.d("INET",fingers.get(i).Step+" " + Gamestep);
                if (fingers.get(i).Step <= Gamestep) {
                    SimpleGLRenderer.players.get(fingers.get(i).id).FingerUpdate(fingers.get(i).finger, fingers.get(i).SelectedSpell);
                    fingers.remove(i);
                } else
                    i++;
            }
        if(Gamestep% Global.InputFrameGap==0)
        {
            k = new NetworkFinger(Gamestep+Global.TargetFrameIncrease , SimpleGLRenderer.finger.WorldPositions(), Global.playerno, selectedSpell);
            fingers.add(k);




        }

//    if(Global.Multiplayer)
//        for(Player p :SimpleGLRenderer.players)
//        {
//            boolean found = false;
//            for(NetworkFinger f : fingers)
//            {
//                if(f.id==p.id&&f.Step==Gamestep)
//                {
//                    found= true;
//                }
//            }
//            if(!found)
//            {
//                Gamestep-=1;
//                break;
//            }
//        }

    }



void Collision()
{
    for (int d = 0; d < SimpleGLRenderer.gameObjects.size(); d++)
    {

        SimpleGLRenderer.gameObjects.get(d).collisions.clear();
        SimpleGLRenderer.gameObjects.get(d).Update();
    }
    for (int x = 0; x < SimpleGLRenderer.gameObjects.size(); x++) {
        ArrayList<Integer> g = new ArrayList<Integer>();
        for (int y = x + 1; y < SimpleGLRenderer.gameObjects.size(); y++) {
            if(SimpleGLRenderer.gameObjects.get(x).objectObjectType==ObjectType.LineSpell)
            {
                if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y)))
                    SimpleGLRenderer.gameObjects.get(x).collisions.add(y);
            }
            else
            {
                if(SimpleGLRenderer.gameObjects.get(y).objectObjectType==ObjectType.LineSpell)
                {
                    if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y)))
                        SimpleGLRenderer.gameObjects.get(y).collisions.add(x);
                }
                else
                {
                    if (SimpleGLRenderer.gameObjects.size() > y
                            && SimpleGLRenderer.gameObjects.size() > x)
                        if (SimpleGLRenderer.gameObjects.get(x).owner == null
                                || SimpleGLRenderer.gameObjects.get(y).owner == null) {
                            if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y))) {
                                if(SimpleGLRenderer.gameObjects.get(y).objectObjectType==ObjectType.LineSpell)
                                    g.add(y);
                                else
                                {
                                    SimpleGLRenderer.gameObjects.get(y).Collision2(
                                            SimpleGLRenderer.gameObjects.get(x));
                                }
                                continue;
                            }

                        } else if ((SimpleGLRenderer.gameObjects.get(x).owner.id != SimpleGLRenderer.gameObjects
                                .get(y).id
                                && SimpleGLRenderer.gameObjects.get(y).owner.id != SimpleGLRenderer.gameObjects
                                .get(x).id))
                            if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y))) {

                                if(SimpleGLRenderer.gameObjects.get(y).objectObjectType==ObjectType.LineSpell)
                                    g.add(y);
                                else
                                {
                                    SimpleGLRenderer.gameObjects.get(y).Collision2(
                                            SimpleGLRenderer.gameObjects.get(x));
                                }
                                continue;
                            }
                }
            }



        }



    }
    for(int e= 0; e<SimpleGLRenderer.gameObjects.size(); e++)
    {
        float lik = 10000000;
        GameObject a = null;
        for(Integer y : SimpleGLRenderer.gameObjects.get(e).collisions)
        {
            float j = Vector.DistanceBetween(SimpleGLRenderer.gameObjects.get(e).bounds.Center,SimpleGLRenderer.gameObjects.get(y).bounds.Center);
            if(j<lik)
            {
                lik= j;
                a = SimpleGLRenderer.gameObjects.get(y);
            }
        }
        if(a!=null)
        {
            a.Collision2(SimpleGLRenderer.gameObjects.get(e));
        }
    }
}

    public void setRenderables(Renderable[] renderables) {
        mRenderables = renderables;
    }

    public void setViewSize(int width, int height) {
        mViewHeight = height;
        mViewWidth = width;
    }

}