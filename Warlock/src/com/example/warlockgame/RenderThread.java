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
import Tools.SpriteSheet;
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
	Button right,left,up,down;
	Archie archie;
	Paint paint;
	Level l;
	public static int objects = 0;
	public Point size;
	
	public GameThread gameThread;
	
	public RenderThread(Context context) {
		super(context);
		paint = new Paint();
		paint.setAntiAlias(false);
		paint.setColor(Color.RED);
		l = new Level(new SpriteSheet(BitmapFactory.decodeResource(getResources(), R.drawable.tiles), 64));
		getHolder().addCallback(this);
		// load sprite sheet
		left = new Button(new RectF(0,500,100,500+100));
		right = new Button(new RectF(100,500,200,500+100));
		up = new Button(new RectF(0,400,100,400+100));
		down = new Button(new RectF(0,600,100,600+100));
		archie = new Archie(BitmapFactory.decodeResource(getResources(), R.drawable.characteridle),BitmapFactory.decodeResource(getResources(), R.drawable.characteridle2));
		addObject(archie);
		int[] left = new int[]
				{
					R.drawable.left_walk1,
					R.drawable.left_walk2,
					R.drawable.left_walk3,
					R.drawable.left_walk4,
					R.drawable.left_walk5,
					R.drawable.left_walk6,
					R.drawable.left_walk7
				};
		for(int x:left)
		{
			archie.left.add(BitmapFactory.decodeResource(getResources(), x));
		}
		// create the game loop thread
		gameThread = new GameThread(getHolder(), this);
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		//getHolder().
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
		Finger.update(event);
		return true;
	}

	protected void onDraw(Canvas canvas) {
		try
		{
			canvas.drawColor(Color.BLACK);
			l.Draw(canvas, paint);
			archie.Draw(canvas);
			right.Draw(canvas);
			left.Draw(canvas);
			up.Draw(canvas);
			down.Draw(canvas);
		}
		catch(Exception ex)
		{
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
