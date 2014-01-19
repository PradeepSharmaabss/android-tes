package com.developmental.myapplication.GL.NewHeirachy;

import java.util.ArrayList;

import Game.ObjectType;
import Tools.BoundingCircle;
import Tools.Vector;

/**
 * Created by Scott on 19/01/14.
 */
public class Moveable extends Renderable {
    // Velocity.
    public Vector velocity;
    public GameObject owner;// = null;
    public ObjectType objectObjectType;
    public BoundingCircle bounds;
    protected float acceleration = 0.75f;
    protected float maxVelocity = 15f;
    public ArrayList<Integer> collisions = new ArrayList<Integer>();
    protected Moveable(int _mResourceID) {
        super(_mResourceID);
    }

    @Override
    public void Update() {
        super.Update();
        this.position = this.position.add(this.velocity);

    }
}
