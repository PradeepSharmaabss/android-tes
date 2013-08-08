package Spells;

import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.SwapProjectile;
import Tools.Vector;
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
    void Shoot(Vector Dest) {
        RenderThread.addObject(new SwapProjectile(new Vector(this.parent.rect.left
                + this.parent.rect.width() / 2, this.parent.rect.top
                + this.parent.rect.height() / 2), Dest.get(), this.parent));
    }

}
