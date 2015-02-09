package Spells.SpellSlots.Slot4;

import Actors.Player;
import SpellProjectiles.AbsorptionProjectile;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class AbsorptionSpell extends Spell {
    public AbsorptionSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
        archetype = Archetype.Poison;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new AbsorptionProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
