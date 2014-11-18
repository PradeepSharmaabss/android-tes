package SpellProjectiles;

import android.util.Log;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 13/11/2014.
 */
public class OrbitalProjectile extends Projectile {
        float offset = 0;

    @Override
    protected void setFrames() {
        this.FramesNoTail();

    }
    Vector Home ;
    public OrbitalProjectile(Vector _from, Vector _to, GameObject shooter, float _offset) {
        super(R.drawable.spell_orbital, _from, _to, shooter, 100, 10, new Vector(50, 50), 6);
        offset = _offset;
        Movement();

    }

    @Override
    protected void Movement() {
        float angle = (float) Math.toRadians(offset + lifePhase * 5);
        float w = 100;
        Vector Dest1 = new Vector((float) (w * Math.cos(angle) + this.owner.bounds.Center.x), (float) (w * Math.sin(angle) + this.owner.bounds.Center.y));

        this.position = Dest1;
    }

    @Override
    protected void Rotate() {

    }

}
