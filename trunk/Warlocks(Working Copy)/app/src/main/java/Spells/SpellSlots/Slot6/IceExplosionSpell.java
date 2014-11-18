package Spells.SpellSlots.Slot6;

import Actors.Player;
import SpellProjectiles.ExplosionIceProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class IceExplosionSpell extends Spell {
    public IceExplosionSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new ExplosionIceProjectile(Origin, new Vector(500, 500), parent,this.Rank));
    }
}
