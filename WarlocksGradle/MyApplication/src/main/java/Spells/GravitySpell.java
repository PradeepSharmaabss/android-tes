package Spells;

import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.GravityProjectile;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

public class GravitySpell extends Spell {

    public GravitySpell(GameObject _parent) {
        super(_parent);
        p.setColor(Color.GREEN);
        curr = Global.ButtonImages.get(3);
        sz = 70;

    }

    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new GravityProjectile(this.parent.getCenter(), new Vector(Dest.x, Dest.y), this.parent));
    }

}
