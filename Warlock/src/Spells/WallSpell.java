package Spells;

import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.WallObject;
import Input.Pointer;
import Tools.Vector;

public class WallSpell extends Spell {
	public WallSpell(GameObject _parent)
	{
		super(_parent);
		
		Cooldown = 30;
	}
	Vector s1,d1;
boolean hadTwo=false;
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
		
		if(count <= 1)
		{
			if(hadTwo)
			{
			
				Shoot(s1, d1);
			
				hadTwo=false;
				Current = Cooldown;
				
			
			}
			else
			{
				if(count == 1)
				Target(s.get(0), new Vector(parent.rect.left + parent.rect.width()/2, parent.rect.bottom - 20));
			
			}
	
		}
		if(count>=2)
		{
			
			
			Target(s.get(0),s.get(1));
			hadTwo = true;
		}
		
	}

	void Target(Vector Dest,Vector Start) 
	{
		// TODO Auto-generated method stub
		RenderThread.addObject(
		new WallObject(
					Start,//+20 to place at players hand
					Dest.get(),parent,false)
				);
		s1= Start;
		d1= Dest;
	}	
	void Shoot(Vector Dest,Vector Start) 
	{
		// TODO Auto-generated method stub
		RenderThread.addObject(
				new WallObject(
					Start,//+20 to place at players hand
					Dest.get(),parent,true)
				);
		Current = Cooldown;
	
	}
}
