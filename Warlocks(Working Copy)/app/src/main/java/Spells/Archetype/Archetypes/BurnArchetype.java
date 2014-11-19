package Spells.Archetype.Archetypes;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypeStatus;
import Spells.SpellEffect;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;

/**
 * Created by Scott on 14/11/2014.
 */
public class BurnArchetype extends ArchetypeStatus {
    public BurnArchetype(int _g, Collideable _p) {
        super(_p, _g);
    }

    @Override
    protected void GetEffect(Collideable parent) {
        parent.Debuffs.add(new SpellEffect(150, SpellEffect.EffectType.Burn, parent, R.drawable.effect_burn, new iVector(0, 0)));
    }
}
