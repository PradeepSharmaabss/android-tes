package com.example.androidproject;
import java.util.ArrayList;
import java.util.List;

import Game.*;
import Shapes.*;
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
    Text text = new Text();
    Player player = new Player();
    List<GameObject> gameObjects = new ArrayList<GameObject>();
    List<Monster> monsters = new ArrayList<Monster>();
    List<Button> buttons = new ArrayList<Button>();
    List<Arrow> arrows = new ArrayList<Arrow>();
    Level level = new Level();
    
    Rectangle Up = new Rectangle(new Vector(75,0),new Vector(150,150));
    Rectangle Right = new Rectangle(new Vector(155,155),new Vector(150,150));
    Rectangle Left = new Rectangle(new Vector(0,155),new Vector(150,150));
    
    public static boolean buttonDown = false;
    
    public Screen(Context context) //this refreshes on view changed.
    {
    	//everything draws from the top left.
        super(context);
        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
        text.text = "Finger";
        monsters.add(new Monster());
        buttons.add(new Button(75, 0, 150, 150, 0));
        buttons.add(new Button(0, 155, 150, 150, 1));
        buttons.add(new Button(155, 155, 150, 150, 2));
    }

    @Override
    public void onDraw(Canvas c) {
    	
    	Up.Draw(c);
    	Right.Draw(c);
    	Left.Draw(c);

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
    	for(int x=0;x<monsters.size();x++)
    	{
    		monsters.get(x).Draw(c);
    		level.Collision(monsters.get(x));
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
	    		arrows.add(new Arrow(player));
	    		arrows.get(arrows.size()-1).Fire(Finger.position.get());
	    		player.cooldown = 100;
    		}
    	}
    	
    	this.invalidate();
        return true;
    }
}
