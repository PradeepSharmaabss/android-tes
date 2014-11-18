package Spells.SpellSlots.Slot2;

import Actors.Player;
import SpellProjectiles.GrenadeProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class GrenadeSpell extends Spell {
    public GrenadeSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new GrenadeProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent));
    }
}
