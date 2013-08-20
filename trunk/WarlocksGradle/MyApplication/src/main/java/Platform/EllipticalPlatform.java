package Platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import Tools.Vector;
import com.developmental.myapplication.RenderThread;

//An elliptical platform is simply a platform in the shape of an ellipse
public class EllipticalPlatform extends Platform {
	public EllipticalPlatform(Vector _position, Vector _size) {
		super(_position, _size);

	}

	@Override
	public void Draw(Canvas c,float playerx,float playery) {

		this.paint.setColor(Color.DKGRAY);
		c.drawOval(new RectF(this.Position.x - this.Size.x / 2-playerx, this.Position.y
				- this.Size.y / 2-playery, this.Position.x + this.Size.x / 2-playerx,
				this.Position.y + this.Size.y / 2-playery), this.paint);
		this.paint.setAlpha(125);
		if (Within(RenderThread.archie.feet))
			this.paint.setColor(Color.GRAY);
		else
			this.paint.setColor(Color.LTGRAY);
		c.drawOval(new RectF(this.Position.x - this.Size.x / 2 + this.Size.x
				/ 11-playerx, this.Position.y - this.Size.y / 2 + this.Size.y / 11-playery,
				this.Position.x + this.Size.x / 2 - this.Size.x / 11-playerx,
				this.Position.y + this.Size.y / 2 - this.Size.y / 11-playery),
				this.paint);
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
