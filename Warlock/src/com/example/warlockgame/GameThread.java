package com.example.warlockgame;

import Game.GameObject;
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
	
	public void Input(int id)
	{
		switch(id)
		{
		case 0:
			renderThread.archie.Command(id);
			break;
		case 1:
			
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
					
					for(GameObject g : RenderThread.gameObjects)
					{
						g.Update();
					}
					//this.renderThread.archie.Update();
					for(Button b : this.renderThread.buttons)
					{
						b.Update();
						if(b.down)
						{
							Input(b.id);
						}
							
					}
					
					if(Finger.down==true && Finger.position.y<RenderThread.size.y)
					{
						this.renderThread.archie.StartTo(Finger.position);
					}
			
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

