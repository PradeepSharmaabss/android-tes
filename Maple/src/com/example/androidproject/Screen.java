package com.example.androidproject;
import java.util.ArrayList;
import java.util.List;

import Game.*;
import World.Level;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Screen extends View implements OnTouchListener  {
    //private static final String TAG = "Screen";
    public static Vector size;
    public enum Action{jump, left, right};
    Player player = new Player();
    public static Resources resource;
    public static List<GameObject> gameObjects = new ArrayList<GameObject>();
    List<Button> buttons = new ArrayList<Button>();
    List<Arrow> arrows = new ArrayList<Arrow>();
    Level level;
    public static int objects = 0;//unique object Identifier
    public static boolean buttonDown = false;
    
    public Screen(Context context) //this refreshes on view changed.
    {
    	//everything draws from the top left.
        super(context);
        
        LoadBmps();
        resource = getResources();
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
       	//addObject(new Monster());
        buttons.add(new Button(75, 0, 150, 150, 0));
        buttons.add(new Button(0, 155, 150, 150, 1));
        buttons.add(new Button(155, 155, 150, 150, 2));
        player.position = new Vector(0,0);
        level = new Level();
        //while(Screen.resource != null);
        //while(getResources() == null);
        
    }
    public void LoadBmps()
    {
        ImageHolder.bg = BitmapFactory.decodeResource(getResources(), R.drawable.asd);
        ImageHolder.bg2 = BitmapFactory.decodeResource(getResources(), R.drawable.penguins);
        List<Bitmap> left= new ArrayList<Bitmap>();
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk1));
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk2));
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk3));
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk4));
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk5));
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk6));
        left.add(BitmapFactory.decodeResource(getResources(), R.drawable.left_walk7));
        ImageHolder.walkLeft = left;
        List<Bitmap> right= new ArrayList<Bitmap>();
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk1));
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk2));
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk3));
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk4));
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk5));
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk6));
        right.add(BitmapFactory.decodeResource(getResources(), R.drawable.right_walk7));
        ImageHolder.walkRight = right;
        //ImageHolder.archieLeft = BitmapFactory.decodeResource(getResources(), R.drawable.left_walk1);
        //ImageHolder.archieRight = BitmapFactory.decodeResource(getResources(), R.drawable.right_walk1);
        ImageHolder.tree = BitmapFactory.decodeResource(getResources(), R.drawable.tree2);
        //if(ImageHolder.archieLeft == null || ImageHolder.archieRight == null)
        	//LoadBmps();
    }

    @Override
    public void onDraw(Canvas c) {
    	level.Draw(c ,player);//need to fix map being drawn even when its not on screen.
    	level.Collision(player);
    	buttonDown = false;
    	for(Button b:buttons)
    	{
    		if(b.isDown())
    		{
    			buttonDown = true;
    			Input(b.id);
    		}
    		b.Draw(c);
    	}
    	for(GameObject obj :gameObjects)
    	{
    		obj.Draw(c);
    		level.Collision(obj);
    	}
    	player.Draw(c);
    	//new Text().Draw(c, ""+player.position.x, new Vector(Screen.size.x/2,player.position.y));
    	this.invalidate();
    }
   
    public void Input(int item)
    {
    	switch(item)
    	{
	    	case 0:
	    		player.Commands(Action.jump);
	    		break;
	    	case 1:
	    		player.Commands(Action.left);
	    		break;
	    	case 2:
	    		player.Commands(Action.right);
	    		break;
    	}
	}
    
    public boolean onTouch(View view, MotionEvent event) {
    	super.onTouchEvent(event);
    	Finger.update(event);
    	
    	this.invalidate();
        return true;
    }
    public static void addObject(GameObject obj)
    {
    	gameObjects.add(obj);
    	gameObjects.get(gameObjects.size()-1).id = objects++;
    }
    
    public static void delObject(int id)
    {
    	for(int x=0;x<gameObjects.size();x++)
    	{
    		if(gameObjects.get(x).id == id)
    			gameObjects.remove(x);
    	}
    }
}
