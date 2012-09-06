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
		for(int x = 0; x < Screen.size.x/2; x+=(Screen.size.x/2)/tileSize)
		{
			tiles.add(new Rectangle(new Vector(x, Screen.size.y- 40),new Vector((Screen.size.x/2) / tileSize - 10,20)));
		}
		tiles.add(new Rectangle(new Vector(Screen.size.x/2, Screen.size.y- 40),new Vector(Screen.size.x/2,20),-45));
	}
	
	public void Collision(Player player)//test collision against the map with the Vector
	{
		boolean foundTile = false;
		Rectangle tile = new Rectangle(new Vector(0,0),new Vector(0,0));
			for(int x=0 ; x < tiles.size(); x++)
			{
				if(	tiles.get(x).Contains(player.rect.Left(),player.rect.Bot()) ||
					tiles.get(x).Contains(player.rect.Right(),player.rect.Bot()))
				{
					tiles.get(x).paint.setColor(Color.GREEN);
					foundTile = true;
					if(tile == null)
						tile = tiles.get(x).get();
				}
				else
				{
					tiles.get(x).paint.setColor(Color.RED);
				}
			}
			
			if(foundTile)
			{
				//normalise here
				//player.position = tile.translate(new Vector(player.position.x, player.position.y));
				//float[] test = tile.translate(new Vector(player.position.x, player.position.y));
				//player.position = new Vector(test[0],test[1]);
				//player.position.y = tile.Top() - player.size.y;
				player.velocity.y = 0;
				player.jumping = false;
				player.grounded = true;
			}
			else
			{
				player.grounded = false;
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
