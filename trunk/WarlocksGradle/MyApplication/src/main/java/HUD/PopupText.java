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
    Vector position;
    int life;
    int thisid;
    public static int id=0;
    public enum TextType{Damage,Message,Poison};
    public PopupText(TextType _t,String _m, Vector _p,int _l)
    {
        thisid=id;
        id+=1;
        text=_m;
        paint=new Paint();
      //  paint.setTextSize(100);
        switch (_t)
        {
            case Damage:
                paint.setColor(Color.YELLOW);
                break;
            case Message:
                paint.setColor(Color.WHITE);
                      break;
            case Poison:
                paint.setColor(Color.GREEN);
                break;
        }
        life= _l;
        position=_p;
    }
   public void Update()
   {
         life-=1;

       this.position.y+=    Math.random()*5;
       if(life==0)
       {
           for(int i = 0; i<RenderThread.popupTexts.size();i++)
           {
               if(RenderThread.popupTexts.get(i).thisid==thisid)
               {
                   RenderThread.popupTexts.remove(i);
                   return;
               }
           }
       }
   }

    public void Draw(float offsetx,float offsety,Canvas canvas)
    {
      // canvas.drawText(text,position.x-offsetx, position.y-offsety,paint);
       canvas.drawText(text,position.x-offsetx, position.y-offsety,paint);

    }


}
