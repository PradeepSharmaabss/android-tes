package Input;

import Tools.Vector;

import com.example.warlockgame.RenderThread;

public class Pointer {
	public Vector startPos = new Vector(0, 0);

	public Pointer() {
		this.position = new Vector(0, 0);
		this.down = false;
	}

	public Vector position;
	public boolean down;

	public void Update() {
		this.position = new Vector(0, 0);
		this.down = false;
	}

	public void Update2(Vector pos) {
		this.position = pos.get();
		this.down = true;
	}

	public boolean WithinScreen() {
		if (this.position != null)
			if (this.position.y < RenderThread.size.y)
				return true;
		return false;
	}

	public Vector WorldPos() {
		return new Vector(this.position.x + RenderThread.archie.position.x
				- RenderThread.size.x / 2, this.position.y
				+ RenderThread.archie.position.y - RenderThread.size.y / 2);
	}
}
