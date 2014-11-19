package Spells.SpellSlots.Slot1;

import Actors.Player;
import SpellProjectiles.FireballProjectile;
import Spells.Archetype.ArchetypePower;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 21/10/2014.
 */
public class FireballSpell extends Spell {
    public FireballSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Burn;
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        ArchetypePower power = parent.CalcArchetypePower();
        SimpleGLRenderer.addObject(new FireballProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank, power));

    }

}
