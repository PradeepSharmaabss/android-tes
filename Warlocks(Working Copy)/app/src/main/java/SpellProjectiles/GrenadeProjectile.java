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

    public GrenadeProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_grenade,_from, _to, shooter, 100, 4, new Vector(150, 150), 20);
        this.velocity=CalculateVelocity(_from,_to);
//        this.paint.setColor(Color.CYAN);
this.objectObjectType = ObjectType.Meteor;
this.height= 0;
        this.pull = 10;
        this.knockback= 40;
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
        if(this.health<=0)
            SimpleGLRenderer.addObject(new ExplosionProjectile(this.bounds.Center.get(),this.bounds.Center.get(),this.owner));
        super.Update();
        this.height+=heightvel;
        if(this.health==50)
            this.heightvel*=-1;

        SimpleGLRenderer.addParticle(new MeteorParticle(new Vector(this.getCenter().x, this.getCenter().y -height), Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 40, R.drawable.particles_meteor));



        }




}
