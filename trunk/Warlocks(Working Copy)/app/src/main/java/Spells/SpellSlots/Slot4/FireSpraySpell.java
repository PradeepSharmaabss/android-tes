package Spells.SpellSlots.Slot4;

import com.developmental.warlocks.R;

import SpellProjectiles.FireballProjectile;
import SpellProjectiles.FiresprayProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class FireSpraySpell extends Spell {
    public FireSpraySpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest) {



        double degrees = Math.atan2((double)Dest.y-this.parent.bounds.Center.y,(double)Dest.x-this.parent.bounds.Center.x);
        degrees+=Global.GetRandomNumer.nextFloat()*Math.toRadians(25);
        float w = Vector.DistanceBetween(this.parent.bounds.Center,new Vector(Dest.x,Dest.y));

        Vector Dest2 = new Vector((float)(w*Math.cos(degrees)+ this.parent.bounds.Center.x),(float)(w*Math.sin(degrees)+ this.parent.bounds.Center.y));

        SimpleGLRenderer.addObject(new FiresprayProjectile(this.parent.bounds.Center, Dest2, this.parent));
    }
}
