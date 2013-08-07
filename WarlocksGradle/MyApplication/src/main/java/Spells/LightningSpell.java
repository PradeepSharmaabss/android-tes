package Spells;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.List;

import Game.GameObject;
import Input.Pointer;
import SpellProjectiles.LightningProjectile;
import Tools.Vector;
import com.developmental.warlocks.RenderThread;

public class LightningSpell extends Spell {
    Paint sp;
	public LightningSpell(GameObject _parent) {
		super(_parent);
        p.setARGB(255, 125, 125, 200);
        sp = new Paint();
        sp.setStrokeWidth(4);
        sp.setColor(Color.WHITE);
        sp.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.OUTER));
        p.setColor(Color.BLUE);
        p.setStrokeWidth(2);
		this.Cooldown = 10;
	}
@Override
public void DrawButton(Canvas c,int x, int y)
{
        c.drawLine(x-50,y-50,x-20,y,p);
        c.drawLine(x-20,y,x,y,p);
        c.drawLine(x,y,x+50,y+50,p);
    c.drawLine(x-50,y-50,x-20,y,sp);
    c.drawLine(x-20,y,x,y,sp);
    c.drawLine(x,y,x+50,y+50,sp);
}

	@Override
	public void Cast(List<Pointer> dest) {
//		int count = 0;
//		List<Vector> s = new ArrayList<Vector>();
//
//		for (int x = 0; x < dest.size(); x++)
//			if (dest.get(x).WithinScreen() && dest.get(x).down) {
//				count++;
//				s.add(dest.get(x).WorldPos().get());
//			}
//		if (count == 1) {
//			Shoot(s.get(0), this.parent.getCenter());
//			this.Current = this.Cooldown;
//		}
//		if (count >= 2)
//			if (this.Current == 0) {
//				Shoot(s.get(0), s.get(1));
//				this.Current = this.Cooldown;
//			}
        super.Cast(dest);
	}

	void Shoot(Vector Dest, Vector Start) {
		RenderThread.addObject(new LightningProjectile(Start,// +20 to place at
														// players hand
				Dest.get(), this.parent));
	}

	@Override
	void Shoot(Vector Dest) {
		RenderThread.addObject(new LightningProjectile(new Vector(
				this.parent.position.x + this.parent.size.x / 2,
				this.parent.position.y + this.parent.size.y / 2 - 20),// +20 to
																		// place
																		// at
																		// players
																		// hand
				Dest.get(), this.parent));

	}
}
