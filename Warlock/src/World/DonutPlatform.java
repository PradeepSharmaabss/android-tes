package World;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.RectF;
import android.graphics.Region;

import com.example.warlockgame.RenderThread;

import Tools.Vector;

public class DonutPlatform extends ElipticalPlatform {
	Vector InnerCircleSize;

	public DonutPlatform(Vector _position, Vector _maxSize, Vector _minSize) {
		super(_position, _maxSize);
		InnerCircleSize = _minSize;
	}

	void Draw(Canvas c) {

		// Just for the sake of proving its subtracting. if wanted i can do a
		// square donut too. might also think on a diamond just cause haha
		paint.setColor(Color.WHITE);
		c.drawRect(new RectF(Position.x, Position.y, Position.x + 100,
				Position.y + 100), paint);

		// http://stackoverflow.com/questions/9285450/on-android-how-do-i-make-oddly-shaped-clipping-areas
		c.save();
		Path p = new Path();
		Path largePath = new Path();
		largePath.addOval(
				new RectF(Position.x - Size.x / 2, Position.y - Size.y / 2,
						Position.x + Size.x / 2, Position.y + Size.y / 2),
				Direction.CW);
		Path smallPath = new Path();
		smallPath.addOval(new RectF(Position.x - InnerCircleSize.x / 2,
				Position.y - InnerCircleSize.y / 2, Position.x
						+ InnerCircleSize.x / 2, Position.y + InnerCircleSize.y
						/ 2), Direction.CW);
		c.clipPath(largePath); // c is a Canvas
		c.clipPath(smallPath, Region.Op.DIFFERENCE);

		phase += 1;
		if (Size.x > 5) {
			if (phase % 5 == 1) {

				Size.x -= 2;
				Size.y -= 1;
			}
		}
		if (InnerCircleSize.x > 5) {
			if (phase % 5 == 1) {

				InnerCircleSize.x += 2;
				InnerCircleSize.y += 1;
			}
		}
		paint = new Paint();
		paint.setColor(Color.DKGRAY);
		c.drawOval(new RectF(Position.x - Size.x / 2, Position.y - Size.y / 2,
				Position.x + Size.x / 2, Position.y + Size.y / 2), paint);
		paint.setAlpha(125);
		if (Within(RenderThread.archie.feet)) {
			paint.setColor(Color.GRAY);
		} else {
			paint.setColor(Color.LTGRAY);
		}
		c.drawOval(new RectF(Position.x - Size.x / 2 + Size.x / 7, Position.y
				- Size.y / 2 + Size.y / 7,
				Position.x + Size.x / 2 - Size.x / 7, Position.y + Size.y / 2
						- Size.y / 7), paint);
		c.restore();
	}

	public boolean Within(Vector _pos) {
		if (WithinEllipse(Position.x, Position.y, Size.x / 2, Size.y / 2,
				_pos.x, _pos.y)) {
			if (!WithinEllipse(Position.x, Position.y, InnerCircleSize.x / 2,
					InnerCircleSize.y / 2, _pos.x, _pos.y)) {
				return true;
			}
		}
		return false;
	}

}
