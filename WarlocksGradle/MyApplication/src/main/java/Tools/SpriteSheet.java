package Tools;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;

import com.developmental.myapplication.Global;

public class SpriteSheet {
    public Bitmap bmp;
    public Vector size = new Vector(64, 64);
    public ArrayList<Bitmap> tiles = new ArrayList<Bitmap>();
    int w ;
    int h;
    public void setBmp(Bitmap bmp)
    {
        this.bmp = bmp;

        Load();
        bmp.recycle();
        this.bmp.recycle();
        Global.Sprites.add(this.tiles);
    }
    public void setBmpBtn(Bitmap bmp)
    {
        this.bmp = bmp;

        Load();
        bmp.recycle();
        this.bmp.recycle();
        Global.ButtonImages.add(this.tiles.get(this.tiles.size()-1));
    }
    public SpriteSheet(Bitmap bmp, int _w, int _h) {
        this.bmp = bmp;
        w = _w;
        h = _h;
        this.size.x = bmp.getWidth() / _w;//FOR SMALL RESOLUTIONS* 2;
        this.size.y = bmp.getHeight() / _h;//FOR SMALL RESOLUTIONS* 2;
        Load();
        // test dynamic sizes;
        // size.x = (int)(bmp.getWidth() / 4);
        // size.y = (int)(bmp.getHeight() / 6);
        // Load(size);
        // LoadScaleIntoHolder(size);
    }

    public void Load() {

        this.size.x = bmp.getWidth() / w;//FOR SMALL RESOLUTIONS* 2;
        this.size.y = bmp.getHeight() / h;//FOR SMALL RESOLUTIONS* 2;
        tiles = new ArrayList<Bitmap>();
        int x, y;
        //this.size = new Vector(32,32);
        for (y = 0; y < this.bmp.getHeight(); y += this.size.y) {
            for (x = 0; x < this.bmp.getWidth(); x += this.size.x) {
                // Log.e("THIS ISNT WORKING","WORKING NOW?"+x+" " + this.size.x+ " " + this.bmp.getWidth());
                this.tiles.add(Bitmap.createBitmap(
                        this.bmp, x, y, (int) this.size.x, (int) this.size.y));

                if (x + this.size.x > this.bmp.getWidth())
                    break;
            }
            if (y + this.size.y > this.bmp.getHeight())
                break;
        }
    }

    public void LoadScaleIntoHolder(Vector bmpSize) {
        int x, y;
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
