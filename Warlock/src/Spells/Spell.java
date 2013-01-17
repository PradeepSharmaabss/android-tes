package Spells;

import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Input.Pointer;
import Tools.Vector;

public class Spell {
		public int Cooldown = 100;
		public int Current = 0;
		GameObject parent;
		public Spell(GameObject _parent)
		{
			parent = _parent;
			//owner = parent.id;
		}
		public void Cast(List<Pointer> dest)
		{
			for(int x = 0; x<dest.size();x++)
			{
				if(dest.get(x).WithinScreen())
				{
					if(Current == 0)
					{
						Shoot(dest.get(x).WorldPos());
						Current = Cooldown;
					}
				}
			}
		}
		public void Cast(Vector dest)
		{
			Shoot(dest);
			//Current = Cooldown;
		}
		public void Update()
		{
			if(Current > 0)
			{
				Current -=1;
			}
		}
		void Shoot(Vector Dest)
		{
			RenderThread.addObject(
					new Fireball(
						new Vector(
							parent.rect.left + parent.rect.width()/2,
							parent.rect.top + parent.rect.height()/2
						),
						Dest.get(), parent)
					);
		}
}
