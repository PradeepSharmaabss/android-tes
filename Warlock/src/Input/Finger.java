package Input;
import java.util.ArrayList;
import java.util.List;

import Tools.Vector;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;

public class Finger {
	public static boolean down = false;
	public static Vector position = new Vector(150, 150);
	public static List<Pointer> pointers = new ArrayList<Pointer>(10);
	public static boolean fired = false;
	
	public static void Update(MotionEvent event)
	{
	
		pointers = new ArrayList<Pointer>(10);
		for(int s = 0; s<10; s++)
		{
			pointers.add(new Pointer());
		}
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		int tmp = event.getPointerCount() - pointers.size();
		/*for(int x=0;x<tmp;x++)
		{
			pointers.add(new Pointer());
		}
		for( int x=0; x<event.getPointerCount();x++)
		{
			pointers.get(x).position.x = event.getX(x);
			pointers.get(x).position.y = event.getY(x);//(event.geta);
		}*/
 		int x=0;
		switch(action)
    	{
	    	case MotionEvent.ACTION_DOWN:
	    	
	    		Log.d("SSS", "" + event.getX());
	    		for(x=0;x < event.getPointerCount();x++)
	    		{
	    			pointers.get(x).Update2(new Vector(event.getX(x),event.getY(x)));
	    		}
	    		for(x=event.getPointerCount();x<10;x++)
	    		{
	    			pointers.get(x).Update();
	    		}
	    		position.x = event.getX();
	    		position.y = event.getY();
	    		down = true;
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		if(fired)
	    		{
	    			fired = false;
	    		}
	   
	    		for(x=0;x < event.getPointerCount();x++)
	    		{
	    			pointers.get(x).Update2(new Vector(event.getX(x),event.getY(x)));
	    		}
	    		for(x=event.getPointerCount();x<10;x++)
	    		{
	    			pointers.get(x).Update();
	    		}
	    		position.x = event.getX();
	    		position.y = event.getY();
	    		down = false;
	    		break;
	    	case MotionEvent.ACTION_MOVE:
	    		for(x=0;x < event.getPointerCount();x++)
	    		{
	    			pointers.get(x).position=(new Vector(event.getX(x),event.getY(x)));
	    		}
	    		for(x=event.getPointerCount();x<10;x++)
	    		{
	    			pointers.get(x).Update();
	    		}
	    		position.x = event.getX();
	    		position.y = event.getY();
	    		break;
	    	default:
	    		break;
	    	
    	}
	}
	
}