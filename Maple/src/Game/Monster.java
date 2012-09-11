package Game;

import android.graphics.Canvas;
import android.graphics.Color;

public class Monster extends GameObject{

	public Monster()
	{
		super();
		velocity.x = 10;
		paint.setColor(Color.BLUE);
		
	}
	public void Draw(Canvas c)
	{
		super.Draw(c);
		paint.setColor(Color.BLUE);
		jump();
	}
}
