package com.developmental.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.gms.common.SignInButton;
import com.google.android.gms.games.GamesActivityResultCodes;
import com.google.android.gms.games.GamesClient;
import com.google.android.gms.games.RealTimeSocket;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
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
import Tools.SpriteSheet;
import World.Level;

public class MenuActivity extends BaseGameActivity implements RoomUpdateListener, RealTimeMessageReceivedListener, RoomStatusUpdateListener, OnInvitationReceivedListener {
    int CurrentPage = -1;

    int PreviousPage = -1;
    public static SoundPool sp;

    void StartMenu() {
        // ((TextView)findViewById(R.id.textView)).setText(getGamesClient().getCurrentAccountName()) ;
        final Button B1 = (Button) findViewById(R.id.button1);
        B1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

            }
        });
        final Button B2 = (Button) findViewById(R.id.button2);
        B2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               startActivity(new Intent(MenuActivity.this,SinglePlayerOptions.class));
            }
        });
        final Button B3 = (Button) findViewById(R.id.button3);
        if (isSignedIn()) {
            B3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    scv(R.layout.multiplayeroptions);
                }
            });
            B3.setVisibility(View.VISIBLE);
        } else B3.setVisibility(View.INVISIBLE);
        findViewById(R.id.button4).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                signOut();
                scv(R.layout.login_layout);
            }
        });
        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MenuActivity.this, ShopActivity.class);
                startActivityForResult(i, 100);
            }
        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(getGamesClient().getAchievementsIntent(), 4535);

            }
        });
    }


    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {

        // First decode with inJustDecodeBounds=true to check dimensions
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);

        // Calculate inSampleSize
       options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

        // Decode bitmap with inSampleSize set
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);
    }
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            // Calculate the largest inSampleSize value that is a power of 2 and keeps both
            // height and width larger than the requested height and width.
            while ((halfHeight / inSampleSize) > reqHeight
                    && (halfWidth / inSampleSize) > reqWidth) {
                inSampleSize *= 2;
            }
        }

        return 1;
    }
    void Load(Point size, Point truesize) {
        Log.e("INET", "LOADING!");
        if(Global.Sprites!=null)
        {
            for(ArrayList<Bitmap> j:Global.Sprites)
            {
                for(Bitmap b :j)
                {
                b.recycle();

                    b = null;
                }
            }
        }
        if(Global.ButtonImages!=null)
        {
            for(Bitmap b:Global.ButtonImages)
            {

                    b.recycle();

                b = null;
            }
        }
        Global.Sprites = new ArrayList<ArrayList<Bitmap>>();
        Global.ButtonImages = new ArrayList<Bitmap>();
        Log.e("DECODING" , "BEGIN DECODING");
        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Charsheet1");
        SpriteSheet s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.charsheetedit, 700, 800), 7, 8)   ;
       s.setBmp(Bitmap.createScaledBitmap(s.bmp, 700, 800, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Charsheet2");
       s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.charsheetedit2, 700, 800), 7, 8)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 700, 800, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
       // s.bmp.recycle();
        Log.e("DECODING" , "Shield1");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.shield, 100, 100), 4, 1)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 400, 100, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Shield2");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.shield, size.x/10, size.x/10), 4, 1)   ;
        s.setBmpBtn(Bitmap.createScaledBitmap(s.bmp, size.x/10*4, size.x/10, false));
        s.bmp.recycle();
        s.bmp=null;
      //  s.bmp.recycle();

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "ice1");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.ice, 100, 100), 7, 1)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 700, 100, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Ice2");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.ice, size.x/10, size.x/10), 7, 1)   ;
        s.setBmpBtn(Bitmap.createScaledBitmap(s.bmp, size.x/10*7, size.x/10, false));
        s.bmp.recycle();
        s.bmp=null;
       // s.bmp.recycle();

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Meteor1");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.meteor, 150, 150), 1, 1)   ;

        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 150, 150,false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Meteor2");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.meteor, 250, 250), 1, 1)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 250, 250, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Meteor3");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.meteor, size.x/10, size.x/10), 1, 1)   ;
        s.setBmpBtn(Bitmap.createScaledBitmap(s.bmp, size.x/10, size.x/10, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Tornado1");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.gravity2, 300, 600), 4, 1)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 1200, 600, false));
s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Tornado2");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.gravity2, size.x/10, size.x/10), 4, 1)   ;

        s.setBmpBtn(Bitmap.createScaledBitmap(s.bmp, size.x/10*4, size.x/10, false));
       // s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Fireball1");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.fireball, 300, 300), 1, 1)   ;

        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 300, 300, false));
        Log.e("DECODING" , "Fireball2");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.fireball, size.x/10, size.x/10), 1, 1)   ;
        s.setBmpBtn(Bitmap.createScaledBitmap(s.bmp, size.x/10, size.x/10, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Log.e("DECODING" , "Charsheet3");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.charsheetedit4, 700, 800), 7, 8)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 700, 800, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        // s.bmp.recycle();
        Log.e("DECODING" , "Charsheet4");
        s = new SpriteSheet(decodeSampledBitmapFromResource(getResources(), R.drawable.charsheet, 700, 800), 8, 8)   ;
        s.setBmp(Bitmap.createScaledBitmap(s.bmp, 800, 800, false));
        s.bmp.recycle();
        s.bmp=null;

        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Global.PaintBlue.setColor(Color.BLUE);
        Global.PaintYellow.setColor(Color.YELLOW);
        Global.PaintCyan.setColor(Color.CYAN);
        Global.PaintGray.setColor(Color.GRAY);
        Global.PaintGreen.setColor(Color.GREEN);
        Global.PaintRed.setColor(Color.RED);
        Global.PaintMagenta.setColor(Color.MAGENTA);
        Global.PaintOrange.setColor(Color.argb(255,255,127,0));
        Global.PaintOutline.setColor(Color.BLACK);
        Global.PaintOutline.setStyle(Paint.Style.STROKE);
        Global.PaintOutline.setStrokeWidth(3);
        Log.e("MEMORY USAGE",Global.getMemoryUsage());
        Toast.makeText(this,Global.getMemoryUsage(),1000).show();
    }



    private void scv(int LayoutName) {
        setContentView(LayoutName);
        switch (LayoutName) {
            case R.layout.multiplayeroptions:
                MultiplayerOptions();
                break;
            case R.layout.activity_menu2:
                if (isSignedIn())
                    Toast.makeText(this, "SIGNED IN!" + getGamesClient().getCurrentAccountName(), Toast.LENGTH_LONG).show();
                StartMenu();
                break;
            case R.layout.login_layout:
                findViewById(R.id.goglesignin).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        try {

                            beginUserInitiatedSignIn();
                        } catch (IllegalStateException e) {
                            e.printStackTrace();
                        }
                    }
                });
                findViewById(R.id.changetomenu).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        scv(R.layout.activity_menu2);
                    }
                });
                break;
            case R.layout.shop:
                Intent i = new Intent(this, ShopActivity.class);
                startActivityForResult(i, 100);
//              ListView l=  (ListView)findViewById(R.id.listView);
//                ListView l2=  (ListView)findViewById(R.id.listView2);
//                ListView l3=  (ListView)findViewById(R.id.listView3);
//                l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                        switch (i)
//                        {
//                            case 0:
//
//                                 break;
//
//                        }
//                    }
//                });
                break;


        }
    }


    void MultiplayerOptions() {
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
                Intent intent = getGamesClient().getSelectPlayersIntent(1, 1);
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

    public static int explosion = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);

        sp = new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
        explosion = sp.load(this, R.raw.boom, 1);
        Display display = getWindowManager().getDefaultDisplay();
      Global.size = new android.graphics.Point();
        display.getSize(Global.size);
        Log.e("ACTIVITY LIFECYCLE","ONCREATE CALLED");

            Load(Global.size, new android.graphics.Point(Global.size.x,Global.size.x));

        if ( RenderThread.renderThread==null) {
            RenderThread.renderThread = new RenderThread(this, Global.size);


        }
        scv(R.layout.login_layout);
    }

    @Override
    public void onSignInFailed() {
//        AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
//        alert.setMessage("Failed");
////        alert.show();
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
            if (response != RESULT_OK) {
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
            if (response != RESULT_OK) {
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
            RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
            roomConfigBuilder.addPlayersToInvite(invitees);
            if (autoMatchCriteria != null) {
                roomConfigBuilder.setAutoMatchCriteria(autoMatchCriteria);
            }
            hosting = true;
            RoomConfig roomConfig = roomConfigBuilder.build();
            getGamesClient().createRoom(roomConfig);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        }
        if (request == RC_WAITING_ROOM) {
            if (response == RESULT_OK) {

                Participants = mRoom.getParticipantIds();
                Global.Multiplayer = true;
                Global.Players = 2;
                GameThread.gamesClient = getGamesClient();
                GameThread.room = mRoom;
                Global.LEFT_HAND_MODE = false;
                startGame(Level.LevelShape.Ellipse);

            } else if (response == RESULT_CANCELED) {
                // Waiting room was dismissed with the back button. The meaning of this
                // action is up to the game. You may choose to leave the room and cancel the
                // match, or do something else like minimize the waiting room and
                // continue to connect in the background.

                // in this example, we take the simple approach and just leave the room:
                getGamesClient().leaveRoom(this, mRoom.getRoomId());
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            } else if (response == GamesActivityResultCodes.RESULT_LEFT_ROOM) {
                // player wants to leave the room.
                getGamesClient().leaveRoom(this, mRoom.getRoomId());
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        }
    }

    void getID() {
        String myid = mRoom.getParticipantId(getGamesClient().getCurrentPlayerId());
        String remoteId = null;

        ArrayList<String> ids = mRoom.getParticipantIds();
        for (int i = 0; i < ids.size(); i++) {
            String test = ids.get(i);
            Log.e("INET", "STRINGS ARE: " + test + " AND " + myid);
            if (test.compareTo(myid) > 0) {
                Log.e("INET", "GREATER THEN");
                Global.playerno += 1;
            }
        }

    }



    void startGame(Level.LevelShape _l) {
        if (Global.Multiplayer)
            getID();
        GameThread.Gamestep=0;
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED, WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
        //
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // set our MainGamePanel as the
        Log.e("TESTING PURPOSES",_l + " ");
RenderThread.SetLevelShape(_l);

        RenderThread.renderThread.MakePlayers();
        RenderThread.UserInterface();
       final Intent intent = new Intent(this,GameActivity.class);
        startActivity(intent);
    }

    private boolean hosting = false;

    public static ArrayList<String> Participants;
    public static ArrayList<RealTimeSocket> realTimeSockets;
    public Room mRoom;

    @Override
    public void onRoomCreated(int statusCode, Room room) {
        if (statusCode != GamesClient.STATUS_OK) {
            // display error
            showAlert("ERROR");
            return;
        }

        mRoom = room;
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
        // Global.playerno=room.getParticipants().size()-1;
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
        if(b!=null){
        NetworkFinger f = Serializer.DeserializefromFiletoVector(b);
        RenderThread.gameThread.MaxStepRecieved = f.Step;

        //  int x = hosting?1:0;
        GameThread.fingers.add(f);
            if(GameThread.locked&&f.Step>GameThread.Gamestep)
                try
                {
                     GameThread.locked=false;
                    RenderThread.gameThread.notify();
                }
                catch (Exception e)
                {

                }
        }

        // RenderThread.players.get(x).FingerUpdate(f.finger,f.SelectedSpell);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("ACTIVITY LIFECYCLE","ONRESUME CALLED");
        Global.alive = true;
    }

    @Override
    public void onRoomConnecting(Room room) {
        mRoom = room;
    }

    @Override
    public void onRoomAutoMatching(Room room) {
        mRoom = room;
    }

    @Override
    public void onPeerInvitedToRoom(Room room, List<String> strings) {
        mRoom = room;
    }

    @Override
    public void onPeerDeclined(Room room, List<String> strings) {
        mRoom = room;
    }

    @Override
    public void onPeerJoined(Room room, List<String> strings) {
        mRoom = room;
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
        mMyId = room.getParticipantId(getGamesClient().getCurrentPlayerId());
    }

    public static String mMyId = null;

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

        Toast.makeText(this, "INVITATION RECIEVED", Toast.LENGTH_LONG).show();
        mIncomingInvitationId = invitation.getInvitationId();
        AlertDialog.Builder alert = new AlertDialog.Builder(MenuActivity.this);
        Global.Multiplayer = true;
        alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
            }
        });
        alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                RoomConfig.Builder roomConfigBuilder = makeBasicRoomConfigBuilder();
                roomConfigBuilder.setInvitationIdToAccept(mIncomingInvitationId);
                getGamesClient().joinRoom(roomConfigBuilder.build());

// prevent screen from sleeping during handshake
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            }
        });
        alert.show();
    }
}
