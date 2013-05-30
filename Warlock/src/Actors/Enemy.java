package Actors;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.warlockgame.RenderThread;

public class Enemy extends GameObject {
	List<Vector> d = new ArrayList<Vector>();
	Bitmap bitmap;
	int x = 0;

	public Enemy()// Bitmap bmp)
	{
		super();
		this.objectObjectType = Game.ObjectType.Enemy;
		this.d.add(new Vector(100, 0));
		this.d.add(new Vector(0, 50));
		this.d.add(new Vector(50, 100));
		this.d.add(new Vector(100, 50));
		// bitmap = bitmap;
		this.rect = new RectF(0, 0, 100, 100);
		this.position = new Vector(0, 0);
		this.destination = new Vector(0, 0);
		this.size = new Vector(100, 100);
	}

	@Override
	public void setNull() {

	}

	int tmptimer = 0;

	@Override
	public void Update() {
		if (this.tmptimer < 100)
			this.tmptimer++;
		else {
			System.out.println("test");
			this.Spells[3].Cast(RenderThread.archie.getCenter());
			this.tmptimer = 0;
		}
		if (this.position.x == this.destination.x
				&& this.position.y == this.destination.y) {
			this.x += 1;
			if (this.x > 3)
				this.x = 0;
			this.destination = new Vector(this.d.get(this.x).x,
					this.d.get(this.x).y);

		}
		super.Update();
	}

	@Override
	public void Draw(Canvas canvas) {
		Vector p1 = RenderThread.archie.getCenter(), p2 = getCenter();
		this.paint.setColor(Color.GREEN);
		canvas.drawLine(p2.x, p2.y, p1.x, p1.y, this.paint);
		this.paint.setColor(Color.BLUE);
		canvas.drawLine(p2.x, p2.y, this.destination.x, this.destination.y,
				this.paint);

		this.paint.setColor(Color.WHITE);
		canvas.drawLine(p2.x, p2.y, p2.x + 30 * this.velocity.x, p2.y + 30
				* this.velocity.y, this.paint);
		super.Draw(canvas);
        if(destination!=null)
            if(Marker!=null)
            Marker.Draw(canvas);
	}
}