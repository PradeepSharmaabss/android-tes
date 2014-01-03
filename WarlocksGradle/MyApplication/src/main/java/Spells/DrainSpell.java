package Spells;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.DrainProjectile;
import SpellProjectiles.FireballProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 3/01/14.
 */
public class DrainSpell extends Spell {
    public DrainSpell(GameObject _parent) {
        super(_parent);
    }
    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new DrainProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
