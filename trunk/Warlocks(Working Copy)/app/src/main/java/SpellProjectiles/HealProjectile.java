package SpellProjectiles;
import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 1/01/14.
 */
public class HealProjectile extends Projectile {
    @Override
    public void Update() {
        super.Update();
        this.destination = owner.bounds.Center.get();
        if (this.destination != null)
            MoveTowards(this.destination, maxVelocity , acceleration );
    }

    public HealProjectile(Vector _from, Vector _to, Collideable _shooter) {

        super(R.drawable.spell_heal,_from,_to,_shooter,5000,5,new Vector(20 ,20),1);
        this.destination= owner.bounds.Center.get();
        this.objectObjectType = ObjectType.HealHoming;
        this.maxVelocity=50;
        this.acceleration =4;

    }
}
