package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 13/11/2014.
 */
public class TrapMineProjectile extends Projectile {
    @Override
    protected void setFrames() {
        FramesNoTail();
    }

    public TrapMineProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_orbital,_to, _to, _shooter, 100, 20f, new Vector(50, 50), 10);
        this.velocity= new Vector(0,0);

    }
}
