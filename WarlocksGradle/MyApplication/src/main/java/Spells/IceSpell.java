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
public class IceSpell extends Spell{

    public IceSpell(GameObject _parent) {
        super(_parent);
        curr = Global.ButtonImages.get(1);
    }
    void Shoot(iVector Dest) {
        RenderThread.addObject(new IceProjectile(new Vector(this.parent.rect.left
                + this.parent.rect.width() / 2, this.parent.rect.top
                + this.parent.rect.height() / 2), new Vector(Dest.x, Dest.y), this.parent));
    }
}
