package com.example.androidproject;


import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;
import android.view.Window;
import android.view.WindowManager;

@SuppressLint("NewApi")
public class MainActivity extends Activity {
	Screen screen;
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	 super.onCreate(savedInstanceState);
         // Set full screen view
         getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
         Display display = getWindowManager().getDefaultDisplay();
         android.graphics.Point size = new android.graphics.Point();
         display.getSize(size);
         
         requestWindowFeature(Window.FEATURE_NO_TITLE);
         Screen.size = new Vector(size.x,size.y);
         screen = new Screen(this);
         setContentView(screen);
         screen.requestFocus();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
