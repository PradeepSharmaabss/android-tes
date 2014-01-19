package Particles;

import android.graphics.Canvas;
import android.graphics.Paint;


import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Tools.Vector;

/**
 * Created by Scott on 14/12/13.
 */
public class HealthDisplay extends Particle {
    GameObject parent;
    public HealthDisplay(Vector position, Vector velocity, int lifeSpan, Paint _p, GameObject g) {
        super(position, velocity, lifeSpan, _p);
        parent = g;
    }

    @Override
    public void Draw(float playerx, float playery, Canvas canvas) {
       // super.Draw(playerx, playery, canvas);
        parent.DrawHealthBar(canvas,0,0);
    }
}
