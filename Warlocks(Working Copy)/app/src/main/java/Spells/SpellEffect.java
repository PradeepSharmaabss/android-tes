package Spells;

import javax.microedition.khronos.opengles.GL10;

import Game.DamageType;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.Global;

/**
 * Created by Scott on 6/19/13.
 */
public class SpellEffect extends Renderable {
    public int Duration;


    public enum EffectType {Stun, Burn, Reflect, Magnetise, Freeze, Cast, Slow, Root, Illusion, Invisible, Thrust, Phase, Explode}

    public EffectType effectType;

    int frameDelay = 0;
    int i = 0;
    Collideable parent;

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
    }

    public void AnimateCasting(iVector dest) {
        if (dest != null) {
            float deltaY = Math.abs(dest.y) - Math.abs(this.parent.feet.y);
            float deltaX = Math.abs(dest.x) - Math.abs(this.parent.feet.x);
            float angleInDegrees = (float) (Math.atan2(deltaY, deltaX) * 180 / Math.PI
                    + 180);


            if (angleInDegrees >= 157.5 && angleInDegrees < 202.5) {
                mGrid = Global.SpritesRightCast1;
            } else if (angleInDegrees >= 112.5
                    && angleInDegrees < 157.5) {
                mGrid = Global.SpritesRightUpCast1;
            } else if (angleInDegrees >= 202.5
                    && angleInDegrees < 247.5) {
                mGrid = Global.SpritesRightDownCast1;
            } else if (angleInDegrees >= 247.5
                    && angleInDegrees < 292.5) {
                mGrid = Global.SpritesDownCast1;
            } else if (angleInDegrees >= 292.5
                    && angleInDegrees < 337.5) {
                mGrid = Global.SpritesLeftDownCast1;
            } else if (angleInDegrees < 22.5
                    || angleInDegrees >= 337.5) {
                mGrid = Global.SpritesLeftCast1;
            } else if (angleInDegrees >= 22.5
                    && angleInDegrees < 67.5) {
                mGrid = Global.SpritesLeftUpCast1;
            } else if (angleInDegrees >= 67.5
                    && angleInDegrees < 112.5)
                mGrid = Global.SpritesUpCast1;

        }

    }

    public SpellEffect(int _d, EffectType _e, Collideable _p, int _r, iVector dest) {
        super(_r);

        parent = _p;
        if (_e == EffectType.Cast) {
            AnimateCasting(dest);
        } else
            this.mGrid = Global.EffectGrid;
        Duration = _d;
        effectType = _e;
        // this.sprites.Load(new Vector(100,100));
        //GetSprites();
        frameDelay = 1;
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
                frameDelay = 5;
                break;
            case Freeze:
                frameDelay = 1;
                break;

            case Slow:
                break;
            case Root:
                break;
            case Illusion:
                break;
            case Invisible:
                break;
            case Thrust:
                break;
            case Explode:
                break;
        }
    }

    @Override
    public void Update() {

        Duration -= 1;
        if (this.effectType == EffectType.Burn) {
            if (Duration % 40 == 0) {
                parent.Damage(3, DamageType.Spell);
            }
        }
        super.Update();
    }

    public void FinalUpdate() {


    }

    public void Animate() {

        if (lifePhase % frameDelay == 0) {
            if (effectType == EffectType.Freeze || effectType == EffectType.Cast) {
                if (frame < this.mGrid.size() - 1)
                    this.frame = (frame + 1) % mGrid.size();
            } else {
                this.frame = (frame + 1) % mGrid.size();
            }
        }
    }
}
