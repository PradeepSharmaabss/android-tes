package World;

import java.util.ArrayList;
import java.util.List;

import Game.Player;
import Shapes.Rectangle;
import android.graphics.Canvas;
import android.graphics.Color;

import com.example.androidproject.Screen;
import com.example.androidproject.Vector;

public abstract class World {
	public List<Tile> tiles = new ArrayList<Tile>();
	public Vector size = new Vector(100, 20);
	public World()
	{
		float tileSize = 20;
		for(int x = 0; x < Screen.size.x/2; x+=(Screen.size.x/2)/tileSize)
		{
			tiles.add(new Tile(new Vector(x, Screen.size.y- 40),new Vector((Screen.size.x/2) / tileSize - 10,20)));
		}
		tiles.add(new Tile(new Vector(Screen.size.x/2, Screen.size.y- 40),new Vector(Screen.size.x/2, 20)));
		tiles.add(new Tile(new Vector(Screen.size.x/2, Screen.size.y- 155),new Vector(Screen.size.x/2,20)));
	}
	
	public void Collision(Player player)//test collision against the map with the Vector
	{
		boolean foundTile = false;
		Tile tile = new Tile();
			
		if(player.velocity.y >= 0)
		{
			for(int x=0 ; x < tiles.size(); x++)
			{
				if(	tiles.get(x).rect.Contains(player.rect.Left(),player.rect.Bot()) ||
					tiles.get(x).rect.Contains(player.rect.Right(),player.rect.Bot()))
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
				player.position.y = tile.rect.Top() - player.size.y;
				System.out.println(tile.rect.Top());
				player.velocity.y = 0;
				player.jumping = false;
				player.grounded = true;
			}
			else
			{
				player.grounded = false;
			}
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
