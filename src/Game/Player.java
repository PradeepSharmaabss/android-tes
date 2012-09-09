package Game;

import com.example.androidproject.ImageHolder;
import com.example.androidproject.R;
import com.example.androidproject.Screen;
import com.example.androidproject.Screen.Action;

import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RectF;

public class Player extends GameObject {

	public int cooldown = 0;
	public Player()
	{
		super();
		paint.setColor(Color.GREEN);
		AI = false;
	}
	
	public void Draw(Object obj)
	{
		rect = new RectF(Screen.size.x / 2, position.y, Screen.size.x/
				2 + size.x, position.y + size.y);
		paint.setColor(Color.RED);
		super.Draw(obj);
		if(cooldown > 0) cooldown = cooldown-1;
		bmp = ImageHolder.archie;
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
