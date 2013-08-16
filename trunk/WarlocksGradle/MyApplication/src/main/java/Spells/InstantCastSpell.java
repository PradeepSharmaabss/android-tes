package Spells;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;

import com.developmental.myapplication.Global;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Game.SpellEffect;
import Input.Pointer;
import Tools.SpriteSheet;
import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 6/19/13.
 */
public class InstantCastSpell extends Spell {

    public InstantCastSpell(GameObject _parent) {
        super(_parent);
        this.p.setColor(Color.MAGENTA);
    this.CastTime = 50;
       curr =  Global.ButtonImages.get(0);


    }

    public void Cast(List<iVector> dest) {
                    if (this.Current == 0) {
                        this.Current = this.Cooldown;

                        this.parent.Debuffs.add(new SpellEffect(this.CastTime,SpellEffect.EffectType.Reflect, Global.Sprites.get(2)));
                    }
    }




}
