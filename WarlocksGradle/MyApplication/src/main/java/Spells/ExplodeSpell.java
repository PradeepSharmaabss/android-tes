package Spells;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import java.util.List;

import Game.GameObject;
import Game.SpellEffect;
import SpellProjectiles.ExplosionProjectile;
import SpellProjectiles.MeteorProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplodeSpell extends InstantCastSpell {
    public ExplodeSpell(GameObject _parent) {
        super(_parent);
        this.CastTime = 10;
    }

    public boolean Cast(iVector[] dest) {
        if(!parent.frozen&&!parent.dead)
        if (this.Current == 0) {
            this.Current = this.Cooldown;
            this.targetLocation =new iVector(0,0);
            castphase= 0;
            fired=  true;
            return true;
        }
        return false;
    }
    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new ExplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
    }

}
