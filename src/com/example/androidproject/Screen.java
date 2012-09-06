package com.example.androidproject;
import Game.Player;
import Shapes.Rectangle;
import Shapes.Text;
import World.Level;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class Screen extends View implements OnTouchListener  {
    private static final String TAG = "Screen";
    public static Vector size;
    public enum Action{left,right,jump};
    Text text = new Text();
    Player player = new Player();
    public Screen(Context context) //this refreshes on view changed.
    {
    	//everything draws from the top left.
        super(context);

        setFocusable(true);
        setFocusableInTouchMode(true);
        setOnTouchListener(this);
        text.text = "Finger";
    }
    Level level = new Level();
    Rectangle Up = new Rectangle(new Vector(75,0),new Vector(150,150));
    Rectangle Right = new Rectangle(new Vector(150,150),new Vector(150,150));
    Rectangle Left = new Rectangle(new Vector(0,150),new Vector(150,150));
    
    @Override
    public void onDraw(Canvas c) {
    	Up.Draw(c);
    	Right.Draw(c);
    	Left.Draw(c);
    	Input();
    	//System.out.println(player.velocity.y);
    	for(int x=0;x<Finger.pointers.size();x++)
    	{
    		new Text().Draw(c, "Finger["+x+"]", Finger.pointers.get(x));
    	}
    	
    	level.Draw(c);
    	player.Draw(c);
    	level.Collision(player);
    	this.invalidate();
    }
    public void Input()
    {
    	if(Up.Click())
    	{
    		player.Commands(Action.jump);
	   	}
		if(Left.Click())
		{
			player.Commands(Action.left);
		}
		if(Right.Click())
		{
			player.Commands(Action.right);
		}
	}
    public boolean onTouch(View view, MotionEvent event) {
    	super.onTouchEvent(event);
    	Finger.update(event);
    	this.invalidate();
        return true;
    }
}
