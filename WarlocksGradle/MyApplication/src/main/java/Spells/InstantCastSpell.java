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
    Bitmap b ;
    public InstantCastSpell(GameObject _parent) {
        super(_parent);
        this.p.setColor(Color.MAGENTA);
    this.CastTime = 50;
        ArrayList<Bitmap> s =  Global.Sprites.get(2);

        b=s.get(0);
    }

    public void Cast(List<iVector> dest) {
                    if (this.Current == 0) {
                        this.Current = this.Cooldown;

                        this.parent.Debuffs.add(new SpellEffect(this.CastTime,SpellEffect.EffectType.Reflect, Global.Sprites.get(2)));
                    }
    }

    @Override
    public void DrawButton(Canvas c,int x, int y,float w,float h)
    {
        c.drawBitmap(b,x+10,y+10,p);
    }


}
