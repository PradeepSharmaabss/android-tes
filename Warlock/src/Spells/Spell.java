package Spells;

import android.util.Log;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Game.LightningBolt;
import Game.Projectile;
import Input.Finger;
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
			if(Current ==0)
			{
				Shoot(dest);
				Current =Cooldown;
				Log.d("SHOOT", "SSS");
			}
		}
		public void Update()
		{
			if(Current>0)
			{
				Current -=1;
			}
		}
		void Shoot(Vector Dest)
		{
			RenderThread.addObject(new Projectile(parent.position.get(),Dest.get(), parent));
		}
}
