package SpellProjectiles;

import android.util.Log;

import com.developmental.myapplication.RenderThread;

import Actors.Player;
import Game.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangProjectile extends Projectile {
    public BoomerangProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.health=300;
        this.acceleration=0.5f;
        this.maxVelocity=30f;
        this.SetVelocity(maxVelocity);
    }
    int i = 0;
    @Override
    public void Update() {
        super.Update();
        if(i++%5==0)
        {
            float td = 10000f;


            this.destination=owner.feet;
        }
        if(i>150)
        if(this.rect.intersect(owner.rect))
            RenderThread.delObject(this.id);

    }
}
