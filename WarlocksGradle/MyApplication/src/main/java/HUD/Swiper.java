package HUD;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Input.Pointer;
import Tools.Vector;

/**
 * Created by Scott on 13/12/13.
 */
public class Swiper {
    Vector Position;
    Vector Size;
    Paint p = new Paint();
    int[] WhichImages;
    int SelectedIndex = 0;
   public boolean Selected = false;
    public Swiper(Vector _p,Vector _s,int[] _w)
    {
        Position= _p;
        Size=_s;
        p.setColor(Color.GRAY);
        WhichImages=_w;

    }
    public void Draw(Canvas canvas)
    {
        if(Selected)
        {
        for(int i =0; i<WhichImages.length;i++)
        {
            Vector Offset = new Vector(0,Size.y*(i-SelectedIndex));
            canvas.drawRect(Position.x+Offset.x,Position.y+Offset.y,Position.x+Size.x+Offset.x,Position.y+Size.y+Offset.y,p);
            canvas.drawBitmap(Global.Sprites.get(WhichImages[i]).get(0),Position.x+Offset.x,Position.y+Offset.y,p);
            //
        }
        }
        else
        {
            canvas.drawRect(Position.x,Position.y,Position.x+Size.x,Position.y+Size.y,p);
            canvas.drawBitmap(Global.Sprites.get(WhichImages[SelectedIndex]).get(0),Position.x,Position.y,p);

        }


    }
    public void Update() {
        if (!Global.LOCKSPELLMODE) {
            for (int x = 0; x < RenderThread.finger.pointers.length; x++) {
                Pointer f = RenderThread.finger.pointers[x];

                if (!f.down)
                    continue;

                if (new RectF(Position.x,Position.y,Position.x+Size.x,Position.y+Size.y).contains(f.position.x, f.position.y)) {
                    this.p.setColor(Color.RED);
                    this.Selected = true;

                    return;
                }
            }
            this.p.setColor(Color.DKGRAY);
            this.Selected = false;


        }
    }


}
