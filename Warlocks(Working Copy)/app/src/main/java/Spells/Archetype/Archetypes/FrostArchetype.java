package Spells.Archetype.Archetypes;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypeStatus;
import Spells.SpellEffect;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 14/11/2014.
 */
public class FrostArchetype extends ArchetypeStatus {
    public FrostArchetype(int _g,Collideable _p) {
        super(_p,_g);
    }

    @Override
    protected void GetEffect(Collideable parent) {

        parent.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, parent, R.drawable.effect_ice));
    }
}
