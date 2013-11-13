package SpellProjectiles;

import android.util.Log;

import com.developmental.myapplication.RenderThread;

import Actors.Player;
import Game.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangProjectile extends Projectile {
    public BoomerangProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter, 90000, 30f, new Vector(50, 50), 3);
        this.acceleration = 0.5f;
    }

    int i = 0;

    @Override
    public void Update() {
        super.Update();
        if (i++ % 5 == 4) {
            float td = 10000f;


            this.destination = owner.feet;
        }
        if (i > 150)
            if (this.rect.intersect(owner.rect))
                RenderThread.delObject(this.id);

    }
}
