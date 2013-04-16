package com.example.warlockgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

public class WarlockGame extends Activity {

	private static final String TAG = WarlockGame.class.getSimpleName();
	RenderThread renderThread;

	@SuppressLint("NewApi")
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set our MainGamePanel as the View
		Display display = getWindowManager().getDefaultDisplay();
		android.graphics.Point size = new android.graphics.Point();
		display.getSize(size);
		this.renderThread = new RenderThread(this, size);

		// renderThread.size = size;
		setContentView(this.renderThread);
		Log.d(TAG, "View added");
		// Indicates a change in the Wi-Fi Peer-to-Peer status.

	}

	@Override
	protected void onDestroy() {
		Log.d(TAG, "Destroying...");
		super.onDestroy();
	}

	@Override
	protected void onStop() {
		Log.d(TAG, "Stopping...");
		super.onStop();
	}

	@Override
	protected void onResume() {
		GameThread.setRunning(false);
		super.onResume();

	}

	@Override
	public void onPause() {
		Log.d(TAG, "Pausing...");
		GameThread.setRunning(false);
		super.onPause(); // Always call the superclass method first

	}

	public void InitiatePeertoPeer() {

	}
}
