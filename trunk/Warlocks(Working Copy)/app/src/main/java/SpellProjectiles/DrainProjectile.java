package SpellProjectiles;


import com.developmental.warlocks.R;

import Game.ObjectType;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 28/08/13.
 */
public class DrainProjectile extends Projectile {

    public DrainProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.boundscircle,_from, _to, shooter, 100, 20, new Vector(50, 50), 5);
        this.objectObjectType = ObjectType.Drain;
    }


    @Override
    public void Update() {
        super.Update();
        if (lifePhase == 50) {
            health = 50;
            float td = 10000;
            GameObject target = this.FindClosestPlayer(td);


                if (target != null)
                    this.velocity = GetVel(this.position, target.position);

        }
    }
}
