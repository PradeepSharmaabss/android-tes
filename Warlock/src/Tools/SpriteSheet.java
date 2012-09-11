package Tools;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class SpriteSheet {
	Bitmap bmp;
	public int size = 64;
	public int offsetx =0,offsety = 0;//offset in tiles
	public List<Bitmap> tiles = new ArrayList<Bitmap>();
	public SpriteSheet(Bitmap bmp, int tileSize, int offsetx)
	{
		this.offsetx = offsetx;
		this.bmp = bmp;
		size = tileSize;
		Load(size);
	}
	public SpriteSheet(Bitmap bmp, int tileSize)
	{
		this.bmp = bmp;
		size = tileSize;
		Load(size);
	}
	public void Load(int size)
	{
		for(int y = offsety * size;y < bmp.getHeight(); y += size)
		{
			for(int x = offsetx * size; x < bmp.getWidth(); x += size)
			{
				tiles.add(Bitmap.createBitmap(bmp, x, y, size ,size));
			}
		}
	}
}
