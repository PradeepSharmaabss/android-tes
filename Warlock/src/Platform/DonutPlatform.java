package Platform;

import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;

import com.example.warlockgame.Global;
import com.example.warlockgame.RenderThread;

public class DonutPlatform extends EllipticalPlatform {
	Vector InnerCircleSize;

	public DonutPlatform(Vector _position, Vector _maxSize, Vector _minSize) {
		super(_position, _maxSize);
		this.InnerCircleSize = _minSize;
	}

	@Override
	public void Draw(Canvas c) {

		// Just for the sake of proving its subtracting. if wanted i can do a
		// square donut too. might also think on a diamond just cause haha
		// http://stackoverflow.com/questions/9285450/on-android-how-do-i-make-oddly-shaped-clipping-areas
		c.save();
		Path largePath = new Path();
		largePath.addOval(new RectF(this.Position.x - this.Size.x / 2,
				this.Position.y - this.Size.y / 2, this.Position.x
						+ this.Size.x / 2, this.Position.y + this.Size.y / 2),
				Direction.CW);
		Path smallPath = new Path();
		smallPath.addOval(new RectF(this.Position.x - this.InnerCircleSize.x
				/ 2, this.Position.y - this.InnerCircleSize.y / 2,
				this.Position.x + this.InnerCircleSize.x / 2, this.Position.y
						+ this.InnerCircleSize.y / 2), Direction.CW);
		c.clipPath(largePath); // c is a Canvas
		c.clipPath(smallPath, Region.Op.DIFFERENCE);

		this.shrinkingPhase += 1;
		if (this.Size.x > 5)
			if (this.shrinkingPhase % 5 == 1) {

				this.Size.x -= 2;
				this.Size.y -= 1;
			}
		if (this.InnerCircleSize.x > 0)
			if (this.shrinkingPhase % 5 == 1) {

				this.InnerCircleSize.x -= 2;
				this.InnerCircleSize.y -= 1;
			}
		this.paint = new Paint();
		this.paint.setColor(Color.DKGRAY);
		c.drawOval(new RectF(this.Position.x - this.Size.x / 2, this.Position.y
				- this.Size.y / 2, this.Position.x + this.Size.x / 2,
				this.Position.y + this.Size.y / 2), this.paint);
		this.paint.setAlpha(125);
		if (Within(RenderThread.archie.feet))
			this.paint.setColor(Color.GRAY);
		else
			this.paint.setColor(Color.LTGRAY);
		c.drawOval(new RectF(this.Position.x - this.Size.x / 2 + this.Size.x
				/ 7, this.Position.y - this.Size.y / 2 + this.Size.y / 7,
				this.Position.x + this.Size.x / 2 - this.Size.x / 7,
				this.Position.y + this.Size.y / 2 - this.Size.y / 7),
				this.paint);
		c.drawBitmap(Global.tilesEllipse.get(0), new Rect(0, 0, 894, 894),
				new RectF(this.Position.x - this.Size.x / 2, this.Position.y
						- this.Size.y / 2, this.Position.x + this.Size.x / 2,
						this.Position.y + this.Size.y / 2), this.paint);
		c.restore();
	}

	@Override
	public boolean Within(Vector _pos) {
		if (WithinShape(this.Position.x, this.Position.y, this.Size.x / 2,
				this.Size.y / 2, _pos.x, _pos.y))
			if (!WithinShape(this.Position.x, this.Position.y,
					this.InnerCircleSize.x / 2, this.InnerCircleSize.y / 2,
					_pos.x, _pos.y))
				return true;
		return false;
	}

}
