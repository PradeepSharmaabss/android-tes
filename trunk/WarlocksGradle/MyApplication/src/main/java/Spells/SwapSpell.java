package Spells;

import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.SwapProjectile;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.RenderThread;

/**
 * Created by Scott on 7/29/13.
 */
public class SwapSpell extends Spell {
    public SwapSpell(GameObject _parent) {
        super(_parent);
        sz = 40;
        p.setColor(Color.GREEN);
    }

    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new SwapProjectile(this.parent.getCenter(), new Vector(Dest.x, Dest.y), this.parent));
    }

}
