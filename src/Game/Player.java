package Game;

import com.example.androidproject.ImageHolder;
import com.example.androidproject.R;
import com.example.androidproject.Screen;
import com.example.androidproject.Screen.Action;

import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Player extends GameObject {

	public int cooldown = 0;
	public Animation walkLeft,walkRight;
	public Player()
	{
		super();
		paint.setColor(Color.GREEN);
		AI = false;
	}
	
	public void Draw(Object obj)
	{
		if(walkLeft == null && ImageHolder.walkLeft!=null)
			walkLeft = new Animation(ImageHolder.walkLeft);
		if(walkRight == null && ImageHolder.walkRight!=null)
			walkRight = new Animation(ImageHolder.walkRight);
		//if(ImageHolder.archie == null) 
			//ImageHolder.archie = BitmapFactory.decodeResource(Screen.resource, R.drawable.character);
		
		rect = new RectF(Screen.size.x / 2, Screen.size.y/2, 
				Screen.size.x/2 + size.x, Screen.size.y/2 + size.y);//make sure to draw player at middle.
		
		feet = new RectF(rect.left, rect.top, rect.right - rect.left, rect.top+5);
		super.Draw(obj);
		paint.setColor(Color.RED);
		
		
		if(cooldown > 0) cooldown = cooldown-1;
		
			if(velocity.x > 0)
			{
				if(velocity.x != 0)
					if(walkRight!=null)
						bmp = walkRight.GetFrame(velocity.get());
			}
			else
			{
				if(velocity.x != 0)
					if(walkLeft!=null)
						bmp = walkLeft.GetFrame(velocity.get());
					//walkLeft.Draw((Canvas)obj, this);
			}
			if(bmp != null)
				((Canvas)obj).drawBitmap(bmp, null, rect, paint);
		
	}

	public boolean onScreen()
	{
		if(rect.left >= 0 && rect.right <= Screen.size.x && rect.top  >= 0 && rect.bottom <= Screen.size.y)
			return true;
		return false;
	}

	public void Commands(Action action)
	{
		switch(action)
		{
			case left:
				if(velocity.x > -maxVelocity.x)
					velocity.x -= acceleration.x;
				else
					velocity.x = -maxVelocity.x;
				break;
			case right:
				if(velocity.x < maxVelocity.x)
					velocity.x += acceleration.x;
				else
					velocity.x = maxVelocity.x;
				break;
			case jump:
				if(jumping == false)
				{
					grounded=false;
					velocity.y = -10;
					jumping = true;
				}
				break;
		}
	}
	
}
