package Spells.SpellSlots.Slot2;

import com.developmental.warlocks.R;

import Actors.Player;
import SpellProjectiles.BoomerangProjectile;
import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class BoomerangSpell extends Spell {
    public BoomerangSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
        archetype = Archetype.Confuse;
    }

    @Override
    public void loadResouce() {
        this.texture = Global.resources.get(R.drawable.button_boomerang);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        SimpleGLRenderer.addObject(new BoomerangProjectile(Origin, new Vector(Dest.x, Dest.y), this.parent, this.Rank));
    }

}
