package com.example.warlockgame;

import java.util.ArrayList;
import java.util.List;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.NetworkInfo;
import android.net.wifi.WpsInfo;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pInfo;
import android.net.wifi.p2p.WifiP2pManager;
import android.net.wifi.p2p.WifiP2pManager.ActionListener;
import android.net.wifi.p2p.WifiP2pManager.Channel;
import android.net.wifi.p2p.WifiP2pManager.PeerListListener;
import android.util.Log;
import android.widget.Toast;

@TargetApi(14)
public class WifiDirectThread extends BroadcastReceiver implements
		WifiP2pManager.PeerListListener, WifiP2pManager.ConnectionInfoListener {
	public WifiDirectThread(WifiP2pManager mManager, Channel mChannel,
			Activity warlockGame) {
		this.mChannel = mChannel;
		this.mManager = mManager;
		this.Parent = warlockGame;
		// TODO Auto-generated constructor stub
	}

	Activity Parent;
	Channel mChannel;
	private final IntentFilter intentFilter = new IntentFilter();
	private final WifiP2pManager mManager;

	void InitiatePeertoPeer() {
		this.mManager.discoverPeers(this.mChannel,
				new WifiP2pManager.ActionListener() {
					public void onSuccess() {
						// Code for when the discovery initiation is successful
						// goes here.
						// No services have actually been discovered yet, so
						// this method
						// can often be left blank. Code for peer discovery goes
						// in the
						// onReceive method, detailed below.
					}

					public void onFailure(int reasonCode) {
						// Code for when the discovery initiation fails goes
						// here.
						// Alert the user that something went wrong.
					}
				});
	}

	private final List<WifiP2pDevice> peers = new ArrayList<WifiP2pDevice>();

	private final PeerListListener peerListListener = new PeerListListener() {

		public void onPeersAvailable(WifiP2pDeviceList peerList) {

			// Out with the old, in with the new.
			WifiDirectThread.this.peers.clear();
			WifiDirectThread.this.peers.addAll(peerList.getDeviceList());

			// If an AdapterView is backed by this data, notify it
			// of the change. For instance, if you have a ListView of available
			// peers, trigger an update.
			// ((WiFiPeerListAdapter) getListAdapter()).notifyDataSetChanged();
			if (WifiDirectThread.this.peers.size() == 0) {
				Log.d("WifiDirectThread.class", "No devices found");
				return;
			}
		}
	};

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
		if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
			// Determine if Wifi Direct mode is enabled or not, alert
			// the Activity.
			int state = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
			if (state == WifiP2pManager.WIFI_P2P_STATE_ENABLED)
				GameThread.setIsWifiP2pEnabled(true);
			else
				GameThread.setIsWifiP2pEnabled(false);
		} else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
			if (this.mManager != null)
				this.mManager
						.requestPeers(this.mChannel, this.peerListListener);
			Log.d("EEE", "P2P peers changed");
			// The peer list has changed! We should probably do something about
			// that.

		} else if (WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION
				.equals(action)) {
			if (this.mManager == null)
				return;

			NetworkInfo networkInfo = (NetworkInfo) intent
					.getParcelableExtra(WifiP2pManager.EXTRA_NETWORK_INFO);

			if (networkInfo.isConnected()) {

				// We are connected with the other device, request connection
				// info to find group owner IP

				// ConnectionInfoListener connectionListener;
				// this.mManager.requestConnectionInfo(this.mChannel,
				// connectionListener);
			}
			// Connection state changed! We should probably do something about
			// that.

		} else if (WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION
				.equals(action)) {
			// DeviceListFragment fragment = (DeviceListFragment)
			// activity.getFragmentManager()
			// .findFragmentById(R.id.frag_list);
			// fragment.updateThisDevice((WifiP2pDevice)
			// intent.getParcelableExtra(
			// WifiP2pManager.EXTRA_WIFI_P2P_DEVICE));

		}
	}

	@TargetApi(14)
	public void connect() {
		this.Link = this.peers.get(0);
		WifiP2pConfig config = new WifiP2pConfig();
		config.deviceAddress = this.Link.deviceAddress;
		config.wps.setup = WpsInfo.PBC;

		this.mManager.connect(this.mChannel, config, new ActionListener() {

			public void onSuccess() {
				// WiFiDirectBroadcastReceiver will notify us. Ignore for now.
			}

			public void onFailure(int reason) {
				Toast.makeText(WifiDirectThread.this.Parent,
						"Connect failed. Retry.", Toast.LENGTH_SHORT).show();
			}
		});
	}

	WifiP2pDevice Link;

	public void onPeersAvailable(WifiP2pDeviceList peers) {
		// TODO Auto-generated method stub

	}

	public void onConnectionInfoAvailable(WifiP2pInfo info) {
		// TODO Auto-generated method stub
		// InetAddress from WifiP2pInfo struct.
		String groupOwnerAddress = info.groupOwnerAddress.getHostAddress();

		// After the group negotiation, we can determine the group owner.
		if (info.groupFormed && info.isGroupOwner) {
			// Do whatever tasks are specific to the group owner.
			// One common case is creating a server thread and accepting
			// incoming connections.
		} else if (info.groupFormed) {
			// The other device acts as the client. In this case,
			// you'll want to create a client thread that connects to the group
			// owner.
		}
	}
}
