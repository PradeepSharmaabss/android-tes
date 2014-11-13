package Spells.SpellSlots.Slot6;

import SpellProjectiles.DrainExplosionProjectile;
import SpellProjectiles.ExplosionProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class DrainExplodeSpell extends Spell {
    public DrainExplodeSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new DrainExplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
    }
}
