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
 * Created by Scott on 19/01/14.
 */
public class Moveable extends Renderable {

    private FloatBuffer mVertexBuffer;
    void colored_rect(GL10 gl,float left, float bottom, float right, float top, float R, float G, float B)
    {

        float rect[] = {
                left, bottom,
                right, bottom,
                right, top,
                left, top
        };
        ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(rect.length * 4);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = vertexByteBuffer.asFloatBuffer();
        mVertexBuffer.put(rect);
        mVertexBuffer.position(0);
        gl.glEnableClientState(gl.GL_VERTEX_ARRAY);
        gl.glColor4f(R,G,B,1.0f);
        gl.glVertexPointer(2, gl.GL_FLOAT, 0, mVertexBuffer);
        gl.glDrawArrays(gl.GL_TRIANGLE_FAN, 0, 4);
    }
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
                gl.glRotatef(rotation, 0, 0, 1.0f);
        }
        mGrid.get(this.frame).draw(gl, true, false);
   //    this.colored_rect(gl,0f,0f,10.0f,10.0f,0.8f,0.2f,0.5f);
//            if(!boundsz)
//            OpenGLTestActivity.boundingCircle.draw(gl,0,0);
        gl.glPopMatrix();
    }
}
