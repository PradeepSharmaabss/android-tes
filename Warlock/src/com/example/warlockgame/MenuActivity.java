package com.example.warlockgame;

import Platform.LevelShape;
import World.Level;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;

public class MenuActivity extends Activity {
    void MenuActivity()
    {
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
                if (RenderThread.l != null)
                    Level.levelShape = LevelShape.Ellipse;
                RenderThread.loaded = false;
                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });
        final Button B3 = (Button) findViewById(R.id.button3);
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RenderThread.gameObjects.clear();
                Level.levelShape = LevelShape.Donut;
                RenderThread.loaded = false;
                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });
        final Button B4 = (Button) findViewById(R.id.button4);
        B4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RenderThread.gameObjects.clear();
                Level.levelShape = LevelShape.Rectangle;
                RenderThread.loaded = false;
                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

    }
    void MenuActivity2pt1()
    {
        setContentView(R.layout.activity_menu2);

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
            setContentView(R.layout.singleplayeroption);
            }
        });
        final Button B3 = (Button) findViewById(R.id.button3);
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RenderThread.gameObjects.clear();
                Level.levelShape = LevelShape.Donut;
                RenderThread.loaded = false;
                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });
        final Button B4 = (Button) findViewById(R.id.button4);
        B4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RenderThread.gameObjects.clear();
                Level.levelShape = LevelShape.Rectangle;
                RenderThread.loaded = false;
                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });

    }

    void MenuActivity2pt2()
    {
        setContentView(R.layout.singleplayeroption);


        final Button B1 = (Button) findViewById(R.id.button);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RadioGroup r = (RadioGroup)findViewById(R.id.radioOptions);
                switch (r.getCheckedRadioButtonId())
                {
                    case R.id.radioButton:
                        break;
                    case R.id.radioButton2:
                        break;
                    case R.id.radioButton3:
                        break;
                }

                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });


    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        MenuActivity();
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
