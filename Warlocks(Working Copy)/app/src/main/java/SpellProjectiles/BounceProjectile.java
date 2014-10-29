package SpellProjectiles;

import android.graphics.Canvas;
import android.util.Log;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 28/08/13.
 */
public class BounceProjectile extends Projectile {
    public BounceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_boomerang,_from, _to, shooter, 100, 20f, new Vector(50, 50), 10);
        health = 300;
        this.maxVelocity = 40;
        this.size = new Vector(30, 30);
        SetVelocity(maxVelocity);
        lastTarget = owner;
        objectObjectType = ObjectType.Bounce;
        this.damagevalue = 4;
    }

   public int bounces = 3;
    public Collideable lastTarget = null;

    GameObject CurrentTarget = null;

    public void findNewTarget() {
        CurrentTarget = null;
        float minD = 10000;

        for (GameObject p : SimpleGLRenderer.players) {
            if (p.id != owner.id) {
                if ((lastTarget == null) || (lastTarget.id != p.id)) {
                    float totalDist = Vector.DistanceBetween(this.bounds.Center,p.bounds.Center);
                    if (totalDist < minD) {
                        minD = totalDist;
                        CurrentTarget = p;
                        Log.d("INET", "TARGET SET TO" + CurrentTarget.id);
                    }
                }
            }
        }


        this.velocity = this.GetVel(this.position, CurrentTarget.position);
        SetVelocity(maxVelocity);
        Log.d("INET", "TARGET SET TO" + CurrentTarget.feet.x + "," + CurrentTarget.feet.y);

    }

}
