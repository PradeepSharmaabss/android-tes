package Spells;

import com.developmental.myapplication.Global;

import java.util.List;

import Game.GameObject;
import Game.SpellEffect;
import Tools.iVector;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplodeSpell extends InstantCastSpell {
    public ExplodeSpell(GameObject _parent) {
        super(_parent);
        this.CastTime = 10;
    }

    public boolean Cast(iVector[] dest) {
        if (this.Current == 0) {
            this.Current = this.Cooldown;

            this.parent.Debuffs.add(new SpellEffect(this.CastTime, SpellEffect.EffectType.Explode, Global.Sprites.get(2), this.parent));
            return true;
        }
        return false;
    }
}
