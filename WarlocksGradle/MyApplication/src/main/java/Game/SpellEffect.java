package Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import Tools.Vector;

/**
 * Created by Scott on 6/19/13.
 */
public class SpellEffect {
    public int Duration;
   public enum EffectType{Stun, Poison, Reflect, Magnetise,Cast}
   public EffectType effectType;
    static Paint paint;
public  SpellEffect(int _d, EffectType _e)
{
    Duration= _d;
    effectType=_e;
    switch (effectType) {
    case Reflect:
        paint = new Paint();
        paint.setColor(Color.MAGENTA);
        paint.setAlpha(125);
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
    public void Draw(Canvas canvas,Vector _pos)
    {


        switch (effectType) {
            case Reflect:
                canvas.drawCircle(_pos.x, _pos.y, 70,
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
