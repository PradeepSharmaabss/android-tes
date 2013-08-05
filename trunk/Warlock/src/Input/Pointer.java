package Input;

import Tools.Vector;
import Tools.iVector;

import com.example.warlockgame.RenderThread;

import java.io.Serializable;

public class Pointer implements Serializable{
	public Vector startPos = new Vector(0, 0);

	public Pointer() {
		this.position = new iVector(0, 0);
		this.down = false;
	}

	public iVector position;
	public boolean down;

	public void Update() {
		this.position = new iVector(0, 0);
		this.down = false;
	}

	public void Update2(iVector pos) {
		this.position = pos.get();
		this.down = true;
	}

	public boolean WithinScreen() {
		if (this.position != null)
			if (this.position.y < RenderThread.size.y)
				return true;
		return false;
	}

	public Vector WorldPos(Vector vector) {
		return new Vector(this.position.x + vector.x
				- RenderThread.size.x / 2, this.position.y
				+vector.y - RenderThread.size.y / 2);
	}
}
