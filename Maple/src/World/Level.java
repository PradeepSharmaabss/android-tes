package World;

import java.util.ArrayList;
import java.util.List;

import com.example.androidproject.ImageHolder;
import com.example.androidproject.Screen;
import com.example.androidproject.Vector;

import Game.GameObject;
import Game.Player;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Level extends World {
	
	public float offsetx = Screen.size.x/2+100;
	public List<Bitmap> backgrounds = new ArrayList<Bitmap>();
	public List<Vector> trees = new ArrayList<Vector>();
	public Paint paint;
	public Level()
	{
		super();
		paint = new Paint();
		for(int x = 0; x <6 * Screen.size.x; x+= Screen.size.x)
		{
			tiles.add(new Tile(new Vector(x, Screen.size.y - 40), new Vector(Screen.size.x-20 , 20)));
			tiles.add(new Tile(new Vector(x, Screen.size.y - 140), new Vector(200 , 20)));
			trees.add(new Vector(x, 100));
		}
		
		backgrounds.add(ImageHolder.bg);
		backgrounds.add(ImageHolder.bg2);
		//tiles.add(new Tile(new Vector(Screen.size.x/2, Screen.size.y- 150),new Vector(Screen.size.x/2,20)));
	}
	public void Draw(Object c, Player p)
	{
		DrawBackgrounds((Canvas)c,p);
		DrawTrees((Canvas)c,p);
		super.Draw(c);
		for(int x=0 ; x < tiles.size(); x++)
		{
			tiles.get(x).DrawAt(c , p.position.x, p.position.y);
		}
		
	}
	public void DrawBackgrounds(Canvas c,Player p)
	{
		for(int x=0;x<backgrounds.size();x++)
			c.drawBitmap(backgrounds.get(x), null, new RectF(-p.position.x + (x*(Screen.size.x*3)), 0, -p.position.x + (Screen.size.x*3) + (x*( Screen.size.x*3)), Screen.size.y), paint);
	}
	public void DrawTrees(Canvas c,Player p)
	{
		for(int x=0;x<trees.size();x++)
		{
			c.drawBitmap(ImageHolder.tree, null, new RectF(-p.position.x + trees.get(x).x, trees.get(x).y, 100, 100), paint);
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
				if(	RectF.intersects(obj.rect, tiles.get(x).platform) )
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
			obj.position.y = tile.rect.top - obj.size.y;
			obj.velocity = new Vector();
			obj.grounded = true;
		}
		else
		{
			if(obj.position.y > tile.rect.top)
			{
				obj.position.y -= obj.position.y - tile.rect.top;//minus the difference to put on top of tile.
			}
			else
			{
				//obj.position.y += (obj.position.y - tile.rect.top);// obj.position.y - tile.rect.top/2;
			}
			//obj.position.y = tile.rect.top ;//- obj.size.y;
			obj.velocity.y = 0;
			obj.jumping = false;
			obj.grounded = true;
		}
	}

}
