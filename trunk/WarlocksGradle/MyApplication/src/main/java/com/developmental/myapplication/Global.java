package com.developmental.myapplication;

import android.graphics.Bitmap;
import android.graphics.Paint;
import android.graphics.Point;
import android.os.Debug;

import com.developmental.myapplication.GL.Grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Spells.Spell;
import Spells.SpellInfo;
import Tools.Vector;

public class Global {
    // public static SpriteSheet ss = null;
    public static List<Bitmap> tiles = new ArrayList<Bitmap>();
    //	public static Paint paint;
    public static List<Bitmap> PlatformSkins = new ArrayList<Bitmap>();
    public static Paint paint;
    public static boolean LEFT_HAND_MODE = false;
    public static int TargetFrameIncrease = 3;
    public static int InputFrameGap = 1;
    public static final int GlobalCooldown = 10;
    public static Vector WORLD_BOUND_SIZE = new Vector(8000, 4000);

    public static boolean DEBUG_MODE = false;
    public static boolean Server = false;
    public static boolean alive = true;
    public static boolean Multiplayer = false;
    public static int Players = 2;
    public static int playerno = 0;
    public static ArrayList<ArrayList<Bitmap>> Sprites = null;
    public static ArrayList<Bitmap> ButtonImages = null;
    public static boolean LOCKSPELLMODE = false;
    public static Random GetRandomNumer = new Random(1002);
    public static SpellInfo[] spellList = new SpellInfo[10];
    public static Paint PaintRed= new Paint();
    public static Paint PaintBlue= new Paint();
    public static Paint PaintGreen= new Paint();
    public static Paint PaintYellow= new Paint();
    public static Paint PaintCyan= new Paint();
    public static Paint PaintMagenta= new Paint();
    public static Paint PaintBlack= new Paint();
    public static Paint PaintGray= new Paint();
    public static Paint PaintOrange= new Paint();
    public static Paint PaintOutline = new Paint();
    public static Point size;
   public static ArrayList<Grid> SpritesLeft = new ArrayList<Grid>();
    public static   ArrayList<Grid> SpritesRight = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesUp = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesDown = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftUp = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftDown = new ArrayList<Grid>();
    public static  ArrayList<Grid> SpritesRightUp = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesRightDown = new ArrayList<Grid>();
    public static String getMemoryUsage()
{
    Debug.MemoryInfo memoryInfo = new Debug.MemoryInfo();
    Debug.getMemoryInfo(memoryInfo);
   return String.format("App Memory: Pss=%.2f MB\nPrivate=%.2f MB\nShared=%.2f MB",
           memoryInfo.getTotalPss() / 1024.0,
           memoryInfo.getTotalPrivateDirty() / 1024.0,
           memoryInfo.getTotalSharedDirty() / 1024.0);

}

}
