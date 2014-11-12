package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 31/12/13.
*/
public class FiresprayProjectile extends Projectile {


    public FiresprayProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_firespray,_from, _to, _shooter, 100, 20f, new Vector(50, 50), 10);
this.burnHit= 30;
    }
}
