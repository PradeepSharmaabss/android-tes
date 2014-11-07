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
    protected boolean WithinShape(float ex, float ey, float ea, float eb,
                                  float px, float py) {

        float dx = px - ex;
        float dy = py - ey;

        return (dx * dx) / (ea * ea) + (dy * dy) / (eb * eb) <= 1;

    }

}
