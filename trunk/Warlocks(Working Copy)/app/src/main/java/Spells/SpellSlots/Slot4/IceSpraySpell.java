package Spells.SpellSlots.Slot4;

import Actors.Player;
import SpellProjectiles.IceSprayProjectile;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class IceSpraySpell extends Spell {
    public IceSpraySpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
        archetype = Archetype.Frost;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {


        double degrees = Math.atan2((double) Dest.y - Origin.y, (double) Dest.x - Origin.x);
        degrees += Global.GetRandomNumer.nextFloat() * Math.toRadians(25);
        float w = Vector.DistanceBetween(Origin, new Vector(Dest.x, Dest.y));

        Vector Dest2 = new Vector((float) (w * Math.cos(degrees) + Origin.x), (float) (w * Math.sin(degrees) + Origin.y));

        SimpleGLRenderer.addObject(new IceSprayProjectile(Origin, Dest2, this.parent, this.Rank));
    }
}
