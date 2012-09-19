package Input;
import java.util.ArrayList;
import java.util.List;

import Tools.Vector;
import android.view.MotionEvent;

public class Finger {
	public static boolean down = false;
	public static Vector position = new Vector(150, 150);
	public static List<Pointer> pointers = new ArrayList<Pointer>(10);
	public static boolean fired = false;
	private static boolean u = false;

	public static int sz()
	{
		int m = 0;
		for(int k = 0;k < 10;k++)
		{
			if(pointers != null)
				if(pointers.size()>0)
					if(pointers.get(k).down)
						m++;
		}
		return m;
	}
	public static void Update(MotionEvent event)
	{
		if(u==false)
		{
		pointers = new ArrayList<Pointer>();
		int s;
		for(s = 0; s<10; s++)
		{
			pointers.add(new Pointer());
		}
	
		u=true;
		
		}
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		//int tmp = event.getPointerCount() - pointers.size();
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
	    	

	    		position.x = event.getX();
	    		position.y = event.getY();
	    		down = true;
	    		
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		if(fired)
	    		{
	    			fired = false;
	    		}
	   
	    		for(x=0;x < 10;x++)
	    		{
	    		//	Log.d("SSS", x+"");
	    			if(pointers.get(x).position.x == event.getX()&&pointers.get(x).position.y==event.getY())
	    			{
	    				//Log.d("Lookie Here",x+"");
	    				pointers.get(x).Update();
	    				
	    					for(x++;x<pointers.size()-1;x++)
	    					{
	    						if(pointers.get(x).down==false)
	    						{
	    							break;
	    						}
	    						Pointer p = pointers.get(x);
	    						pointers.set(x, pointers.get(x+1));

	    						pointers.set(x+1, p);
	    					}
	    					break;
	    				
	    			}
	    		}
//	    		for(x=event.getPointerCount();x<10;x++)
//	    		{
//	    			pointers.get(x).Update();
//	    		//	Log.d(x+"","x: "+pointers.get(x).position.x+" y:"+pointers.get(x).position.y + "Down: " +pointers.get(x).down);
//		    		
//	    		}
	    		position.x = event.getX();
	    		position.y = event.getY();
	    		down = false;
	    		break;
	    	case MotionEvent.ACTION_MOVE:
	    		
	    		for(x=0;x < event.getPointerCount();x++)
	    		{
	    			pointers.get(x).position=(new Vector(event.getX(x),event.getY(x)));
	    			pointers.get(x).down = true;
	    		//	Log.d(event.getPointerCount()+"","x: "+pointers.get(x).position.x+" y:"+pointers.get(x).position.y + "Down: " +pointers.get(x).down);
	    		}
	    		int ptrcount = event.getPointerCount();
	    		for(x = ptrcount; x < 10 ; x++)
	    			pointers.get(x).Update();
	    		position.x = event.getX();
	    		position.y = event.getY();
	    		break;
	    	default:
	    		break;
	    	
    	}
	}
	
}