package developmental.warlocks.GL.NewHeirarchy;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import developmental.warlocks.GL.Grid;
import developmental.warlocks.Global;

/**
 * Created by Scott on 2/05/2014.
 */
public class glText  {
    static ArrayList<Grid> letters = new ArrayList<Grid>();
    private static int mTextureName;
    private int mResourceId;

    private static void Bind(GL10 gl) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);

    }


    public void draw(String text,GL10 gl, float offsetX, float offsetY) {
   Bind(gl);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glTranslatef(offsetX,offsetY,0);
        for(int i = 0; i<text.length(); i++) {
            gl.glPushMatrix();
            gl.glTranslatef(i*SPRITE_WIDTH*2/3,0,0);


            letters.get(text.charAt(i)-32).draw(gl, true, false);

            gl.glPopMatrix();
        }


    gl.glPopMatrix();
    }

    public void setTextureName(int name) {
        mTextureName = name;
    }
    float SPRITE_WIDTH=70;
    float SPRITE_HEIGHT=70;
    public int getResourceId() {
        return mResourceId;
    }
    public glText(int _mResourceID,float _width, float _height) {
        setTextureName(Global.resources.get(_mResourceID));
        Grid spriteGrid;
         SPRITE_WIDTH=_width;
         SPRITE_HEIGHT=_height;
        float k = 1f/15;// number of sprites accross
        float j = 1f/12;//number of sprites down
        for( int y = 0;y<12;y++) {

            for (int x = 0; x < 15; x++) {

                spriteGrid = new Grid(2, 2, false);
                spriteGrid.set(0, 0, 0.0f, 0.0f, 0.0f, k * x, j + j * y, null);
                spriteGrid.set(1, 0, SPRITE_WIDTH, 0.0f, 0.0f, k + k * x, j + j * y, null);
                spriteGrid.set(0, 1, 0.0f, SPRITE_HEIGHT, 0.0f, k * x, j * y, null);
                spriteGrid.set(1, 1, SPRITE_WIDTH, SPRITE_HEIGHT, 0.0f, k + k * x, j * y, null);
               letters.add(spriteGrid);
            }
        }
    }
}
