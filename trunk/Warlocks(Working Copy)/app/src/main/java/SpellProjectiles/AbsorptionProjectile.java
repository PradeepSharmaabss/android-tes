package SpellProjectiles;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 2/01/14.
 */
public class AbsorptionProjectile extends GravityProjectile {
    private int projectiles = 0;
    public AbsorptionProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(_from, _to, _shooter);
        this.bounds.Radius=20;
        this.objectObjectType= ObjectType.Absorb;
    }
    public void Absorb(GameObject g)
    {
        projectiles+=1;
        this.bounds.Radius+=g.bounds.Radius;
        SimpleGLRenderer.delObject(g.id);
    }

}
