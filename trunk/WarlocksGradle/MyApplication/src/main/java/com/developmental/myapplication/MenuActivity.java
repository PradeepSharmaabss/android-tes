package com.developmental.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Switch;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.RealTimeSocket;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.Participant;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;

import java.util.ArrayList;
import java.util.List;

import Input.NetworkFinger;
import Tools.Serializer;
import World.Level;

public class MenuActivity extends BaseGameActivity implements RoomUpdateListener, RealTimeMessageReceivedListener,RoomStatusUpdateListener, OnInvitationReceivedListener {
    int CurrentPage =-1;

            int PreviousPage=-1;
public static SoundPool sp;

    void StartMenu()
    {
       // ((TextView)findViewById(R.id.textView)).setText(getGamesClient().getCurrentAccountName()) ;
        final Button B1 = (Button) findViewById(R.id.button1);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startGame();
            }
        });
        final Button B2 = (Button) findViewById(R.id.button2);
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            scv(R.layout.singleplayeroption);
            }
        });
        final Button B3 = (Button) findViewById(R.id.button3);
        if(isSignedIn())
        {
        B3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               scv( R.layout.multiplayeroptions);
            }
        });
            B3.setVisibility(View.VISIBLE);
        }
        else B3.setVisibility(View.INVISIBLE);
        ((Button)findViewById(R.id.button4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signOut();
                scv(R.layout.login_layout);
            }
        });
        ((Button)findViewById(R.id.button5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signOut();
                scv(R.layout.shop);
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
                startGame();
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
                try {

                    beginUserInitiatedSignIn();
                }
                catch (IllegalStateException e)
                {
                    e.printStackTrace();
                }
                }
            });
                ((Button)findViewById(R.id.changetomenu)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                          scv(R.layout.activity_menu2);
                    }
                });
break;
            case R.layout.shop:
              ListView l=  (ListView)findViewById(R.id.listView);
                ListView l2=  (ListView)findViewById(R.id.listView2);
                ListView l3=  (ListView)findViewById(R.id.listView3);
                l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        switch (i)
                        {
                            case 0:

                                 break;

                        }
                    }
                });
            break;




        }
    }


    void MultiplayerOptions()
    {
//if(isSignedIn())
//        getGamesClient().unlockAchievement("CgkIyNWg07IKEAIQAQ");
        final Button B7 = (Button) findViewById(R.id.createroom);

//Get Ip Address Info
//        try {
//            String  ownIP =new NetTask().execute().get();
//            ((TextView)findViewById(R.id.textView3)).setText("Waiting for Connections\n IP ADDRESS:"+ ownIP);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        }

        final Button B8 = (Button) findViewById(R.id.quickmatch);
        B8.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startQuickGame();
            }
        });
        B7.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
              //launch the player selection screen
                // minimum: 1 other player; maximum: 3 other players
                Intent intent = getGamesClient().getSelectPlayersIntent(1,1);
                startActivityForResult(intent, RC_SELECT_PLAYERS);
            }
        });
    }// request code for the "select players" UI
    // can be any number as long as it's unique
    final static int RC_SELECT_PLAYERS = 10000;

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
   public static int explosion=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
        sp = new SoundPool(5, AudioManager.STREAM_MUSIC,0);
        explosion=sp.load(this,R.raw.boom,1);
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

    scv(R.layout.activity_menu2);
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


    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.optionsmenu, menu);
		return true;
	}
    @Override
    public void onActivityResult(int request, int response, Intent data) {
        super.onActivityResult(request, response, data);
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
        if (request == RC_SELECT_PLAYERS) {
            if (response != Activity.RESULT_OK) {
                // user canceled
                return;
            }

            // get the invitee list
            Bundle extras = data.getExtras();
            final ArrayList<String> invitees =
                    data.getStringArrayListExtra(GamesClient.EXTRA_PLAYERS);

            // get automatch criteria
            Bundle autoMatchCriteria = null;
            int minAutoMatchPlayers =
                    data.getIntExtra(GamesClient.EXTRA_MIN_AUTOMATCH_PLAYERS, 0);
            int maxAutoMatchPlayers =
                    data.getIntExtra(GamesClient.EXTRA_MAX_AUTOMATCH_PLAYERS, 0);

            if (minAutoMatchPlayers > 0) {
                autoMatchCriteria =
                        RoomConfig.createAutoMatchCriteria(
                                minAutoMatchPlayers, maxAutoMatchPlayers, 0);
            } else {
                autoMatchCriteria = null;
            }

            // create the room and specify a variant if appropriate
            RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
            roomConfigBuilder.addPlayersToInvite(invitees);
            if (autoMatchCriteria != null) {
                roomConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
            }
            hosting=true;
            RoomConfig roomConfig = roomConfigBuilder.build();
            getGamesClient().createRoom(roomConfig);

            // prevent screen from sleeping during handshake
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        if (request == RC_WAITING_ROOM) {
            if (response == Activity.RESULT_OK) {

                // (start game)
              Participants= mRoom.getParticipants();
             //   realTimeSockets= new ArrayList<RealTimeSocket>(Participants.size());
                Global.Multiplayer=true;
                for(int p=0;p<Participants.size();p++)
                {
                    Log.d("INET", "THERE IS A PARTICIPANT HERE");
                 //   realTimeSockets.set(p,getGamesClient().getRealTimeSocketForParticipant(mRoom.getRoomId(),Participants.get(p).getParticipantId()));
                }
                Global.Players =2;
                Global.playerno=hosting?0:1;

                GameThread.gamesClient=getGamesClient();
                GameThread.room= mRoom;
                Global.LEFT_HAND_MODE=false;
                startGame();

            }
            else if (response == Activity.RESULT_CANCELED) {
                // Waiting room was dismissed with the back button. The meaning of this
                // action is up to the game. You may choose to leave the room and cancel the
                // match, or do something else like minimize the waiting room and
                // continue to connect in the background.

                // in this example, we take the simple approach and just leave the room:
                getGamesClient().leaveRoom(this, mRoom.getRoomId());
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
            else if (response == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                // player wants to leave the room.
                getGamesClient().leaveRoom(this, mRoom.getRoomId());
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        }
    }
    RenderThread renderThread;
    void startGame()
    {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
       //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the View
        Display display = getWindowManager().getDefaultDisplay();
        android.graphics.Point size = new android.graphics.Point();
        display.getSize(size);
        if(!RenderThread.loaded)
            this.renderThread = new RenderThread(this, size);


        setContentView(this.renderThread);
    }

    private boolean hosting = false;

    public static ArrayList<Participant> Participants;
    public static ArrayList<RealTimeSocket> realTimeSockets;
   public Room mRoom;
    @Override
    public void onRoomCreated(int statusCode, Room room) {
        if (statusCode != GamesClient.STATUS_OK) {
            // display error
            showAlert("ERROR");
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
        byte[] b = realTimeMessage.getMessageData();

        NetworkFinger f = Serializer.DeserializefromFiletoVector(b);
      //  int x = hosting?1:0;
        GameThread.fingers.add(f);
       // RenderThread.players.get(x).FingerUpdate(f.finger,f.SelectedSpell);
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        Global.alive=true;
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
    public void onPause() {

        super.onPause(); // Always call the superclass method first

        Global.alive = false;
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
