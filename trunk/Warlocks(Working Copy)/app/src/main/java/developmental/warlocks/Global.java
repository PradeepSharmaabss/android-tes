package developmental.warlocks;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Debug;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import Spells.SpellInfo;
import Tools.Vector;
import developmental.warlocks.GL.Grid;

public class Global {
    // public static SpriteSheet ss = null;

    public static List<Bitmap> PlatformSkins = new ArrayList<Bitmap>();
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
    public static ArrayList<Bitmap> ButtonImages = null;
    public static boolean LOCKSPELLMODE = false;
    public static Random GetRandomNumer = new Random(1002);
    public static SpellInfo[] spellList = new SpellInfo[10];

    public static Point size;
   public static ArrayList<Grid> SpritesLeft = new ArrayList<Grid>();
    public static   ArrayList<Grid> SpritesRight = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesUp = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesDown = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftUp = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftDown = new ArrayList<Grid>();
    public static  ArrayList<Grid> SpritesRightUp = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesRightDown = new ArrayList<Grid>();
    public static ArrayList<Grid> SpritesLeftCast1 = new ArrayList<Grid>();
    public static   ArrayList<Grid> SpritesRightCast1 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesUpCast1 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesDownCast1 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftUpCast1 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftDownCast1 = new ArrayList<Grid>();
    public static  ArrayList<Grid> SpritesRightUpCast1 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesRightDownCast1= new ArrayList<Grid>();
       public static ArrayList<Grid> SpritesLeftCast2 = new ArrayList<Grid>();
    public static   ArrayList<Grid> SpritesRightCast2 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesUpCast2 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesDownCast2 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftUpCast2 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesLeftDownCast2 = new ArrayList<Grid>();
    public static  ArrayList<Grid> SpritesRightUpCast2 = new ArrayList<Grid>();
    public static    ArrayList<Grid> SpritesRightDownCast2 = new ArrayList<Grid>();
    public static Map<Integer,Integer> resources = new HashMap<Integer, Integer>();
    public static float ButtonSize;
    public static float healthBarHeight = 40;
    public static float NumberOfHealthBars = 2;
    public static boolean OpenGL=true;
    public static ArrayList<Grid> spellSpritesFire;
    public static ArrayList<Grid> spellSpritesMeteor;
    public static ArrayList<Grid> EffectGrid;
    public static ArrayList<Grid> fireballSpellSprites;

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
