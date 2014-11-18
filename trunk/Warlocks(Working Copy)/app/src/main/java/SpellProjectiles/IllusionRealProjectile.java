package SpellProjectiles;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 23/10/2014.
 */
public class IllusionRealProjectile extends Projectile {

    public IllusionRealProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_illusion,_from, _to, shooter, 100, 10, new Vector(50, 50), 6);


    }
}
