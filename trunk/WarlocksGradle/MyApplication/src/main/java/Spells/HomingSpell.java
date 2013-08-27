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
        RenderThread.addObject(new FireballProjectile(new Vector(this.parent.rect.left
                + this.parent.rect.width() / 2, this.parent.rect.top
                + this.parent.rect.height() / 2), new Vector(Dest.x, Dest.y), this.parent));
    }
}
