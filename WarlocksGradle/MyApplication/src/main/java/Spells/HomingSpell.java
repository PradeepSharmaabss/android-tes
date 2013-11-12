package Spells;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.FireballProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 28/08/13.
 */
public class HomingSpell extends Spell {


    public HomingSpell(GameObject _parent) {
        super(_parent);
    }

    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new FireballProjectile(this.parent.getCenter(), new Vector(Dest.x, Dest.y), this.parent));
    }
}
