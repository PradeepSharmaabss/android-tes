package Spells.SpellSlots.Slot6;

import Actors.Player;
import SpellProjectiles.OrbitalProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class OrbitalsSpell extends Spell {
    public OrbitalsSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    protected void Shoot(iVector Dest, Vector Origin) {

        SimpleGLRenderer.addObject(new OrbitalProjectile(this.parent.bounds.Center, new Vector(0, 0), this.parent,120f,this.Rank));
        SimpleGLRenderer.addObject(new OrbitalProjectile(this.parent.bounds.Center,  new Vector(0, 0), this.parent,0,this.Rank));
        SimpleGLRenderer.addObject(new OrbitalProjectile(this.parent.bounds.Center, new Vector(0, 0), this.parent,240,this.Rank));
    }
}
