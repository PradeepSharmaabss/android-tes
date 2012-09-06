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
    Rectangle button = new Rectangle(new Vector(0,0),new Vector(150,150));
    @Override
    public void onDraw(Canvas c) {
    	button.Draw(c);
    	if(button.Click()&&player.jumping==false)
        	{
        		//player.jumping=true;
       		player.velocity.y=-15;
       	}
    	System.out.println(player.velocity.y);
    	for(int x=0;x<Finger.pointers.size();x++)
    	{
    		new Text().Draw(c, "Finger["+x+"]", Finger.pointers.get(x));
    	}
    	
    	level.Draw(c);
    	player.Draw(c);
    	level.Collision(player);
    	this.invalidate();
    }

    public boolean onTouch(View view, MotionEvent event) {
    	super.onTouchEvent(event);
    	Finger.update(event);
    	this.invalidate();
        return true;
    }
}
