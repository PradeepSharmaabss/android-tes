package Platform;

import com.example.warlockgame.RenderThread;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import Tools.Vector;

//creates and manages a square platform for use as the levels ground
public class Platform {
	public Vector Position;
	public Vector Size;
	Paint paint = new Paint();

	public Platform(Vector _position, Vector _size) {
		Position = _position;
		Size = _size;

	}

	int shrinkingPhase;
void Shrink()
{
	if (Size.x > 5) {
		shrinkingPhase += 1;
		if (shrinkingPhase % 5 == 1) {
			Size.x -= 2;
			Size.y -= 1;
		}
	}
	
}
	public void Draw(Canvas c) {
		//Shrinks the platform every few updates(should be put in an update function)
Shrink();
		//The outer Rectangle
		paint.setColor(Color.DKGRAY);
		c.drawRect(new RectF(Position.x - Size.x / 2, Position.y - Size.y / 2,
				Position.x + Size.x / 2, Position.y + Size.y / 2), paint);
		paint.setAlpha(125);
		
		//This is a debugging statement that highligihts the map if you are outside it
		if (Within(RenderThread.archie.feet)) {
			paint.setColor(Color.GRAY);
		} else {
			paint.setColor(Color.LTGRAY);
		}
		
		//the smaller, inner rectangle
		c.drawRect(new RectF(Position.x - Size.x / 2 + Size.x / 11, Position.y
				- Size.y / 2 + Size.y / 11,
				Position.x + Size.x / 2 - Size.x / 11, Position.y + Size.y / 2
						- Size.y / 11), paint);
	}

	// Tests if a point is located within the bounds of the platform
	public boolean Within(Vector _pos) {
		if (WithinShape(Position.x, Position.y, Size.x / 2, Size.y / 2, _pos.x,
				_pos.y)) {
			return true;
		}
		return false;
	}

	// Gets passed a position and some general parameters based on the
	// size/position etc of the rectangle
	// then returns true if the point it is passed is within its bounds
	protected boolean WithinShape(float centerx, float centery, float sizex,
			float sizey, float posx, float posy) {
		if (posx > centerx - sizex) {
			if (posy > centery - sizey) {
				if (posx < centerx + sizex) {
					if (posy < centery + sizey) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
