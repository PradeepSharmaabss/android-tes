package Platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import Tools.Vector;
import developmental.warlocks.GL.SimpleGLRenderer;

//An elliptical platform is simply a platform in the shape of an ellipse
public class EllipticalPlatform extends Platform {
    public EllipticalPlatform(Vector _position, Vector _size,int image) {
        super(_position, _size,image);

    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

        this.paint.setColor(Color.DKGRAY);
        c.drawOval(new RectF(this.center.x - this.size.x / 2 - playerx, this.center.y
                - this.size.y / 2 - playery, this.center.x + this.size.x / 2 - playerx,
                this.center.y + this.size.y / 2 - playery), this.paint);
        this.paint.setAlpha(125);
        if (Within(SimpleGLRenderer.archie.feet))
            this.paint.setColor(Color.GRAY);
        else
            this.paint.setColor(Color.LTGRAY);
        c.drawOval(new RectF(this.center.x - this.size.x / 2 + this.size.x
                / 11 - playerx, this.center.y - this.size.y / 2 + this.size.y / 11 - playery,
                this.center.x + this.size.x / 2 - this.size.x / 11 - playerx,
                this.center.y + this.size.y / 2 - this.size.y / 11 - playery),
                this.paint);
    }

    @Override
    protected boolean WithinShape(float ex, float ey, float ea, float eb,
                                  float px, float py) {

        float dx = px - ex;
        float dy = py - ey;

        return (dx * dx) / (ea * ea) + (dy * dy) / (eb * eb) <= 1;

    }

}
