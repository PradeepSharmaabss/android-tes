
package World;

import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

public class Level {
	public int[][] map;
	public SpriteSheet sprites;
	Vector size = new Vector(32,32);
	int Type;
	public RectF bounds;
	public Level(SpriteSheet sprites,Vector v,int _type)
	{
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
		Vector first = new Vector(),last =  new Vector();
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
			for (int i = 0; i < map.length; i++)
			{
				for (int j = 0; j < map[i].length; j++)
				{
					
					Vector pos = new Vector(j*size.x+(i%2)*size.x/2,(i*size.y/2)-8*i);
					canvas.drawBitmap(sprites.tiles.get(map[i][j]), null, 
							new RectF(pos.x, 
									pos.y, 
									(pos.x) + size.x, 
									(pos.y) + size.y), 
							paint);
					canvas.drawText(j + "," + i, pos.x + size.x/2, pos.y+size.y/2, paint);
					if(i==0 && j == 0)
					{
						first = pos.get();
					}
					if(i+1 == map.length && j+1 == map[i].length)
					{
						last = pos.get();
					}
				}
			}
			bounds = new RectF(
					first.x, 
					first.y, 
					first.x + last.x, 
					first.y+last.y
				);
		}
	}
	
	public boolean onTile()
	{
		return false;
	}
}
