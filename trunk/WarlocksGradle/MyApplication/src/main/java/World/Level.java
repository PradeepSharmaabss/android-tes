package World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Platform.DonutPlatform;
import Platform.EllipticalPlatform;
import Platform.Platform;
import Tools.SpriteSheet;
import Tools.Vector;
import com.developmental.myapplication.GameThread;
import com.developmental.myapplication.Global;

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

	public Level() {
		switch (levelShape) {
		case Donut:
            this.platform = new DonutPlatform(new Vector(Global.WORLD_BOUND_SIZE.x/2, Global.WORLD_BOUND_SIZE.y/2),
                    new Vector(Global.WORLD_BOUND_SIZE.x/2-300, Global.WORLD_BOUND_SIZE.y/2-150), new Vector(1000, 500));
            break;
		case Ellipse:
			this.platform = new EllipticalPlatform(new Vector(Global.WORLD_BOUND_SIZE.x/2, Global.WORLD_BOUND_SIZE.y/2),
                    new Vector(Global.WORLD_BOUND_SIZE.x/2-300, Global.WORLD_BOUND_SIZE.y/2-150));
			break;
		case Rectangle:
			this.platform = new Platform(new Vector(Global.WORLD_BOUND_SIZE.x/2, Global.WORLD_BOUND_SIZE.y/2), new Vector(Global.WORLD_BOUND_SIZE.x/2-300, Global.WORLD_BOUND_SIZE.y/2-150));
			break;
		}

		this.size = new Vector(128, 128);
		this.size.y /= 2;
		this.paint = new Paint();
		this.paint.setTextSize(30);
		this.paint.setColor(Color.WHITE);
	}

	float yoff;

	public void Draw(Canvas c, float playerx, float playery) {
       // c.drawLine(playerx,playery,this.platform.Position.x,this.platform.Position.y,new Paint());
        c.drawRect(-playerx, -playery
                , Global.WORLD_BOUND_SIZE.x-playerx, Global.WORLD_BOUND_SIZE.y-playery, Global.paint);

        this.platform.Draw(c, playerx, playery);
        GameThread.q.Draw(c,(playerx) ,(playery) );


    }
}
