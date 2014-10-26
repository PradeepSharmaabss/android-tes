package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;


/**
 * Created by Scott on 7/29/13.
 */
public class IceProjectile extends Projectile {
    public IceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_iceball,_from, _to, shooter, 100, 10, new Vector(50, 50), 6);

        this.objectObjectType = ObjectType.IceSpell;
    }



    @Override
    public void Update() {
        super.Update();
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, Global.GetRandomNumer.nextFloat()), 10, R.drawable.spell_iceball));
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, Global.GetRandomNumer.nextFloat()), 10, R.drawable.spell_iceball));
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, Global.GetRandomNumer.nextFloat()), 10,  R.drawable.spell_iceball));
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, Global.GetRandomNumer.nextFloat()), 10,  R.drawable.spell_iceball));
    }

}
