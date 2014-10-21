package Spells.SpellSlots.Slot4;

import com.developmental.warlocks.R;

import SpellProjectiles.BoomerangProjectile;
import SpellProjectiles.FireballProjectile;
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
public class BouncerSpell extends Spell {
    public BouncerSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    public void loadResouce() {
        this.texture = Global.resources.get(R.drawable.button_boomerang);
    }
    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new BoomerangProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
