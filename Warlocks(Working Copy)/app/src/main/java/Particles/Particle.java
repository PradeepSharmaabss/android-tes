package Particles;

import android.graphics.Canvas;
import android.graphics.Paint;

import Tools.Vector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 28/08/13.
 */
public class Particle {
    Vector position;
    Vector velocity;
    int lifeSpan;
    public int id;
    Paint p;

    public Particle(Vector position, Vector velocity, int lifeSpan, Paint _p) {
        this.position = position.get();
        this.velocity = velocity.get();
        this.lifeSpan = lifeSpan;
        p = _p;
    }

    public void Update() {
        lifeSpan -= 1;
        if (lifeSpan <= 0) {
            SimpleGLRenderer.delParticle(this.id);
            return;
        }
        position = position.add(velocity);
    }

    public void Draw(float playerx, float playery, Canvas canvas) {
        canvas.drawCircle(position.x - playerx, position.y - playery, 5 + 2 * lifeSpan, p);

    }

}
