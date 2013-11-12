package Particles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import Tools.Vector;

/**
 * Created by Scott on 30/08/13.
 */
public class IceParticle extends Particle {
    int r;
    int g;
    int b;

    public IceParticle(Vector position, Vector velocity, int lifeSpan, Paint _p) {
        super(position, velocity, lifeSpan, _p);
        p = new Paint();
        p.setColor(Color.WHITE);
        r = Color.red(p.getColor());
        g = Color.green(p.getColor());
        b = Color.blue(p.getColor());
    }

    @Override
    public void Update() {
        super.Update();

    }

    @Override
    public void Draw(float playerx, float playery, Canvas canvas) {
        //  super.Draw(playerx, playery, canvas);
        canvas.drawLine(position.x - playerx - (lifeSpan), position.y - playery - (lifeSpan), position.x - playerx + (lifeSpan), position.y - playery + (lifeSpan), p);
        canvas.drawLine(position.x - playerx - (lifeSpan), position.y - playery + (lifeSpan), position.x - playerx + (lifeSpan), position.y - playery - (lifeSpan), p);
        //   canvas.drawCircle(position.x-playerx,position.y-playery,5+2*lifeSpan,p);
    }
}
