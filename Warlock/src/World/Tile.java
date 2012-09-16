package World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class Tile {
	public RectF rect;
	Bitmap bitmap;
	public Tile(Bitmap bitmap, RectF rect)
	{
		this.bitmap = bitmap;
		this.rect = rect;
	}

	public void DrawAt(Canvas c, float x, float y , Paint paint)
	{
		c.drawBitmap(bitmap, null, new RectF(rect.left - x, rect.top - y, rect.right - x, rect.bottom - y), paint);
	}
}
