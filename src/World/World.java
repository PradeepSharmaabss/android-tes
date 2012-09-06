package World;

import java.util.ArrayList;
import java.util.List;

import Game.*;
import android.graphics.Canvas;
import android.graphics.Color;

import com.example.androidproject.Screen;
import com.example.androidproject.Vector;

public abstract class World {
	public List<Tile> tiles = new ArrayList<Tile>();
	public Vector size = new Vector(100, 20);
	Tile tile;
	
	public World()
	{ 
		Setup();
	}
	
	public void Setup()
	{
		//float tileSize = 20;
		for(int x = 0; x < 10 * Screen.size.x; x+= Screen.size.x)
		{
			tiles.add(new Tile(new Vector(x, Screen.size.y- 40),new Vector(Screen.size.x ,20)));
		}
		//tiles.add(new Tile(new Vector(Screen.size.x/2, Screen.size.y- 40),new Vector(Screen.size.x/2, 20)));
		tiles.add(new Tile(new Vector(Screen.size.x/2, Screen.size.y- 150),new Vector(Screen.size.x/2,20)));
	}

	public void Collision(GameObject obj)//test collision against the map with the Vector
	{
		tile = new Tile();
		boolean foundTile = false;

			
		if(obj.velocity.y >= 0)
		{
			for(int x=0 ; x < tiles.size(); x++)
			{
				if(	tiles.get(x).rect.Overlap(obj.rect.Left(),obj.rect.Bot()) ||
					tiles.get(x).rect.Overlap(obj.rect.Right(),obj.rect.Bot()))
				{
					tiles.get(x).rect.paint.setColor(Color.GREEN);
					foundTile = true;
					tile = tiles.get(x).get();
				}
				else
				{
					tiles.get(x).rect.paint.setColor(Color.RED);
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
	public void Draw(Canvas c)
	{
		for(int x=0 ; x < tiles.size(); x++)
		{
			tiles.get(x).Draw(c);
		}
	}
}
