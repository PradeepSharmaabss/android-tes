package Spells.SpellSlots.Slot4;

import Actors.Player;
import SpellProjectiles.AbsorptionProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class AbsorptionSpell extends Spell {
    public AbsorptionSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new AbsorptionProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent,this.Rank));
    }
}
