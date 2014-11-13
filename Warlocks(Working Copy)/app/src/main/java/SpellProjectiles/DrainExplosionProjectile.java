package SpellProjectiles;

import android.graphics.Color;
import android.graphics.Paint;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;

/**
 * Created by Scott on 13/11/2014.
 */
public class DrainExplosionProjectile extends ExplosionProjectile {


        public DrainExplosionProjectile(Vector _to, Vector _s, Collideable shooter) {
            super(_to, _s, shooter);


            this.objectObjectType = ObjectType.DrainExplosion;

        }



}
