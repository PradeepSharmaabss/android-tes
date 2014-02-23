package com.developmental.myapplication.GL.NewHeirachy;

import android.graphics.Color;
import android.graphics.RectF;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Input.Pointer;

/**
 * Created by Scott on 24/01/14.
 */
public class glButton extends Renderable {

    public RectF rect;
    public glButton(int _mResourceID, int x, int y, int w, int h) {
        super(_mResourceID);
        rect = new RectF(x,y,x+w,y+h);

    }
public boolean down =false;
    @Override
    public void Update() {
        super.Update();

        if (!Global.LOCKSPELLMODE) {
            for (int x = 0; x < RenderThread.finger.pointers.length; x++) {
                Pointer f = RenderThread.finger.pointers[x];

                if (!f.down)
                    continue;

                if (this.rect.contains(f.position.x, Global.size.y*5/4-f.position.y)) {
                  frame=1;
                    this.down = true;
                    return;
                }
            }
           frame= 0;
            this.down = false;

        }
    }
}
