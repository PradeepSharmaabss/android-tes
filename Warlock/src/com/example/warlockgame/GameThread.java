package com.example.warlockgame;

import java.util.Collections;

import HUD.Button;
import Input.Finger;
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

	private static boolean isWifiP2PEnabled = false;

	public static final void setIsWifiP2pEnabled(boolean b) {
		isWifiP2PEnabled = b;

	}

	public void  Update() {

		// boolean f = false;
		int selectedSpell = -1;
		// Chekcs Which Buttons are Down, the last down one in order of left to
		// right becomes the selected spell
		for (Button b : this.renderThread.buttons) {
			b.Update();
			if (b.down)
				selectedSpell = this.renderThread.buttons.indexOf(b);
		}

		if (selectedSpell != -1)
			RenderThread.archie.Spells[selectedSpell].Cast(Finger.pointers);

		Collision();
		Collections.sort(RenderThread.gameObjects);

	}

	public void Collision() {

        for (int x = 0; x < RenderThread.gameObjects.size(); x++) {
			for (int y = 0; y < RenderThread.gameObjects.size(); y++)
				if (RenderThread.gameObjects.size() > y
						&& RenderThread.gameObjects.size() > x)
					if (y != x)
						if (RenderThread.gameObjects.get(x).owner == null
								|| RenderThread.gameObjects.get(y).owner == null)// no
																					// owner
																					// set
																					// ,
																					// collide
																					// with
																					// all.
						{
							if (RenderThread.gameObjects.get(x).Intersect(
									RenderThread.gameObjects.get(y).rect))
								RenderThread.gameObjects.get(y).Collision(
										RenderThread.gameObjects.get(x));
						} else if (RenderThread.gameObjects.get(x).owner.id != RenderThread.gameObjects
								.get(y).id
								&& RenderThread.gameObjects.get(y).owner.id != RenderThread.gameObjects
										.get(x).id)
							if (RenderThread.gameObjects.get(x).Intersect(
									RenderThread.gameObjects.get(y).rect))
								RenderThread.gameObjects.get(y).Collision(
										RenderThread.gameObjects.get(x));
		}
        for (int v = 0; v < RenderThread.gameObjects.size(); v++) {
            RenderThread.gameObjects.get(v).Update();
        }
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
