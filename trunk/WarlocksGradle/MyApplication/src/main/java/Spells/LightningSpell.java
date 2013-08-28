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
import Tools.iVector;

import com.developmental.myapplication.RenderThread;

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
public void DrawButton(Canvas c,int x, int y,float w,float h)
{
    c.drawLine(x,y,x+30,y+h/2,p);
    c.drawLine(x+30,y+h/2,x+w/2,y+h/2,p);
    c.drawLine(x+w/2,y+h/2,x+w,y+h,p);
    c.drawLine(x,y,x+30,y+h/2,sp);
    c.drawLine(x+30,y+h/2,x+w/2,y+h/2,sp);
    c.drawLine(x+w/2,y+h/2,x+w,y+h,sp);
}

	void Shoot(Vector Dest, Vector Start) {
		RenderThread.addObject(new LightningProjectile(Start,// +20 to place at
														// players hand
				Dest.get(), this.parent));
	}

	@Override
	void Shoot(iVector Dest) {
		RenderThread.addObject(new LightningProjectile(this.parent.getCenter(),// +20 to
																		// place
																		// at
																		// players
																		// hand
				new Vector(Dest.x,Dest.y), this.parent));

	}
}
