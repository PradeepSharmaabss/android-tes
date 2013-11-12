package Spells;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.IceProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 11/08/13.
 */
public class IceSpell extends Spell {

    public IceSpell(GameObject _parent) {
        super(_parent);
        curr = Global.ButtonImages.get(1);
    }

    void Shoot(iVector Dest) {
        RenderThread.addObject(new IceProjectile(this.parent.getCenter(), new Vector(Dest.x, Dest.y), this.parent));
    }
}
