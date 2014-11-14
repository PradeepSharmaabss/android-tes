package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 2/01/14.
 */
public class PiercingProjectile extends Projectile {
    private int projectiles = 0;

    @Override
    protected void setFrames() {
        FramesTail();
    }

    public PiercingProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_piercing,_from, _to, _shooter, 5000, 4, new Vector(150, 150), 1);

        this.objectObjectType= ObjectType.Piercing ;
    }


}
