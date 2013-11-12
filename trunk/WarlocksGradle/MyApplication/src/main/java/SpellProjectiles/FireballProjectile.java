package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Particles.Particle;
import Tools.Vector;

public class FireballProjectile extends Projectile {
    List<Vector> path = new ArrayList<Vector>();

    public FireballProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(_from, _to, _shooter, 100, 20f, new Vector(50, 50), 10);

        this.paint.setColor(Color.argb(130, 255, 120, 30));
        this.shadowPaint.setColor(Color.argb(200, 0, 0, 0));
    }

    @Override
    public void Update() {

        super.Update();

        RenderThread.addParticle(new Particle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        RenderThread.addParticle(new Particle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        // RenderThread.addParticle(new Particle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()),10));


    }

    int i = 0;

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

//        c.drawCircle(this.rect.centerX()+20-playerx, this.rect.centerY()+20-playery, this.size.x / 2,
//                this.shadowPaint);
//        c.drawCircle(this.rect.centerX()-playerx, this.rect.centerY()-playery, this.size.x / 2,
//				this.paint);

    }

}
