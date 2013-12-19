package Spells;

import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import SpellProjectiles.WallProjectile;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.RenderThread;

public class WallSpell extends Spell {
    public WallSpell(GameObject _parent) {
        super(_parent);
        p.setColor(Color.MAGENTA);
        this.Cooldown = 30;
    }

    Vector s1, d1;
    boolean hadTwo = false;

    @Override
    public void DrawButton(Canvas c, int x, int y, float w, float h) {
        c.drawLine(x, y, x + 30, y + h / 2, p);
        c.drawLine(x + 30, y + h / 2, x + w / 2, y + h / 2, p);
        c.drawLine(x + w / 2, y + h / 2, x + w, y + h, p);
    }

    @Override
    public boolean Cast(iVector[] dest) {
        if (dest.length > 0) {
            int count = 0;
            List<iVector> s = new ArrayList<iVector>();
            for (int x = 0; x < dest.length; x++) {
                count++;
                s.add(dest[x]);
            }

            if (count <= 1)
                if (this.hadTwo) {

                    Shoot(this.s1, this.d1);

                    this.hadTwo = false;
                    this.Current = this.Cooldown;
                    return true;
                } else if (count == 1)
                    Target(new Vector(s.get(0).x, s.get(0).y), new Vector(((int) (this.parent.rect.left
                            + this.parent.rect.width() / 2)), ((int) (
                            this.parent.rect.bottom - 20))));
            if (count >= 2) {

                Target(new Vector(s.get(0).x, s.get(0).y), new Vector(s.get(1).x, s.get(1).y));
                this.hadTwo = true;
            }
        }
        return false;
    }

    void Target(Vector Dest, Vector Start) {
        RenderThread.addObject(new WallProjectile(Start,// +20 to place at players
                // hand
                Dest.get(), this.parent, false));
        this.s1 = Start;
        this.d1 = Dest;
    }

    void Shoot(Vector Dest, Vector Start) {
        RenderThread.addObject(new WallProjectile(Start,// +20 to place at players
                // hand
                Dest.get(), this.parent, true));
        this.Current = this.Cooldown;

    }
}
