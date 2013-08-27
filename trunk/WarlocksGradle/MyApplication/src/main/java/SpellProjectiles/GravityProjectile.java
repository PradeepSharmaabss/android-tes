package SpellProjectiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.developmental.myapplication.Global;

import java.util.ArrayList;

import Game.GameObject;
import Tools.Vector;

public class GravityProjectile extends Projectile {
	protected float maxVelocity = 10f;

    ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
	public GravityProjectile(Vector _from, Vector _to, GameObject _shooter) {
		super(_from, _to, _shooter);
		this.size = new Vector(300, 300);
		this.paint.setColor(Color.GREEN);
		//this.paint.setAlpha(127);
		this.shadowPaint.setColor(Color.argb(200, 0, 0, 0));
		this.objectObjectType = Game.ObjectType.GravityField;

		SetVelocity(this.maxVelocity);
		this.health = 200;
		this.pull = 1;
        this.frames = Global.Sprites.get(6);
        this.curr= Global.Sprites.get(6).get(0);
	}

	@Override
	public void Update() {
		super.Update();
		this.rect = new RectF(this.position.x - this.size.x / 2,
				this.position.y - this.size.y / 2, this.position.x
						+ this.size.x / 2, this.position.y + this.size.y / 2);
        Animate();
	}
    int currFrame = 0;
    int frameRate = 1;
    int frameDelay =5;
    int i = 0;
    public void Animate()
    {
        if(i<frameDelay)
            i++;
        else
        {
            i=0;
            currFrame +=frameRate;
            if(currFrame>=frames.size())
            {

                    currFrame=0;
             }
         }
            curr=frames.get(currFrame);
    }

	@Override
	public void Collision(GameObject obj) {
        obj.velocity = obj.velocity.add(this
                .DirectionalPull(obj.position,pull));
		//obj.velocity = obj.velocity.add(DirectionalPull(obj.position));
	}

	@Override
	public void Draw(Canvas c,float playerx,float playery) {
        this.dRect=new RectF(this.position.x-playerx-size.x/2,this.position.y-playery-size.y/2,this.position.x-playerx+size.x/2,this.position.y-playery+size.y/2);
		c.drawBitmap(curr,this.position.x-playerx-size.x/2,this.position.y-playery-size.y/2,paint);
//        c.drawCircle(this.position.x-playerx, this.position.y-playery, this.size.x / 2,
//				this.paint);

	}

}