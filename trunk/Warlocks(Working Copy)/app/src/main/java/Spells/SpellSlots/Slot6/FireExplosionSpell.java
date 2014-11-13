package Spells.SpellSlots.Slot6;

import com.developmental.warlocks.R;

import SpellProjectiles.ExplosionProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class FireExplosionSpell extends Spell {
    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new ExplosionProjectile(parent.bounds.Center.get(), new Vector(500, 500), parent));
    }

    public FireExplosionSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }

}
