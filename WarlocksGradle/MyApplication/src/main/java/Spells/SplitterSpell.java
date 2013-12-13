package Spells;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.SplitterProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 28/08/13.
 */
public class SplitterSpell extends Spell {
    public SplitterSpell(GameObject _parent) {
        super(_parent);
    }

    void Shoot(iVector Dest) {
        RenderThread.addObject(new SplitterProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }

}
