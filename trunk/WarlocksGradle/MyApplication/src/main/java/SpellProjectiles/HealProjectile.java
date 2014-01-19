package SpellProjectiles;

import android.graphics.Color;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 1/01/14.
 */
public class HealProjectile extends FireballProjectile {
    @Override
    public void Update() {
        super.Update();
        this.destination = owner.bounds.Center.get();
    }

    public HealProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(_from, _to, _shooter);
        this.destination= owner.bounds.Center.get();
        this.objectObjectType = ObjectType.HealHoming;
        this.maxVelocity=50;
        this.acceleration =4;
        this.paint.setColor(Color.WHITE);
    }
}
