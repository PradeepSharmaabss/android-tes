package Spells.SpellSlots.Slot2;

import Actors.Player;
import SpellProjectiles.HomingProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class HomingSpell extends Spell {
    public HomingSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new HomingProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent));
    }
}
