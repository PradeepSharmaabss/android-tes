package SpellProjectiles;

import android.graphics.Paint;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import Particles.MeteorParticle;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

public class GrenadeProjectile extends Projectile {

    int heightvel = 5;

    public GrenadeProjectile(Vector _from, Vector _to, GameObject shooter,int Rank) {
        super(R.drawable.spell_grenade,_from, _to, shooter,Rank);
        this.velocity=CalculateVelocity(_from,_to);
//        this.paint.setColor(Color.CYAN);
this.objectObjectType = ObjectType.Meteor;
this.height= 0;
        this.pull = 10;
        this.knockback= 40;
    }
    @Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;

                break;
            case 2:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;
                break;
            case 3:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;
                break;
            case 4:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;
                break;
            case 5:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;
                break;
            case 6:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;
                break;
            case 7:
                this.health = 100;
                this.knockback =30;
                this.size = new Vector(150,150);
                this.damagevalue = 15;
                break;
        }


    }

    private Vector CalculateVelocity(Vector from, Vector to) {
        Vector v = new Vector();
        v.x = (to.x-from.x)/100;
        v.y =(to.y-from.y)/100;
        return v;
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
    }


    @Override
    protected void setFrames() {
        FramesNoTail();
    }
    @Override
    public void Update() {
        if(this.health%10==1)
            this.heightvel-=1;
        if(this.health<=0)
            SimpleGLRenderer.addObject(new ExplosionProjectile(0,this.bounds.Center.get(), this.owner, new Vector(200, 200),5,3));
        super.Update();
        this.height+=heightvel;


        SimpleGLRenderer.addParticle(new MeteorParticle(new Vector(this.getCenter().x, this.getCenter().y -height), Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 40, R.drawable.particles_meteor));



        }




}
