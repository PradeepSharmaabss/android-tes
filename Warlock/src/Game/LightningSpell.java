package Game;

import Tools.Vector;

public class LightningSpell extends Spell {

	LightningSpell(GameObject _parent)
	{
		super(_parent);
		Cooldown = 20;
	}
	@Override
	public void Cast(Vector dest)
	{
		if(Current ==0)
		{
			parent.ShootL(dest);
			Current =Cooldown;
			
		}
	}
}
