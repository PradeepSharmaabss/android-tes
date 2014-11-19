package Spells.SpellSlots.Slot4;

import Actors.Player;
import SpellProjectiles.BoomerangProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class BouncerSpell extends Spell {
    public BouncerSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Illusion;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new BoomerangProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
