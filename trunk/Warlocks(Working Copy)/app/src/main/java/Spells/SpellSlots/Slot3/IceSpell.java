package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.IceProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class IceSpell extends Spell {
    public IceSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new IceProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent));
    }
}
