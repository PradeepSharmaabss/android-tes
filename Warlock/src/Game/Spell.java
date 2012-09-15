package Game;

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
				parent.Shoot(dest);
				Current =Cooldown;
				
			}
		}
		public void Update()
		{
			if(Current>0)
			{
				Current --;
			}
			
		}
}
