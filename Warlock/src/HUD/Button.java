package HUD;

import Input.Finger;
import Input.Pointer;
import Spells.Spell;
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
	Paint Cd;
	Spell s;
	public Button(RectF r,int i)
	{
		super();
		id = i;
		paint.setColor(Color.RED);
		paint2 = new Paint();
		paint2.setColor(Color.WHITE);
		rect = r;
		Cd= new Paint();
		Cd.setColor(Color.GREEN);
	}
	public void Draw(Canvas canvas,Spell s)
	{
		canvas.drawRect(rect, paint2);
		//draw the blue rect
		canvas.drawRect(new RectF(rect.left + 2, rect.top + 2, rect.right - 2, rect.bottom - 2), paint);
		//Draw the cooldown
	
		canvas.drawRect(new RectF(rect.left+20,rect.bottom-20,rect.right-((float)s.Current/(float)s.Cooldown)*rect.width()-20, rect.bottom),Cd);
	}
	public void Update()
	{
		for(int x=0; x < Finger.pointers.size(); x++)
		{
			Pointer f = Finger.pointers.get(x);
			
			if(f.down==false)
			{
				continue;
			}
		
			if(rect.contains(f.position.x, f.position.y))
			{
				paint.setColor(Color.RED);
				down =true;
				return;
			}
		}
		paint.setColor(Color.BLUE);
		down=false;
	
	}
}
