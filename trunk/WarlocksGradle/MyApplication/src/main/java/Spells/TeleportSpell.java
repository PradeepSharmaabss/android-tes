package Spells;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;

import Game.GameObject;
import Tools.Vector;

public class TeleportSpell extends Spell {
    public TeleportSpell(GameObject _parent) {
        super(_parent);
        sz = 40;
        p.setMaskFilter(new BlurMaskFilter(14, BlurMaskFilter.Blur.INNER));
        p.setColor(Color.BLACK);
    }

    @Override
    void Shoot(Vector Dest) {
      this.parent.position=new Vector(Dest.x-parent.size.x/2,Dest.y-parent.size.y);
    }

}