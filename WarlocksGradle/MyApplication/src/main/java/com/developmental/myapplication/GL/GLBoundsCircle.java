package com.developmental.myapplication.GL;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;

/**
 * Created by Scott on 7/01/14.
 */
public class GLBoundsCircle {
    float buffer[];
    int vcount;

    private FloatBuffer mVertexBuffer;
    public GLBoundsCircle(float _r, Vector pos)
    {
        int vertexCount = 30;
        vcount = vertexCount;
        float radius = _r;
        float center_x = pos.x;
        float center_y = pos.y;

//create a buffer for vertex data
       buffer = new float[vertexCount*2]; // (x,y) for each vertex
        int idx = 0;

//center vertex for triangle fan
        buffer[idx++] = center_x;
        buffer[idx++] = center_y;

//outer vertices of the circle
        int outerVertexCount = vertexCount-1;

        for (int i = 0; i < outerVertexCount; ++i){
            float percent = (i / (float) (outerVertexCount-1));
            float rad = percent * (float)(2*Math.PI);

            //vertex position
            float outer_x = center_x + radius * (float)Math.cos(rad);
            float outer_y = center_y + radius * (float)Math.sin(rad);

            buffer[idx++] = outer_x;
            buffer[idx++] = outer_y;
        }
        ByteBuffer vertexByteBuffer = ByteBuffer.allocateDirect(buffer.length * 4);
        vertexByteBuffer.order(ByteOrder.nativeOrder());
        mVertexBuffer = vertexByteBuffer.asFloatBuffer();
        mVertexBuffer.put(buffer);
        mVertexBuffer.position(0);
    }
    void draw(GL10 gl)
    {
//        mVertexBuffer.position(0);
//        gl.glColor4f(1f,1f,1f,1f);
//        gl.glVertexPointer(2, GL10.GL_FLOAT, 0, mVertexBuffer   );
//        gl.glDrawArrays(GL10.GL_TRIANGLE_FAN,1,vcount*2);
    }


}
