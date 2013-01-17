package com.example.warlockgame;

import Platform.DonutPlatform;
import Platform.EllipticalPlatform;
import Tools.Vector;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu);
		

		final Button B1 = (Button) findViewById(R.id.button1);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
           	 Intent myIntent = new Intent(MenuActivity.this, WarlockGame.class);
           	 MenuActivity.this.startActivity(myIntent);
            }
        });
    	final Button B2 = (Button) findViewById(R.id.button2);
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	RenderThread.gameObjects.clear();
            	if(RenderThread.l!=null)
            	RenderThread.l.platform = new EllipticalPlatform(
    					new Vector(2800,900), 
    					new Vector(2500,1250)
    				);
           	 Intent myIntent = new Intent(MenuActivity.this, WarlockGame.class);
           	 MenuActivity.this.startActivity(myIntent);
            }
        });
     	final Button B3 = (Button) findViewById(R.id.button3);
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            	RenderThread.gameObjects.clear();
            	if(RenderThread.l!=null)
            	RenderThread.l.platform = new DonutPlatform(
    					new Vector(2800,900), 
    					new Vector(2500,1250), 
    					new Vector(1000,500)
    				);
           	 Intent myIntent = new Intent(MenuActivity.this, WarlockGame.class);
           	 MenuActivity.this.startActivity(myIntent);
            }
        });
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
