package Spells;

import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.MeteorProjectile;
import Tools.Vector;
import com.developmental.myapplication.RenderThread;

public class MeteorSpell extends Spell {

	public MeteorSpell(GameObject _parent) {
		super(_parent);
        p.setColor(Color.CYAN);
        sz = 50;
	}

	@Override
	void Shoot(Vector Dest) {
		RenderThread.addObject(new MeteorProjectile(new Vector(this.parent.rect.left
				+ this.parent.rect.width() / 2, this.parent.rect.top
				+ this.parent.rect.height() / 2), Dest.get(), this.parent));
	}

}
