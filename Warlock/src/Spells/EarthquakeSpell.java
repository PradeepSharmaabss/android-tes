package Spells;

import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Input.Pointer;

public class EarthquakeSpell extends Spell {
	public EarthquakeSpell(GameObject _parent)
	{
		super(_parent);
		Cooldown = 20;
	}
	public void Cast(List<Pointer> dest)
	{
		if(Current==0)
		{
		RenderThread.l.timer = 0;
		Current = Cooldown;
		}
	}

}
