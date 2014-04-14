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
    public enum type{Health,Mana,Backbar}
    Grid grid2;
    type t;
    public glHealthBar(int _mResourceID,Vector _size, Vector _position,GameObject _parent,type _t) {
        super(_mResourceID);
        size = _size;
        position=  _position;
        parent = _parent;
        ArrayList<Grid> g = new ArrayList<Grid>();
      Grid bG2 = new Grid(2,2,false);
        g.add(bG2);
        this.setGrid(g);
        grid2 = new Grid(2,2,false);
       grid2.set(0, 0,  0.0f, size.y, 0.0f, 0.0f, 0, null);
        grid2.set(1, 0,  size.x,size.y, 0.0f, 1.0f,0, null);
        grid2.set(0, 1, 0.0f, 0, 0.0f, 0.0f, (1)/8f, null);
        grid2.set(1, 1, size.x,0, 0.0f,1.0f, (1)/8f, null );
        t = _t;
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean b) {
        gl.glBindTexture(GL10.GL_TEXTURE_2D, mTextureName);
        gl.glPushMatrix();
        gl.glLoadIdentity();
        if(b)
            gl.glTranslatef(position.x,position.y,0);
        else
            gl.glTranslatef(
                    position.x-offsetX,
                    Global.WORLD_BOUND_SIZE.y-position.y-offsetY,
                    z);
              grid2.draw(gl,true,false);
//            if(!boundsz)
//            OpenGLTestActivity.boundingCircle.draw(gl,0,0);
        gl.glPopMatrix();
        setTextureName(Global.resources.get(this.getResourceId()));

        float i;
        switch (t) {
            case Health:
                if(parent.health/parent.maxhealth>0.5)
                i= 1;
                else
                   if(parent.health/parent.maxhealth>0.2)
                       i =2;
                    else
                        i = 4;

                getGrid().get(frame).set(0, 0,  0.0f, size.y, 0.0f, 0.0f, i/8f, null);
                getGrid().get(frame).set(1, 0,  size.x*parent.health/parent.maxhealth,size.y, 0.0f, 1.0f,i/8f, null);
                getGrid().get(frame).set(0, 1, 0.0f, 0, 0.0f, 0.0f, (i+1)/8f, null);
                getGrid().get(frame).set(1, 1, size.x*parent.health/parent.maxhealth,0, 0.0f,1.0f, (i+1)/8f, null );
                break;
            case Mana:
                if(parent.health/parent.maxhealth>0.5)
                    i= 1;
                else
                if(parent.health/parent.maxhealth>0.2)
                    i =2;
                else
                    i = 4;

                getGrid().get(frame).set(0, 0,  0.0f, size.y, 0.0f, 0.0f, i/8f, null);
                getGrid().get(frame).set(1, 0,  size.x*parent.mana/200,size.y, 0.0f, 1.0f,i/8f, null);
                getGrid().get(frame).set(0, 1, 0.0f, 0, 0.0f, 0.0f, (i+1)/8f, null);
                getGrid().get(frame).set(1, 1, size.x*parent.mana/200,0, 0.0f,1.0f, (i+1)/8f, null );
                break;
            case Backbar:
                 i = 0;

                break;
        }

      //  this.frame = 0;
        super.draw(gl, offsetX, offsetY, b);
     }
}
