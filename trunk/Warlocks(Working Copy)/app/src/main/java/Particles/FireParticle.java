package Particles;

import Particles.glParticle;
import Tools.Vector;
import developmental.warlocks.Global;

/**
 * Created by Scott on 20/10/2014.
 */
public class FireParticle extends glParticle {
    public FireParticle(Vector position, Vector velocity, int lifeSpan, int _mResourceID) {
        super(position, velocity, lifeSpan, _mResourceID, Global.spellSpritesFire);
    }
}
