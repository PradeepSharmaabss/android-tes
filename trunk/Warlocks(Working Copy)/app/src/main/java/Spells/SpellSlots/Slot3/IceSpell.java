package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.IceProjectile;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class IceSpell extends Spell {
    public IceSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
        archetype = Archetype.Frost;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new IceProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
