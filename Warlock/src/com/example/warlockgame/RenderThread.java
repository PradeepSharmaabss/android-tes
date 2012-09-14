package com.example.warlockgame;
/**
 * 
 */

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import HUD.Button;
import Input.Finger;
import NPC.Archie;
import NPC.Enemy;
import Tools.SpriteSheet;
import Tools.Vector;
import World.Level;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * @author impaler
 * This is the main surface that handles the ontouch events and draws
 * the image to the screen.
 */
public class RenderThread extends SurfaceView implements SurfaceHolder.Callback 
{
	private static final String TAG = RenderThread.class.getSimpleName();
	public static List<GameObject> gameObjects = new ArrayList<GameObject>();
	
	public enum GameState {};
	Archie archie;
	Paint paint;
	public static Level l;
	public static int objects = 0;
	
	public static Point size,trueSize;
	
	public GameThread gameThread;
	
	public RenderThread(Context context,Point _size) {
		super(context);
		RenderThread.size = _size;
		trueSize = new Point(_size.x,_size.y);
		RenderThread.size.y -= size.y/5;
		
		paint = new Paint();
		paint.setAntiAlias(false);
		paint.setColor(Color.RED);
		l = new Level(new SpriteSheet(BitmapFactory.decodeResource(getResources(), R.drawable.isotiles),64),new Vector(size.x/10,size.y/10),1);
		getHolder().addCallback(this);
		// load sprite sheet
		Log.d("NEW ARCHIE", "SSS");
		archie = new Archie(new SpriteSheet(BitmapFactory.decodeResource(getResources(), R.drawable.charsheet), 64));
		addObject(archie);
		//addObject(new Enemy());
		// create the game loop thread
		UserInterface();
		gameThread = new GameThread(getHolder(), this);
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		//getHolder().
	}
	List<Button> buttons = new ArrayList<Button>();
	public void UserInterface()
	{
		for(int x=0;x < size.x; x+=size.x/10)
		{
			buttons.add(
				new Button(
					new RectF(
						x,
						size.y,
						x + (size.x/10),
						trueSize.y),
						x / (size.x/10)
						)
			);
		}
	}
	
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
		boolean retry = true;
		while (retry) {
			try {
				gameThread.join();
				retry = false;
			} catch (InterruptedException e) {
				// try again shutting down the thread
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		Finger.Update(event);
		return true;
	}

	protected void onDraw(Canvas canvas) {
		try
		{
			canvas.drawColor(Color.BLACK);//buffer refresh color
			l.Draw(canvas, paint);
			for( GameObject obj :gameObjects)
				obj.Draw(canvas);
			for(Button b : buttons)
				b.Draw(canvas);
		}
		catch(Exception ex)
		{
			System.out.println(ex+"");
			//System.out.println("asd");
		}
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

	public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
		if(!gameThread.isAlive())
		{
			gameThread.setRunning(true);
			gameThread.start();
		}
	}

	public void surfaceCreated(SurfaceHolder holder) {
		if(!gameThread.isAlive())
		{
			gameThread.setRunning(true);
			gameThread.start();
		}
	}

	
}




