package Game;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

import Particles.WindParticle;
import SpellProjectiles.ExplosionProjectile;
import Tools.SpriteSheet;
import Tools.Vector;

/**
 * Created by Scott on 6/19/13.
 */
public class SpellEffect {
    public int Duration;

    public enum EffectType {Stun, Burn, Reflect, Magnetise, Freeze, Cast, Slow, Explode}

    public EffectType effectType;
    static Paint paint;
    SpriteSheet sprites;
    ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
    Bitmap curr;
    int currFrame = 0;
    int frameRate = 1;
    int frameDelay = 0;
    int i = 0;
    GameObject parent;

    public SpellEffect(int _d, EffectType _e, ArrayList<Bitmap> _s, GameObject _p) {
        parent = _p;
        Duration = _d;
        effectType = _e;
        frames = _s;
        // this.sprites.Load(new Vector(100,100));
        //GetSprites();
        switch (effectType) {
            case Reflect:
                paint = new Paint();
                paint.setColor(Color.MAGENTA);
                frameDelay = 2;
                break;
            case Burn:
                paint=Global.PaintRed;
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

    public void Update() {
        Duration -= 1;
        if(this.effectType == EffectType.Burn)
        {
            if(Duration%40==0)
            {
                parent.Damage(3,DamageType.Spell);
            }
        }
        Animate();
    }

    public void FinalUpdate() {

        Log.d("INET", effectType + " " + Duration);
        if (effectType == EffectType.Explode) {
            Log.d("INET", "EXPLODED");
            RenderThread.addObject(new ExplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
        }
    }

    public void Animate() {
       if(frames!=null)
       {
        if (i < frameDelay)
            i++;
        else {
            i = 0;
            currFrame += frameRate;
            if (currFrame >= frames.size()) {
                if (effectType != EffectType.Freeze)
                    currFrame = -0;
                else {
                    frameRate = 0;
                    currFrame -= 1;
                }
            }
            curr = frames.get(currFrame);
        }
       }
    }

    public void Draw(Canvas canvas, Vector _pos) {


        switch (effectType) {
            case Reflect:
                if (curr != null)
                    canvas.drawBitmap(curr, _pos.x, _pos.y,
                            paint);
                break;
            case Burn:
                canvas.drawCircle(_pos.x+Global.GetRandomNumer.nextInt(100), _pos.y+Global.GetRandomNumer.nextInt(100),5,paint);
                canvas.drawCircle(_pos.x+Global.GetRandomNumer.nextInt(100), _pos.y+Global.GetRandomNumer.nextInt(100),5,paint);
                canvas.drawCircle(_pos.x+Global.GetRandomNumer.nextInt(100), _pos.y+Global.GetRandomNumer.nextInt(100),5,paint);
                canvas.drawCircle(_pos.x+Global.GetRandomNumer.nextInt(100), _pos.y+Global.GetRandomNumer.nextInt(100),5,paint);
                break;
            case Stun:
                break;
            case Magnetise:
                break;
            case Cast:
                break;
            case Freeze:
                if (curr != null)
                    canvas.drawBitmap(curr, _pos.x, _pos.y,
                            paint);
                break;
            case Slow:
               RenderThread.addParticle(new WindParticle(parent.bounds.Center.get(),new Vector(0,0),10,this.paint,100,30));
                break;
            case Explode:
                break;
        }


    }
}
