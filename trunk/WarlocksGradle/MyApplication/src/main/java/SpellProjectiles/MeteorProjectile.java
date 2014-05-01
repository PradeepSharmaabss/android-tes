package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Particles.Particle;
import Tools.Vector;

import com.developmental.myapplication.GL.NewHeirachy.glParticle;
import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;
import com.developmental.myapplication.GL.SimpleGLRenderer;

import javax.microedition.khronos.opengles.GL10;

public class MeteorProjectile extends Projectile {
    float height = 400;
    public final int landing = 10;
    Paint Chunks;

    public MeteorProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.meteor,_from, _to, shooter, 110, 4, new Vector(150, 150), 20);
        Chunks = new Paint();
        Chunks.setARGB(255, 85, 64, 64);
        this.paint.setColor(Color.CYAN);
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
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y - height), Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 40,  R.drawable.fireball2));
        }

        if (this.health < landing) {
            this.velocity = new Vector(0, 0);

            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.fireball2));
            SimpleGLRenderer.addParticle(new glParticle(new Vector(this.getCenter().x, this.getCenter().y), Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20,  R.drawable.fireball2));
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

    @Override
    public void Draw(Canvas c, float playerx, float playery) {
        if (landed == false) {
            c.drawCircle(this.rect.centerX() - playerx, this.rect.centerY() - playery, this.size.x / 2 * (1 - (height / 400)),
                    this.shadowPaint);
            c.drawBitmap(this.curr, this.position.x - playerx, this.position.y - playery - this.height,
                    this.paint);

        }
// c.drawCircle(this.rect.centerX()-playerx, this.rect.centerY() - this.height-playery,
//				this.size.x / 3, this.paint);

    }

}
