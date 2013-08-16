package Spells;

import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.MeteorProjectile;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

public class MeteorSpell extends Spell {

	public MeteorSpell(GameObject _parent) {
		super(_parent);
        p.setColor(Color.CYAN);
        sz = 50;
        curr= Global.ButtonImages.get(2);
	}

	@Override
	void Shoot(iVector Dest) {
		RenderThread.addObject(new MeteorProjectile(new Vector(this.parent.rect.left
				+ this.parent.rect.width() / 2, this.parent.rect.top
				+ this.parent.rect.height() / 2), new Vector(Dest.x,Dest.y), this.parent));
	}

}
