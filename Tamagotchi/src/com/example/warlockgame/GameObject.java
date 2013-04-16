package com.example.warlockgame;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class GameObject implements Comparable<GameObject> {
	public int id = 0;

	public GameObject() {

	}

	public int compareTo(GameObject another) {
		// TODO Auto-generated method stub
		if (this.id > another.id)
			return 1;
		return 0;
	}

	void Update() {

	}

	public void Draw(Canvas c) {

		c.drawRect((new Rect(0, 0, 50, 50)), new Paint());
	}
}
