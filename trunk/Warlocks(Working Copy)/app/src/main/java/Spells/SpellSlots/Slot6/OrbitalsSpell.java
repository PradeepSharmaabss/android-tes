package Spells.SpellSlots.Slot6;

import SpellProjectiles.OrbitalProjectile;
import SpellProjectiles.SplitterProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class OrbitalsSpell extends Spell {
    public OrbitalsSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest) {

        SimpleGLRenderer.addObject(new OrbitalProjectile(this.parent.bounds.Center, new Vector(0, 0), this.parent,120f));
        SimpleGLRenderer.addObject(new OrbitalProjectile(this.parent.bounds.Center,  new Vector(0, 0), this.parent,0));
        SimpleGLRenderer.addObject(new OrbitalProjectile(this.parent.bounds.Center, new Vector(0, 0), this.parent,240));
    }
}
