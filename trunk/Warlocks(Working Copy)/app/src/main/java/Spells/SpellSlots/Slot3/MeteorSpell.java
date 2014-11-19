package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.MeteorProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class MeteorSpell extends Spell {
    public MeteorSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Burn;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new MeteorProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
