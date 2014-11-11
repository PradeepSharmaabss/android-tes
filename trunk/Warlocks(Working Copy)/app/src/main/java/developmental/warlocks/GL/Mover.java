package developmental.warlocks.GL;

/**
 * Created by Scott on 5/01/14.
 */

import android.os.SystemClock;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;

import Input.NetworkFinger;
import SpellProjectiles.LightningProjectile;
import Tools.Vector;
import World.Level;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import HUD.glButton;
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

    public static NetworkFinger k;
    public static int Gamestep = 0;

    public static ArrayList<NetworkFinger> fingers = new ArrayList<NetworkFinger>();

    OpenGLTestActivity openGLTestActivity;
    public Mover(OpenGLTestActivity openGLTestActivity) {
       this.openGLTestActivity = openGLTestActivity;
    }
    public void run() {

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

        if(SimpleGLRenderer.Countdown>0) {
            if(Gamestep%30==0)
            SimpleGLRenderer.Countdown -= 1;
            if(SimpleGLRenderer.Countdown==0) {
                this.openGLTestActivity.finish();
            }
        }
        Log.d("COUNTDONW",SimpleGLRenderer.Countdown+"");
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


            }
        }
        for (int f = 0; f < SimpleGLRenderer.popupTexts.size(); f++) {
            SimpleGLRenderer.popupTexts.get(f).Update();
        }
        for (int f = 0; f < SimpleGLRenderer.Particles.size(); f++) {
            SimpleGLRenderer.Particles.get(f).Update();
        }


        Collision();


        if(SimpleGLRenderer.navMesh!=null)
        SimpleGLRenderer.navMesh.Update();
        Collections.sort(SimpleGLRenderer.gameObjects);


        int i = 0;
        if (i < fingers.size())

            while (i < fingers.size()) {
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
        if(!statuslockedin) {
            gamestatus = SimpleGLRenderer.l.Update();
            switch (gamestatus) {

                case Passed:
                case Failed:
                    BeginFinishScreen();
                    break;

            }
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

    private void BeginFinishScreen() {
        SimpleGLRenderer.Countdown = 20;
        statuslockedin= true;
    }


    boolean statuslockedin = false;
    public static Level.status gamestatus;

void Collision()
{
    for (int d = 0; d < SimpleGLRenderer.gameObjects.size(); d++)
    {
        Collideable g = SimpleGLRenderer.gameObjects.get(d);
        if((g.objectObjectType== Collideable.ObjectType.LineSpell))
        {
            ((LightningProjectile)g).collisions.clear();

        }
         g.Update();

    }
    ArrayList<LightningProjectile> lightinings = new ArrayList<LightningProjectile>();
    for (int x = 0; x < SimpleGLRenderer.gameObjects.size(); x++) {
        ArrayList<Integer> g = new ArrayList<Integer>();
        for (int y = x + 1; y < SimpleGLRenderer.gameObjects.size(); y++) {
            if(SimpleGLRenderer.gameObjects.get(x).objectObjectType== Collideable.ObjectType.LineSpell)
            {
                LightningProjectile r= (LightningProjectile) SimpleGLRenderer.gameObjects.get(x);
                lightinings.add(r);
                if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y)))
                    r.collisions.add(y);
            }
            else
            {
                if(SimpleGLRenderer.gameObjects.get(y).objectObjectType== Collideable.ObjectType.LineSpell)
                {
                    LightningProjectile r = (LightningProjectile) SimpleGLRenderer.gameObjects.get(y);
                    lightinings.add(r);
                    if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y)))
                       r.collisions.add(x);
                }
                else
                {
                    if (SimpleGLRenderer.gameObjects.size() > y
                            && SimpleGLRenderer.gameObjects.size() > x)
                        if (SimpleGLRenderer.gameObjects.get(x).owner == null
                                || SimpleGLRenderer.gameObjects.get(y).owner == null) {
                            if (SimpleGLRenderer.gameObjects.get(x).CollidesWith(SimpleGLRenderer.gameObjects.get(y))) {
                                if(SimpleGLRenderer.gameObjects.get(y).objectObjectType== Collideable.ObjectType.LineSpell)
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

                                if(SimpleGLRenderer.gameObjects.get(y).objectObjectType== Collideable.ObjectType.LineSpell)
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
    for(int e= 0; e<lightinings.size(); e++) {
        if (lightinings.get(e).collisions.size() > 0) {
            float lik = 10000000;
            Collideable a = null;
            for (Integer y : lightinings.get(e).collisions) {
                float j = Vector.DistanceBetween(lightinings.get(e).bounds.Center, SimpleGLRenderer.gameObjects.get(y).bounds.Center);
                if (j < lik) {
                    lik = j;
                    a = SimpleGLRenderer.gameObjects.get(y);
                }
            }
            if (a != null) {
                a.Collision2(SimpleGLRenderer.gameObjects.get(e));
            }
            lightinings.get(e).collisions.clear();
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