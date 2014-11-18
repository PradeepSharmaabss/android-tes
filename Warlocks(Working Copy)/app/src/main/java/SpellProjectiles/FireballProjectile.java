package SpellProjectiles;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

public class FireballProjectile extends Projectile {

    public FireballProjectile(Vector _from, Vector _to, GameObject _shooter, int rank) {
        super(R.drawable.spell_fireball,_from, _to, _shooter,rank);
        this.archetypePower = new ArchetypePower(0,100,0,0,0,0,0);
    }
@Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 100;
                this.knockback =7;
                this.size = new Vector(50,50);
                this.damagevalue = 6;

                break;
            case 2:
                this.health = 110;
                this.knockback =8.5;
                this.size = new Vector(50,50);
                this.damagevalue = 7;
                break;
            case 3:
                this.health = 120;
                this.knockback =10;
                this.size = new Vector(50,50);
                this.damagevalue = 8;
                break;
            case 4:
                this.health = 130;
                this.knockback =11.5;
                this.size = new Vector(50,50);
                this.damagevalue = 9;
                break;
            case 5:
                this.health = 140;
                this.knockback =13;
                this.size = new Vector(60,60);
                this.damagevalue = 10;
                break;
            case 6:
                this.health = 150;
                this.knockback =14.5;
                this.size = new Vector(60,60);
                this.damagevalue = 11;
                break;
            case 7:
                this.health = 160;
                this.knockback =16;
                this.size = new Vector(60,60);
                this.damagevalue = 12;
                break;
        }


    }

    @Override
    public void Update() {

        super.Update();
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity, 0.5f), 10, R.drawable.spell_fireball));
        SimpleGLRenderer.addParticle(new FireParticle(this.bounds.Center, Vector.multiply(this.velocity,0.5f), 10,  R.drawable.spell_fireball));

        // SimpleGLRenderer.addParticle(new Particle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()),10));


    }



}
