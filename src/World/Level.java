package World;

import com.example.androidproject.Screen;
import com.example.androidproject.Vector;

import Game.GameObject;
import Game.Player;
import android.graphics.Canvas;
import android.graphics.Color;

public class Level extends World {

	public float offsetx = Screen.size.x/2+100;
	public Level()
	{
		super();
		for(int x = 0; x < 3 * Screen.size.x; x+= Screen.size.x)
		{
			tiles.add(new Tile(new Vector(x, Screen.size.y - 40), new Vector(Screen.size.x-20 , 20)));
			tiles.add(new Tile(new Vector(x, Screen.size.y - 155), new Vector(20 , 20)));
		}
		//tiles.add(new Tile(new Vector(Screen.size.x/2, Screen.size.y- 150),new Vector(Screen.size.x/2,20)));
	}
	public void Draw(Canvas c, Player p)
	{
		super.Draw(c);
		for(int x=0 ; x < tiles.size(); x++)
		{
			tiles.get(x).DrawAt(c, p.position.x);
		}
		
	}
	public void Collision(GameObject obj)
	{
		tile = new Tile();
		boolean foundTile = false;

			
		if(obj.velocity.y >= 0)
		{
			for(int x=0 ; x < tiles.size(); x++)
			{
				if(	tiles.get(x).rect.Overlap(obj.rect.Left(),obj.rect.Bot(),obj.position.x) ||
					tiles.get(x).rect.Overlap(obj.rect.Right(),obj.rect.Bot(),obj.position.x))
				{
					//tiles.get(x).rect.paint.setColor(Color.GREEN);
					foundTile = true;
					tile = tiles.get(x).get();
				}
				else
				{
					//tiles.get(x).rect.paint.setColor(Color.RED);
				}
			}
			
			if(foundTile)
			{
				Collide(obj);
			}
			else
			{
				obj.grounded = false;
			}
		}
	}
	public void Collide(GameObject obj)
	{
		if("arrow".equals(obj))
		{
			obj.position.y = tile.rect.Top() - obj.size.y;
			obj.velocity = new Vector();
			obj.grounded = true;
		}
		else
		{
			obj.position.y = tile.rect.Top() - obj.size.y;
			obj.velocity.y = 0;
			obj.jumping = false;
			obj.grounded = true;
		}
	}

}
