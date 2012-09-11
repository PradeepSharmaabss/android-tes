package com.example.warlockgame;

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
					this.renderThread.archie.Update();
					this.renderThread.left.Update();
					this.renderThread.right.Update();
					this.renderThread.down.Update();
					this.renderThread.up.Update();
					if(Finger.down==true)
					{
						this.renderThread.archie.StartTo(Finger.position);
					}
					if(this.renderThread.right.down)
						this.renderThread.archie.position.x += 3;
					if(this.renderThread.left.down)
						this.renderThread.archie.position.x -= 3;
					if(this.renderThread.down.down)
						this.renderThread.archie.position.y += 3;
					if(this.renderThread.up.down)
						this.renderThread.archie.position.y -= 3;
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

