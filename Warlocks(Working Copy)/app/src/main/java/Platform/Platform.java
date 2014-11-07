package Platform;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

//creates and manages a square platform for use as the levels ground
public class Platform extends Renderable {


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
    g.set(0, 0, center.x -size.x/2, center.y- Global.WORLD_BOUND_SIZE.y-size.y/2, 0.0f, 0.0f, 1.0f, null);
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
