

package com.example.warlockgame;

import HUD.Button;
import Input.Finger;
import android.graphics.Canvas;
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
		//boolean f = false;
		int selectedSpell=-1;
		for(Button b : this.renderThread.buttons)
		{
			b.Update();
			if(b.down)
				selectedSpell = this.renderThread.buttons.indexOf(b);
		}
		
	
				
					if(selectedSpell!=-1)
					{
						RenderThread.archie.Spells[selectedSpell].Cast(Finger.pointers);
					}
				
			
		
		Collision();

	}
	public void Collision()
	{
		for(int x = 0; x < RenderThread.gameObjects.size(); x++)
		{
			RenderThread.gameObjects.get(x).Update();
			for(int y = 0; y < RenderThread.gameObjects.size(); y++)
			{
				if(RenderThread.gameObjects.size()> y && RenderThread.gameObjects.size() > x )
				{
					if(y != x)//not collide with self
					{
						if(		RenderThread.gameObjects.get(x).owner == null || 
								RenderThread.gameObjects.get(y).owner == null)//no owner set , collide with all.
						{
							if(RenderThread.gameObjects.get(x).Intersect(RenderThread.gameObjects.get(y).rect))
							{
								RenderThread.gameObjects.get(y).Collision(RenderThread.gameObjects.get(x));
							}
						}
						else if(RenderThread.gameObjects.get(x).owner.id != RenderThread.gameObjects.get(y).id &&
								RenderThread.gameObjects.get(y).owner.id != RenderThread.gameObjects.get(x).id	)
						{
							if(RenderThread.gameObjects.get(x).Intersect(RenderThread.gameObjects.get(y).rect))
							{
								RenderThread.gameObjects.get(y).Collision(RenderThread.gameObjects.get(x));
							}
						}
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

