package Tools;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import com.developmental.warlocks.Global;

public class SpriteSheet {
	Bitmap bmp;
	public Vector size = new Vector(64, 64);
	public List<Bitmap> tiles = new ArrayList<Bitmap>();

	public SpriteSheet(Bitmap bmp,int _w, int _h) {
		this.bmp = bmp;
		this.size.x = bmp.getWidth()/_w;//FOR SMALL RESOLUTIONS* 2;
		this.size.y = bmp.getHeight()/_h;//FOR SMALL RESOLUTIONS* 2;
		// test dynamic sizes;
		// size.x = (int)(bmp.getWidth() / 4);
		// size.y = (int)(bmp.getHeight() / 6);
		// Load(size);
		// LoadScaleIntoHolder(size);
	}

	public void Load(Vector bmpSize) {
		int x = 0, y;
        //this.size = new Vector(32,32);
		for (y = 0; y < this.bmp.getHeight(); y += this.size.y) {
			for (x = 0; x < this.bmp.getWidth(); x += this.size.x) {
                Log.d(""+this.bmp.getWidth()+","+this.size.x,"LOOKIE HERE");
				this.tiles.add(Bitmap.createScaledBitmap(Bitmap.createBitmap(
                        this.bmp, x, y, (int) this.size.x, (int) this.size.y),
                        (int) bmpSize.x, (int) bmpSize.y, false));

				if (x + this.size.x > this.bmp.getWidth())
					break;
			}
			if (y + this.size.y > this.bmp.getHeight())
				break;
		}
	}

	public void LoadScaleIntoHolder(Vector bmpSize) {
		int x = 0, y;
		for (y = 0; y < this.bmp.getHeight(); y += this.size.y) {
			for (x = 0; x < this.bmp.getWidth(); x += this.size.x) {
				// tiles.add(Bitmap.createScaledBitmap(Bitmap.createBitmap(bmp,
				// x, y, (int)size.x ,(int)size.y),(int)size.x, (int)size.y,
				// false));
				Global.tiles.add(Bitmap.createScaledBitmap(Bitmap.createBitmap(
						this.bmp, x, y, (int) this.size.x, (int) this.size.y),
						(int) bmpSize.x, (int) bmpSize.y, false));
				if (x + this.size.x > this.bmp.getWidth())
					break;
			}
			if (y + this.size.y > this.bmp.getHeight())
				break;
		}
	}
}
