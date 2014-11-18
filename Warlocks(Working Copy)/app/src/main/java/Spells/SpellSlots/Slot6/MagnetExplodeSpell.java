package Spells.SpellSlots.Slot6;

import Actors.Player;
import SpellProjectiles.ExplosionProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class MagnetExplodeSpell extends Spell {
    public MagnetExplodeSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new ExplosionProjectile(0,Origin, parent, new Vector(200, 200),5));
    }
}
