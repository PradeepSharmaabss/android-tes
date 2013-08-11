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
   public enum EffectType{Stun, Poison, Reflect, Magnetise,Cast}
   public EffectType effectType;
    static Paint paint;
    SpriteSheet sprites;
    ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
    Bitmap curr;
    int currFrame = 0;
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
        break;
    case Poison:
        break;
    case Stun:

        break;
    case Magnetise:
        break;
    case Cast:
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
        currFrame +=1;
        if(currFrame>3)
        {
            currFrame=-0;
        }
        curr=frames.get(currFrame);
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
        }


    }
}
