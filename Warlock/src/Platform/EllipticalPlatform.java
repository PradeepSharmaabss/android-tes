package Platform;

import com.example.warlockgame.RenderThread;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import Tools.Vector;

//An elliptical platform is simply a platform in the shape of an ellipse
public class EllipticalPlatform extends Platform {
	public EllipticalPlatform(Vector _position, Vector _size) {
		super(_position, _size);

	}

	public void Draw(Canvas c) {
		Shrink();
		paint.setColor(Color.DKGRAY);
		c.drawOval(new RectF(Position.x - Size.x / 2, Position.y - Size.y / 2,
				Position.x + Size.x / 2, Position.y + Size.y / 2), paint);
		paint.setAlpha(125);
		if (Within(RenderThread.archie.feet)) {
			paint.setColor(Color.GRAY);
		} else {
			paint.setColor(Color.LTGRAY);
		}
		c.drawOval(new RectF(Position.x - Size.x / 2 + Size.x / 11, Position.y
				- Size.y / 2 + Size.y / 11, Position.x + Size.x / 2 - Size.x
				/ 11, Position.y + Size.y / 2 - Size.y / 11), paint);
	}

	@Override
	protected boolean WithinShape(float ex, float ey, float ea, float eb,
			float px, float py) {
		// ex,ey = position ellipse
		// ea,eb = radiants of ellipse
		// px,py = position of point
		float dx = px - ex;
		float dy = py - ey;

		return (dx * dx) / (ea * ea) + (dy * dy) / (eb * eb) <= 1;

	}

}
