package com.example.warlockgame;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Color;
import android.graphics.Paint;

import Tools.Vector;

public class Global {
	// public static SpriteSheet ss = null;
	public static List<Bitmap> tiles = new ArrayList<Bitmap>();
//	public static Paint paint;
	public static List<Bitmap> PlatformSkins = new ArrayList<Bitmap>();
    public static Paint paint;
    public static boolean LEFT_HAND_MODE = true;
    public static Vector WORLD_BOUND_SIZE = new Vector(5600,2800);
    public static boolean DEBUG_MODE= true;
    public static boolean Server = false;
}
