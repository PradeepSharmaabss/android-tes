package Spells.Archetype.Archetypes;

import com.developmental.warlocks.R;

import Actors.ShadowClone;
import HUD.PopupText;
import Spells.Archetype.ArchetypeStatus;
import Spells.SpellEffect;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 14/11/2014.
 */
public class IllusionArchetype extends ArchetypeStatus {
    public IllusionArchetype(int _g,Collideable _p) {
        super(_p,_g);
    }

    @Override
    protected void GetEffect(Collideable parent) {

        LastSent.shadowClone = new ShadowClone(R.drawable.charsheet_shadow,parent.position,LastSent.size);
      //  LastSent.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Illusion, parent, R.drawable.effect_ice));
    }
}
