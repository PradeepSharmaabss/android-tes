package com.example.warlockgame;

import Game.GameObject;
import Game.Projectile;
import HUD.Button;
import Input.Finger;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;
import android.view.SurfaceHolder;


/**
 * @author impaler
 *
 * The Main thread which contains the game loop. The thread must have access to 
 * the surface view and holder to trigger events every game tick.
 */
public class GameThread extends Thread {
	
	private static final String TAG = GameThread.class.getSimpleName();

	// Surface holder that can access the physical surface
	private SurfaceHolder surfaceHolder;
	// The actual view that handles inputs
	// and draws to the surface
	private RenderThread renderThread;
	
	// flag to hold game state 
	private boolean running;
	public void setRunning(boolean running) {
		this.running = running;
	}
	
	public GameThread(SurfaceHolder surfaceHolder, RenderThread gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.renderThread = gamePanel;
	}
	public void Update()
	{
		boolean f = false;
		for(Button b : this.renderThread.buttons)
		{
			b.Update();

			if(b.down)
				f = true;
		}
		//GameObject.WithinIsoTile(Finger.position,RenderThread.l.map);
		if(f==true&&Finger.sz()>=2)
		{
			for(int w = 0; w < 10;w++)
			{
				if(Finger.pointers.get(w).WithinScreen()&&Finger.pointers.get(w).down)						
				RenderThread.gameObjects.get(0).Shoot(Finger.pointers.get(w).position.get());	
			}
		}
		for(int x = 0; x < RenderThread.gameObjects.size(); x++)
		{
			RenderThread.gameObjects.get(x).Update();
			for(int y = 0; y < RenderThread.gameObjects.size(); y++)
			{
				boolean collided = false;
				if(y!=x)
				{
					if(RectF.intersects(RenderThread.gameObjects.get(x).rect, RenderThread.gameObjects.get(y).rect))
					{
						RenderThread.gameObjects.get(x).Collision(RenderThread.gameObjects.get(y));
					}
				}
			}
		}
	}
	@Override
	public void run() {
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (running) {
			canvas = null;
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {
				
				canvas = this.surfaceHolder.lockCanvas();
				synchronized (surfaceHolder) {
					Update();
			
					// update game state 
					// render state to the screen
					// draws the canvas on the panel
					this.renderThread.onDraw(canvas);
				}
			} finally {
				// in case of an exception the surface is not left in 
				// an inconsistent state
				if (canvas != null) {
					surfaceHolder.unlockCanvasAndPost(canvas);
				}
			}	// end finally
		}
	}
	
}
