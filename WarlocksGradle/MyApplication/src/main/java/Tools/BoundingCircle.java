package Tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Scott on 27/08/13.
 */
public class BoundingCircle {
    public Vector Center;
    public double Radius;
    Paint p = new Paint();
    public BoundingCircle(Vector _c, double _r)
    {
        Center=_c;
        Radius=_r;
        p.setStyle(Paint.Style.STROKE);
        p.setColor(Color.WHITE);
    }
    public boolean CollidesWith(BoundingCircle b)
    {
        final double a = this.Radius + b.Radius;
        final double dx = this.Center.x - b.Center.x;
        final double dy = this.Center.y - this.Center.y;
        return a * a > (dx * dx + dy * dy);
    }
    public void Draw(Canvas c, float playerx, float playery)
    {
        c.drawCircle(Center.x-playerx,Center.y-playery,(float)Radius,p);
    }

}
