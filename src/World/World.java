package World;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Canvas;
import com.example.androidproject.Vector;

public abstract class World {
	public List<Tile> tiles = new ArrayList<Tile>();
	public Vector size = new Vector(100, 20);
	Tile tile;
	
	public World()
	{ 
	}
	
	public void Draw(Canvas c)
	{
		for(int x=0 ; x < tiles.size(); x++)
		{
			tiles.get(x).Draw(c);
		}
	}
}
