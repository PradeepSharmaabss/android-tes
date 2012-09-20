package Spells;

import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.Projectile;
import Input.Pointer;
import Tools.Vector;

public class Spell {
		int Cooldown = 100;
		int Current = 0;
		GameObject parent;
		public Spell(GameObject _parent)
		{
			parent = _parent;
		}
		public void Cast(List<Pointer> dest)
		{
			for(int x = 0; x<dest.size();x++)
			{
				if(dest.get(x ).WithinScreen())
				{
					if(Current == 0)
					{
						Shoot(dest.get(x).WorldPos());
						Current = Cooldown;
					}
				}
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
			RenderThread.addObject(new Fireball(
					new Vector(RenderThread.archie.position.x,
					RenderThread.archie.rect.top+RenderThread.archie.rect.height()/2),
					Dest.get(), parent));
		}
}
