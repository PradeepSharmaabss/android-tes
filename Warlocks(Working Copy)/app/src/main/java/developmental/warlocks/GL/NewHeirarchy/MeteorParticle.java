package developmental.warlocks.GL.NewHeirarchy;



import Tools.Vector;
import developmental.warlocks.Global;

/**
 * Created by Scott on 20/10/2014.
 */
public class MeteorParticle extends glParticle{
    public MeteorParticle(Vector position, Vector velocity, int lifeSpan, int _mResourceID) {
        super(position, velocity, lifeSpan, _mResourceID, Global.spellSpritesMeteor);
    }
    @Override
    protected void Rotate() {
        if(this.velocity!=null)
            rotation = 270;
    }
}
