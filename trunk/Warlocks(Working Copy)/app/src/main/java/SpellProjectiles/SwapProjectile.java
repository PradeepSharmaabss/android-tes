package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;


/**
 * Created by Scott on 7/29/13.
 */
public class SwapProjectile extends Projectile {
    public SwapProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_boundsircle,_from, _to, shooter, 100, 15f, new Vector(50, 50), 0);
        this.objectObjectType = ObjectType.SwapProjectile;
    }

    public void Swap(Collideable obj) {
        Vector l;
        l = obj.position;
        obj.position = this.owner.position;
        this.owner.position = l;
        SimpleGLRenderer.delObject(this.id);
    }

}
