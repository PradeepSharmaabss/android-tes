package developmental.warlocks.GL.NewHeirarchy;

/**
 * Created by Scott on 5/01/14.
 */

import android.graphics.RectF;

import com.developmental.warlocks.R;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.Global;

/**
 * Base class defining the core set of information necessary to render (and move
 * an object on the screen.  This is an abstract type and must be derived to
 * add methods to actually draw
 */
public abstract class Renderable {
    /**
     * Reality fields. These fields define where and how the Renderable object moves and is displayed in the world.
     */
    //The position that the object is drawn in the world
    public Vector position=new Vector(0,0);
    //How many frames this object has been alive for
    protected int lifePhase = 0;
    //The width and heigh of the object stored as 'x' and 'y' accordingly.
    protected float height = 0;
    public Vector size;
    //The 'z' location for drawing to the screen. Defaults to 0
    protected final float z=0;
    //Whether or not to calculate the rotation of this object before drawing.
    protected boolean rotateable= false;
    //The angle in degrees to rotate this object by before drawing.
    protected float rotation = 0;


    /**
     *The OpenGL variables used for drawing the objects.
     */
    // The OpenGL ES texture handle to draw.
    protected int mTextureName;
    // The id of the original resource that mTextureName is based on. Use this in Global.resources.get() to get the texture to draw.
    protected int mResourceId;
    //The Array of "Grids" that define the sample of the texutre that is drawn.
    protected ArrayList<Grid> mGrid;
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
    protected Grid shadowGrid;
    public void setShadowGrid()
    {
        if(shadowed)
        Grid.shadowGridGenerateProjectile(this.size);
    }
   protected boolean shadowed = false;

    //The information stored about the offset for animation
    protected int frameRate = 5;
    protected int frame=0;

    //The base constructor.
    protected Renderable(int _mResourceID) {
        rotation=0;
        setResourceId(_mResourceID);
        if(Global.resources.get(getResourceId())!=null)
            setTextureName(Global.resources.get(getResourceId()));
        this.size=new Vector(100,100);


    }

    /**
     * Draw the object to the screen
     * @param gl The gl10 Renderer you wish to draw this object to
     * @param offsetX The offset to draw on the x axis(Usually the players world position)
     * @param offsetY The offset to draw on the y axis(Usually the players world position)
     * @param dontDrawInRelationToWorld Whether or not to draw in relation to the world. Buttons and UI Elements= false; world object = true;
     */
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld){
        //Bind the texture

        //Push the move matrix
        gl.glPushMatrix();
        //Set it to 0
        gl.glLoadIdentity();

        //Translate to position on screen
        if(dontDrawInRelationToWorld)
            gl.glTranslatef(position.x,position.y,0);
        else
            gl.glTranslatef(
                    position.x-offsetX,
                    -position.y-offsetY,
                    z);
        //rotate if necessary
        if(rotateable) {

            gl.glRotatef(rotation, 0, 0, 1.0f);
        }
        //Draw the object at the given frame
        gl.glPushMatrix();
        gl.glTranslatef(0f,-5f,0f);
        gl.glBindTexture(GL10.GL_TEXTURE_2D, Global.resources.get(R.drawable.shadow));
        if(shadowGrid!=null)
            shadowGrid.draw(gl,true,false);
        gl.glPopMatrix();
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);
        gl.glTranslatef(0f, height, 0f);
        mGrid.get(this.frame).draw(gl, true, false);

        //Pop the matrix back
        gl.glPopMatrix();
    }

    /**
     *Called each frame for each object. This controls where and how the object moves and interacts with the world
     */
    public void Update(){
        lifePhase++;
        Animate();
        Rotate();
    }

    /**
     *  A Simple check to see whether this object is located inside a given rectangle
     *@param Bounds The Rectangle defined by a RectF to check
     **/
    public boolean Within(RectF Bounds)
    {
        if(this.position.x>Bounds.left)
            if(this.position.x<Bounds.right)
                if(this.position.y>Bounds.top)
                    if(this.position.y<Bounds.bottom)
                        return true;
        return false;
    }


    //A Function to rotate this object on each update.
    protected void Rotate()
    {
    }
    //Change the displayed frame or Grid for this object
    public void Animate() {
    }
}


