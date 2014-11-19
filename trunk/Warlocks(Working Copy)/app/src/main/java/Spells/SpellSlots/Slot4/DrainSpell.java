package Spells.SpellSlots.Slot4;

import Actors.Player;
import SpellProjectiles.DrainProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class DrainSpell extends Spell {
    public DrainSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Lifesteal;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new DrainProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
