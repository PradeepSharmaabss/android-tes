package Spells.SpellSlots.Slot6;

import com.developmental.warlocks.R;

import Actors.Player;
import Spells.Spell;
import Spells.SpellEffect;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.Global;

/**
 * Created by Scott on 21/10/2014.
 */
public class ReflectSpell extends Spell {
    public ReflectSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }
    @Override
    public void loadResouce() {
        this.texture = Global.resources.get(R.drawable.button_shield);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Reflect, this.parent,R.drawable.effect_shield));
    }
}
