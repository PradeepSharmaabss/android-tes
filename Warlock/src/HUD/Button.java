package HUD;

import Input.Finger;
import Tools.Drawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Button extends Drawable{
	
	public RectF rect;
	public boolean down = false;
	public int id = 0;
	public String name = "default";
	Paint paint2;
	public Button(RectF r,int i)
	{
		super();
		id = i;
		paint.setColor(Color.RED);
		paint2 = new Paint();
		paint2.setColor(Color.WHITE);
		rect = r;
	}
	
	public void Draw(Canvas canvas)
	{
		canvas.drawRect(rect, paint2);
		canvas.drawRect(new RectF(rect.left + 2, rect.top + 2, rect.right - 2, rect.bottom - 2), paint);
	}
	public void Update()
	{
		if(rect.contains(Finger.position.x, Finger.position.y) && Finger.down)
		{
			down = true;
			paint.setColor(Color.RED);
		}
		else 
		{
			down = false;
			paint.setColor(Color.BLUE);
		}
	}
}
