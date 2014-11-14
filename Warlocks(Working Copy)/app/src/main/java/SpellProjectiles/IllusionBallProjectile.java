package SpellProjectiles;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 31/12/13.
 */
public class IllusionBallProjectile extends Projectile {


    public IllusionBallProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_firespray, _from, _to, _shooter, 1000, 5f, new Vector(100, 100), 10);

    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        if (lifePhase > 100) {
            if (Vector.DistanceBetween(SimpleGLRenderer.archie.velocity, new Vector(0, 0)) == 0)
                super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
        } else {
            super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
        }
    }
}
