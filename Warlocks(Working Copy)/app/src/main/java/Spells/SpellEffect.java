package Spells;

import android.graphics.Canvas;
import android.util.Log;

import javax.microedition.khronos.opengles.GL10;

import Game.DamageType;
import SpellProjectiles.ExplosionProjectile;
import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 6/19/13.
 */
public class SpellEffect extends Renderable {
    public int Duration;


    public enum EffectType {Stun, Burn, Reflect, Magnetise, Freeze, Cast, Slow, Explode}

    public EffectType effectType;

    int frameDelay = 0;
    int i = 0;
    Collideable parent;

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
    }

    public SpellEffect(int _d, EffectType _e,  Collideable _p, int _r) {
        super(_r);
        this.mGrid= Global.EffectGrid;
        parent = _p;
        Duration = _d;
        effectType = _e;
        // this.sprites.Load(new Vector(100,100));
        //GetSprites();
        switch (effectType) {
            case Reflect:

//                paint = new Paint();
//                paint.setColor(Color.MAGENTA);
                frameDelay = 2;
                break;
            case Burn:
               // paint= Global.PaintRed;
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
                parent.Damage(3, DamageType.Spell);
            }
        }
        Animate();
    }

    public void FinalUpdate() {


    }

    public void Animate() {
        if(effectType==EffectType.Freeze) {
            if (frame < 7)
                this.frame = (frame + 1) % 8;
        }
        else
        {
            this.frame= (frame+1)%8;
        }
    }
}
