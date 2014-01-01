package SpellProjectiles;

import android.graphics.Color;

import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 31/12/13.
*/
public class FiresprayProjectile extends FireballProjectile {


    public FiresprayProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(_from, _to, _shooter);
        this.burnHit = 30;
        this.paint.setColor(Color.YELLOW);
    }
}
