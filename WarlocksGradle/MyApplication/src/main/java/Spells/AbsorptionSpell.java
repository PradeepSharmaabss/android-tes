package Spells;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.AbsorptionProjectile;
import SpellProjectiles.DrainProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 3/01/14.
 */
public class AbsorptionSpell extends Spell {
    public AbsorptionSpell(GameObject _parent) {
        super(_parent);
    }
    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new AbsorptionProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
