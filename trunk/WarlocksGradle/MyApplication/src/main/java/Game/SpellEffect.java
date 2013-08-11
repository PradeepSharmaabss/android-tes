package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.ArrayList;

import Tools.SpriteSheet;
import Tools.Vector;

/**
 * Created by Scott on 6/19/13.
 */
public class SpellEffect {
    public int Duration;
   public enum EffectType{Stun, Poison, Reflect, Magnetise, Freeze, Cast}
   public EffectType effectType;
    static Paint paint;
    SpriteSheet sprites;
    ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
    Bitmap curr;
    int currFrame = 0;
    int frameRate = 1;
    int frameDelay =0;
    int i = 0;
public  SpellEffect(int _d, EffectType _e, ArrayList<Bitmap> _s)
{
    Duration= _d;
    effectType=_e;
    frames = _s;
   // this.sprites.Load(new Vector(100,100));
    //GetSprites();
    switch (effectType) {
    case Reflect:
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
        frameDelay=2;
        break;
    case Poison:
        break;
    case Stun:

        break;
    case Magnetise:
        break;
    case Cast:
        break;
    case Freeze:
        frameDelay = 1;
        break;
}

}

    protected void GetSprites() {
        for (int x = 0; x < 4; x++)
            this.frames.add(this.sprites.tiles.get(x));
       curr = frames.get(0);
    }
    public void Animate()
    {
        if(i<frameDelay)
            i++;
        else
        {
        i=0;
        currFrame +=frameRate;
        if(currFrame>=frames.size())
        {
            if(effectType!=EffectType.Freeze)
            currFrame=-0;
            else{
                frameRate=0;
                currFrame-=1;
            }
        }
        curr=frames.get(currFrame);}
    }

    public void Draw(Canvas canvas,Vector _pos)
    {


        switch (effectType) {
            case Reflect:
                if(curr!=null)
                canvas.drawBitmap(curr,_pos.x, _pos.y,
                        paint);
                break;
            case Poison:
                break;
            case Stun:
                break;
            case Magnetise:
                break;
            case Cast:
                break;
            case Freeze:
                if(curr!=null)
                    canvas.drawBitmap(curr,_pos.x, _pos.y,
                            paint);
                break;
        }


    }
}
