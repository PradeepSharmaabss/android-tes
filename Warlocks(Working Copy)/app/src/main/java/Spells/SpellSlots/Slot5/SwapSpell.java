package Spells.SpellSlots.Slot5;

import SpellProjectiles.FireballProjectile;
import SpellProjectiles.SwapProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class SwapSpell extends Spell {
    public SwapSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new SwapProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
