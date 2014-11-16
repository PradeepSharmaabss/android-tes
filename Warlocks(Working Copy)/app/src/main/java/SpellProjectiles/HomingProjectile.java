package SpellProjectiles;

import android.util.Log;

import com.developmental.warlocks.R;

import Particles.FireParticle;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 27/07/13.
 */
public class HomingProjectile extends Projectile {
    GameObject target;


    public HomingProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_homing,_from, _to, shooter, 100, 10, new Vector(50, 50), 6);

        this.acceleration = 0.2f;


        this.damagevalue = 7;
    }
    int i = 0;

    @Override
    public void Update() {
        super.Update();

            if (this.destination != null)
                MoveTowards(this.destination, maxVelocity , acceleration );


        if (i++ % 15 == 0) {
            float td = 10000f;

            GameObject p = this.FindClosestPlayer(td);
            if(p !=null)
            {
                target=p;
            this.destination = target.feet;
            }
        }
       // SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, 0.5f), 10, R.drawable.spell_homing));

    }
}
