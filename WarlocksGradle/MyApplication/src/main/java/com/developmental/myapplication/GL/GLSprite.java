package com.developmental.myapplication.GL;

/**
 * Created by Scott on 5/01/14.
 */

import com.developmental.myapplication.Global;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11Ext;

import Tools.Vector;

/**
 * This is the OpenGL ES version of a sprite.  It is more complicated than the
 * CanvasSprite class because it can be used in more than one way.  This class
 * can draw using a grid of verts, a grid of verts stored in VBO objects, or
 * using the DrawTexture extension.
 */
public class GLSprite extends Renderable {
    // The OpenGL ES texture handle to draw.
    private int mTextureName;
    // The id of the original resource that mTextureName is based on.
    private int mResourceId;
    // If drawing with verts or VBO verts, the grid object defining those verts.
    private ArrayList<Grid> mGrid;
    private float s[] = {
         0.0f, 0.0f,  0.0f,        // V1 - bottom left

         0.0f,  1.0f,  0.0f,        // V2 - top left
         1.0f, 0.0f,  0.0f,        // V3 - bottom right

        1.0f,  1.0f,  0.0f         // V4 - top right

    };
//GLBoundsCircle boundsCircle = new GLBoundsCircle(50,new Vector(50,100));

    public GLSprite(int resourceId) {
        super();
        this.position= new Vector(0,0);
        this.velocity = new Vector(100,100);
        this.se = true;
        mResourceId = resourceId;
        ByteBuffer byteBuffer = ByteBuffer.allocateDirect(56);
        byteBuffer.order(ByteOrder.nativeOrder());
        vertices = byteBuffer.asFloatBuffer();
        vertices.put(s);
        vertices.position(0);
    }
FloatBuffer vertices;
    public void CollideMap() {
        if (this.position.x < 0)
            this.velocity.x = Math.abs(this.velocity.x);
        if (this.position.x + 100 > Global.WORLD_BOUND_SIZE.x)
            this.velocity.x = -Math.abs(this.velocity.x);
        if (this.position.y +100 > Global.WORLD_BOUND_SIZE.y)
            this.velocity.y = -Math.abs(this.velocity.y);
        if (this.position.y < 0)
            this.velocity.y = Math.abs(this.velocity.y);
    }
    public void setTextureName(int name) {
        mTextureName = name;
    }

    public int getTextureName() {
        return mTextureName;
    }

    public void setResourceId(int id) {
        mResourceId = id;
    }

    public int getResourceId() {
        return mResourceId;
    }

    public void setGrid(ArrayList<Grid> grid) {
        mGrid = grid;
    }

    public ArrayList<Grid> getGrid() {
        return mGrid;
    }
    public void Update(float timeDeltaSeconds)
    {
        position = position.add(Vector.multiply(velocity,timeDeltaSeconds));
        lifePhase++;

        if(lifePhase%frameRate ==frameRate-1)
            if(se)
                frame++;

        if(frame>=(getGrid().size()))
            frame=0;
       Animate(velocity);
        CollideMap();
    }
    public boolean bounds = false;
    public void draw(GL10 gl,float offsetX,float offsetY) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);

        if (mGrid == null) {
            // Draw using the DrawTexture extension.
            ((GL11Ext) gl).glDrawTexfOES(position.x, position.y, z, width, height);
        } else {
            // Draw using verts or VBO verts.
            gl.glPushMatrix();
            gl.glLoadIdentity();
            gl.glTranslatef(
                    position.x-offsetX,
                    position.y-offsetY,
                    z);
            mGrid.get(this.frame).draw(gl, true, false);
//            if(!bounds)
//            OpenGLTestActivity.boundingCircle.draw(gl,0,0);
            gl.glPopMatrix();

          //
        }
        gl.glTexCoordPointer(2, GL10.GL_FLOAT, 0, vertices);
        gl.glDrawArrays(GL10.GL_TRIANGLE_STRIP,0,4);
//        new MyGLBall().draw(gl);


    }
    public void Animate(Vector dest) {
        if (dest != null) {
            float deltaY = -dest.y;
            float deltaX =dest.x;
           float angleInDegrees =(float)(Math.atan2(deltaY, deltaX) * 180 / Math.PI
                    + 180);


                if (angleInDegrees >= 157.5 && angleInDegrees < 202.5) {
                     mGrid= Global.SpritesRight;
                } else if (angleInDegrees >= 112.5
                        && angleInDegrees < 157.5) {
                    mGrid=Global.SpritesRightUp;
                } else if (angleInDegrees >= 202.5
                        && angleInDegrees < 247.5) {

                    mGrid=Global.SpritesRightDown;
                } else if (angleInDegrees >= 247.5
                        && angleInDegrees < 292.5) {
                    mGrid=Global.SpritesDown;
                } else if (angleInDegrees >= 292.5
                        && angleInDegrees < 337.5) {

                    mGrid=Global.SpritesLeftDown;
                } else if (angleInDegrees < 22.5
                        || angleInDegrees >= 337.5) {

                    mGrid=Global.SpritesLeft;
                } else if (angleInDegrees >= 22.5
                        && angleInDegrees < 67.5) {

                    mGrid=Global.SpritesLeftUp;
                } else if (angleInDegrees >= 67.5
                        && angleInDegrees < 112.5)

                    mGrid=Global.SpritesUp;

        }
    }

}