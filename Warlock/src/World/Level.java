
package World;

import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;
import com.example.warlockgame.Test;
import com.example.warlockgame.TileHolder;

import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Level {
	public int[][] map;
	public Bitmap iso;
	public Paint paint;
	public SpriteSheet sprites;
	Vector size = new Vector(32,16);
	public RectF bounds = new RectF();
	public Vector position = new Vector(0,0);
	Bitmap bbuffer;
	public List<Tile> tiles = new ArrayList<Tile>();
	
	public Level(SpriteSheet sprites, Vector v, Bitmap iso)
	{
		this.iso = iso;
		this.size = v;
		this.size = new Vector(128, 128);
		this.size.y /= 2;
		//this.size = new Vector(200,200);
		this.sprites = sprites;
		this.paint = new Paint();
		
		map = new int[][] 	
		{
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},

		};
		paint.setTextSize(30);
		paint.setColor(Color.WHITE);
		Setup();
		sprites = null;
	}
	float yoff;
	public void Setup()
	{
		TileHolder.tiles.clear();
		for(Bitmap b: sprites.tiles)
			TileHolder.tiles.add(Bitmap.createScaledBitmap(b,(int)size.x, (int)size.y,false));
		yoff = (size.y / 4) + (size.y / 16);
		float xoffset = RenderThread.size.x / 2;
		float yoffset = RenderThread.size.y / 2;
		for (int y = 0; y < map.length; y++)
		{
			for (int x = 0; x < map[y].length; x++)
			{
				float tx = (x * size.x + (y % 2) * size.x / 2);
				float ty = (y *  size.y) - ((size.y/4) * y);
				
				tiles.add(
							new Tile(TileHolder.tiles.get(map[y][x]), 
								new RectF(
									(tx + xoffset), 
									(ty + yoffset ) - (yoff * y),
									(tx + xoffset) + size.x, 
									(ty + yoffset - (yoff * y)) + size.y
								)
							)
						);
			}
		}
		sprites.tiles = null;
		sprites = null;
	}
	public void Draw(Canvas c, float playerx, float playery)
	{
		Vector v = onTile(
				new Vector(
					RenderThread.archie.position.x + RenderThread.archie.size.x / 2, 
					RenderThread.archie.rect.bottom)
				);
		
		int px = (int)v.x, py = (int)v.y;
		v = null;
		int radius = 8;
		
		int mlength = map[0].length;
	
		for( int y = py - radius * 2; y < py + radius; y++)
		{
			for( int x = px - radius ; x < px + radius; x++)
			{
				int calc = x + (y * mlength);
				if(calc >= 0 && calc < tiles.size())
					tiles.get(calc).DrawAt(c, playerx, playery, paint);
			}
		}
	}

	public Vector onTile(Vector pos)
	{
		//if(!bounds.contains(pos.x, pos.y))
			//return null;
		//Log.d("y" + pos.y, "SSSS");
		int RegionX=(int)(pos.x / size.x);
		int RegionY=(int)(pos.y / (size.y  / (2.30f)));
		//float temp = RegionY * yoff;
		//RegionY = (int)(RegionY + temp);
		//Log.d("RegionY" + RegionY, "SSSS");
		float realx = iso.getWidth();
		float realy = iso.getHeight();
		float offsetx = (pos.x % size.x);
		float offsety = (pos.y % size.y);
		
		float translatex = size.x > realx ? size.x / realx: realx / size.x;
		float translatey = size.y > realy ? size.y / realy: realy / size.y;
		float toffsetx = offsetx / translatex;
		float toffsety = offsety / translatey;

		try
		{
			int pixel = iso.getPixel((int)(toffsetx), (int)(toffsety));
			if(pixel == iso.getPixel(0,0))//red left
			{
				RegionY -= 1;
				RegionX -= 1;
			}
			else if(pixel == iso.getPixel(iso.getWidth()-1,0))//yellow right
			{
				RegionY -= 1;
			}
			else if(pixel == iso.getPixel(iso.getWidth()-1,iso.getHeight()-1))//blue botright
			{
				RegionY += 1;
			}
			else if(pixel == iso.getPixel( 0, iso.getHeight()-1))//green botleft
			{
				RegionY += 1;
				RegionX -= 1;
			}
		}
		catch(Exception ex)
		{
			System.out.println("out of range");
		}
		//else if(RegionY>=0 && RegionX >= 0 && RegionY < map.length && RegionX<map[0].length)
			//map[RegionY][RegionX] = 22;
		return new Vector(RegionX > 0 ? RegionX: 0 ,RegionY > 0 ? RegionY: 0);
	}

}
