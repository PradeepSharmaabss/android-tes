package Game;

import com.example.androidproject.Screen;
import com.example.androidproject.Screen.Action;

import android.graphics.Canvas;
import Shapes.Rectangle;

public class Player extends GameObject {
	public Rectangle rect;
	public Player()
	{
		super();
		rect = new Rectangle(position.get(),size.get());
	}
	
	public void Draw(Canvas c)
	{
		super.Draw(c);
		
		if(onScreen())
			position = position.add(velocity);
		
		rect.Draw(c);
		rect.position = position;
		Gravity();
	}

	public boolean onScreen()
	{
		if(rect.Left() >= 0 && rect.Right() <= Screen.size.x && rect.Top()  >= 0 && rect.Bot() <= Screen.size.y)
			return true;
		return false;
	}
	public void Commands(Action action)
	{
		//need to do some Grounded testing;
		switch(action)
		{
			case left:
				if(velocity.x > -maxVelocity.x)
					velocity.x -= 0.5;
				else
					velocity.x = -maxVelocity.x;
				break;
			case right:
				if(velocity.x < maxVelocity.x)
					velocity.x += 0.5;
				else
					velocity.x = maxVelocity.x;
				break;
			case jump:
				if(jumping == false)
				{
					grounded=false;
					velocity.y =- 10;
					jumping = true;
				}
				break;
		}
	}
	
}
