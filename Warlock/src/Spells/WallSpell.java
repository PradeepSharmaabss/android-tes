package Spells;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Input.Pointer;
import SpellProjectiles.WallObject;
import Tools.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import com.example.warlockgame.RenderThread;

public class WallSpell extends Spell {
	public WallSpell(GameObject _parent) {
		super(_parent);
        p.setColor(Color.MAGENTA);
		this.Cooldown = 30;
	}

	Vector s1, d1;
	boolean hadTwo = false;
    @Override
    public void DrawButton(Canvas c,int x, int y)
    {
        c.drawLine(x-50,y-50,x-20,y,p);
        c.drawLine(x-20,y,x,y,p);
        c.drawLine(x,y,x+50,y+50,p);
    }
	@Override
	public void Cast(List<Pointer> dest) {

		int count = 0;
		List<Vector> s = new ArrayList<Vector>();
		for (int x = 0; x < dest.size(); x++)
			if (dest.get(x).WithinScreen() && dest.get(x).down) {
				count++;
				s.add(dest.get(x).WorldPos().get());
			}

		if (count <= 1)
			if (this.hadTwo) {

				Shoot(this.s1, this.d1);

				this.hadTwo = false;
				this.Current = this.Cooldown;

			} else if (count == 1)
				Target(s.get(0), new Vector(this.parent.rect.left
						+ this.parent.rect.width() / 2,
						this.parent.rect.bottom - 20));
		if (count >= 2) {

			Target(s.get(0), s.get(1));
			this.hadTwo = true;
		}

	}

	void Target(Vector Dest, Vector Start) {
		// TODO Auto-generated method stub
		RenderThread.addObject(new WallObject(Start,// +20 to place at players
													// hand
				Dest.get(), this.parent, false));
		this.s1 = Start;
		this.d1 = Dest;
	}

	void Shoot(Vector Dest, Vector Start) {
		// TODO Auto-generated method stub
		RenderThread.addObject(new WallObject(Start,// +20 to place at players
													// hand
				Dest.get(), this.parent, true));
		this.Current = this.Cooldown;

	}
}
