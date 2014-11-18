package SpellProjectiles;

import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 23/10/2014.
 */
public class IllusionFakeProjectile extends IllusionRealProjectile {

    public IllusionFakeProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.objectObjectType= ObjectType.Illusion;
        this.archetypePower = new ArchetypePower(0,100,0,0,0,0,0);
    }
}

