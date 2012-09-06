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
	public List<Rectangle> tiles = new ArrayList<Rectangle>();
	public Vector size = new Vector(100, 20);
	public World()
	{
		float tileSize = 20;
		for(int x = 0; x < Screen.size.x; x+=Screen.size.x/tileSize)
		{
			tiles.add(new Rectangle(new Vector(x, Screen.size.y - 30),new Vector(Screen.size.x / tileSize - 10,20)));
		}
	}
	
	public void Collision(Player player)//test collision against the map with the Vector
	{
		boolean foundTile = false;
		Rectangle tile = new Rectangle(new Vector(0,0),new Vector(0,0));
		for(int x=0 ; x < tiles.size(); x++)
		{
			if(tiles.get(x).Contains(player.rect.Left(), player.rect.Top()) || 
					tiles.get(x).Contains(player.rect.Right(),player.rect.Top()) ||
					tiles.get(x).Contains(player.rect.Left(),player.rect.Bot()) ||
					tiles.get(x).Contains(player.rect.Right(),player.rect.Bot()))
			{
				System.out.println("found tile");
				player.paint.setColor(Color.GREEN);
				foundTile = true;
				tile = tiles.get(x).get();
			}
			else
			{
				player.paint.setColor(Color.RED);
			}
		}
		if(foundTile)
		{
			player.position.y = tile.Top() - player.size.y;
			player.velocity.y=0;
		}
		else
			player.position.y += 10;
	}
	public void Draw(Canvas c)
	{
		for(int x=0 ; x < tiles.size(); x++)
		{
			tiles.get(x).Draw(c);
		}
	}
}
