package Input;

import android.view.MotionEvent;

import java.io.Serializable;
import java.util.ArrayList;

import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

public class Finger implements Serializable {
    public boolean down = false;
    public Pointer position = new Pointer();
    public Pointer[] pointers = new Pointer[10];

    public Finger() {
        pointers = new Pointer[10];
        int s;
        for (s = 0; s < 10; s++)
            pointers[s] = new Pointer();


    }

    public iVector[] WorldPositions() {
        ArrayList<iVector> p = new ArrayList<iVector>();
        if (position.down&&(position.WithinScreen()))
            p.add(position.iWorldPos(SimpleGLRenderer.archie.bounds.Center));
        for (int k = 0; k < 10; k++)
            if (pointers != null)
                if (pointers[k].down)
                    if (pointers[k].WithinScreen())
                        p.add(pointers[k].iWorldPos(SimpleGLRenderer.archie.bounds.Center));
        iVector[] v = new iVector[p.size()];
        int i = 0;
        for(iVector pp : p)
        {
            v[i] = pp;
           i++;
        }
        return v;
    }



    public void Update(MotionEvent event) {

        int action = event.getAction() & MotionEvent.ACTION_MASK;
        int x;

        for (x = 0; x < event.getPointerCount(); x++) {
            pointers[x].position = (new iVector((int) event.getX(x),
                    (int) event.getY(x)));
            pointers[x].down = true;
        }
        int ptrcount = event.getPointerCount();
        for (x = ptrcount; x < 10; x++)
            pointers[x].Update();
        position.position.x = (short) event.getX();
        position.position.y = (short) (short) event.getY();
        switch (action) {
            case MotionEvent.ACTION_DOWN:

                down = true;

                break;
            case MotionEvent.ACTION_UP:
                for (x = 0; x < 10; x++)
                    pointers[x].Update();
                position.position.x = (short) event.getX();
                position.position.y = (short)  (short) event.getY();
                down = false;
                break;
            case MotionEvent.ACTION_MOVE:
                down = true;
                break;
            default:
                break;

        }
    }

}