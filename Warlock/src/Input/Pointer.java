package Input;

import Tools.Vector;

public class Pointer {
	Pointer()
	{
		down = false;
	}
	public Vector position;
	public boolean down;
	public void Update()
	{
		down = false;
	}
	public void Update2(Vector pos)
	{
		position = pos.get();
		down = true;
	}
}
