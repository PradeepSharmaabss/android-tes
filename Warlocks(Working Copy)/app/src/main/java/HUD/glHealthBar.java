package HUD;


import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.Renderable;
import developmental.warlocks.Global;

/**
 * Created by Scott on 6/03/14.
 */
public class glHealthBar extends Renderable {
    Collideable parent;
    Grid grid2;
    type t;

    public glHealthBar(int _mResourceID, Vector _size, Vector _position, Collideable _parent, type _t) {
        super(_mResourceID);
        size = _size;
        position = _position;
        parent = _parent;
        ArrayList<Grid> g = new ArrayList<Grid>();
        Grid bG2 = new Grid(2, 2, false);
        g.add(bG2);
        this.setGrid(g);
        grid2 = new Grid(2, 2, false);
        grid2.set(0, 0, 0.0f, size.y, 0.0f, 0.0f, 0, null);
        grid2.set(1, 0, size.x, size.y, 0.0f, 1.0f, 0, null);
        grid2.set(0, 1, 0.0f, 0, 0.0f, 0.0f, 1 / 8f, null);
        grid2.set(1, 1, size.x, 0, 0.0f, 1.0f, 1 / 8f, null);
        t = _t;
    }

    void setGrid2(int i) {
        grid2.set(0, 0, 0.0f, size.y, 0.0f, 0.0f, i / 8f, null);
        grid2.set(1, 0, size.x, size.y, 0.0f, 1.0f, i / 8f, null);
        grid2.set(0, 1, 0.0f, 0, 0.0f, 0.0f, (i + 1) / 8f, null);
        grid2.set(1, 1, size.x, 0, 0.0f, 1.0f, (i + 1) / 8f, null);
    }

    void drawGrid2(GL10 gl, float offsetX, float offsetY) {

        gl.glPushMatrix();
        gl.glLoadIdentity();
        if (offsetY == 0)
            gl.glTranslatef(position.x - offsetX, position.y - offsetY, 0);
        else
            gl.glTranslatef(position.x - offsetX, -position.y - offsetY, 0);

        grid2.draw(gl, true, false);
        gl.glPopMatrix();
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {

        setTextureName(Global.resources.get(this.getResourceId()));
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);
        float i = 1;
        switch (t) {
            case Health:
                drawGrid2(gl, offsetX, offsetY);
                if (parent.health / parent.maxhealth > 0.5)
                    i = 1;
                else if (parent.health / parent.maxhealth > 0.2)
                    i = 2;
                else
                    i = 4;

                getGrid().get(frame).set(0, 0, 0.0f, size.y, 0.0f, 0.0f, i / 8f, null);
                getGrid().get(frame).set(1, 0, size.x * parent.health / parent.maxhealth, size.y, 0.0f, 1.0f, i / 8f, null);
                getGrid().get(frame).set(0, 1, 0.0f, 0, 0.0f, 0.0f, (i + 1) / 8f, null);
                getGrid().get(frame).set(1, 1, size.x * parent.health / parent.maxhealth, 0, 0.0f, 1.0f, (i + 1) / 8f, null);
                break;
            case Mana:
                switch (((int) parent.mana / 100) % 5) {
                    case 0:
                        setGrid2(parent.mana / 100 < 4 ? 0 : 6);
                        i = 2;// = Global.PaintYellow;
                        break;
                    case 1:
                        setGrid2(2);
                        i = 3;
                        break;
                    case 2:
                        setGrid2(3);
                        i = 4;
                        break;
                    case 3:
                        setGrid2(4);
                        i = 5;
                        break;
                    case 4:
                        setGrid2(5);
                        i = 6;
                        break;
                }
                getGrid().get(frame).set(0, 0, 0.0f, size.y, 0.0f, 0.0f, i / 8f, null);
                getGrid().get(frame).set(1, 0, size.x * ((parent.mana % 100 / 100)), size.y, 0.0f, 1.0f, i / 8f, null);
                getGrid().get(frame).set(0, 1, 0.0f, 0, 0.0f, 0.0f, (i + 1) / 8f, null);
                getGrid().get(frame).set(1, 1, size.x * ((parent.mana % 100 / 100)), 0, 0.0f, 1.0f, (i + 1) / 8f, null);
                drawGrid2(gl, offsetX, offsetY);
                break;

        }

        //  this.frame = 0;
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
    }

    public enum type {Health, Mana}
}
