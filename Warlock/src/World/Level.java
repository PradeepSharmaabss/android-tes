package World;

import Platform.DonutPlatform;
import Platform.EllipticalPlatform;
import Platform.Platform;
import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.example.warlockgame.RenderThread;

public class Level {
    public enum LevelShape {
        Rectangle, Ellipse, Donut
    }

    public int[][] map;
	public Bitmap tileCorners;
	public Paint paint;
	public SpriteSheet sprites;
	Vector size = new Vector(32, 16);
	public RectF bounds = new RectF();
	public Vector position = new Vector(0, 0);
	Bitmap bbuffer;
	public Platform platform;

	public static LevelShape levelShape = LevelShape.Ellipse;

	public Level(SpriteSheet _sprites, Vector _size, Bitmap iso) {
		switch (levelShape) {
		case Donut:
            this.platform = new DonutPlatform(new Vector(2800, 900),
                    new Vector(2500, 1250), new Vector(1000, 500));
            break;
		case Ellipse:
			this.platform = new EllipticalPlatform(new Vector(2800, 900),
					new Vector(2500, 1250));
			break;
		case Rectangle:
			this.platform = new Platform(new Vector(2800, 900), new Vector(
					2500, 1250));
			break;
		}

		this.size = _size;
		this.size = new Vector(128, 128);
		this.size.y /= 2;
		this.sprites = _sprites;
		this.paint = new Paint();
		this.paint.setTextSize(30);
		this.paint.setColor(Color.WHITE);
		_sprites = null;
	}

	float yoff;

	public void Draw(Canvas c, float playerx, float playery) {
       // c.drawLine(playerx,playery,this.platform.Position.x,this.platform.Position.y,new Paint());
		this.platform.Draw(c,playerx,playery);

	}
}
