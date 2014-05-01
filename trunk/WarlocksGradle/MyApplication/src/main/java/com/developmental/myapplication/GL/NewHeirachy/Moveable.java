package com.developmental.myapplication.GL.NewHeirachy;

import com.developmental.myapplication.Global;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import Game.ObjectType;
import Tools.BoundingCircle;
import Tools.Vector;

/**
 * Created by Scott on 19/01/14.
 */
public class Moveable extends Renderable {
    // Velocity.
    float rotation = 0;
    public int id=0;
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
protected boolean rotateable= false;
    @Override
    public void Update() {
        super.Update();
        this.position = this.position.add(this.velocity);

    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean b) {

        super.draw(gl, offsetX, offsetY, b);
        gl.glPushMatrix();
//        gl.glLoadIdentity();
//
//            gl.glTranslatef(
//        bounds.Center.x-offsetX,
//                Global.WORLD_BOUND_SIZE.y-bounds.Center.y-offsetY,
//                z);
//        mGrid.get(this.frame).draw(gl, true, false);
////            if(!boundsz)
////            OpenGLTestActivity.boundingCircle.draw(gl,0,0);
//        gl.glPopMatrix();
        gl.glTranslatef(
                position.x-offsetX,
                Global.WORLD_BOUND_SIZE.y-position.y-offsetY,
                z);
        if(rotateable) {
            rotation = (float) Math.toDegrees(Math.atan2(-this.velocity.y, this.velocity.x));
            if (rotation != 0)
                gl.glRotatef(rotation, 0, 0, 1.0f);
        }
        mGrid.get(this.frame).draw(gl, true, false);
//            if(!boundsz)
//            OpenGLTestActivity.boundingCircle.draw(gl,0,0);
        gl.glPopMatrix();
    }
}
