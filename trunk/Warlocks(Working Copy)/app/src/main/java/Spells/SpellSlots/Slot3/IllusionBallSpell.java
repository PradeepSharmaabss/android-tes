package Spells.SpellSlots.Slot3;

import Actors.Player;
import SpellProjectiles.IllusionBallProjectile;
import Spells.LoadOutInfo;
import Spells.Spell;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class IllusionBallSpell extends Spell {
    public IllusionBallSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
        archetype = Archetype.Illusion;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new IllusionBallProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }
}
