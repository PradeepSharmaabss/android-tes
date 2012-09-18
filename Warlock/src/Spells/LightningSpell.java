package Spells;

import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.LightningBolt;
import Input.Pointer;
import Tools.Vector;

public class LightningSpell extends Spell {
	public LightningSpell(GameObject _parent)
	{
		super(_parent);
		
		Cooldown = 2;
	}

	public void Cast(List<Pointer> dest)
	{
		int count  = 0;
		List<Vector> s = new ArrayList<Vector>();
		for(int x = 0; x<dest.size();x++)
		{
			if(dest.get(x ).WithinScreen()&&dest.get(x).down)
			{
			count++;
			s.add(dest.get(x).WorldPos().get());
			}
		}
	if(count ==1)
	{
		Shoot(s.get(0),parent.position);
	}
		if(count>=2)
		{
			if(Current==0)
			{
			Shoot(s.get(0),s.get(1));
			Current = Cooldown;
			}
		
		}
	}

	void Shoot(Vector Dest,Vector Start) 
	{
		// TODO Auto-generated method stub
		RenderThread.addObject(
				new LightningBolt(
					Start,//+20 to place at players hand
					Dest.get(),parent)
				);
	}
	void Shoot(Vector Dest) 
	{
		// TODO Auto-generated method stub
		RenderThread.addObject(
				new LightningBolt(
						new Vector(parent.position.x + parent.size.x/2, parent.position.y + parent.size.y/2 - 20),//+20 to place at players hand
					Dest.get(),parent)
				);
	}
}
