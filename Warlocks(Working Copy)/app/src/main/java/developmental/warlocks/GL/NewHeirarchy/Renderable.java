package developmental.warlocks.GL.NewHeirarchy;

/**
 * Created by Scott on 5/01/14.
 */

import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.Global;

/**
 * Base class defining the core set of information necessary to render (and move
 * an object on the screen.  This is an abstract type and must be derived to
 * add methods to actually draw (see CanvasSprite and GLSprite).
 */
public abstract class Renderable {
    // Position.
    public boolean Within(RectF Bounds)
    {
//         if(this.position.x>Bounds.left)
//             if(this.position.x<Bounds.right)
//                 if(this.position.y>Bounds.top)
//                     if(this.position.y<Bounds.bottom)

                         return true;

//                         return false;
    }


public Vector position=new Vector(0,0);
    public float z=0;

    // The OpenGL ES texture handle to draw.
    protected int mTextureName;
    // The id of the original resource that mTextureName is based on.
    protected int mResourceId;
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

    public boolean boundsz=false;


    // Size.
public Vector size;
    protected ArrayList<Grid> mGrid;

    public void setGrid(ArrayList<Grid> grid) {
        mGrid = grid;
    }

    public ArrayList<Grid> getGrid() {
        return mGrid;
    }
   protected int lifePhase = 0;
   protected int frameRate = 5;
    protected int frame=0;
   public boolean se = false;

    protected Renderable(int _mResourceID) {
        setResourceId(_mResourceID);
        if(Global.resources.get(getResourceId())!=null)
     setTextureName(Global.resources.get(getResourceId()));
    }

    public void draw(GL10 gl, float offsetX, float offsetY, boolean b){
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);

        gl.glPushMatrix();
        gl.glLoadIdentity();
        if(b)
            gl.glTranslatef(position.x,position.y,0);
        else
            gl.glTranslatef(
                    position.x-offsetX,
                    Global.WORLD_BOUND_SIZE.y-position.y-offsetY,
                    z);
        Log.e("ERROR WAS HERE", String.valueOf(mGrid));
        mGrid.get(this.frame).draw(gl, true, false);
        gl.glPopMatrix();



    }
    public void draw2(GL10 gl, float offsetX, float offsetY, boolean b){
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);

        gl.glPushMatrix();
        gl.glLoadIdentity();
        if(b)
            gl.glTranslatef(position.x,position.y,0);
        else
            gl.glTranslatef(
                    position.x-offsetX,
                    Global.WORLD_BOUND_SIZE.y-position.y-offsetY,
                    z);
        mGrid.get(this.frame).draw(gl, true, false);
        gl.glPopMatrix();



    }
    public void Update(){
        lifePhase++;
      Animate();
    }
    public void Animate() {
//        frame++;
//        if(mGrid!=null)
//        if(frame>=mGrid.size())
//        {
//            frame = 0;
//        }
    }
}


