package Actors;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.warlockgame.Global;
import com.example.warlockgame.RenderThread;

public abstract class Enemy extends Player {
	List<Vector> d = new ArrayList<Vector>();
	Bitmap bitmap;
	int x = 0;

	public Enemy(SpriteSheet _spriteSheet, Vector _pos)// Bitmap bmp)
	{
		super(_spriteSheet,_pos);
		super.objectObjectType = Game.ObjectType.Enemy;
		this.d.add(new Vector(100, 0));
		this.d.add(new Vector(0, 50));
		this.d.add(new Vector(50, 100));
		this.d.add(new Vector(100, 50));
		// bitmap = bitmap;
		this.rect = new RectF(0, 0, 100, 100);
		//this.position = new Vector(0, 0);
		this.destination = new Vector(0, 0);
		this.size = new Vector(100, 100);
        this.maxhealth = 100;
        this.health= 100;
        this.owner=null;
	}



	int tmptimer = 0;

	@Override
	public void Update() {
		if (this.tmptimer < 100)
			this.tmptimer++;
		else {
			System.out.println("test");
			this.Spells[0].Cast(RenderThread.archie.getCenter());
			this.tmptimer = 0;
		}

		super.Update();
	}

	@Override
	public void Draw(Canvas canvas,float playerx,float playery) {
        super.Draw(canvas,playerx,playery);
        if(Global.DEBUG_MODE)
        {
            Vector p1 = RenderThread.archie.getCenter(), p2 = getCenter();
            this.paint.setColor(Color.GREEN);
            canvas.drawLine(p2.x-playerx, p2.y-playery, p1.x-playerx, p1.y-playery, this.paint);
            if(destination!=null){
            this.paint.setColor(Color.BLUE);

            canvas.drawLine(p2.x-playerx, p2.y-playery, this.destination.x-playerx, this.destination.y-playery,
                    this.paint);
            }
            this.paint.setColor(Color.WHITE);
            canvas.drawLine(p2.x-playerx, p2.y-playery, p2.x + 30 * this.velocity.x-playerx, p2.y + 30
                    * this.velocity.y-playery, this.paint);
        }
        this.DrawHealthBar(canvas,0,0);

        if(destination!=null)
            if(Marker!=null)
            Marker.Draw(canvas,playerx,playery);
	}
}