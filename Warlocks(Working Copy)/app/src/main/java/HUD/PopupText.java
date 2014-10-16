package HUD;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.glText;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 7/29/13.
 */
public class PopupText {
    public String text;

    Vector position;
    int life;
    int thisid;
    public static int id = 0;

    public enum TextType {Lava, Message, Poison,Spell,Burn}
    public void draw(glText g,GL10 gl, float offsetX, float offsetY, boolean b) {
       g.draw(text,gl, position.x-offsetX, Global.WORLD_BOUND_SIZE.y-position.y-offsetY);
      
    }
        public PopupText(TextType _t, String _m, Vector _p, int _l) {
        thisid = id;
        id += 1;
        text = _m;

        life = _l;
        position = _p.get();
    }

    public void Update() {
        life -= 1;

        this.position.y += Math.random() * 5;
        if (life == 0) {
            for (int i = 0; i < SimpleGLRenderer.popupTexts.size(); i++) {
                if (SimpleGLRenderer.popupTexts.get(i).thisid == thisid) {
                    SimpleGLRenderer.popupTexts.remove(i);
                    return;
                }
            }
        }
    }



}
