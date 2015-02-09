package Spells.SpellSlots.Slot5;

import com.developmental.warlocks.R;

import Actors.Player;
import Spells.LoadOutInfo;
import Spells.Spell;
import Spells.SpellEffect;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 21/10/2014.
 */
public class WindWalkSpell extends Spell {
    public WindWalkSpell(Player _parent, LoadOutInfo s) {
        super(_parent, s);
    }

    @Override
    protected void Shoot(iVector Dest, Vector Origin) {
        this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Invisible, this.parent, R.drawable.effect_shield, new iVector(0, 0)));
    }
}
