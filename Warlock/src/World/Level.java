
package World;

import java.util.ArrayList;
import java.util.List;

import com.example.warlockgame.RenderThread;

import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

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
		this.sprites = sprites;
		this.paint = new Paint();
		map = new int[][] 	
		{
//			{3,4,3,4,3,4,65,66,66,66,66,66,66,66,66,66,66,66,66,67},
//			{4,3,4,3,4,3,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,0,0,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,1,1,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,1,1,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,1,1,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,1,1,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,1,1,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{0,0,0,0,1,1,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{4,3,4,3,4,3,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{4,4,3,4,3,4,70,71,71,71,71,71,71,71,71,71,71,71,71,72},
//			{4,3,4,3,4,3,75,76,76,76,76,76,76,76,76,76,76,76,76,77}
				{23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23},
				{23,23,9,10,10,10,10,10,11,23,23},
				{23,23,6,6,6,6,6,6,6,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,0,0,0,0,0,0,7,23,23},
				{23,23,5,0,0,0,0,0,0,23,23},
				{23,23,4,4,4,4,4,4,7,23,23},
				{23,23,8,8,8,8,8,8,8,23,23},
				{23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23},
				{23,23,23,23,23,23,23,23,23,23,23},
				
		};
		Setup();
		sprites = null;
	}
	public void Setup()
	{
		float xoffset = RenderThread.size.x/2;
		float yoffset = RenderThread.size.y/2;
		int offsety = 16;
		for (int y = 0; y < map.length; y++)
		{
			for (int x = 0; x < map[y].length; x++)
			{
				float tx = (x* size.x + (y % 2) * size.x / 2);
				float ty = (y* size.y / 2) - 8 * y;
				
				tiles.add(
							new Tile(sprites.tiles.get(map[y][x]), 
								new RectF(
									(tx + xoffset), 
									(ty + yoffset )- offsety * y,
									(tx + xoffset) + size.x, 
									(ty + yoffset - offsety * y) + size.y
								)
							)
						);
			}
		}
	}
	/*public void Draw(Canvas canvas, Vector offset)
	{
			//Vector first = new Vector(),
					//last = new Vector();
		int offsety = 16;
		
		for (int y = 0; y < map.length; y++)
		{
			for (int x = 0; x < map[y].length; x++)
			{
				Vector pos = new Vector(
							(x*size.x + (y % 2) * size.x / 2), 
							(y* size.y / 2) - 8 * y
						);
				//System.out.println(""+sprites.tiles.get(map[y][x]).getConfig());
				canvas.drawBitmap(sprites.tiles.get(map[y][x]), null, 
						new RectF(
								(pos.x + RenderThread.size.x/2 - offset.x), 
								(pos.y + RenderThread.size.y/2- offset.y )- offsety * y,
								(pos.x + RenderThread.size.x/2- offset.x) + size.x, 
								(pos.y + RenderThread.size.y/2- offset.y - offsety * y) + size.y), 
						paint);
				//canvas.drawText(x + "," + y, pos.x+size.x/2, pos.y+size.y/2, paint);
				//if(y == 0 && x ==0)
					//first = pos.get();
				//if(y+1 == map.length  && x+1 == map[y-1].length)
					//last = new Vector(pos.x+size.x,pos.y+size.y);
			}
		}
		//float xtmp = last.y -( map.length * offsety);
		//bounds = new RectF(first.x, first.y,
				//first.x + last.x,
				//first.y + xtmp);
		//paint.setColor(Color.RED);
		//canvas.drawRect(bounds, paint);
	
	}*/
	public void Draw(Canvas canvas, float xoffset ,float yoffset)
	{
		for(int x=0; x < tiles.size(); x++)
		{
			tiles.get(x).DrawAt(canvas, xoffset, yoffset, paint);
		}
	}
	public void setTile()
	{
		
	}

	public Vector onTile(Vector pos)
	{
		if(!bounds.contains(pos.x, pos.y))
			return null;
		int RegionX=(int)((pos.x / bounds.width()) * map[0].length);
		int RegionY=(int)((pos.y / bounds.height()) * map.length);
		
		float realx = iso.getWidth();
		float realy = iso.getHeight();
		float offsetx = (pos.x % size.x);
		float offsety = (pos.x % size.y);
		
		float translatex = size.x > realx ? size.x / realx: realx / size.x;
		float translatey = size.y > realy ? size.y / realy: realy / size.y;
		float toffsetx = offsetx / translatex;
		float toffsety = offsety / translatey;

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

		//else if(RegionY>=0 && RegionX >= 0 && RegionY < map.length && RegionX<map[0].length)
			//map[RegionY][RegionX] = 1;
		
		return new Vector(RegionY,RegionX);
	}
}
