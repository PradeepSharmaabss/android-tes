package Spells;

import android.graphics.Canvas;
import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.LinkProjectile;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.RenderThread;

/**
 * Created by Scott on 27/07/13.
 */
public class LinkSpell extends Spell {
    public LinkSpell(GameObject _parent) {
        super(_parent);
        //this.Cooldown=5;
        this.p.setColor(Color.YELLOW);
    }

    @Override
    void Shoot(iVector Dest) {
        RenderThread.addObject(new LinkProjectile(this.parent.bounds.Center, new Vector(Dest.x, Dest.y), this.parent));
    }

    @Override
    public void DrawButton(Canvas c, int x, int y, float w, float h) {
        c.drawCircle(x + w / 2 - 25, y + h / 2 - 25, 4, p);
        c.drawCircle(x + w / 2 + 25, y + h / 2 + 25, 4, p);
        c.drawLine(x + w / 2 - 25, y + h / 2 - 25, x + w / 2 + 25, y + h / 2 + 25, p);
    }

}
