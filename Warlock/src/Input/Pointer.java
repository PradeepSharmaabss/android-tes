package Input;

import com.example.warlockgame.RenderThread;

import Tools.Vector;

public class Pointer {
	public Vector startPos = new Vector(0,0);
	public Pointer()
	{
		position = new Vector(0,0);
		down = false;
	}
	public Vector position;
	public boolean down;
	public void Update()
	{
		position =new Vector(0,0);
		down = false;
	}
	public void Update2(Vector pos)
	{
		position = pos.get();
		down = true;
	}
	public boolean WithinScreen()
	{
		if(position!=null)
		{
		if(position.y<RenderThread.size.y)
		{
			return true;
		}
		}
		return false;
	}
	public Vector WorldPos()
	{
		return new Vector(position.x+RenderThread.archie.position.x-RenderThread.size.x/2,position.y+RenderThread.archie.position.y-RenderThread.size.y/2);
	}
}
