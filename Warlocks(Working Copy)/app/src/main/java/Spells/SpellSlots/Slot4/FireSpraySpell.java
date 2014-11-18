package Spells.SpellSlots.Slot4;

import Actors.Player;
import SpellProjectiles.FiresprayProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class FireSpraySpell extends Spell {
    public FireSpraySpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {



        double degrees = Math.atan2((double)Dest.y-Origin.y,(double)Dest.x-Origin.x);
        degrees+=Global.GetRandomNumer.nextFloat()*Math.toRadians(25);
        float w = Vector.DistanceBetween(Origin,new Vector(Dest.x,Dest.y));

        Vector Dest2 = new Vector((float)(w*Math.cos(degrees)+ Origin.x),(float)(w*Math.sin(degrees)+ Origin.y));

        SimpleGLRenderer.addObject(new FiresprayProjectile(Origin, Dest2, this.parent));
    }
}
