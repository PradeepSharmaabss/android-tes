package com.developmental.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import World.Level;

public class MenuActivity extends BaseGameActivity implements RoomUpdateListener, RealTimeMessageReceivedListener,RoomStatusUpdateListener, OnInvitationReceivedListener {
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

        ((Button)findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            signOut();
            scv(R.layout.login_layout);
        }
    });
        ((Button)findViewById(R.id.button)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getGamesClient().getAchievementsIntent(), 4535);

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
Global.Multiplayer=false;
                Intent myIntent = new Intent(MenuActivity.this,
                        WarlockGame.class);
                MenuActivity.this.startActivity(myIntent);
            }
        });


    }
    @Override
    public void onBackPressed()
    {
        if(PreviousPage!=-1)
  scv(PreviousPage);
        else super.onBackPressed();
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
            case R.layout.login_layout:
                    ((SignInButton)findViewById(R.id.goglesignin)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                beginUserInitiatedSignIn();
                }
            });
                ((Button)findViewById(R.id.changetomenu)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                      if(isSignedIn())
                          scv(R.layout.activity_menu2);
                    }
                });



        }
    }


    void MultiplayerOptions()
    {
if(isSignedIn())
        getGamesClient().unlockAchievement("CgkIyNWg07IKEAIQAQ");
        final Button B7 = (Button) findViewById(R.id.beginserver);

//Get Ip Address Info
//        try {
//            String  ownIP =new NetTask().execute().get();
//            ((TextView)findViewById(R.id.textView3)).setText("Waiting for Connections\n IP ADDRESS:"+ ownIP);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        final Button B8 = (Button) findViewById(R.id.joinserver);
        B8.setOnClickListener(new View.OnClickListener() {
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
                Global.Server=false;
                RenderThread.playerno= 1;
                Log.d("STARTING Multi PLAYER GAME!", " ");
                             AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                alert.setMessage("IP ADDRESS");
                final EditText input = new EditText(MenuActivity.this);

                input.setText("192.168.1.10");
                alert.setView(input);
                Global.Multiplayer=true;
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {


                        Global.SAddress = input.getText().toString();

                        ServerThread.Connect(Global.SAddress);
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
              //  Global.Server=false;
                Log.d("STARTING Multi PLAYER GAME!", " ");
                RenderThread.playerno=0;
                AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
                alert.setMessage("IP ADDRESS");
                final TextView input = new TextView(MenuActivity.this);

                try {
                    String  ownIP =new NetTask().execute().get();
                    input.setText("Waiting for Connections\n IP ADDRESS:"+ ownIP);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }



                alert.setView(input);
                Global.Server=true;
Global.Multiplayer=true;
                alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        // Global.SAddress = input.getText().toString();
                        Intent myIntent = new Intent(MenuActivity.this,
                                WarlockGame.class);
                        MenuActivity.this.startActivity(myIntent);
                        //call a unction/void which is using the public var playerName
                    }
                });
                alert.show();
                try {
                    (new ServerThread(ServerThread.ActionType.AcceptConnections)).start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // the variable playerName is NULL at this point
                //  Global.SAddress=(String)getText(R.id.editText);

            }
        });
    }
    void CreateServer()
    {
        getGamesClient().createRoom();
    }
    private void startQuickGame() {
        // automatch criteria to invite 1 random automatch opponent.
        // You can also specify more opponents (up to 3).
        Bundle am = RoomConfig.createAutoMatchCriteria(1, 1, 0);

        // build the room config:
        RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
        roomConfigBuilder.setAutoMatchCriteria(am);
        RoomConfig roomConfig = roomConfigBuilder.build();

        // create room:
        getGamesClient().createRoom(roomConfig);

        // prevent screen from sleeping during handshake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        // go to game screen
    }
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
        scv(R.layout.login_layout);
			}
@Override
    public void onSignInFailed() {
    AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
    alert.setMessage("Failed");
    alert.show();
    }
    private RoomConfig.Builder makeBasicRoomConfigBuilder() {
        return RoomConfig.builder(this)
                .setMessageReceivedListener(this)
                .setRoomStatusUpdateListener(this);
    }


    // request code (can be any number, as long as it's unique)
    final int RC_INVITATION_INBOX = 10001;
@Override
    public void onSignInSucceeded() {
    if (getInvitationId() != null) {

        RoomConfig.Builder roomConfigBuilder =
                makeBasicRoomConfigBuilder();
        roomConfigBuilder.setInvitationIdToAccept(getInvitationId());
        getGamesClient().joinRoom(roomConfigBuilder.build());

        // prevent screen from sleeping during handshake
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getGamesClient().registerInvitationListener(this);
        // go to game screen
    }


// launch the intent to show the invitation inbox screen
    Intent intent = getGamesClient().getInvitationInboxIntent();
    this.startActivityForResult(intent, RC_INVITATION_INBOX);
        scv(R.layout.activity_menu2);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optionsmenu, menu);
		return true;
	}
    @Override
    public void onActivityResult(int request, int response, Intent data) {
        if (request == RC_INVITATION_INBOX) {
            if (response != Activity.RESULT_OK) {
                // canceled
                return;
            }

            // get the selected invitation
            Bundle extras = data.getExtras();
            Invitation invitation =
                    extras.getParcelable(GamesClient.EXTRA_INVITATION);

            // accept it!
            RoomConfig roomConfig = makeBasicRoomConfigBuilder()
                    .setInvitationIdToAccept(invitation.getInvitationId())
                    .build();
            getGamesClient().joinRoom(roomConfig);

            // prevent screen from sleeping during handshake
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            // go to game screen
        }
        if (request == RC_WAITING_ROOM) {
            if (response == Activity.RESULT_OK) {
                // (start game)
              Participants= mRoom.getParticipants();
            }
            else if (response == Activity.RESULT_CANCELED) {
                // Waiting room was dismissed with the back button. The meaning of this
                // action is up to the game. You may choose to leave the room and cancel the
                // match, or do something else like minimize the waiting room and
                // continue to connect in the background.

                // in this example, we take the simple approach and just leave the room:
                getGamesClient().leaveRoom(this, null);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
            else if (response == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                // player wants to leave the room.
                getGamesClient().leaveRoom(this, null);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        }
    }
    ArrayList<Participant> Participants;
    Room mRoom;
    @Override
    public void onRoomCreated(int statusCode, Room room) {
        if (statusCode != GamesClient.STATUS_OK) {
            // display error
            return;
        }
        mRoom=room;
        // get waiting room intent
        Intent i = getGamesClient().getRealTimeWaitingRoomIntent(room, Integer.MAX_VALUE);
        startActivityForResult(i, RC_WAITING_ROOM);
    }
    final static int RC_WAITING_ROOM = 10002;
    @Override
    public void onJoinedRoom(int statusCode, Room room) {
        if (statusCode != GamesClient.STATUS_OK) {
            // display error
            return;
        }

        // get waiting room intent
        Intent i = getGamesClient().getRealTimeWaitingRoomIntent(room, Integer.MAX_VALUE);
        startActivityForResult(i, RC_WAITING_ROOM);
    }

    @Override
    public void onLeftRoom(int i, String s) {

    }

    @Override
    public void onRoomConnected(int i, Room room) {

    }

    @Override
    public void onRealTimeMessageReceived(RealTimeMessage realTimeMessage) {

    }

    @Override
    public void onRoomConnecting(Room room) {

    }

    @Override
    public void onRoomAutoMatching(Room room) {

    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> strings) {

    }

    @Override
    public void onPeerDeclined(Room room, List<String> strings) {

    }

    @Override
    public void onPeerJoined(Room room, List<String> strings) {

    }

    @Override
    public void onPeerLeft(Room room, List<String> strings) {

    }

    @Override
    public void onConnectedToRoom(Room room) {

    }

    @Override
    public void onDisconnectedFromRoom(Room room) {

    }

    @Override
    public void onPeersConnected(Room room, List<String> strings) {

    }

    @Override
    public void onPeersDisconnected(Room room, List<String> strings) {

    }

String mIncomingInvitationId;
    @Override
    public void onInvitationReceived(Invitation invitation) {

        mIncomingInvitationId = invitation.getInvitationId();
        AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
        Global.Multiplayer=true;
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {




                //call a unction/void which is using the public var playerName
            }
        });
        alert.setNegativeButton("NO",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
                roomConfigBuilder.setInvitationIdToAccept(mIncomingInvitationId);
                getGamesClient().joinRoom(roomConfigBuilder.build());

// prevent screen from sleeping during handshake
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

// now, go to game screen
            }
        });
        alert.show();
    }
}
