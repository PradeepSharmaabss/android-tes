package Tools;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class SpriteSheet {
	Bitmap bmp;
	public Vector size = new Vector(64,64);
	public List<Bitmap> tiles = new ArrayList<Bitmap>();
	public SpriteSheet(Bitmap bmp, Vector tileSize)
	{
		this.bmp = bmp;
		size.x = tileSize.x * 2;
		size.y = tileSize.y * 2;
		//test dynamic sizes;
		//size.x = (int)(bmp.getWidth() / 4);
		//size.y = (int)(bmp.getHeight() / 6);
		Load(size);
	}
	public void Load(Vector size)
	{
		int x=0,y;
		for (y = 0; y < bmp.getHeight(); y += size.y)
		{
			for (x = 0; x < bmp.getWidth(); x += size.x)
			{
				tiles.add(Bitmap.createBitmap(bmp, x, y, (int)size.x ,(int)size.y));
				if(x+size.x > bmp.getWidth())
				{
					break;
				}
			}
			if(y+size.y > bmp.getHeight())
			{
				break;
			}
			
		}
	}
	
}
