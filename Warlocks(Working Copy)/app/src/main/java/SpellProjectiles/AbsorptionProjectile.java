package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 2/01/14.
 */
public class AbsorptionProjectile extends Projectile {
    private int projectiles = 0;
    public AbsorptionProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_grenade,_from, _to, _shooter, 5000, 4, new Vector(150, 150), 1);
        this.FramesNoTail();
        this.objectObjectType= ObjectType.Absorb;
    }
    public void Absorb(Collideable g)
    {
        projectiles+=1;
        this.bounds.Radius+=g.bounds.Radius;
       this.size=this.size.add(new Vector(g.bounds.Radius*2,g.bounds.Radius*2));
        this.FramesNoTail();
        SimpleGLRenderer.delObject(g.id);
    }

}
