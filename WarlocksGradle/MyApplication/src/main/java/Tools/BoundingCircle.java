package Tools;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Scott on 27/08/13.
 */
public class BoundingCircle {
    public Vector Center;
    public float Radius;
    public BoundingCircle(Vector _c, float _r)
    {
        Center=_c;
        Radius=_r;

    }
    public boolean CollidesWith(BoundingCircle b)
    {
        final double a = this.Radius + b.Radius;
        final double dx = this.Center.x - b.Center.x;
        final double dy = this.Center.y - b.Center.y;
        return a * a > (dx * dx + dy * dy);
    }
    public  final Vector closestpointonline(Vector l1,
                                                Vector l2){
        float lx1 = l1.x;
        float ly1 = l1.y;
        float lx2 = l2.x;
        float ly2 = l2.y;
        float x0 =this.Center.x;
        float y0 = this.Center.y;
        float A1 = ly2 - ly1;
        float B1 = lx1 - lx2;
        float C1 = (ly2 - ly1)*lx1 + (lx1 - lx2)*ly1;
        float C2 = -B1*x0 + A1*y0;
        float det = A1*A1 - -B1*B1;
        float cx = 0;
        float cy = 0;
        if(det != 0){
            cx = (float)((A1*C1 - B1*C2)/det);
            cy = (float)((A1*C2 - -B1*C1)/det);
        }else{
            cx = x0;
            cy = y0;
        }
        return new Vector(cx, cy);
    }
    public void Draw(Canvas c, float playerx, float playery,Paint p)
    {
        c.drawCircle(Center.x-playerx,Center.y-playery,(float)Radius,p);
    }

}
