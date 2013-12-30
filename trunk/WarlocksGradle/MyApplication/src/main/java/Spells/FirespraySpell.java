package Spells;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import SpellProjectiles.FireballProjectile;
import SpellProjectiles.MeteorProjectile;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 30/12/13.
 */
public class FirespraySpell extends Spell{

    public FirespraySpell(GameObject _parent) {
        super(_parent);
        this.CastTime = 15;
    }
    @Override
    public void Update() {
        if(fired)
        {
            castphase+=1;
            if(castphase%3==2)
            {
                Shoot((parent.position.subtract(this.targetLocation)));
            }
            if(castphase==CastTime)
            {
                Shoot((parent.position.subtract(this.targetLocation)));
                fired= false;
            }
        }
        else
        {
            if (this.Current > 0)
                this.Current -= 1;
        }
    }
    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new FireballProjectile(this.parent.bounds.Center, Dest.add(new Vector(Global.GetRandomNumer.nextInt(80)-40,Global.GetRandomNumer.nextInt(80)-40)), this.parent));
    }
}
