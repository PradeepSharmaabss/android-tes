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
		 i+=1;
			angle += 0.005;
		 if(i%50==49)
		 {
		// RenderThread.addObject(new Projectile(position,
		// RenderThread.archie.position.get(),this));
	
		// RenderThread.addObject(new Projectile(position,
		// RenderThread.gameObjects.get(0).position.get(),this));

		double _x = (RenderThread.l.platform.Size.x / 2 - (RenderThread.l.platform.Size.x / 10))
				* Math.cos((double) angle % 360)
				+ RenderThread.l.platform.Position.x;
		double _y = (RenderThread.l.platform.Size.y / 2 - (RenderThread.l.platform.Size.y / 10))
				* Math.sin((double) angle % 360)
				+ RenderThread.l.platform.Position.y;

		feet = new Vector((float) _x, (float) _y);
		 destination = feet.get();
		//position = new Vector(feet.x - size.x / 2, feet.y - size.y);
		// Log.d("Ellipse",destination.x+ " , "+ destination.y);
		 }

		super.Update();
	}
}
