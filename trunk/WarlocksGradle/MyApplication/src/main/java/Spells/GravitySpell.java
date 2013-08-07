package Spells;

import android.graphics.Color;

import Game.GameObject;
import SpellProjectiles.GravityProjectile;
import Tools.Vector;
import com.developmental.warlocks.RenderThread;

public class GravitySpell extends Spell {

	public GravitySpell(GameObject _parent) {
		super(_parent);
        p.setColor(Color.GREEN);
        p.setAlpha(125);
        sz = 70;

	}
	@Override
	void Shoot(Vector Dest) {
		RenderThread.addObject(new GravityProjectile(new Vector(this.parent.rect.left
				+ this.parent.rect.width() / 2, this.parent.rect.top
				+ this.parent.rect.height() / 2), Dest.get(), this.parent));
	}

}
