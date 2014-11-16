package SpellProjectiles;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 31/12/13.
*/
public class FiresprayProjectile extends Projectile {


    public FiresprayProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_firespray,_from, _to, _shooter, 100, 2, new Vector(50, 50), 10);
        archetypePower= new ArchetypePower(0,0,0,0,0,0,20);
    }
}
