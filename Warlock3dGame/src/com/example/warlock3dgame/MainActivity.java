package com.example.warlock3dgame;


import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ConfigurationInfo;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	/** Hold a reference to our GLSurfaceView */
	private GameSurfaceView mGLSurfaceView;
	private GameRenderer mRenderer;
	
	private int mMinSetting = -1;
	private int mMagSetting = -1;
	
	private static final String MIN_SETTING = "min_setting";
	private static final String MAG_SETTING = "mag_setting";
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//setContentView(R.layout.activity_main);
		
		mGLSurfaceView = new GameSurfaceView(this);

		// Check if the system supports OpenGL ES 2.0.
		final ActivityManager activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
		final ConfigurationInfo configurationInfo = activityManager.getDeviceConfigurationInfo();
		final boolean supportsEs2 = configurationInfo.reqGlEsVersion >= 0x20000;

		if (supportsEs2) 
		{
			// Request an OpenGL ES 2.0 compatible context.
			mGLSurfaceView.setEGLContextClientVersion(2);
			
			final DisplayMetrics displayMetrics = new DisplayMetrics();
			getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

			// Set the renderer to our demo renderer, defined below.
			mRenderer = new GameRenderer(this);
			mGLSurfaceView.setRenderer(mRenderer, displayMetrics.density);					
		}
		else 
		{
			// This is where you could create an OpenGL ES 1.x compatible
			// renderer if you wanted to support both ES 1 and ES 2.
			return;
		}
		
		// Restore previous settings
		if (savedInstanceState != null)
		{
			//mMinSetting = savedInstanceState.getInt(MIN_SETTING, -1);
			//mMagSetting = savedInstanceState.getInt(MAG_SETTING, -1);
			mMinSetting = -1;
			mMagSetting = -1;
			//if (mMinSetting != -1) { setMinSetting(mMinSetting); }
			//if (mMagSetting != -1) { setMagSetting(mMagSetting); }
		}
		setContentView(mGLSurfaceView);
	}

	@Override
	protected void onResume() 
	{
		// The activity must call the GL surface view's onResume() on activity
		// onResume().
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause() 
	{
		// The activity must call the GL surface view's onPause() on activity
		// onPause().
		super.onPause();
		mGLSurfaceView.onPause();
	}
	
	@Override
	protected void onSaveInstanceState (Bundle outState)
	{
		//outState.putInt(MIN_SETTING, mMinSetting);
		//outState.putInt(MAG_SETTING, mMagSetting);
	}
	
}
