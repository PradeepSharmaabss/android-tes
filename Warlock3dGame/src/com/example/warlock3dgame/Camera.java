package com.example.warlock3dgame;

import android.opengl.Matrix;

public class Camera {
	public static float x,y,z,
	offsetx = 0, offsety= 5, offsetz = 5;
	public static void Update(float x, float y, float z, float[] m)
	{
		Matrix.setLookAtM(m, 0, x + offsetx, y + offsety, z + offsetz, x, y, z, 0, 1, 0);
	}
}
