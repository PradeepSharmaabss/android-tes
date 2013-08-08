package com.developmental.myapplication;

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
        //HARDWARE ACCELERATED
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		// set our MainGamePanel as the View
		Display display = getWindowManager().getDefaultDisplay();
		android.graphics.Point size = new android.graphics.Point();
		display.getSize(size);
        if(!RenderThread.loaded)
		this.renderThread = new RenderThread(this, size);

		setContentView(this.renderThread);
		Log.d(TAG, "View added");

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
//		this.reciever = new WifiDirectThread(this.mManager, this.mChannel, this);}
//		registerReceiver(this.reciever, this.intentFilter);
		super.onResume();
        Global.alive=true;
        //this.renderThread.gameThread.run();
	}

	@Override
	public void onPause() {

		super.onPause(); // Always call the superclass method first
//		unregisterReceiver(this.recieve);

        Log.d(TAG, "Pausing...");
      Global.alive = false;
	}
}
