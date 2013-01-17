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
		
		 if(i%50==49)
		 {
			 
				angle =(float)Math.random()*360;
			
		// RenderThread.addObject(new Projectile(position,
		// RenderThread.archie.position.get(),this));
	
		// RenderThread.addObject(new Projectile(position,
		// RenderThread.gameObjects.get(0).position.get(),this));


		destination =PositiononEllipse(angle);
	
		//position = new Vector(feet.x - size.x / 2, feet.y - size.y);
		// Log.d("Ellipse",destination.x+ " , "+ destination.y);
		 }

		super.Update();
	}
	protected Vector PositiononEllipse(float _angle)
	{
		float _x = (RenderThread.l.platform.Size.x / 2 - (RenderThread.l.platform.Size.x / 10))
				* (float)Math.cos((double) _angle % 360)
				+ RenderThread.l.platform.Position.x;
		float _y = (RenderThread.l.platform.Size.y / 2 - (RenderThread.l.platform.Size.y / 10))
				* (float)Math.sin((double) _angle % 360)
				+ RenderThread.l.platform.Position.y;
return new Vector(_x,_y);

	
	}
}
