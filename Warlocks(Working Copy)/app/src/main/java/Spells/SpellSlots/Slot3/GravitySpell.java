package Spells.SpellSlots.Slot3;

import com.developmental.warlocks.R;

import SpellProjectiles.FireballProjectile;
import SpellProjectiles.GravityProjectile;
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
public class GravitySpell extends Spell {
    public GravitySpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    public void loadResouce() {
        this.texture = Global.resources.get(R.drawable.button_gravity);
    }
    @Override
    protected void Shoot(iVector Dest) {
        SimpleGLRenderer.addObject(new GravityProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }
}
