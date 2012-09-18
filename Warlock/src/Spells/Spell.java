package Spells;


import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.Projectile;
import Tools.Vector;

public class Spell {
		int Cooldown = 100;
		int Current = 0;
		GameObject parent;
		public Spell(GameObject _parent)
		{
			parent = _parent;
		}
		public void Cast(Vector dest)
		{
			if(Current == 0)
			{
				Shoot(dest);
				Current =Cooldown;
				
			}
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
			RenderThread.addObject(new Projectile(
					new Vector(RenderThread.archie.position.x,
					RenderThread.archie.rect.top+RenderThread.archie.rect.height()/2),
					Dest.get(), parent));
		}
}
