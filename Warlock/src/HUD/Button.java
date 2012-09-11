package HUD;

import Input.Finger;
import Tools.Drawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Button extends Drawable{
	
	public RectF rect;
	public boolean down = false;
	public Button(RectF r)
	{
		super();
		rect = r;
	}
	public void Draw(Canvas c)
	{
		c.drawRect(rect, paint);
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
