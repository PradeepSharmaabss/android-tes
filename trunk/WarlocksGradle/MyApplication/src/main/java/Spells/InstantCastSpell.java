package Spells;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.List;

import Game.GameObject;
import Game.SpellEffect;
import Input.Pointer;

/**
 * Created by Scott on 6/19/13.
 */
public class InstantCastSpell extends Spell {

    public InstantCastSpell(GameObject _parent) {
        super(_parent);
        this.p.setColor(Color.MAGENTA);
    this.CastTime = 50;
    }
    public void Cast(List<Pointer> dest) {
                    if (this.Current == 0) {
                        this.Current = this.Cooldown;

                        this.parent.Debuffs.add(new SpellEffect(this.CastTime,SpellEffect.EffectType.Reflect));
                    }
    }

    @Override
    public void DrawButton(Canvas c,int x, int y)
    {
        c.drawCircle(x,y,50,p);
    }


}
