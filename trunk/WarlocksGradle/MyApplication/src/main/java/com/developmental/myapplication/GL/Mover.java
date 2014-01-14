package com.developmental.myapplication.GL;

/**
 * Created by Scott on 5/01/14.
 */

import android.os.SystemClock;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

import Game.GameObject;
import Game.ObjectType;
import Input.NetworkFinger;
import Tools.Vector;

/**
 * A simple runnable that updates the position of each sprite on the screen
 * every frame by applying a very simple gravity and bounce simulation.  The
 * sprites are jumbled with random velocities every once and a while.
 */
public class Mover implements Runnable {
    private GameObject[] mRenderables;
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
            final boolean jumble =
                    (time - mLastJumbleTime > JUMBLE_EVERYTHING_DELAY);
            if (jumble) {
                mLastJumbleTime = time;
            }
            int selectedSpell = -1;
            int i = 0;
            if (i < fingers.size())

            {
                while (i < fingers.size()) {
                    // Log.d("INET",fingers.get(i).Step+" " + Gamestep);
                    NetworkFinger f=  fingers.get(i);
                    if (fingers.get(i).Step <= Gamestep) {
                        RenderThread.players.get(f.id).FingerUpdate(f.finger,f.SelectedSpell);
                        fingers.remove(i);
                    } else
                        i++;
                }
            }
            k = new NetworkFinger(Gamestep+1+ Global.TargetFrameIncrease , RenderThread.finger.WorldPositions(), Global.playerno, selectedSpell);
            RenderThread.archie.FingerUpdate(k.finger,k.SelectedSpell);
            fingers.add(k);

            for (int x = 0; x < RenderThread.gameObjects.size(); x++) {
                GameObject object = RenderThread.gameObjects.get(x);
                //pass timedelta seconds
                object.Update();
                Collision();
                // Jumble!  Apply random velocities.
                if (jumble) {
                    object.velocity.add(new Vector((MAX_VELOCITY / 2.0f)
                            - (float)(Math.random() * MAX_VELOCITY),(MAX_VELOCITY / 2.0f)
                            - (float)(Math.random() * MAX_VELOCITY)));
                }

                // Move.

                // Apply Gravity.
             //   object.velocity.y -= SPEED_OF_GRAVITY * timeDeltaSeconds;

                // Bounce.



            }
        }

    }
void Collision()
{
    for (int x = 0; x < RenderThread.gameObjects.size(); x++) {
        ArrayList<Integer> g = new ArrayList<Integer>();
        for (int y = x + 1; y < RenderThread.gameObjects.size(); y++) {
            if(RenderThread.gameObjects.get(x).objectObjectType== ObjectType.LineSpell)
            {
                if (RenderThread.gameObjects.get(x).CollidesWith(RenderThread.gameObjects.get(y)))
                    RenderThread.gameObjects.get(x).collisions.add(y);
            }
            else
            {
                if(RenderThread.gameObjects.get(y).objectObjectType==ObjectType.LineSpell)
                {
                    if (RenderThread.gameObjects.get(x).CollidesWith(RenderThread.gameObjects.get(y)))
                        RenderThread.gameObjects.get(y).collisions.add(x);
                }
                else
                {
                    if (RenderThread.gameObjects.size() > y
                            && RenderThread.gameObjects.size() > x)
                        if (RenderThread.gameObjects.get(x).owner == null
                                || RenderThread.gameObjects.get(y).owner == null) {
                            if (RenderThread.gameObjects.get(x).CollidesWith(RenderThread.gameObjects.get(y))) {
                                if(RenderThread.gameObjects.get(y).objectObjectType==ObjectType.LineSpell)
                                    g.add(y);
                                else
                                {
                                    RenderThread.gameObjects.get(y).Collision2(
                                            RenderThread.gameObjects.get(x));
                                }
                                continue;
                            }

                        } else if ((RenderThread.gameObjects.get(x).owner.id != RenderThread.gameObjects
                                .get(y).id
                                && RenderThread.gameObjects.get(y).owner.id != RenderThread.gameObjects
                                .get(x).id))
                            if (RenderThread.gameObjects.get(x).CollidesWith(RenderThread.gameObjects.get(y))) {

                                if(RenderThread.gameObjects.get(y).objectObjectType==ObjectType.LineSpell)
                                    g.add(y);
                                else
                                {
                                    RenderThread.gameObjects.get(y).Collision2(
                                            RenderThread.gameObjects.get(x));
                                }
                                continue;
                            }
                }
            }



        }



    }
    for(int e= 0; e<RenderThread.gameObjects.size(); e++)
    {
        float lik = 10000000;
        GameObject a = null;
        for(Integer y : RenderThread.gameObjects.get(e).collisions)
        {
            float j = Vector.DistanceBetween(RenderThread.gameObjects.get(e).bounds.Center,RenderThread.gameObjects.get(y).bounds.Center);
            if(j<lik)
            {
                lik= j;
                a = RenderThread.gameObjects.get(y);
            }
        }
        if(a!=null)
        {
            a.Collision2(RenderThread.gameObjects.get(e));
        }
    }
}

    public void setRenderables(GameObject[] renderables) {
        mRenderables = renderables;
    }

    public void setViewSize(int width, int height) {
        mViewHeight = height;
        mViewWidth = width;
    }

}