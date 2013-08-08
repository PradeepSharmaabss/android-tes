package Actors;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;
import java.util.List;

import Game.GameObject;
import Game.SpellEffect;
import Tools.SpriteSheet;
import Tools.Vector;
import com.developmental.myapplication.Global;

public class Player extends GameObject {
	int frame = 0;
	List<Bitmap> left, right, up, down, downleft, downright, upright, upleft;
	int timer = 0;
	double angleInDegrees = 0;
private final int FramesShown = 1;
	public Player(SpriteSheet _spriteSheet, Vector _pos) {
		super();

		super.objectObjectType = Game.ObjectType.Player;
		super.owner = this;
		this.position = _pos;
		this.size = new Vector(100, 100);
		this.spriteSheet = _spriteSheet;
		this.spriteSheet.Load(this.size);
		GetSprites();
		this.rect = new RectF(0, 0, 100, 100);

		this.paint.setTextSize(30);
		this.paint.setColor(Color.BLACK);

		this.shadowPaint = new Paint();

		this.shadowPaint.setMaskFilter( new BlurMaskFilter(30,
                BlurMaskFilter.Blur.INNER));
		this.paint.setAntiAlias(true);
        this.maxVelocity = 30;
        this.acceleration = 1;
	}

	@Override
	protected void GetSprites() {

		this.right = new ArrayList<Bitmap>();
		this.left = new ArrayList<Bitmap>();
		this.down = new ArrayList<Bitmap>();
		this.up = new ArrayList<Bitmap>();
		this.downleft = new ArrayList<Bitmap>();
		this.downright = new ArrayList<Bitmap>();
		this.upright = new ArrayList<Bitmap>();
		this.upleft = new ArrayList<Bitmap>();
		for (int x = 0; x < 7; x++)
			this.left.add(this.spriteSheet.tiles.get(x));
		for (int x = 7; x < 14; x++)
			this.upleft.add(this.spriteSheet.tiles.get(x));
		for (int x = 14; x < 21; x++)
			this.up.add(this.spriteSheet.tiles.get(x));
		for (int x = 21; x < 28; x++)
			this.upright.add(this.spriteSheet.tiles.get(x));
		for (int x = 28; x < 35; x++)
			this.right.add(this.spriteSheet.tiles.get(x));
		for (int x = 35; x < 42; x++)
			this.downright.add(this.spriteSheet.tiles.get(x));
		for (int x = 42; x < 49; x++)
			this.down.add(this.spriteSheet.tiles.get(x));
		for (int x = 49; x < 56; x++)
			this.downleft.add(this.spriteSheet.tiles.get(x));
		this.curr = this.spriteSheet.tiles.get(0);
	}

	@Override
	public void Draw(Canvas canvas,float playerx,float playery) {
		super.Draw(canvas,playerx,playery);
		if (this.curr != null)
			canvas.drawBitmap(this.curr, this.position.x-playerx, this.position.y-playery,
					this.paint);
        if(Global.DEBUG_MODE)
        {
		this.paint.setColor(Color.WHITE);
		 canvas.drawText(""+(int)feet.x +","+(int)feet.y, dRect.left, dRect.top, paint);
        }
		DrawHealthBar(canvas,0,0);
        if(destination!=null)
        {
            if(Marker!=null)
            Marker.Draw(canvas,playerx,playery);
        }
        boolean Shielded = false;
        for(int i = 0; i<Debuffs.size();i++)
        {

            SpellEffect e = Debuffs.get(i);

            if(e.Duration>0)
            {
                if(e.effectType == SpellEffect.EffectType.Reflect)
                    Shielded = true;
                e.Draw(canvas,new Vector(this.getCenter().x-playerx,this.getCenter().y-playery));
            }
            else
            {
                Debuffs.remove(i);
            }
        }
//        if(Shielded)
//        {
//            Paint p = new Paint();
//            p.setColor(Color.MAGENTA);
//            p.setAlpha(125);
//
//            canvas.drawCircle( this.getCenter().x-playerx, this.getCenter().y-playery,70,
//                    p);
//        }
        // canvas.drawRect(new RectF(position.x,
		// position.y,position.x+4,position.y+4), paint);
	}

	@Override
	public void Update() {
		super.Update();
		this.rect = new RectF(this.position.x, this.position.y, this.position.x
				+ this.size.x, this.position.y + this.size.y);
        if(!this.casting)

	    	Animate(this.destination);
	}

	// based on angle to the destination point the players frame is chosen. it
	// then cycles through until the angle changes
	public void Animate(Vector dest) {
		if (dest != null) {
			float deltaY = Math.abs(dest.y) - Math.abs(this.feet.y);
			float deltaX = Math.abs(dest.x) - Math.abs(this.feet.x);
			this.angleInDegrees = Math.atan2(deltaY, deltaX) * 180 / Math.PI
					+ 180;

		if (this.timer < FramesShown) {
			if (this.angleInDegrees >= 157.5 && this.angleInDegrees < 202.5) {
				if (this.frame < this.right.size())
					this.curr = this.right.get(this.frame);
				else if (this.right.size() > 0) {
					this.curr = this.right.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees >= 112.5
					&& this.angleInDegrees < 157.5) {
				if (this.frame < this.upright.size())
					this.curr = this.upright.get(this.frame);

				else if (this.upright.size() > 0) {
					this.curr = this.upright.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees >= 202.5
					&& this.angleInDegrees < 247.5) {
				if (this.frame < this.downright.size())
					this.curr = this.downright.get(this.frame);

				else if (this.downright.size() > 0) {
					this.curr = this.downright.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees >= 247.5
					&& this.angleInDegrees < 292.5) {
				if (this.frame < this.down.size())
					this.curr = this.down.get(this.frame);

				else if (this.down.size() > 0) {
					this.curr = this.down.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees >= 292.5
					&& this.angleInDegrees < 337.5) {
				if (this.frame < this.downleft.size())
					this.curr = this.downleft.get(this.frame);

				else if (this.downleft.size() > 0) {
					this.curr = this.downleft.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees < 22.5
					|| this.angleInDegrees >= 337.5) {
				if (this.frame < this.left.size())
					this.curr = this.left.get(this.frame);

				else if (this.left.size() > 0) {
					this.curr = this.left.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees >= 22.5
					&& this.angleInDegrees < 67.5) {
				if (this.frame < this.upleft.size())
					this.curr = this.upleft.get(this.frame);

				else if (this.upleft.size() > 0) {
					this.curr = this.upleft.get(0);
					this.frame = 0;// reset to 0
				}
			} else if (this.angleInDegrees >= 67.5
					&& this.angleInDegrees < 112.5)
				if (this.frame < this.up.size())
					this.curr = this.up.get(this.frame);

				else if (this.up.size() > 0) {
					this.curr = this.up.get(0);
					this.frame = 0;// reset to 0
				}
			this.timer++;
		} else {
			this.timer = 0;
			this.frame++;// next frame
		}
        }
	}
}
