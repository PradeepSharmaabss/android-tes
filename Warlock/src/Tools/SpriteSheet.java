package Tools;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class SpriteSheet {
	Bitmap bmp;
	public int size = 64;
	public List<Bitmap> tiles = new ArrayList<Bitmap>();
	public SpriteSheet(Bitmap bmp, int tileSize)
	{
		this.bmp = bmp;
		size = tileSize*2;
		Load(size);
	}
	public void Load(int size)
	{
		int x=0,y;
		for(y = 0;y < bmp.getHeight(); y += size)
		{
			for(x = 0;x < bmp.getWidth(); x += size)
			{
				tiles.add(Bitmap.createBitmap(bmp, x, y, size ,size));
				if(x+size > bmp.getWidth())
				{
					break;
				}
			}
			if(y+size > bmp.getHeight())
			{
				break;
			}
			
		}
	}
	
}
