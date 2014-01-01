package Particles;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.developmental.myapplication.Global;

import Tools.Vector;

/**
 * Created by Scott on 29/08/13.
 */
public class LightningParticle extends Particle {
    Paint shadowPaint;

    @Override
    public void Draw(float playerx, float playery, Canvas canvas) {
        if (!Global.DEBUG_MODE) {
            for (int Arcs = 0; Arcs < 4; Arcs++) {
                Vector s = this.Start.get();
                float dx = this.position.x - this.Start.x;
                float dy = this.position.y - this.Start.y;
                for (int i = 0; i < 11; i++) {
                    float offsetx = (float) (Math.random() * 20 - 10);
                    float offsety = (float) (Math.random() * 20 - 10);
                    canvas.drawLine(s.x - playerx, s.y - playery, s.x + (dx / 11) + offsetx - playerx,
                            s.y + (dy / 11) + offsety - playery, this.shadowPaint);
                    canvas.drawLine(s.x - playerx, s.y - playery, s.x + (dx / 11) + offsetx - playerx, s.y + (dy / 11)
                            + offsety - playery, this.p);
                    s = new Vector(s.x + dx / 11 + offsetx, s.y + dy / 11 + offsety);
                }
//                canvas.drawLine(s.x - playerx, s.y - playery, this.position.x - playerx, this.position.y - playery,
//                        this.shadowPaint);
//                canvas.drawLine(s.x - playerx, s.y - playery, this.position.x - playerx, this.position.y - playery, this.p);

            }
        } else {
          //  canvas.drawLine(Start.x - playerx, Start.y - playery, position.x - playerx, position.y - playery, p);
        }
    }

    Vector Start;

    public LightningParticle(Vector Start, Vector End, Vector velocity, int lifeSpan, Paint _p) {
        super(End, velocity, lifeSpan, _p);
        this.Start = Start;
        this.position = End;
        this.velocity = new Vector(0, 0);
        shadowPaint = new Paint();
        shadowPaint.setStrokeWidth(4);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.OUTER));
        shadowPaint.setColor(Color.WHITE);
    }
}
