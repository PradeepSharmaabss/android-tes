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


    }


   
}
