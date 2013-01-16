package World;

import com.example.warlockgame.RenderThread;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;
import Tools.Vector;

public class Platform {
	public Vector Position;
	public Vector Size;
	Paint paint = new Paint();

	public Platform(Vector _position, Vector _size) {
		Position = _position;
		Size = _size;

	}

	int phase;

	void Draw(Canvas c) {
		phase += 1;
		if (Size.x > 5) {
			if (phase % 5 == 1) {
				Size.x -= 2;
				Size.y -= 1;
			}
		}
		paint = new Paint();
		paint.setColor(Color.DKGRAY);
		c.drawRect(new RectF(Position.x - Size.x / 2, Position.y - Size.y / 2,
				Position.x + Size.x / 2, Position.y + Size.y / 2), paint);
		paint.setAlpha(125);
		if (Within(RenderThread.archie.feet)) {
			paint.setColor(Color.GRAY);
		} else {
			paint.setColor(Color.LTGRAY);
		}
		c.drawRect(new RectF(Position.x - Size.x / 2 + Size.x / 7, Position.y
				- Size.y / 2 + Size.y / 7,
				Position.x + Size.x / 2 - Size.x / 7, Position.y + Size.y / 2
						- Size.y / 7), paint);
	}

	public boolean Within(Vector _pos) {
		if (WithinRect(Position.x, Position.y, Size.x / 2, Size.y / 2, _pos.x,
				_pos.y)) {
			return true;
		}
		return false;
	}

	private boolean WithinRect(float centerx, float centery, float sizex,
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
