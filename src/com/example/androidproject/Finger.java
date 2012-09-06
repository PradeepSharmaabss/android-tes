package com.example.androidproject;

import java.util.ArrayList;
import java.util.List;

import android.view.MotionEvent;

public class Finger {
	public static boolean down = false;
	public static Vector position = new Vector(150, 150);
	public static List<Vector> pointers = new ArrayList<Vector>();
	public static void update(MotionEvent event)
	{
		int action = event.getAction() & MotionEvent.ACTION_MASK;
		switch(action)
    	{
	    	case MotionEvent.ACTION_DOWN:
	    		down = true;
	    		break;
	    	case MotionEvent.ACTION_UP:
	    		down = false;
	    		position.x = 0;
	    		position.y = 0;
	    		break;
	    	case MotionEvent.ACTION_MOVE:
	    		pointers.clear();
	    		for(int x=0;x < event.getPointerCount();x++)
	    		{
	    			pointers.add(new Vector(event.getX(x),event.getY(x)));
	    		}
	    		position.x = event.getX();
	    		position.y = event.getY();
	    		break;
	    	default:
	    		break;
	    	
    	}
	}
}