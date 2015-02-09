package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.TrapMineProjectile;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class TrapMinesSpell extends Spell {
    public TrapMinesSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
        archetype = Archetype.Vitro;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new TrapMineProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
