package Spells;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.LightningBolt;
import Game.Projectile;
import Tools.Vector;

public class LightningSpell extends Spell {
	public LightningSpell(GameObject _parent)
	{
		super(_parent);
		
		Cooldown = 20;
	}

	public void Cast(Vector dest)
	{
		if(Current ==0)
		{
			Shoot(dest);
			Current =Cooldown;
			
		}
	}

	void Shoot(Vector Dest) {
		// TODO Auto-generated method stub
		RenderThread.addObject(new LightningBolt(parent.position.get(),Dest.get(),parent));
	}
}
