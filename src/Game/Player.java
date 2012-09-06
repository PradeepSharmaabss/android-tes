package Game;

import com.example.androidproject.Screen;
import com.example.androidproject.Screen.Action;

import android.graphics.Canvas;
import android.graphics.Color;
import Shapes.Rectangle;

public class Player extends GameObject {

	public int cooldown = 0;
	public Player()
	{
		super();
		rect = new Rectangle(position.get() ,size.get());
		paint.setColor(Color.GREEN);
		AI = false;
	}
	
	public void Draw(Canvas c)
	{
		super.Draw(c);
		if(cooldown>0)	cooldown = cooldown-1;
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
					velocity.y = -10;
					jumping = true;
				}
				break;
		}
	}
	
}
