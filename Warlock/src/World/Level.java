
package World;

import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;
import com.example.warlockgame.Global;

import Platform.DonutPlatform;
import Platform.Platform;
import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
public class Level {
	public int[][] map;
	public Bitmap tileCorners;
	public Paint paint;
	public SpriteSheet sprites;
	Vector size = new Vector(32,16);
	public RectF bounds = new RectF();
	public Vector position = new Vector(0,0);
	Bitmap bbuffer;
	public List<Tile> tiles = new ArrayList<Tile>();
	public Platform platform;
	public Level(SpriteSheet sprites, Vector v, Bitmap iso)
	{
		platform = new DonutPlatform(
					new Vector(2800,900), 
					new Vector(2500,1250), 
					new Vector(250,125)
				);

		this.size = v;
		this.size = new Vector(128, 128);
		this.size.y /= 2;
		//this.size = new Vector(200,200);
		this.sprites = sprites;
		this.paint = new Paint();
		this.tileCorners = Bitmap.createScaledBitmap(iso, (int)size.x, (int)size.y, false);
		
		map = new int[][] 	
		{
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
			{3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3},
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
		Global.tiles.clear();
		sprites.LoadScaleIntoHolder(size);
		yoff = (size.y / 4) + (size.y / 10);
		float xoffset = RenderThread.size.x / 2;
		float yoffset = RenderThread.size.y / 2;
		int mapheight = map.length;
		int mapwidth = map[0].length;
		
		for (int y = 0; y < mapheight; y++)
		{
			for (int x = 0; x < mapwidth; x++)
			{
				float tx = (x * size.x + (y % 2) * size.x / 2);
				float ty = (y *  size.y) - ((size.y/4) * y);
				
				tiles.add(
							new Tile(Global.tiles.get(map[y][x]), 
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
		//c.save();
		//c.translate(-playerx, -playery);
//		Vector v = onTile(
//				new Vector(
//					RenderThread.archie.position.x + RenderThread.archie.size.x / 2, 
//					RenderThread.archie.rect.bottom)
//				);
//		
//		int	px = (int)v.x, 
//			py = (int)v.y;
//		v = null;
//		int radius = 10;
//		
//		int mlength = map[0].length;
	
//		for( int y = py - radius * 2+2; y < py + radius; y++)
//		{
//			for( int x = px - radius ; x < px + radius; x++)
//			{
//				int calc = x + (y * mlength);
//				if(calc >= 0 && calc < tiles.size())
//					tiles.get(calc).DrawAt(c, playerx, playery, paint);
//			}
//		}
		
		
		
		
		
		//new Tile(Global.tiles.get(0), tiles.get(px + (py*mlength)).rect).DrawAt(c, playerx, playery, paint);
		//tiles.get(px + (py*mlength)).DrawAt(c, playerx, playery, paint);
		c.save();
		c.translate(RenderThread.size.x/2, RenderThread.size.y/2);
		platform.Draw(c);
		paint.setStrokeWidth(4);
//TestEllipse
		c.restore();
		
		
		//c.restore();
		
	}
private void TestEllipse(Canvas c)
{
	for(int i = 0; i<100;i++)
	{
	
		for(int j = 0; j<100;j++)
		{
		if(platform.Within(new Vector(2200+i*8,500+j*8)))
		{
		paint.setColor(Color.BLUE);
		}
		else
			paint.setColor(Color.WHITE);
		c.drawPoint(2200+i*8, 500+j*8, paint);
		}
	}
}

	public int[] etiles = null;
	public int timer = 50;
	
	public void Shrink()
	{
		//logic : find first 0 , continue until find last 3 then replace with 0
		int mwidth = map[0].length;
		int mheight = map.length;
		for(int x=0;x<2;x++)
		{
			for(int i = 0; i < mwidth;i++)
			{
				for(int j = 0;j< mheight;j++)
				{
					if(map[j][i] == 0)
					{
						map[j][i] = 3;
						tiles.get(i + (j * mwidth)).bitmap = Global.tiles.get(map[j][i]);
						break;
					}
				}
			}
		}
	}
	public void Earthquake(float playerx, float playery)
	{
		if(timer < 50)
		{
			//sprites.tiles.remove(3);
			//sprites.tiles.get(3) = TileHolder.tiles.get(0);
			if(etiles == null)//create Earthquake tiles.
			{
				etiles = new int[100];
				//Shrink();
				int mwidth = map[0].length;
				Vector v = onTile(
						new Vector(
							RenderThread.archie.position.x + RenderThread.archie.size.x / 2, 
							RenderThread.archie.rect.bottom)
						);
				
				int px = (int)v.x, py = (int)v.y;
				v = null;
				int radius = 5;
				int ctr = 0;
				for( int y = py - radius *2 ; y < py + radius; y++)
				{
					for( int x = px - radius/2 ; x < px + radius/2; x++)
					{
						int calc = x + (y * mwidth);
						if(calc >= 0 && calc < tiles.size())
						{
							tiles.get(calc).earthquake = true;
							etiles[ctr++] = calc;
						}
					}
				}
			}
			timer++;
		}
		else if(etiles != null)
		{
			for(int x=0;x<etiles.length;x++)
				tiles.get(etiles[x]).earthquake = false;
			etiles = null;
		}
			
	}
	public Vector onTile(Vector pos)
	{
		//if(!bounds.contains(pos.x, pos.y))
			//return null;
		//Log.d("y" + pos.y, "SSSS");
		int RegionX=(int)(pos.x / (size.x));
		//Log.d("", size.y+"");
		int RegionY=(int)(pos.y / (size.y  / (2.45f)));// need to figure out this logic , the height should not need to be halfed to get the current tile.
		//float temp = RegionY * yoff;
		//RegionY = (int)(RegionY + temp);
		//Log.d("RegionY" + RegionY, "SSSS");

		if(RegionX > 0 && RegionY > 0)
		{
			int pixel = tileCorners.getPixel((int)(pos.x % size.x), (int)(pos.y % size.y));
			if(pixel == tileCorners.getPixel(0,0))//red left
			{
				RegionY -= 1;
				RegionX -= 1;
			}
			else if(pixel == tileCorners.getPixel(tileCorners.getWidth()-1,0))//yellow right
			{
				RegionY -= 1;
				RegionX += 1;
			}
			else if(pixel == tileCorners.getPixel(tileCorners.getWidth()-1,tileCorners.getHeight()-1))//blue botright
			{
				RegionY += 1;
			}
			else if(pixel == tileCorners.getPixel(0, tileCorners.getHeight()-1))//green botleft
			{
				RegionY += 1;
				RegionX -= 1;
			}
		}
		//else if(RegionY>=0 && RegionX >= 0 && RegionY < map.length && RegionX<map[0].length)
			//map[RegionY][RegionX] = 22;
		return new Vector(RegionX > 0 ? RegionX: 0 ,RegionY > 0 ? RegionY: 0);
	}

}
