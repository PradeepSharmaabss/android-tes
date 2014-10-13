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

    public int id=0;
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
