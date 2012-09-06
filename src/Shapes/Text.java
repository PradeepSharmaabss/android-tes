package Shapes;

import com.example.androidproject.Vector;

import android.graphics.Canvas;
import android.graphics.Paint;

public class Text {
	Paint paint = new Paint();
	Vector position = new Vector(), size = new Vector();
	public Text()
	{
	}
	public void Draw(Canvas c)
	{
		this.Draw(c, position.get());
	}
	public void Draw(Canvas c, Vector v)
	{
		this.Draw(c, "none", v);
	}
	public void Draw(Canvas c, String text, Vector v)
	{
		c.drawText(text, v.x, v.y, paint);
	}
}
