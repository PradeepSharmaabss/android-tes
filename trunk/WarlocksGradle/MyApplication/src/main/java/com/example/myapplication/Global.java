package com.example.myapplication;

import android.graphics.Bitmap;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.List;

import Tools.Vector;

public class Global {
	// public static SpriteSheet ss = null;
	public static List<Bitmap> tiles = new ArrayList<Bitmap>();
//	public static Paint paint;
	public static List<Bitmap> PlatformSkins = new ArrayList<Bitmap>();
    public static Paint paint;
    public static boolean LEFT_HAND_MODE = true;
    public static Vector WORLD_BOUND_SIZE = new Vector(8000,4000);
    public static boolean DEBUG_MODE= true;
    public static boolean Server = false;
    public static String SAddress;
    public static boolean alive =true;
    public static boolean Multiplayer= false;
    public static int Players = 2;
}
