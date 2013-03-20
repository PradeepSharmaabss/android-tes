package com.example.warlockgame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.Channel;
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
		this.intentFilter
				.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);

		// Indicates a change in the list of available peers.
		this.intentFilter
				.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);

		// Indicates the state of Wi-Fi P2P connectivity has changed.
		this.intentFilter
				.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);

		// Indicates this device's details have changed.
		this.intentFilter
				.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);
		this.mManager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
		this.mChannel = this.mManager.initialize(this, getMainLooper(), null);
	}

	Channel mChannel;
	private final IntentFilter intentFilter = new IntentFilter();
	private WifiP2pManager mManager;
	WifiDirectThread reciever;

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
		this.reciever = new WifiDirectThread(this.mManager, this.mChannel, this);

		registerReceiver(this.reciever, this.intentFilter);
		super.onResume();

	}

	@Override
	public void onPause() {
		Log.d(TAG, "Pausing...");
		GameThread.setRunning(false);
		super.onPause(); // Always call the superclass method first
		unregisterReceiver(this.reciever);
	}

	public void InitiatePeertoPeer() {

	}
}
