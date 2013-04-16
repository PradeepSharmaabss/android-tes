package com.example.warlockgame;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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
				Intent myIntent = new Intent(MenuActivity.this,
						WarlockGame.class);
				MenuActivity.this.startActivity(myIntent);
			}
		});
		final Button B2 = (Button) findViewById(R.id.button2);
		B2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				RenderThread.gameObjects.clear();

				Intent myIntent = new Intent(MenuActivity.this,
						WarlockGame.class);
				MenuActivity.this.startActivity(myIntent);
			}
		});
		final Button B3 = (Button) findViewById(R.id.button3);
		B3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				RenderThread.gameObjects.clear();

				RenderThread.loaded = false;
				Intent myIntent = new Intent(MenuActivity.this,
						WarlockGame.class);
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
