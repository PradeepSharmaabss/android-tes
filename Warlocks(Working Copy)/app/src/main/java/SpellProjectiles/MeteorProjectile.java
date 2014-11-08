package SpellProjectiles;

import android.graphics.Paint;
import android.util.Log;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import Particles.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import Particles.MeteorParticle;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

public class MeteorProjectile extends Projectile {

    Paint Chunks;

    public MeteorProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_meteor,_from, _to, shooter, 100, 4, new Vector(150, 150), 20);

        Chunks = new Paint();
        Chunks.setARGB(255, 85, 64, 64);
//        this.paint.setColor(Color.CYAN);
        this.objectObjectType = ObjectType.Meteor;
        height = 400;
        this.velocity = GetVel(_from, _to);
        this.pull = 10;
        this.knockback= 40;

    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
    }

    @Override
    public Vector GetVel(Vector from, Vector to) {
        to= to.add(new Vector(0,size.y/2));
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
        Vector v = new Vector(this.maxVelocity * (distanceX / totalDist),
                this.maxVelocity * distanceY / totalDist);
        this.position = new Vector(to.x - v.x * 100, to.y - v.y * 100 );
        return v;

    }


    @Override
    protected void setFrames() {
        FramesNoTail();
    }
    @Override
    public void Update() {
        if (this.health <=0) {
            SimpleGLRenderer.addObject(new ExplosionProjectile(this.bounds.Center.get(),this.bounds.Center.get(),this.owner));

        }
        super.Update();
        if (this.height > 0) {
            this.height -= 4;
            SimpleGLRenderer.addParticle(new MeteorParticle(new Vector(this.getCenter().x, this.getCenter().y -height), Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 40, R.drawable.particles_meteor));
        }


    }



}
