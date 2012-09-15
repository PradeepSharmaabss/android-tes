
package World;

import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

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
	}

	public void Draw(Canvas canvas, Paint paint)
	{
		Vector first = new Vector(),
				last = new Vector();
		int offsety = 16;
		if(Type == 1)
		{
			for (int y = 0; y < map.length; y++)
			{
				for (int x = 0; x < map[y].length; x++)
				{
					Vector pos = new Vector(x*size.x+(y%2)*size.x/2,(y*size.y/2)-8*y);
					canvas.drawBitmap(sprites.tiles.get(map[y][x]), null, 
							new RectF(pos.x, 
									pos.y - offsety * y,
									(pos.x) + size.x, 
									(pos.y- offsety * y) + size.y), 
							paint);
					//canvas.drawText(x + "," + y, pos.x+size.x/2, pos.y+size.y/2, paint);
					if(y == 0 && x ==0)
						first = pos.get();
					if(y+1 == map.length  && x+1 == map[y-1].length)
						last = new Vector(pos.x+size.x,pos.y+size.y);
				}
			}
			float xtmp = last.y -( map.length * offsety);
			bounds = new RectF(first.x, first.y,
					first.x + last.x,
					first.y + xtmp);
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
