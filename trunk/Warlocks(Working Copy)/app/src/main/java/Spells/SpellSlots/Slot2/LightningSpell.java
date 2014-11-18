package Spells.SpellSlots.Slot2;

import Actors.Player;
import SpellProjectiles.LightningProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class LightningSpell extends Spell {
    public LightningSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new LightningProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent,this.Rank));
        SimpleGLRenderer.playSound(SimpleGLRenderer.explosion);
    }
}
