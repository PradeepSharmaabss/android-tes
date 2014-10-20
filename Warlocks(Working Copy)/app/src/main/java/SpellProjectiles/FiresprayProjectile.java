package SpellProjectiles;

import android.graphics.Color;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 31/12/13.
*/
public class FiresprayProjectile extends FireballProjectile {


    public FiresprayProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(_from, _to, _shooter);
        this.burnHit = 30;

    }
}
