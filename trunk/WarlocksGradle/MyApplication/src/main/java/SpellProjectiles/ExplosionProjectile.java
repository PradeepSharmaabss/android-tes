package SpellProjectiles;

import android.util.Log;

import Game.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplosionProjectile extends Projectile {

    public ExplosionProjectile(Vector _to,Vector _s, GameObject shooter) {
        super(_to, _to, shooter);
        velocity=new Vector(0,0);
        size= _s;
        this.health=10;
        this.position=new Vector(_to.x-size.x/2,_to.y-size.y/2);
        this.bounds.Center= _to.get();
        Log.d("INET","EXPLOSION CREATED");
        this.objectObjectType = ObjectType.Explosion;
    }
}
