package HUD;

import Input.Finger;
import Input.Pointer;
import Tools.Drawable;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

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
		boolean touched = false;
	
		for(int x=0;x<Finger.pointers.size();x++)
		{
			Pointer f = Finger.pointers.get(x);
			
			if(f.down==false)
			{
				Log.d("pointerpos", "in " );
				break;
			}
		
		
		if(rect.contains(f.position.x, f.position.y))
		{
			paint.setColor(Color.RED);
			down =true;
	return;
		}
		}
		paint.setColor(Color.BLUE);
	
	}
}
