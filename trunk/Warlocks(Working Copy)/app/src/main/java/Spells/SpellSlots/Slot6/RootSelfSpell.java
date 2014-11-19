package Spells.SpellSlots.Slot6;

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
public class RootSelfSpell extends Spell {
    public RootSelfSpell(Player _parent, SpellInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        this.parent.Debuffs.add(new SpellEffect(150, SpellEffect.EffectType.Root, this.parent, R.drawable.effect_explode, new iVector(0, 0)));
    }
}
