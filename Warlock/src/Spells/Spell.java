package Spells;

import java.util.List;

import Game.GameObject;
import Input.Finger;
import Input.Pointer;
import SpellProjectiles.Fireball;
import Tools.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.example.warlockgame.GameThread;
import com.example.warlockgame.RenderThread;

public class Spell {
	public int Cooldown = 100;
	public int Current = 0;
	GameObject parent;
Paint p;
    int sz= 40;
	public Spell(GameObject _parent) {
		this.parent = _parent;
		p = new Paint();
        p.setColor(Color.RED);
		// owner = parent.id;
	}
    public void DrawButton(Canvas c,int x, int y)
    {
        c.drawCircle(x,y,sz,p);
    }

	public void Cast(List<Pointer> dest) {
		if (Finger.sz() >= 2)
			for (int x = 0; x < dest.size(); x++)
				if (dest.get(x).WithinScreen())
					if (this.Current == 0) {
						Shoot(dest.get(x).WorldPos());
						this.Current = this.Cooldown;
					}
	}

	public void Cast(Vector dest) {
		Shoot(dest);
		// Current = Cooldown;
	}

	public void Update() {
		if (this.Current > 0)
			this.Current -= 1;
	}

	void Shoot(Vector Dest) {
        GameThread.s= false;
		RenderThread.addObject(new Fireball(new Vector(this.parent.rect.left
				+ this.parent.rect.width() / 2, this.parent.rect.top
				+ this.parent.rect.height() / 2), Dest.get(), this.parent));
	}
}
