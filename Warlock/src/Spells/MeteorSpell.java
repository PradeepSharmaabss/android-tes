package Spells;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.Meteor;
import Tools.Vector;

public class MeteorSpell extends Spell{

	public MeteorSpell(GameObject _parent) {
		super(_parent);
		// TODO Auto-generated constructor stub
	}
	void Shoot(Vector Dest)
	{
		RenderThread.addObject(
				new Meteor(
					new Vector(
						parent.rect.left + parent.rect.width()/2,
						parent.rect.top + parent.rect.height()/2
					),
					Dest.get(), parent)
				);
	}

}
