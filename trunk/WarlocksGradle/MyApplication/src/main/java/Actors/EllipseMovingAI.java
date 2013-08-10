package Actors;

import android.graphics.Bitmap;
import android.graphics.Color;

import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

import Game.Destination;
import Game.ObjectType;
import Tools.SpriteSheet;
import Tools.Vector;

public class EllipseMovingAI extends Enemy {
	public EllipseMovingAI(ArrayList<Bitmap> _spriteSheet, Vector _pos) {
		super(_spriteSheet,_pos);
		double _x = (RenderThread.l.platform.Size.x / 2 - 3)
				* Math.cos((double) this.angle % 360)
				+ RenderThread.l.platform.Position.x;
		double _y = (RenderThread.l.platform.Size.y / 2 - 3)
				* Math.sin((double) this.angle % 360)
				+ RenderThread.l.platform.Position.y;
        this.objectObjectType = ObjectType.Enemy;
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

            Marker = new Destination( destination);
		}
		super.Update();
	}

	// returns the position along the ellips the angle is.

}
