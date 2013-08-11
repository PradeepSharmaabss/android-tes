package com.developmental.myapplication;

/**
 * 
 */

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.ArrayList;
import java.util.List;

import Actors.Player;
import Game.Block;
import Game.GameObject;
import HUD.Button;
import HUD.PopupText;
import Input.Finger;
import Tools.SpriteSheet;
import Tools.Vector;
import World.Level;

/**
 * @author impaler This is the main surface that handles the ontouch events and
 *         draws the image to the screen.
 */
public class RenderThread extends SurfaceView implements SurfaceHolder.Callback {
	private static final String TAG = RenderThread.class.getSimpleName();
	public static List<GameObject> gameObjects = new ArrayList<GameObject>();
	public static Player archie;
    public static Player archie2;
    public static List<Player> players=new ArrayList<Player>();

	public static int r = 0, g = 0;
	public static Level l;
	public static int objects = 0;
	public List<Button> buttons = new ArrayList<Button>();
	public static Point size, trueSize;
	private final SurfaceHolder holder;
	public static GameThread gameThread;
	public static boolean loaded = false;
	public static MenuActivity c;

    public static Finger finger= new Finger();
	public RenderThread(Context context, Point _size) {
		super(context);
		c = (MenuActivity) context;
		getHolder().addCallback(this);
		size = _size;
		trueSize = new Point(_size.x, _size.y);
		size.y -= size.y / 5;
		Global.paint = new Paint();
		Global.paint.setAntiAlias(true);
		Global.paint.setColor(Color.RED);



		// create the game loop thread

        gameThread = new GameThread(getHolder(), this);
		this.holder = getHolder();
		this.holder.addCallback(new SurfaceHolder.Callback() {

			public void surfaceDestroyed(SurfaceHolder holder) {
//				boolean retry = true;
//				GameThread.setRunning(false);
//				while (retry)
//					try {
//						RenderThread.gameThread.join();
//						retry = false;
//					} catch (InterruptedException e) {
//
//					}
			}

			public void surfaceCreated(SurfaceHolder holder) {
				GameThread.setRunning(true);
			//	RenderThread.gameThread.start();
			}

			public void surfaceChanged(SurfaceHolder holder, int format,
					int width, int height) {
			}
		});

		// make the GamePanel focusable so it can handle events
		setFocusable(true);
		// getHolder().
	}

	public void Load() {
		if (Global.PlatformSkins.size() == 0) {
			// tiles.add(Bitmap.createScaledBitmap(Bitmap.createBitmap(bmp, x,
			// y, (int)size.x ,(int)size.y),(int)size.x, (int)size.y, false));
			Bitmap tmpbmp = BitmapFactory.decodeResource(getResources(),
					R.drawable.ground);

			Global.PlatformSkins.add(tmpbmp);
		}
        Global.Sprites = new ArrayList<ArrayList<Bitmap>>();
        SpriteSheet s = new SpriteSheet(BitmapFactory.decodeResource(
                getResources(), R.drawable.charsheetedit),7,8);
        s.Load(new Vector(100,100));
        Global.Sprites.add(s.tiles);
        s=new SpriteSheet(BitmapFactory.decodeResource(
                getResources(), R.drawable.charsheet),7,8);
        s.Load(new Vector(100,100));
        Global.Sprites.add(s.tiles);
        s=new SpriteSheet(BitmapFactory.decodeResource(
                getResources(), R.drawable.shield),4,1);
        s.Load(new Vector(100,100));
        Global.Sprites.add(s.tiles);
        s=new SpriteSheet(BitmapFactory.decodeResource(
                getResources(), R.drawable.ice),7,1);
        s.Load(new Vector(100,100));
        Global.Sprites.add(s.tiles);
		if (!loaded) {
			l = new Level();
			loaded = true;
		}
        gameObjects=new ArrayList<GameObject>();
        Log.d("INET", "PLAYER NO."+Global.playerno);
		if (gameObjects.size() == 0) {
            // load sprite sheet
            if(Global.Multiplayer)
            {

                int a = 360/Global.Players;
                players=new ArrayList<Player>();

                for(int x =0; x<Global.Players;x++)
                {
                    Player p = new Player(Global.Sprites.get(0), GameObject.PositiononEllipse(a*x+45));
                    players.add(p );
                    addObject(p);
                    Log.d("INET","PLAYER CREATED "+Global.playerno+ " " + Global.Players);

                }

                archie=players.get(Global.playerno);

            }
else
            {
               // playerno=0;
                players=new ArrayList<Player>();
                Player p = new Player(Global.Sprites.get(0), GameObject.PositiononEllipse(45));
                players.add(p );
                addObject(p);
                 p = new Player(Global.Sprites.get(0), GameObject.PositiononEllipse(100));
                players.add(p );
                addObject(p);
            archie= players.get(0);
            }

//           addObject(new EllipseMovingAI(new SpriteSheet(BitmapFactory.decodeResource(
//                    getResources(), R.drawable.charsheet),7,8),new Vector(2800,1050)));
//            addObject(new EllipseMovingAI(new SpriteSheet(BitmapFactory.decodeResource(
//                    getResources(), R.drawable.charsheet),7,8),new Vector(2800,1050)));

addObject(new Block(2700,750));




//            try {
//
//                ClientTask.Send("127.0.0.1");
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//			// Game.Block b = new Game.Block();
            // b.position=new Vector(2800,900);
            // addObject(b);

        }

		UserInterface();
	}

	public void UserInterface() {
		if (this.buttons.size() == 0) {
			int screenSize = size.x;
			for (int x = 0; x < 10; x += 1)
            {
                if( Global.LEFT_HAND_MODE)
                {
                    this.buttons.add(new Button(new RectF((9-x) * screenSize / 10,
                            size.y, (9-x) * screenSize / 10 + (screenSize / 10),
                            trueSize.y), x, archie.Spells[x]));
                }
                else
                {
				this.buttons.add(new Button(new RectF(x * screenSize / 10,
						size.y, x * screenSize / 10 + (screenSize / 10),
						trueSize.y), x, archie.Spells[x]));
                }
            }
		}
	}
    int i = 0;
public static List<PopupText> popupTexts = new ArrayList<PopupText>();
	@Override
	protected void onDraw(Canvas canvas) {

		canvas.drawColor(Color.BLACK);
		// buffer refresh color
		// canvas.drawBitmap(R.drawable.previewjpg,new
		// RectF(size.x/2,size.y/2,size.x/2+15,size.y/2+15),new
		// Paint(Color.MAGENTA));


        float offsetX=(archie.position.x - size.x / 2),offsetY=(archie.position.y - size.y / 2);
        l.Draw(canvas,offsetX, offsetY);

        int listsize = gameObjects.size() - 1;
		for (int x = 0; x <= listsize; x++)
        {
            gameObjects.get(x).Draw(canvas,offsetX, offsetY);
            if(Global.DEBUG_MODE)
            gameObjects.get(x).DrawHitBox(offsetX, offsetY,canvas);
        }

		for (int y = 0; y < 10; y++)
			this.buttons.get(y).Draw(canvas);
        for(int f = 0; f<popupTexts.size();f++)
        {
            popupTexts.get(f).Draw(offsetX,offsetY,canvas);
        }

        canvas.drawText(""+GameThread.Gamestep,50,50,new Paint());

	}

	public static void addObject(GameObject obj) {
		gameObjects.add(obj);
		gameObjects.get(gameObjects.size() - 1).id = objects++;
	}

	public static void delObject(int id) {
		for (int x = 0; x < gameObjects.size(); x++)
			if (gameObjects.get(x).id == id)
				gameObjects.remove(x);
	}

	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

		//Load();

	}

	public void surfaceCreated(SurfaceHolder holder) {
		/*
		 * if(!gameThread.isAlive()) { gameThread.setRunning(true);
		 * gameThread.start(); }
		 */
        Load();

        GameThread.setRunning(true);
//        try {
//            //playerno = 0;
////            if(Global.Multiplayer==true)
////                new ServerThread(ServerThread.ActionType.AcceptInfomation).start();
//        } catch (IOException e) {
//            Log.d("INET","BREAK!\n");
//            Log.d("INET",e.toString());
//        }
        if(!gameThread.isAlive())
        gameThread.start();
		System.out.println("surface Created");
        //Load();
		// Load();
	}

	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG, "Surface is being destroyed");
		// tell the thread to shut down and wait for it to finish
		// this is a clean shutdown
        GameThread.setRunning(false);
	gameThread.interrupt();
		Log.e(TAG, "Thread was shut down cleanly");
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		RenderThread.finger.Update(event);
		return true;
	}

}
