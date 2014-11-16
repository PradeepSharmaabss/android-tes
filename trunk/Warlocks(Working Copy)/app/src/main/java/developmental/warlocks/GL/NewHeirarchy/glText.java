package developmental.warlocks.GL.NewHeirarchy;

import android.util.Log;

import com.developmental.warlocks.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.microedition.khronos.opengles.GL10;

import HUD.PopupText;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.Global;

/**
 * Created by Scott on 2/05/2014.
 */
public class glText  {

    private static Map<PopupText.TextType,Integer> mTextureName=new HashMap<PopupText.TextType, Integer>();


    public void draw(String text, GL10 gl, float offsetX, float offsetY, PopupText.TextType textType, Size Size) {
        float fontoffset = 10;

        switch (Size)
        {

            case Small:
                fontoffset= 30;
                break;
            case Medium:
                fontoffset= 70;
                break;
            case Large:
                fontoffset= 100;
                break;
        }
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName.get(textType));
        gl.glPushMatrix();
        gl.glLoadIdentity();
        gl.glTranslatef(offsetX,offsetY,0);
        for(int i = 0; i<text.length(); i++) {
            gl.glPushMatrix();
            gl.glTranslatef(i*fontoffset*2/3,0,0);


            Fonts.get(Size).get(text.charAt(i)-32).draw(gl, true, false);

            gl.glPopMatrix();
        }


    gl.glPopMatrix();
    }




    Map<Size,ArrayList<Grid>> Fonts = new HashMap<Size, ArrayList<Grid>>();
    public enum Size{Small,Medium,Large};
    public glText() {

        this.mTextureName.put(PopupText.TextType.Message, Global.resources.get(R.drawable.font_white));
        this.mTextureName.put(PopupText.TextType.Burn, Global.resources.get(R.drawable.font_red));
        this.mTextureName.put(PopupText.TextType.Poison, Global.resources.get(R.drawable.font_green));
        this.mTextureName.put(PopupText.TextType.Spell, Global.resources.get(R.drawable.font_purple));
        this.mTextureName.put(PopupText.TextType.Lava, Global.resources.get(R.drawable.font_yellow));

        Fonts.put(Size.Large,makeFont(100,100));
        Fonts.put(Size.Medium,makeFont(70,70));
        Fonts.put(Size.Small,makeFont(30,30));
    }
    ArrayList<Grid> makeFont(float _width, float _height)
    {
        Grid spriteGrid;
        ArrayList<Grid> letters = new ArrayList<Grid>();

        float SPRITE_WIDTH=_width;
        float SPRITE_HEIGHT=_height;
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
        return letters;
    }

}
