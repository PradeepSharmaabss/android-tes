package Spells;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.BoomerangProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangSpell extends Spell {
    public BoomerangSpell(GameObject _parent) {
        super(_parent);
    }

    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new BoomerangProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
