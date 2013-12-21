package SpellProjectiles;

import Actors.Player;
import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class DrainProjectile extends Projectile {

    public DrainProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter, 100, 15, new Vector(50, 50), 5);

    }


    @Override
    public void Update() {
        super.Update();
        if (lifePhase == 50) {
            health = 50;
            float td = 10000;
            Player target = this.FindClosestPlayer(td);


                if (target != null)
                    this.velocity = GetVel(this.position, target.position);

        }
    }
}
