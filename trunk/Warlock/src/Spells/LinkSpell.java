package Spells;

import android.graphics.Canvas;
import android.graphics.Color;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import SpellProjectiles.LinkProjectile;
import Tools.Vector;

/**
 * Created by Scott on 27/07/13.
 */
public class LinkSpell extends  Spell {
    public LinkSpell(GameObject _parent) {
        super(_parent);
        //this.Cooldown=5;
       this.p.setColor(Color.YELLOW);
    }
    @Override
    void Shoot(Vector Dest) {
        RenderThread.addObject(new LinkProjectile(new Vector(this.parent.rect.left
                + this.parent.rect.width() / 2, this.parent.rect.top
                + this.parent.rect.height() / 2), Dest.get(), this.parent));
    }

    @Override
    public void DrawButton(Canvas c,int x, int y)
    {
        c.drawCircle(x-25,y-25,4,p);
        c.drawCircle(x+25,y+25,4,p);
        c.drawLine(x-25,y-25,x+25,y+25,p);
    }

}
