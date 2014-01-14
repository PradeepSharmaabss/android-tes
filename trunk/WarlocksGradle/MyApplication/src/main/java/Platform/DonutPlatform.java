package Platform;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region;

import Tools.Vector;

import com.developmental.myapplication.Global;

public class DonutPlatform extends EllipticalPlatform {
    Vector InnerCircleSize;

    public DonutPlatform(Vector _position, Vector _maxSize, Vector _minSize,int image) {
        super(_position, _maxSize,image);
        this.InnerCircleSize = _minSize;
    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

        // Just for the sake of proving its subtracting. if wanted i can do a
        // square donut too. might also think on a diamond just cause haha
        // http://stackoverflow.com/questions/9285450/on-android-how-do-i-make-oddly-shaped-clipping-areas
        c.save();
        Path largePath = new Path();
        largePath.addOval(new RectF(this.center.x - this.size.x / 2 - playerx,
                this.center.y - this.size.y / 2 - playery, this.center.x
                + this.size.x / 2 - playerx, this.center.y + this.size.y / 2 - playery),
                Direction.CW);
        Path smallPath = new Path();
        smallPath.addOval(new RectF(this.center.x - this.InnerCircleSize.x
                / 2 - playerx, this.center.y - this.InnerCircleSize.y / 2 - playery,
                this.center.x + this.InnerCircleSize.x / 2 - playerx, this.center.y
                + this.InnerCircleSize.y / 2 - playery), Direction.CW);
        c.clipPath(largePath); // c is a Canvas
        c.clipPath(smallPath, Region.Op.DIFFERENCE);

        this.shrinkingPhase += 1;
        if (this.size.x > 5)
            if (this.shrinkingPhase % 5 == 1) {

                this.size.x -= 2;
                this.size.y -= 1;
            }
        if (this.InnerCircleSize.x > 0)
            if (this.shrinkingPhase % 5 == 1) {

                this.InnerCircleSize.x -= 2;
                this.InnerCircleSize.y -= 1;
            }

        c.drawBitmap(Global.PlatformSkins.get(0), new Rect(0, 0, 894, 894),
                new RectF(this.center.x - this.size.x / 2 - playerx, this.center.y
                        - this.size.y / 2 - playery, this.center.x + this.size.x / 2 - playerx,
                        this.center.y + this.size.y / 2 - playery), this.paint);
        c.restore();
    }

    @Override
    public boolean Within(Vector _pos) {
        if (WithinShape(this.center.x, this.center.y, this.size.x / 2,
                this.size.y / 2, _pos.x, _pos.y))
            if (!WithinShape(this.center.x, this.center.y,
                    this.InnerCircleSize.x / 2, this.InnerCircleSize.y / 2,
                    _pos.x, _pos.y))
                return true;
        return false;
    }

}
