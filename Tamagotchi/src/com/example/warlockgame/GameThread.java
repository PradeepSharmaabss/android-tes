package com.example.warlockgame;

import HUD.Button;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceHolder;

/**
 * @author impaler
 * 
 *         The Main thread which contains the game loop. The thread must have
 *         access to the surface view and holder to trigger events every game
 *         tick.
 */
public class GameThread extends Thread {

	private static final String TAG = GameThread.class.getSimpleName();
	static final long FPS = 60;
	// Surface holder that can access the physical surface
	private final SurfaceHolder surfaceHolder;
	// The actual view that handles inputs
	// and draws to the surface
	private final RenderThread renderThread;

	// flag to hold game state
	private static boolean running;

	public static void setRunning(boolean _running) {
		running = _running;
	}

	public GameThread(SurfaceHolder surfaceHolder, RenderThread gamePanel) {
		super();
		this.surfaceHolder = surfaceHolder;
		this.renderThread = gamePanel;
	}

	public static final void setIsWifiP2pEnabled(boolean b) {

	}

	public void Update() {
		// boolean f = false;
		int selectedSpell = -1;
		// Chekcs Which Buttons are Down, the last down one in order of left to
		// right becomes the selected spell
		for (Button b : this.renderThread.buttons) {
			b.Update();
			if (b.down)
				selectedSpell = this.renderThread.buttons.indexOf(b);
		}
		if (selectedSpell == 1) {

			Log.d("FEEDING", "FED");
			RenderThread.Tama.Feed(0);
		}
		for (GameObject g : RenderThread.gameObjects)
			g.Update();

	}

	@Override
	public void run() {
		long ticksPS = 1000 / FPS;
		long startTime;
		long sleepTime;
		Canvas canvas;
		Log.d(TAG, "Starting game loop");
		while (running) {
			canvas = null;
			startTime = System.currentTimeMillis();
			// try locking the canvas for exclusive pixel editing
			// in the surface
			try {

				canvas = this.surfaceHolder.lockCanvas();
				synchronized (this.surfaceHolder) {
					Update();

					// update game state
					// render state to the screen
					// draws the canvas on the panel
					this.renderThread.onDraw(canvas);
				}
			} finally {
				// in case of an exception the surface is not left in
				// an inconsistent state
				if (canvas != null)
					this.surfaceHolder.unlockCanvasAndPost(canvas);
			} // end finally
			sleepTime = ticksPS - (System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {
			}
		}
	}

}
