package Spells.SpellSlots.Slot6;

import com.developmental.warlocks.R;

import Spells.Spell;
import Spells.SpellInfo;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class FireExplosionSpell extends Spell {
    public FireExplosionSpell(GameObject _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    public void loadResouce() {
        this.texture = Global.resources.get(R.drawable.button_explosion);
    }
}
