package SpellProjectiles;

import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.warlocks.R;

import Game.ObjectType;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

public class WallProjectile extends Projectile {
    Vector Start, Dest;
    boolean live;

    public WallProjectile(Vector _start, Vector _dest, GameObject _parent,
                          boolean _live) {
        super(R.drawable.spell_boomerang,_start, _dest, _parent, 1, 0, new Vector(50, 50), 10);
        this.live = _live;
        this.Start = new Vector(_start.x - 1, _start.y - 1);
        this.Dest = _dest;
        if (this.live) {
            this.health = 100;

            float dx = this.Start.x - this.Dest.x;
            float dy = this.Start.y - this.Dest.y;
            float ToteDist = Math.abs(dx) + Math.abs(dy);

            this.velocity = new Vector(-dx / ToteDist, -dy / ToteDist);

        } else {
            this.health = 2;
            this.velocity = new Vector(0, 0);

        }

        this.objectObjectType = ObjectType.LineSpell;

    }

    @Override
    public boolean Intersect(RectF s) {
        if (!this.live)
            return false;
        boolean in = false;
        Vector d;
        d = lineIntersect(this.Start.x, this.Start.y, this.Dest.x, this.Dest.y,
                s.left, s.top, s.right, s.top);
        if (d != null) {
            this.Dest = d.get();
            in = true;
        }
        d = lineIntersect(this.Start.x, this.Start.y, this.Dest.x, this.Dest.y,
                s.right, s.top, s.right, s.bottom);
        if (d != null) {
            this.Dest = d.get();
            in = true;
        }
        d = lineIntersect(this.Start.x, this.Start.y, this.Dest.x, this.Dest.y,
                s.right, s.bottom, s.left, s.bottom);
        if (d != null) {
            this.Dest = d.get();
            in = true;
        }
        d = lineIntersect(this.Start.x, this.Start.y, this.Dest.x, this.Dest.y,
                s.left, s.bottom, s.left, s.top);
        if (d != null) {
            this.Dest = d.get();
            in = true;
        }

        return in;
    }

    public static Vector lineIntersect(float x1, float y1, float x2, float y2,
                                       float x3, float y3, float x4, float y4) {
        float denom = (y4 - y3) * (x2 - x1) - (x4 - x3) * (y2 - y1);
        if (denom == 0.0)
            return null;
        float ua = ((x4 - x3) * (y1 - y3) - (y4 - y3) * (x1 - x3)) / denom;
        float ub = ((x2 - x1) * (y1 - y3) - (y2 - y1) * (x1 - x3)) / denom;
        if (ua >= 0.0f && ua <= 1.0f && ub >= 0.0f && ub <= 1.0f)
            // Get the intersection point.
            return new Vector((int) (x1 + ua * (x2 - x1)), (int) (y1 + ua
                    * (y2 - y1)));
        // return true;

        return null;
    }
}
