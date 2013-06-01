package SpellProjectiles;

import Game.GameObject;
import Tools.Vector;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class WallObject extends Projectile {
	Vector Start, Dest;
	boolean live;

	public WallObject(Vector _start, Vector _dest, GameObject _parent,
			boolean _live) {
		super(_start, _dest, _parent);
		this.live = _live;
		this.Start = new Vector(_start.x - 1, _start.y - 1);
		this.Dest = _dest;
		if (this.live) {
			this.health = 100;

			float dx = this.Start.x - this.Dest.x;
			float dy = this.Start.y - this.Dest.y;
			float ToteDist = Math.abs(dx) + Math.abs(dy);

			this.velocity = new Vector(-dx / ToteDist, -dy / ToteDist);
			this.paint.setColor(Color.YELLOW);
		} else {
			this.health = 2;
			this.velocity = new Vector(0, 0);
			this.paint.setColor(Color.MAGENTA);
		}
		this.shadowPaint.setColor(Color.argb(50, 0, 0, 0));
		this.shadowPaint.setMaskFilter(new BlurMaskFilter(2,
				BlurMaskFilter.Blur.SOLID));
		this.shadowPaint.setStrokeWidth(3);
		this.paint.setStrokeWidth(3);
	}

	@Override
	public void Draw(Canvas c,float playerx,float playery) {
		for (int Arcs = 0; Arcs < 4; Arcs++) {
			Vector s = this.Start.get();
			float dx = this.Dest.x - this.Start.x;
			float dy = this.Dest.y - this.Start.y;
			for (int i = 0; i < 10; i++) {
				float offsetx = (float) (Math.random() * 20 - 10);
				float offsety = (float) (Math.random() * 20 - 10);
				c.drawLine(s.x + 20-playerx, s.y - 20-playery, s.x + (dx / 11) + offsetx + 20-playerx,
						s.y + (dy / 11) + offsety - 20-playery, this.shadowPaint);
				c.drawLine(s.x-playerx, s.y-playery, s.x + (dx / 11) + offsetx-playerx, s.y + (dy / 11)
						+ offsety-playery, this.paint);
				s = new Vector(s.x + dx / 11 + offsetx-playerx, s.y + dy / 11 + offsety-playery);
			}
			c.drawLine(s.x + 20-playerx, s.y - 20-playery, this.Dest.x + 20-playerx, this.Dest.y - 20-playery,
					this.shadowPaint);
			c.drawLine(s.x-playerx, s.y-playery, this.Dest.x-playerx, this.Dest.y-playery, this.paint);

		}

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
