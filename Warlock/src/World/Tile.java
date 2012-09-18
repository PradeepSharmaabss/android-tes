package World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;


public class Tile 
{
	public RectF rect;
	Bitmap bitmap;
	public Tile(Bitmap bitmap, RectF rect)
	{
		//Log.d("" + rect.width(), "" +rect.height());
		//Log.d("" + bitmap.getWidth(), "" + bitmap.getHeight());
		//this.bitmap = Bitmap.createScaledBitmap(bitmap,(int)rect.width(), (int)rect.height(),false);
		//this.bitmap = bitmap;
		this.bitmap = bitmap;
		bitmap = null;
		//System.gc();
		this.rect = rect;
	}

	public void DrawAt(Canvas c, float x, float y , Paint paint)
	{
		c.drawBitmap(bitmap, rect.left - x, rect.top - y, paint);
		//c.drawBitmap(bitmap, null,new RectF(rect.left - x, rect.top - y, rect.right - x,rect.bottom - y), paint);
	}
}
