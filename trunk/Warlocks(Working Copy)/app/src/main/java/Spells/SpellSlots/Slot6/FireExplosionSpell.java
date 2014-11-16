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

        SimpleGLRenderer.addObject(new ExplosionProjectile(0,parent.bounds.Center.get(), parent, new Vector(200, 200),5));
    }

    public FireExplosionSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }

}
