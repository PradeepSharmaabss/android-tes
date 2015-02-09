package Spells.SpellSlots.Slot6;

import Actors.Player;
import SpellProjectiles.ExplosionProjectile;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class MagnetExplodeSpell extends Spell {
    public MagnetExplodeSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new ExplosionProjectile(0,Origin, parent, new Vector(200, 200),5,this.Rank));
    }
}
