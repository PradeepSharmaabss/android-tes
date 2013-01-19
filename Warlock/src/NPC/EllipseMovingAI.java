package NPC;

import android.graphics.Color;

import com.example.warlockgame.RenderThread;
import Tools.Vector;

public class EllipseMovingAI extends Enemy {
	public EllipseMovingAI() {
		super();
		double _x = (RenderThread.l.platform.Size.x / 2 - 3)
				* Math.cos((double) angle % 360)
				+ RenderThread.l.platform.Position.x;
		double _y = (RenderThread.l.platform.Size.y / 2 - 3)
				* Math.sin((double) angle % 360)
				+ RenderThread.l.platform.Position.y;

		destination = new Vector((float) _x, (float) _y);
		this.maxVelocity = 10;
		paint.setColor(Color.YELLOW);
	}

	float angle = 0;
	int i = 0;

	public void Update() {
		i += 1;
		// angle+=0.005;
		if (i % 50 == 49) {
			angle = (float) Math.random() * 360;
			destination = PositiononEllipse(angle);
		}
		super.Update();
	}

	//returns the position along the ellips the angle is.
	protected Vector PositiononEllipse(float _angle) {
		float _x = (RenderThread.l.platform.Size.x / 2 - (RenderThread.l.platform.Size.x / 10))
				* (float) Math.cos((double) _angle % 360)
				+ RenderThread.l.platform.Position.x;
		float _y = (RenderThread.l.platform.Size.y / 2 - (RenderThread.l.platform.Size.y / 10))
				* (float) Math.sin((double) _angle % 360)
				+ RenderThread.l.platform.Position.y;
		return new Vector(_x, _y);
	}
}
