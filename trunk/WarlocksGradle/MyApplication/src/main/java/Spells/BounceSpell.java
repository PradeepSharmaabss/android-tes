package Spells;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.BounceProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 28/08/13.
 */
public class BounceSpell extends Spell {
    public BounceSpell(GameObject _parent) {
        super(_parent);
    }

    void Shoot(iVector Dest) {
        RenderThread.addObject(new BounceProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }

}
