package Particles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Tools.Vector;

/**
 * Created by Scott on 26/12/13.
 */
public class WindParticle extends Particle {
    float radius,angle;
    Paint p2= new Paint();
    public WindParticle(Vector position, Vector velocity, int lifeSpan, Paint _p,float _radius, float _angle) {
        super(position, velocity, lifeSpan, _p);
        p2.setColor(Color.GRAY);
        p2.setStrokeWidth(5);
        p2.setAlpha(125);
        this.radius=  _radius;
        p.setStyle(Paint.Style.STROKE);
        p2.setStyle(Paint.Style.STROKE);
        this.angle = _angle;
    }

    @Override
    public void Draw(float playerx, float playery, Canvas canvas) {
      //  super.Draw(playerx, playery, canvas);

        canvas.drawArc(new RectF(position.x -radius/2 - playerx,position.y-radius/2-playery,position.x+radius/2-playerx,position.y-playery+radius/2),((angle-lifeSpan*10)+160)%360,80,false,p2);
        canvas.drawArc(new RectF(position.x -radius/2 - playerx,position.y-radius/2-playery,position.x+radius/2-playerx,position.y-playery+radius/2),((angle+lifeSpan*10)+160)%360,80,false,p2);
        canvas.drawArc(new RectF(position.x -radius/2 - playerx,position.y-radius/2-playery,position.x+radius/2-playerx,position.y-playery+radius/2),((angle-lifeSpan*10)+150)%360,60,false,p);
        canvas.drawArc(new RectF(position.x -radius/2 - playerx,position.y-radius/2-playery,position.x+radius/2-playerx,position.y-playery+radius/2),((angle+lifeSpan*10)+150)%360,60,false,p);
    }
}
