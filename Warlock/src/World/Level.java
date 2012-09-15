
package World;

import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

public class Level {
	public int[][] map;
	public Bitmap iso;
	
	public SpriteSheet sprites;
	Vector size = new Vector(32,32);
	int Type;
	public RectF bounds = new RectF();
	public Level(SpriteSheet sprites, Vector v, int _type, Bitmap iso)
	{
		this.iso = iso;
		Type = _type;
		this.size = v;
		this.sprites= sprites;
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
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0},
				{0,0,0,0,0,0,0,0,0,0,0}
					
				
		};
	}


	public void Draw(Canvas canvas, Paint paint)
	{
		Vector first = new Vector(),
				last = new Vector();
		if(Type == 2)
		{
			for (int i = 0; i < map.length; i++)
			{
				for (int j = 0; j < map[i].length; j++)
				{
					canvas.drawBitmap(sprites.tiles.get(map[i][j]), null, 
							new RectF(j * size.x , 
									i * size.y, 
									(j * size.x) + size.x, 
									(i * size.y) + size.y), 
							paint);
				}
			}
		}
		if(Type == 1)
		{
			for (int y = 0; y < map.length; y++)
			{
				for (int x = 0; x < map[y].length; x++)
				{
					Vector pos = new Vector(x*size.x+(y%2)*size.x/2,(y*size.y/2)-8*y);
					canvas.drawBitmap(sprites.tiles.get(map[y][x]), null, 
							new RectF(pos.x, 
									pos.y, 
									(pos.x) + size.x, 
									(pos.y) + size.y), 
							paint);
					//canvas.drawText(x + "," + y, pos.x+64, pos.y+32, paint);
					if(y == 0 && x ==0)
						first = pos.get();
					if(y+1 == map.length  && x+1 == map[y-1].length)
						last = new Vector(pos.x+size.x,pos.y+size.y);
				}
			}
			bounds = new RectF(first.x, first.y,
					first.x + last.x,
					first.y + last.y);
			//paint.setColor(Color.RED);
			//canvas.drawRect(bounds, paint);
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
		int pixel = iso.getPixel((int)pos.x % 64,(int)pos.y % 64);
		
		if(pixel == iso.getPixel(0,0))
		{
			RegionY -=1;
		
		}
		else if(pixel == iso.getPixel(iso.getWidth()-1,0))
		{
			RegionY -=1;
			
		}
		else if(pixel == iso.getPixel(iso.getWidth()-1,39))
		{
			RegionY +=1;
			RegionY+=1;
		
		}
		else if(pixel == iso.getPixel(0,39))
		{
			RegionY +=1;
			
			//RegionX-=1;
		
		}
		/*if(pixel == iso.getPixel(63,63))
		{
			RegionY +=2;
			Log.d("Green", pixel + "");
			//RegionX-=1;
		
		}*/
		if(RegionY>=0&&RegionX>=0&&RegionY<map.length&&RegionX<map[0].length)
			map[RegionY][RegionX] = 1;
		
		return new Vector(RegionY,RegionX);
	}
}
