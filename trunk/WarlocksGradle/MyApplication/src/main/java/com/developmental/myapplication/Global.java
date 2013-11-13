package com.developmental.myapplication;

import android.graphics.Bitmap;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Tools.SpriteSheet;
import Tools.Vector;

public class Global {
    // public static SpriteSheet ss = null;
    public static List<Bitmap> tiles = new ArrayList<Bitmap>();
    //	public static Paint paint;
    public static List<Bitmap> PlatformSkins = new ArrayList<Bitmap>();
    public static Paint paint;
    public static boolean LEFT_HAND_MODE = false;
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
}
