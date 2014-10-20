package World;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.warlocks.R;

import Platform.DonutPlatform;
import Platform.EllipticalPlatform;
import Platform.Platform;
import Tools.SpriteSheet;
import Tools.Vector;
import developmental.warlocks.Global;


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
    public Platform iceplatform;
    private LevelShape levelShape = LevelShape.Ellipse;

    public Level(LevelShape _l) {
        levelShape = _l;
        switch (levelShape) {
            case Donut:
                this.platform = new DonutPlatform(new Vector(Global.WORLD_BOUND_SIZE.x / 2, Global.WORLD_BOUND_SIZE.y / 2),
                        new Vector(Global.WORLD_BOUND_SIZE.x / 2 - 300, Global.WORLD_BOUND_SIZE.y / 2 - 150), new Vector(1000, 500),0);
                break;
            case Ellipse:
                this.platform = new EllipticalPlatform(new Vector(Global.WORLD_BOUND_SIZE.x / 2, Global.WORLD_BOUND_SIZE.y / 2),
                        new Vector(Global.WORLD_BOUND_SIZE.x / 2 - 300, Global.WORLD_BOUND_SIZE.y / 2 - 150), R.drawable.platform_main);

                break;
            case Rectangle:
                this.platform = new Platform(new Vector(Global.WORLD_BOUND_SIZE.x / 2, Global.WORLD_BOUND_SIZE.y / 2), new Vector(Global.WORLD_BOUND_SIZE.x / 2 - 300, Global.WORLD_BOUND_SIZE.y / 2 - 150),0);
                break;
        }

        this.size = new Vector(128, 128);
        this.size.y /= 2;
        this.paint = new Paint();
        this.paint.setTextSize(30);
        this.paint.setColor(Color.WHITE);
    }

    float yoff;

   
}
