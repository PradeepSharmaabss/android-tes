package World;

import com.developmental.warlocks.R;

import Platform.DonutPlatform;
import Platform.EllipticalPlatform;
import Platform.Platform;
import Scene.Mission;
import Tools.Vector;
import developmental.warlocks.Global;


public class Level {
    public enum LevelShape {
        Rectangle, Ellipse, Donut
    }

    public Platform platform;
    public Platform iceplatform;
    private LevelShape levelShape = LevelShape.Ellipse;
    public Mission mission;

    public Level(LevelShape _l, Mission _m) {
        levelShape = _l;
        switch (levelShape) {
            case Donut:
                this.platform = new DonutPlatform(new Vector(Global.WORLD_BOUND_SIZE.x / 2, Global.WORLD_BOUND_SIZE.y / 2),
                        new Vector(Global.WORLD_BOUND_SIZE.x / 2 - 300, Global.WORLD_BOUND_SIZE.y / 2 - 150), new Vector(1000, 500), R.drawable.level_platform_round);
                break;
            case Ellipse:
                this.platform = new EllipticalPlatform(new Vector(Global.WORLD_BOUND_SIZE.x / 2, Global.WORLD_BOUND_SIZE.y / 2),
                        new Vector(Global.WORLD_BOUND_SIZE.x / 2 - 300, Global.WORLD_BOUND_SIZE.y / 2 - 150), R.drawable.level_platform_round);

                break;
            case Rectangle:
                this.platform = new Platform(new Vector(Global.WORLD_BOUND_SIZE.x / 2, Global.WORLD_BOUND_SIZE.y / 2), new Vector(Global.WORLD_BOUND_SIZE.x / 2 - 300, Global.WORLD_BOUND_SIZE.y / 2 - 150),R.drawable.level_platform_rect);
                break;
        }
      this.mission = _m;

}   public enum status{Passed, Failed, NA}
    public status Update()
    {
        this.platform.Shrink();
        if(this.mission!=null) {
            if (this.mission.Failed()) {
                //Failed Logic
                return status.Failed;
            }
            if (this.mission.Done()) {
                //Succeed logic
                return status.Passed;
            }

        }
        return status.NA;
    }


   
}
