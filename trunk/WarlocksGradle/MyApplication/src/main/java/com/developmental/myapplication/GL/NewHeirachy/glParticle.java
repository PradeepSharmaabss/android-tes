package com.developmental.myapplication.GL.NewHeirachy;

import com.developmental.myapplication.GL.Grid;
import com.developmental.myapplication.GL.SimpleGLRenderer;

import java.util.ArrayList;

import Tools.Vector;

/**
 * Created by Scott on 19/01/14.
 */
public class glParticle extends Moveable {
int lifespan = 0;
    public glParticle(Vector position, Vector velocity, int lifeSpan,int _mResourceID) {
        super(_mResourceID);
        this.rotateable = true;
        this.position = position.get();
        this.velocity = velocity.get();
        this.lifespan = lifeSpan;
        this.size = new Vector(30,30);
        this.mGrid= new ArrayList<Grid>();
        for(int i = 0; i< 4; i++) {
            Grid backgroundGrid = new Grid(2, 2, false);
            backgroundGrid.set(0, 0, -1.5f * size.x / 2, -size.y / 2, 0.0f,  0.25f * i, 1.0f, null);
            backgroundGrid.set(1, 0, size.x / 2, -size.y / 2, 0.0f, 0.25f * (i+1), 1.0f, null);
            backgroundGrid.set(0, 1, -1.5f * size.x / 2, size.y / 2, 0.0f,  0.25f * i, 0.0f, null);
            backgroundGrid.set(1, 1, size.x / 2, size.y / 2, 0.0f,  0.25f * (i+1), 0.0f, null);
            mGrid.add(backgroundGrid);
        }

    }
    public void Update() {
        lifespan -= 1;
        if (lifespan <= 0) {
            SimpleGLRenderer.delParticle(this.id);
            return;
        }
        position = position.add(velocity);
    }

}
