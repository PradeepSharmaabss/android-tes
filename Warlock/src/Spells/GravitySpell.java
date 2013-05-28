package Spells;

import Game.GameObject;
import SpellProjectiles.GravityBall;
import Tools.Vector;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import com.example.warlockgame.RenderThread;

public class GravitySpell extends Spell {

	public GravitySpell(GameObject _parent) {
		super(_parent);
        p.setColor(Color.GREEN);
        p.setAlpha(125);
        sz = 70;

	}
	@Override
	void Shoot(Vector Dest) {
		RenderThread.addObject(new GravityBall(new Vector(this.parent.rect.left
				+ this.parent.rect.width() / 2, this.parent.rect.top
				+ this.parent.rect.height() / 2), Dest.get(), this.parent));
	}

}
