package com.example.warlockgame;

import World.Level;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;

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
                    Level.levelShape = Level.LevelShape.Ellipse;
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
                Level.levelShape = Level.LevelShape.Donut;
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
                Level.levelShape = Level.LevelShape.Rectangle;
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
            MenuActivity2pt2();
            }
        });
        final Button B3 = (Button) findViewById(R.id.button3);
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            }
        });
        final Button B4 = (Button) findViewById(R.id.button4);
        B4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                RenderThread.gameObjects.clear();
                Level.levelShape = Level.LevelShape.Rectangle;
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


        final Button B7 = (Button) findViewById(R.id.singleplayerbeginbutton);
        B7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                RadioGroup r = (RadioGroup)findViewById(R.id.radioOptions);
                switch (r.getCheckedRadioButtonId())
                {
                    case R.id.radioButton:
                        RenderThread.gameObjects.clear();

                            Level.levelShape = Level.LevelShape.Ellipse;
                        RenderThread.loaded = false;
                        break;
                    case R.id.radioButton2:
                        RenderThread.gameObjects.clear();

                            Level.levelShape = Level.LevelShape.Rectangle;
                        RenderThread.loaded = false;
                        break;
                    case R.id.radioButton3:
                        RenderThread.gameObjects.clear();

                            Level.levelShape = Level.LevelShape.Donut;
                        RenderThread.loaded = false;
                        break;
                }
                Switch s = (Switch)findViewById(R.id.debug);
                Global.DEBUG_MODE = s.isChecked();
                s = (Switch)findViewById(R.id.lefthandmode);
                Global.LEFT_HAND_MODE=s.isChecked();

                Log.d("STARTING SINGLE PLAYER GAME!", " ");

                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });


    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        MenuActivity2pt1();
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
