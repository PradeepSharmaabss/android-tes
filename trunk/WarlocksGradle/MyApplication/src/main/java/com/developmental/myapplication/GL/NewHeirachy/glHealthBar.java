package com.developmental.myapplication.GL.NewHeirachy;

import com.developmental.myapplication.GL.Grid;
import com.developmental.myapplication.Global;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import Tools.Vector;

/**
 * Created by Scott on 6/03/14.
 */
public class glHealthBar extends Renderable {
    GameObject parent;
    public glHealthBar(int _mResourceID,Vector _size, Vector _position,GameObject _parent) {
        super(_mResourceID);
        size = _size;
        position=  _position;
        parent = _parent;
        ArrayList<Grid> g = new ArrayList<Grid>();
      Grid bG2 = new Grid(2,2,false);
        g.add(bG2);
        this.setGrid(g);
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean b) {
        setTextureName(Global.resources.get(this.getResourceId()));
        float i = 0;
        getGrid().get(frame).set(0, 0,  0.0f, size.y, 0.0f, 0.0f, i/8f, null);
        getGrid().get(frame).set(1, 0,  size.x,size.y, 0.0f, 1.0f,i/8f, null);
        getGrid().get(frame).set(0, 1, 0.0f, 0, 0.0f, 0.0f, (i+1)/8f, null);
        getGrid().get(frame).set(1, 1, size.x,0, 0.0f,1.0f, (i+1)/8f, null );
      //  this.frame = 0;
        super.draw(gl, offsetX, offsetY, b);
     }
}
