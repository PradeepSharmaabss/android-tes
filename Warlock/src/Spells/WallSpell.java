package Spells;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.LightningBolt;
import Game.WallObject;
import Input.Pointer;
import Tools.Vector;

public class WallSpell extends Spell {
	public WallSpell(GameObject _parent)
	{
		super(_parent);
		
		Cooldown = 10;
	}
	Vector s1,d1;
boolean hadTwo=false;
	public void Cast(List<Pointer> dest)
	{
		if(Current == 0)
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
			}
			else
			{
				
				Target(s.get(0), new Vector(parent.rect.left + parent.rect.width()/2, parent.rect.bottom - 20));
			}
	
		}
		if(count>=2)
		{
			hadTwo = true;
			if(Current==0)
			{
				
				Target(s.get(0),s.get(1));
			Current = Cooldown;
			}
		}
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
