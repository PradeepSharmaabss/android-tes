package Spells.SpellSlots.Slot5;

import com.developmental.warlocks.R;

import Actors.Player;
import Spells.Spell;
import Spells.SpellEffect;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 21/10/2014.
 */
public class JuggernautSpell extends Spell {

    public JuggernautSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Juggernaught, this.parent, R.drawable.effect_explode, new iVector(0, 0)));
    }
}
