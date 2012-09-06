package Game;

import com.example.androidproject.Screen;

import android.graphics.Canvas;
import Shapes.Rectangle;

public class Player extends GameObject {
	public Rectangle rect;
	public boolean jumping = false;
	public Player()
	{
		super();
		rect = new Rectangle(position.get(),size.get());
	}
	
	public void Draw(Canvas c)
	{
		position = position.add(velocity);
		super.Draw(c);
		rect.Draw(c);
		rect.position = position.get();
		//Collision();
		Gravity();
	}
	public void Collision()
	{
		if(onScreen() ==true)
		{
			if(rect.Bot() >= Screen.size.y - 31)
			{
				position.y = (Screen.size.y - 31) - size.y;
			}
			else
			{
				position.y += 10;
			}
		}
		
	}
	public boolean onScreen()
	{
		if(rect.Left() >= 0 && rect.Right() <= Screen.size.x && rect.Top()  >= 0 && rect.Bot() <= Screen.size.y)
			return true;
		return false;
	}
	
}
