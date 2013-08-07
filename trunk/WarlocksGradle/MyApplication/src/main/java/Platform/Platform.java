package Platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Tools.Vector;
import com.developmental.warlocks.RenderThread;

//creates and manages a square platform for use as the levels ground
public class Platform {
	public Vector Position;
	public Vector Size;
	Paint paint = new Paint();

	public Platform(Vector _position, Vector _size) {
		this.Position = _position;
		this.Size = _size;
	}

	int shrinkingPhase;

	void Shrink() {
		if (this.Size.x > 5) {
			this.shrinkingPhase += 1;
			if (this.shrinkingPhase % 5 == 1) {
				this.Size.x -= 2;
				this.Size.y -= 1;
			}
		}

	}

	public void Draw(Canvas c,float playerx,float playery) {
		// Shrinks the platform every few updates(should be put in an update
		// function)
		Shrink();
		// The outer Rectangle
		this.paint.setColor(Color.DKGRAY);
		c.drawRect(new RectF(this.Position.x - this.Size.x / 2-playerx, this.Position.y
				- this.Size.y / 2-playery, this.Position.x + this.Size.x / 2-playerx,
				this.Position.y + this.Size.y / 2-playery), this.paint);
		this.paint.setAlpha(125);

		// This is a debugging statement that highligihts the map if you are
		// outside it
		if (Within(RenderThread.archie.feet))
			this.paint.setColor(Color.GRAY);
		else
			this.paint.setColor(Color.LTGRAY);

		// the smaller, inner rectangle
		c.drawRect(new RectF(this.Position.x - this.Size.x / 2 + this.Size.x
				/ 11-playerx, this.Position.y - this.Size.y / 2 + this.Size.y / 11-playery,
				this.Position.x + this.Size.x / 2 - this.Size.x / 11-playerx,
				this.Position.y + this.Size.y / 2 - this.Size.y / 11-playery),
				this.paint);
	}

	// Tests if a point is located within the bounds of the platform
	public boolean Within(Vector _pos) {
		if (WithinShape(this.Position.x, this.Position.y, this.Size.x / 2,
				this.Size.y / 2, _pos.x, _pos.y))
			return true;
		return false;
	}

	// Gets passed a position and some general parameters based on the
	// size/position etc of the rectangle
	// then returns true if the point it is passed is within its bounds
	protected boolean WithinShape(float centerx, float centery, float sizex,
			float sizey, float posx, float posy) {
		if (posx > centerx - sizex)
			if (posy > centery - sizey)
				if (posx < centerx + sizex)
					if (posy < centery + sizey)
						return true;
		return false;
	}

}
