package developmental.warlocks.GL.NewHeirarchy;


import android.util.Log;

import java.util.ArrayList;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 19/01/14.
 * Particles are temporary moveables, which will dissappear after their lifespan is up. they are usually dropped by projectiles
 */
public abstract class glParticle extends Moveable {
    //A variable to set how long the particle will live
    int lifespan = 0;

    public glParticle(Vector position, Vector velocity, int lifeSpan,int _mResourceID,ArrayList<Grid> _g) {
        super(_mResourceID);
        this.rotateable = true;
        this.position = position.get();
        this.velocity = velocity.get();
        this.lifespan = lifeSpan;
        this.size = new Vector(30,30);
        this.mGrid= _g;
    }


    public void Update() {
        //tick down, and at the end of its lifespan, kill this particle
        lifespan -= 1;
        if (lifespan <= 0) {
            SimpleGLRenderer.delParticle(this.id);
            return;
        }

        super.Update();
    }

}
