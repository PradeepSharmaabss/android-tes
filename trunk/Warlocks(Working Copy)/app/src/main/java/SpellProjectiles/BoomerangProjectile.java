package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.developmental.warlocks.R;

import Game.ObjectType;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangProjectile extends Projectile {
    public BoomerangProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_boomerang,_from, _to, shooter, 90000, 20f, new Vector(100, 100), 3);
        this.acceleration = 0.5f;
        this.objectObjectType= ObjectType.Boomerang;
        this.knockback= 25;

    }

    @Override
    protected void setFrames() {
        FramesNoTail();
    }



    @Override
    public void Update() {
        super.Update();
        if(lifePhase>15)
        if (    lifePhase% 5 == 4) {



            this.destination = owner.feet;
        }
        if (lifePhase > 50)
            if (this.bounds.CollidesWith(owner.bounds))
                SimpleGLRenderer.delObject(this.id);

    }

    @Override
    protected void Rotate() {
        this.rotation+=5;
    }
}
