package com.example.androidproject;
import java.util.ArrayList;
import java.util.List;

import Game.*;
import World.Level;
import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Screen extends View implements OnTouchListener  {
    //private static final String TAG = "Screen";
    public static Vector size;
    public enum Action{jump, left, right};
    Player player = new Player();
    
    public static List<GameObject> gameObjects = new ArrayList<GameObject>();
    List<Button> buttons = new ArrayList<Button>();
    List<Arrow> arrows = new ArrayList<Arrow>();
    Level level = new Level();
    public static int objects = 0;//unique object Identifier
    public static boolean buttonDown = false;
    
    public Screen(Context context) //this refreshes on view changed.
    {
    	//everything draws from the top left.
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
       	addObject(new Monster());
        buttons.add(new Button(75, 0, 150, 150, 0));
        buttons.add(new Button(0, 155, 150, 150, 1));
        buttons.add(new Button(155, 155, 150, 150, 2));
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
    @Override
    public void onDraw(Canvas c) {
    	level.Draw(c);
    	level.Collision(player);
    	buttonDown = false;
    	for(Arrow a :arrows)
    	{
    		a.Draw(c);
    		level.Collision(a);
    	}
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
    	if(!buttonDown)
    	{
    		System.out.println(player.cooldown);
    		if(player.cooldown == 0)
    		{
    			/*addGameObject(new Arrow(player));
	    		arrows.add(new Arrow(player));
	    		arrows.get(arrows.size()-1).Fire(Finger.position.get());
	    		player.cooldown = 100;*/
    		}
    	}
    	
    	this.invalidate();
        return true;
    }
}
