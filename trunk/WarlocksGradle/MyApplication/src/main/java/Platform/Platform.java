package Platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Game.GameObject;
import Tools.Vector;

import com.developmental.myapplication.GL.Grid;
import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

//creates and manages a square platform for use as the levels ground
public class Platform extends GameObject {
    Paint paint = new Paint();

    @Override
    public void Animate(Vector dest) {
//        super.Animate(dest);
    }

    @Override
    public void Update() {
//        super.Update();
    }
    Vector center ;
    public Platform(Vector _position, Vector _size,int image) {
        super(image);
        this.position = new Vector(0,0);
        this.center = new Vector(_position.x,_position.y);
        this.size = _size;
        ArrayList<Grid> s = new ArrayList<Grid>();
        s.add(new Grid(2,2,false));
        setGrid(s);
        this.setGrid();
    }
public void setGrid()
{
    Grid g = getGrid().get(0);
    g.set(0, 0, center.x -size.x/2, center.y-Global.WORLD_BOUND_SIZE.y-size.y/2, 0.0f, 0.0f, 1.0f, null);
    g.set(1, 0, center.x+size.x/2, center.y-Global.WORLD_BOUND_SIZE.y-size.y/2, 0.0f, 1.0f, 1.0f, null);
    g.set(0, 1, center.x-size.x/2,center.y-Global.WORLD_BOUND_SIZE.y+ size.y/2, 0.0f, 0.0f, 0.0f, null);
    g.set(1, 1, center.x+size.x/2,center.y-Global.WORLD_BOUND_SIZE.y+size.y/2, 0.0f,1.0f, 0.0f, null );
}

    int shrinkingPhase;

    public void Shrink() {
        if (this.size.x > 5) {
            this.shrinkingPhase += 1;
            if (this.shrinkingPhase % 5 == 1) {
                this.size.x -= 2;
                this.size.y -= 1;
            }
        }
setGrid();
    }


    public void Draw(Canvas c, float playerx, float playery) {
        // Shrinks the platform every few updates(should be put in an update
        // function)
        //	Shrink();
        // The outer Rectangle
        this.paint.setColor(Color.DKGRAY);
        c.drawRect(new RectF(this.center.x - this.size.x / 2 - playerx, this.center.y
                - this.size.y / 2 - playery, this.center.x + this.size.x / 2 - playerx,
                this.center.y + this.size.y / 2 - playery), this.paint);
        this.paint.setAlpha(125);

        // This is a debugging statement that highligihts the map if you are
        // outside it
        if (Within(RenderThread.archie.feet))
            this.paint.setColor(Color.GRAY);
        else
            this.paint.setColor(Color.LTGRAY);

        // the smaller, inner rectangle
        c.drawRect(new RectF(this.center.x - this.size.x / 2 + this.size.x
                / 11 - playerx, this.center.y - this.size.y / 2 + this.size.y / 11 - playery,
                this.center.x + this.size.x / 2 - this.size.x / 11 - playerx,
                this.center.y + this.size.y / 2 - this.size.y / 11 - playery),
                this.paint);
    }

    // Tests if a point is located within the bounds of the platform
    public boolean Within(Vector _pos) {
        if (WithinShape(this.center.x, this.center.y, this.size.x / 2,
                this.size.y / 2, _pos.x, _pos.y))
            return true;
        return false;
    }

    // Gets passed a position and some general parameters based on the
    // size/position etc of the rectangle
    // then returns true if the point it is passed is within its bounds
    protected boolean WithinShape(float centerx, float centery, float sizex,
                                  float sizey, float posx, float posy) {
        if (posx > centerx - sizex)
            if (posy > centery - sizey)
                if (posx < centerx + sizex)
                    if (posy < centery + sizey)
                        return true;
        return false;
    }

}
