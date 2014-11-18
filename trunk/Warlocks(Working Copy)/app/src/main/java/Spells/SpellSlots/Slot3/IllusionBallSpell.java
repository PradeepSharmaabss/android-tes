package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.IllusionBallProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class IllusionBallSpell extends Spell {
    public IllusionBallSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new IllusionBallProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent));
    }
}
