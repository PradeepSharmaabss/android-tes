package Spells.SpellSlots.Slot3;

import SpellProjectiles.MeteorProjectile;
import SpellProjectiles.TrapMineProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class TrapMinesSpell extends Spell {
    public TrapMinesSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new TrapMineProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
