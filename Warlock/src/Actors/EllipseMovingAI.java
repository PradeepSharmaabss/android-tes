package Actors;

import Tools.Vector;
import android.graphics.Color;

import com.example.warlockgame.RenderThread;

public class EllipseMovingAI extends Enemy {
	public EllipseMovingAI() {
		super();
		double _x = (RenderThread.l.platform.Size.x / 2 - 3)
				* Math.cos((double) this.angle % 360)
				+ RenderThread.l.platform.Position.x;
		double _y = (RenderThread.l.platform.Size.y / 2 - 3)
				* Math.sin((double) this.angle % 360)
				+ RenderThread.l.platform.Position.y;

		this.destination = new Vector((float) _x, (float) _y);
		this.maxVelocity = 10;
		this.paint.setColor(Color.YELLOW);
	}

	float angle = 0;
	int i = 0;

	@Override
	public void Update() {
		this.i += 1;
		// angle+=0.005;
		if (this.i % 50 == 49) {
			this.angle = (float) Math.random() * 360;
			this.destination = PositiononEllipse(this.angle);
		}
		super.Update();
	}

	// returns the position along the ellips the angle is.
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
