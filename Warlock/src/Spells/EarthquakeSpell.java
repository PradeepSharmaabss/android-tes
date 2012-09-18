package Spells;

import java.util.List;

import com.example.warlockgame.RenderThread;

import Game.GameObject;
import Input.Pointer;

public class EarthquakeSpell extends Spell {
	public EarthquakeSpell(GameObject _parent)
	{
		super(_parent);
		Cooldown = 0;
	}
	public void Cast(List<Pointer> dest)
	{
		RenderThread.l.timer = 0;
	}

}
