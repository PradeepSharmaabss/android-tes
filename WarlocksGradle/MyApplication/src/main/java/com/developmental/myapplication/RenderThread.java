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

import Actors.EllipseMovingAI;
import Actors.Player;
import Game.Block;
import Game.GameObject;
import Particles.Particle;
import HUD.Button;
import HUD.PopupText;
import Input.Finger;
import World.Level;

/**
 * @author impaler This is the main surface that handles the ontouch events and
 *         draws the image to the screen.
 */
public class RenderThread extends SurfaceView implements SurfaceHolder.Callback {
    private static final String TAG = RenderThread.class.getSimpleName();
    public static List<GameObject> gameObjects = new ArrayList<GameObject>();

    public static List<Particle> Particles = new ArrayList<Particle>();
    public static Player archie;
    public static Player archie2;
    public static List<Player> players = new ArrayList<Player>();

    public static int r = 0, g = 0;
    public static Level l;
    public static int objects = 0;
    public static int particles = 0;

    public static List<Button> buttons = new ArrayList<Button>();
    public static Point size, trueSize;
    private final SurfaceHolder holder;
    public static GameThread gameThread;
    public static boolean loaded = false;
    public static MenuActivity c;

    public static Finger finger = new Finger();

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

            }

            public void surfaceCreated(SurfaceHolder holder) {
                GameThread.setRunning(true);
            }

            public void surfaceChanged(SurfaceHolder holder, int format,
                                       int width, int height) {
            }
        });
        setFocusable(true);
        this.Load();
    }
    public static void SetLevelShape(Level.LevelShape _l)
    {
        lShape = _l;
        l= new Level(lShape);
    }
    public static Level.LevelShape lShape;

    public void Load() {

        if (Global.PlatformSkins.size() == 0) {
            Bitmap tmpbmp = BitmapFactory.decodeResource(getResources(),
                    R.drawable.ground);

            Global.PlatformSkins.add(tmpbmp);
        }


          l = new Level(Level.LevelShape.Ellipse);


        loaded = true;
        gameObjects = new ArrayList<GameObject>();
        Log.d("INET", "PLAYER NO." + Global.playerno);
        if (gameObjects.size() == 0) {
            // load sprite sheet
            if (Global.Multiplayer) {

                int a = 360 / Global.Players;
                players = new ArrayList<Player>();

                for (int x = 0; x < Global.Players; x++) {
                    Player p = new Player(Global.Sprites.get(0), GameObject.PositiononEllipse(a * x + 45));
                    players.add(p);
                    addObject(p);
                    Log.d("INET", "PLAYER CREATED " + Global.playerno + " " + Global.Players);

                }

                archie = players.get(Global.playerno);

            } else {
                // playerno=0;
                players = new ArrayList<Player>();
                Player p = new Player(Global.Sprites.get(0), GameObject.PositiononEllipse(45));
                players.add(p);
                addObject(p);
                p = new EllipseMovingAI(Global.Sprites.get(0), GameObject.PositiononEllipse(100));
                players.add(p);
                addObject(p);
                p = new EllipseMovingAI(Global.Sprites.get(1), GameObject.PositiononEllipse(300));
                players.add(p);
                addObject(p);
                archie = players.get(0);

            }
            addObject(new Block(2700, 750));
            addObject(new Block(2700, 950));
            addObject(new Block(2700, 1150));
            addObject(new Block(2700, 1450));
            addObject(new Block(2700, 1650));
        }


    }

    public static void UserInterface() {
        if (buttons.size() == 0) {
            int screenSize = size.x;
            for (int x = 0; x < 10; x += 1) {
                if (Global.LEFT_HAND_MODE) {
                    buttons.add(new Button(new RectF((9 - x) * screenSize / 10,
                            size.y, (9 - x) * screenSize / 10 + (screenSize / 10),
                            trueSize.y), x, archie.Spells[x]));
                } else {
                    buttons.add(new Button(new RectF(x * screenSize / 10,
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


        float offsetX = (archie.position.x - size.x / 2), offsetY = (archie.position.y - size.y / 2);
        l.Draw(canvas, offsetX, offsetY);
        for (Particle p : Particles)
            p.Draw(offsetX, offsetY, canvas);
        int listsize = gameObjects.size() - 1;
        for (int x = 0; x <= listsize; x++) {


            gameObjects.get(x).Draw(canvas, offsetX, offsetY);
            if (Global.DEBUG_MODE)
                gameObjects.get(x).DrawHitBox(offsetX, offsetY, canvas);

        }


        for (int f = 0; f < popupTexts.size(); f++) {
            popupTexts.get(f).Draw(offsetX, offsetY, canvas);
        }

        for (int y = 0; y < 10; y++)
            this.buttons.get(y).Draw(canvas);
        DrawScoreBoard(canvas);
        Paint j = new Paint();
        j.setTextSize(GameThread.Gamestep % 100 > 90 ? 50 : 30);
        canvas.drawText("" + GameThread.Gamestep, 50, 50, j);

    }

    void DrawScoreBoard(Canvas canvas) {
        Paint alive = new Paint();
        alive.setColor(Color.WHITE);
        alive.setTextSize(20);
        Paint dead = new Paint();
        dead.setColor(Color.YELLOW);
        dead.setTextSize(20);
        Paint paint;
        Paint backColour = new Paint();
        backColour.setTextSize(20);
        backColour.setStrokeWidth(3);
        backColour.setStyle(Paint.Style.STROKE);
        int i = 0;
        for (Player p : RenderThread.players) {
            i++;
            paint = (!p.dead) ? alive : dead;
            canvas.drawText(p.health + "/" + p.maxhealth + " DAMAGE DEALT: " + p.damageDealtThisRound + " , SPEED = " + (int) p.CurrentVelocity(p.velocity), 50, 100 + i * 50, backColour);
            canvas.drawText(p.health + "/" + p.maxhealth + " DAMAGE DEALT: " + p.damageDealtThisRound + " , SPEED = " + (int) p.CurrentVelocity(p.velocity), 50, 100 + i * 50, paint);
        }
    }

    public static void addObject(GameObject obj) {
        gameObjects.add(obj);
        gameObjects.get(gameObjects.size() - 1).id = objects++;
    }

    public static void addParticle(Particle obj) {
        Particles.add(obj);
        Particles.get(Particles.size() - 1).id = particles++;
    }

    public static void delObject(int id) {
        for (int x = 0; x < gameObjects.size(); x++)
            if (gameObjects.get(x).id == id) {

                gameObjects.remove(x);


                return;
            }


    }

    public static void delParticle(int id) {
        for (int x = 0; x < Particles.size(); x++)
            if (Particles.get(x).id == id) {

                Particles.remove(x);
                return;
            }


    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width,
                               int height) {
    }

    public void surfaceCreated(SurfaceHolder holder) {

        if (!GameThread.running) {
            GameThread.setRunning(true);
            if (!gameThread.isAlive())
                gameThread.start();
        }
        System.out.println("surface Created");

    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        Log.d(TAG, "Surface is being destroyed");
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
