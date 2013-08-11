package Input;

import android.view.MotionEvent;

import com.developmental.myapplication.RenderThread;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import Tools.iVector;

public class Finger implements Serializable {
	public boolean down = false;
	public Pointer position = new Pointer();
	public List<Pointer> pointers = new ArrayList<Pointer>(10);

public Finger()
{
    pointers = new ArrayList<Pointer>();
    int s;
    for (s = 0; s < 10; s++)
        pointers.add(new Pointer());


}
    public ArrayList<iVector> WorldPositions()
    {
        ArrayList<iVector> p = new ArrayList<iVector>();
      //  if(position.WithinScreen())
            if(position.down)
        p.add(position.iWorldPos(RenderThread.archie.position));
        for (int k = 0; k < 10; k++)
            if (pointers != null)
                    if (pointers.get(k).down)
                       if(pointers.get(k).WithinScreen())
                           p.add(pointers.get(k).iWorldPos(RenderThread.archie.position));
        return p;
    }

	public int sz() {
		int m = 0;
		for (int k = 0; k < 10; k++)
			if (pointers != null)
				if (pointers.size() > 0)
					if (pointers.get(k).down)
						m++;
		return m;
	}

	public void Update(MotionEvent event) {

		int action = event.getAction() & MotionEvent.ACTION_MASK;
		// int tmp = event.getPointerCount() - pointers.size();
		/*
		 * for(int x=0;x<tmp;x++) { pointers.add(new Pointer()); } for( int x=0;
		 * x<event.getPointerCount();x++) { pointers.get(x).position.x =
		 * event.getX(x); pointers.get(x).position.y =
		 * event.getY(x);//(event.geta); }
		 */
		int x;

        for (x = 0; x < event.getPointerCount(); x++) {
            pointers.get(x).position = (new iVector((int)event.getX(x),
                    (int) event.getY(x)));
            pointers.get(x).down = true;
            // Log.Marker(event.getPointerCount()+"","x: "+pointers.get(x).position.x+" y:"+pointers.get(x).position.y
            // + "Down: " +pointers.get(x).down);
        }
        int ptrcount = event.getPointerCount();
        for (x = ptrcount; x < 10; x++)
            pointers.get(x).Update();
        position.position.x = (int)event.getX();
        position.position.y = (int)event.getY();
		switch (action) {
		case MotionEvent.ACTION_DOWN:

			down = true;

			break;
		case MotionEvent.ACTION_UP:
            for (x = 0; x < 10; x++)
                pointers.get(x).Update();


// Log.Marker("SSS", x+"");

			// for(x=event.getPointerCount();x<10;x++)
			// {
			// pointers.get(x).Update();
			// //
			// Log.Marker(x+"","x: "+pointers.get(x).position.x+" y:"+pointers.get(x).position.y
			// + "Down: " +pointers.get(x).down);
			//
			// }
			position.position.x = (int)event.getX();
			position.position.y = (int)event.getY();
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