package com.example.warlockgame;

import World.Level;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

public class MenuActivity extends Activity {
    int CurrentPage =-1;

            int PreviousPage=-1;
    void MenuActivity()
    {
        setContentView(R.layout.activity_menu);
        PreviousPage=CurrentPage;
        CurrentPage =R.layout.activity_menu;
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
    void StartMenu()
    {

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
            scv(R.layout.singleplayeroption);
            }
        });
        final Button B3 = (Button) findViewById(R.id.button3);
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               scv( R.layout.multiplayeroptions);
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

    void SinglePlayerOptions()
    {

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
    public void onBackPressed()
    {
  scv(PreviousPage);
    }
    private void scv(int LayoutName)
    {
        setContentView(LayoutName);
        PreviousPage=CurrentPage;
        CurrentPage =LayoutName;
        switch (LayoutName)
        {
            case R.layout.multiplayeroptions:
                MultiplayerOptions();
                break;
            case R.layout.singleplayeroption:
                SinglePlayerOptions();
                break;
            case R.layout.activity_menu2:
                StartMenu();
                break;
        }
    }
    void MultiplayerOptions()
    {


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
                Switch se = (Switch)findViewById(R.id.switch1);
                Global.Server=se.isChecked();
                Log.d("STARTING Multi PLAYER GAME!", " ");
                AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                alert.setMessage("IP ADDRESS");
                final EditText input = new EditText(MenuActivity.this);
                input.setText("192.168.1.9");
                alert.setView(input);
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        Global.SAddress = input.getText().toString();
                        Intent myIntent = new Intent(MenuActivity.this,
                                WarlockGame.class);
                        MenuActivity.this.startActivity(myIntent);
                        //call a unction/void which is using the public var playerName
                    }
                });
                alert.show();
                // the variable playerName is NULL at this point
              //  Global.SAddress=(String)getText(R.id.editText);

            }
        });


    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
       scv(R.layout.activity_menu2);
			}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_menu, menu);
		return true;
	}

}
