package World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Tile {
	public RectF rect;
	Bitmap bitmap;
	public float offsety = 0;
	public boolean earthquake = false;

	public Tile(Bitmap bitmap, RectF rect) {
		// Log.Marker("" + rect.width(), "" +rect.height());
		// Log.Marker("" + bitmap.getWidth(), "" + bitmap.getHeight());
		// this.bitmap = Bitmap.createScaledBitmap(bitmap,(int)rect.width(),
		// (int)rect.height(),false);
		// this.bitmap = bitmap;
		this.bitmap = bitmap;
		bitmap = null;
		// System.gc();
		this.rect = rect;
	}

	public void DrawAt(Canvas c, float x, float y, Paint paint) {
		if (this.earthquake)
			this.offsety = (float) (Math.random() * 20 - 15);
		else if (this.offsety != 0)
			this.offsety = 0;
		c.drawBitmap(this.bitmap, this.rect.left - x, this.rect.top - y
				+ this.offsety, paint);
		// c.drawBitmap(bitmap, null,new RectF(rect.left - x, rect.top - y,
		// rect.right - x,rect.bottom - y), paint);
	}
}
