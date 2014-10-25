package developmental.warlocks.GL.NewHeirarchy;


import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import Game.ObjectType;
import Tools.BoundingCircle;
import Tools.Vector;
import developmental.warlocks.Global;

/**
 * This object is very similar to Renderable, however it has the difference that it also stores velocity and moves on each update
 */
public class Moveable extends Renderable {


    //Every moveable object is temporary. the 'id" field is to make sure I can delete then if and when necessary
    public int id=0;
    //The object that created this object
    public GameObject owner;// = null;


    //The rate the object is moving in 'x' and 'y' direction.
    public Vector velocity;
    //The Maximum Speed it can get by applying force to itself in the given direction
    protected float maxVelocity = 15f;
    //The amount of force it can apply to change its velocity each frame
    protected float acceleration = 0.75f;





    protected Moveable(int _mResourceID) {
        super(_mResourceID);
    }






    @Override
    public void Update() {
        super.Update();
        //Change the position by the velocity vector
        this.position = this.position.add(this.velocity);
    }

    /**
     *  The default behaviour of a moveable object is for it to rotate in the direction it is moving.
     */
    @Override
    protected void Rotate()
    {
        if(this.velocity!=null)
            rotation = (float) Math.toDegrees(Math.atan2(-this.velocity.y, this.velocity.x));
    }
}
