package SpellProjectiles;

import android.graphics.Color;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 27/07/13.
 */
public class HomingProjectile extends FireballProjectile {
    GameObject target;

    public HomingProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.acceleration = 0.2f;


        this.damagevalue = 7;
    }

    int i = 0;

    @Override
    public void Update() {
        super.Update();

        if (i++ % 15 == 0) {
            float td = 10000f;

            GameObject p = this.FindClosestPlayer(td);
            if(p !=null)
            {
                target=p;
            this.destination = target.feet;
            }
        }
    }
}
