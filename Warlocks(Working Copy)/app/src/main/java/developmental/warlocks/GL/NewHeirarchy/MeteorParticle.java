package developmental.warlocks.GL.NewHeirarchy;

import java.util.ArrayList;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.Global;

/**
 * Created by Scott on 20/10/2014.
 */
public class MeteorParticle extends glParticle{
    public MeteorParticle(Vector position, Vector velocity, int lifeSpan, int _mResourceID) {
        super(position, velocity, lifeSpan, _mResourceID, Global.spellSpritesMeteor);
    }
}
