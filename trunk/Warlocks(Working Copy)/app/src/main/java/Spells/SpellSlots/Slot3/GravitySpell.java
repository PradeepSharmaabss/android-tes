package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.GravityProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class GravitySpell extends Spell {
    public GravitySpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Poison;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new GravityProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
