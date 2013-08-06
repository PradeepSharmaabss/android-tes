package SpellProjectiles;

import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 27/07/13.
 */
public class HomingProjectile extends Projectile {
    public HomingProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
    }
}
