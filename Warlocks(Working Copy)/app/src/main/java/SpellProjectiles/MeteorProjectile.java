package SpellProjectiles;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.FireParticle;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.NewHeirarchy.MeteorParticle;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

public class MeteorProjectile extends Projectile {
    float height = 400;
    public final int landing = 10;
    Paint Chunks;

    public MeteorProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_meteor,_from, _to, shooter, 110, 4, new Vector(150, 150), 20);
        Chunks = new Paint();
        Chunks.setARGB(255, 85, 64, 64);
//        this.paint.setColor(Color.CYAN);
        this.objectObjectType = Game.ObjectType.Meteor;
        this.velocity = GetVel(_from, _to);
        this.pull = 10;
        this.knockback= 40;
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean b) {
        super.draw(gl, offsetX, offsetY-height, b);
    }

    @Override
    public Vector GetVel(Vector from, Vector to) {
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
        Vector v = new Vector(this.maxVelocity * (distanceX / totalDist),
                this.maxVelocity * distanceY / totalDist);
        this.position = new Vector(to.x - v.x * 100 - size.x / 2, to.y - v.y * 100 - size.y / 2);
        return v;

    }

    boolean landed = false;

    @Override
    protected void setFrames() {
        FramesNoTail();
    }
    @Override
    public void Update() {
        super.Update();
        if (this.height > 0) {
            this.height -= 4;
            SimpleGLRenderer.addParticle(new MeteorParticle(new Vector(this.getCenter().x, this.getCenter().y - height), Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 40, R.drawable.spell_fireball));
        }

        if (this.health < landing) {
            this.velocity = new Vector(0, 0);

            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
            SimpleGLRenderer.addParticle(new FireParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.spell_fireball));
            this.size = new Vector(250, 250);
            bounds.Radius = 125;
            if (!landed) {
                landed = true;
                this.position.x -= 50;
                this.position.y -= 50;
            }
        }
    }



    @Override
    public boolean Intersect(RectF PassedObj) {
        if (this.health == landing)
            return super.Intersect(PassedObj);
        return false;
    }

}