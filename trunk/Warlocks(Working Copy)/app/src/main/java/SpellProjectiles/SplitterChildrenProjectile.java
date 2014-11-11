package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 28/08/13.
 */
public class SplitterChildrenProjectile extends Projectile {
    public SplitterChildrenProjectile(Vector _from, Vector _to, Collideable shooter) {
        super(R.drawable.spell_fireball,_from,_to,shooter,5,5,new Vector(20,20),1);
        this.maxVelocity = 15;
        this.health = 20;
        SetVelocity(maxVelocity);
        this.size = new Vector(15, 15);
    }
}
