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
import NPC.EllipseMovingAI;
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
	public static Archie archie;
	public static int r=0,g=0;
	public static Level l;
	public static int objects = 0;
	public List<Button> buttons = new ArrayList<Button>();
	public static Point size,trueSize;
	
	public GameThread gameThread;
	public static boolean loaded = false;
	
	
	public RenderThread(Context context, Point _size) {
		super(context);
		getHolder().addCallback(this);
		size = _size;
		trueSize = new Point(_size.x,_size.y);
		size.y -= size.y/5;
		Global.paint = new Paint();
		Global.paint.setAntiAlias(true);
		Global.paint.setColor(Color.RED);
		
		Load();
		
		// create the game loop thread
		gameThread = new GameThread(getHolder(), this);
		
		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		//getHolder().
	}
	public void Load()
	{
		
		if(gameObjects.size()==0)
		{
			// load sprite sheet
			archie = new Archie(new SpriteSheet(BitmapFactory.decodeResource(getResources(), R.drawable.charsheetedit), new Vector(32, 32)));
			addObject(archie);
			archie.position=new Vector(2800,900);
			
			Game.Block b = new Game.Block();
			b.position=new Vector(2800,380);
			addObject(b);

		}
		if(l == null)
		{
			l = new Level(
						new SpriteSheet(BitmapFactory.decodeResource(getResources(), R.drawable.isotiles),new Vector(32,32)),
						new Vector(100 ,100),
						BitmapFactory.decodeResource(getResources(), R.drawable.mousepos));
		}
		EllipseMovingAI e= new EllipseMovingAI();
		e.position = new Vector(2800,900);
		addObject(e);
		
		UserInterface();
	}
	
	public void UserInterface()
	{
		if(buttons.size() == 0)
		{
			int screenSize = size.x;
			for(int x=0; x < screenSize; x += screenSize/10)
			{
				buttons.add(
					new Button(
						new RectF(
								x,
								size.y,
								x + (screenSize/10),
								trueSize.y),
								x / (screenSize/10)
							)
				);
			}
		}
	}

	protected void onDraw(Canvas canvas) {
		canvas.drawColor(Color.GREEN);//buffer refresh color
		canvas.save();
		canvas.translate(-archie.position.x-archie.size.x/2, -archie.position.y);
		l.Draw(canvas, 0, 0);
		
		canvas.translate(size.x/2, size.y/2);
		
		int size = gameObjects.size()-1;
		for( int x=size;x>=0;x-- )//draw the objects back to front, so the first added are drawn on top.
			gameObjects.get(x).Draw(canvas);
		
		canvas.restore();
		
		for(int y = 0; y<10;y++)
			buttons.get(y).Draw(canvas,archie.Spells[y]);

	}

	public static void addObject(GameObject obj)
	{
		gameObjects.add(obj);
		gameObjects.get(gameObjects.size()-1).id = objects++;
	}
    
	public static void delObject(int id)
	{
		for(int x=0; x < gameObjects.size();x++)
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
		System.out.println("surface Changed");
		Load();
	}

	public void surfaceCreated(SurfaceHolder holder) {
		/*if(!gameThread.isAlive())
		{
			gameThread.setRunning(true);
			gameThread.start();
		}*/
		System.out.println("surface Created");
		//Load();
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
				gameThread.setRunning(false);
			}
		}
		Log.d(TAG, "Thread was shut down cleanly");
	}
	
	public boolean onTouchEvent(MotionEvent event) {
		Finger.Update(event);
		return true;
	}

}




