package Game;

import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Class for Storing and drawing a destination vector, like when the player is tapping around the map
 * Created by Scott on 5/30/13.
 */
public class Destination {

protected float x;
   protected float y;
    protected int Frame = 0;
    protected Paint paint = new Paint();

    protected Paint paint2 = new Paint();
    public  Destination(Vector d)
    {
        x = d.x;
        y= d.y;
        paint.setColor(Color.GREEN);
        paint.setStrokeWidth(3);
        paint2.setColor(Color.BLACK);
        paint2.setStrokeWidth(14);

    }
    public void Draw(Canvas canvas,float playerx,float playery)
    {
        if(Frame<25)
        {
        Frame+=1;
        }
        canvas.drawLine(x-Frame-playerx, y-Frame-playery,x+Frame-playerx,y+Frame-playery,paint2);
        canvas.drawLine(x-Frame-playerx, y+Frame-playery,x+Frame-playerx,y-Frame-playery,paint2);
        canvas.drawLine(x-Frame-playerx, y-Frame-playery,x+Frame-playerx,y+Frame-playery,paint);
        canvas.drawLine(x-Frame-playerx, y+Frame-playery,x+Frame-playerx,y-Frame-playery,paint);

    }

}
