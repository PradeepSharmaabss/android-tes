package HUD;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import Tools.Vector;

import com.developmental.myapplication.RenderThread;

/**
 * Created by Scott on 7/29/13.
 */
public class PopupText {
    String text;
    Paint paint;
    Paint shadowPaint;
    Vector position;
    int life;
    int thisid;
    public static int id = 0;

    public enum TextType {Lava, Message, Poison,Spell,Burn}

    public PopupText(TextType _t, String _m, Vector _p, int _l) {
        thisid = id;
        id += 1;
        text = _m;
        paint = new Paint();
        paint.setTextSize(30);
        shadowPaint = new Paint();
        shadowPaint.setColor(Color.BLACK);
        shadowPaint.setTextSize(30);
        shadowPaint.setStrokeWidth(1);
        shadowPaint.setStyle(Paint.Style.STROKE);
        //  paint.setTextSize(100);
        switch (_t) {
            case Lava:
                paint.setColor(Color.YELLOW);
                break;
            case Message:
                paint.setColor(Color.WHITE);
                break;
            case Poison:
                paint.setColor(Color.GREEN);
                break;
            case Spell:
                paint.setColor(Color.BLUE);
                break;
            case Burn:
                paint.setColor(Color.WHITE);
                break;
        }
        life = _l;
        position = _p.get();
    }

    public void Update() {
        life -= 1;

        this.position.y += Math.random() * 5;
        if (life == 0) {
            for (int i = 0; i < RenderThread.popupTexts.size(); i++) {
                if (RenderThread.popupTexts.get(i).thisid == thisid) {
                    RenderThread.popupTexts.remove(i);
                    return;
                }
            }
        }
    }

    public void Draw(float offsetx, float offsety, Canvas canvas) {
        // canvas.drawText(text,position.x-offsetx, position.y-offsety,paint);

        canvas.drawText(text, position.x - offsetx, position.y - offsety, shadowPaint);
        canvas.drawText(text, position.x - offsetx, position.y - offsety, paint);


    }


}
