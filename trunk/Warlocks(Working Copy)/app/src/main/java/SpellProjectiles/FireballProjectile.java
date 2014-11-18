package SpellProjectiles;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

public class FireballProjectile extends Projectile {

    public FireballProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_fireball,_from, _to, _shooter, 100, 20f, new Vector(50, 50), 10);
        this.archetypePower = new ArchetypePower(0,100,0,0,0,0,0);
    }



    @Override
    public void Update() {

        super.Update();
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, 0.5f), 10, R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity,0.5f), 10,  R.drawable.spell_fireball));

        // SimpleGLRenderer.addParticle(new Particle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()),10));


    }



}
