package SpellProjectiles;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.myapplication.Global;

import java.util.ArrayList;

import Game.GameObject;
import Tools.Vector;

public class GravityProjectile extends Projectile {
    protected float maxVelocity = 10f;
    ArrayList<Ball> FrontBalls;
    ArrayList<Bitmap> frames = new ArrayList<Bitmap>();
    Paint paint2= new Paint();
    public GravityProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(_from, _to, _shooter, 200, 15f, new Vector(300, 300), 1);

        this.paint.setColor(Color.LTGRAY);
        this.paint2.setColor(Color.DKGRAY);
        this.paint.setAlpha(125);this.paint2.setAlpha(125);
        FrontBalls =new ArrayList<Ball>();
        for(int i = 0; i<15; i++)
        {
            int rnd = Global.GetRandomNumer.nextInt()%120;
            int rnd2 = Global.GetRandomNumer.nextInt()%10+4;
            FrontBalls.add(new Ball(rnd,i,rnd2));
            FrontBalls.add(new Ball(rnd+120,i,rnd2));
            FrontBalls.add(new Ball(rnd+240,i,rnd2));


        }
       // this.paint.setAlpha(100);
        this.shadowPaint.setColor(Color.argb(200, 0, 0, 0));
        this.objectObjectType = Game.ObjectType.GravityField;

        SetVelocity(this.maxVelocity);

        this.pull = 1;
        this.frames = Global.Sprites.get(6);
        this.curr = Global.Sprites.get(6).get(0);

//        this.damagevalue=1;
    }

    int p = 0;

    @Override
    public void Update() {
        super.Update();
        this.rect = new RectF(this.position.x - this.size.x / 2,
                this.position.y - this.size.y / 2, this.position.x
                + this.size.x / 2, this.position.y + this.size.y / 2);
        Animate();
        p++;
        this.bounds.Center = position;
        for(Ball b:FrontBalls)
            b.angle+=b.speed;
    }

    int currFrame = 0;
    int frameRate = 1;
    int frameDelay = 2;
    int i = 0;

    public void Animate() {
        if (i < frameDelay)
            i++;
        else {
            i = 0;
            currFrame += frameRate;
            if (currFrame >= frames.size()) {

                currFrame = 0;
            }
        }
        curr = frames.get(currFrame);
    }

    @Override
    public void Collision(GameObject obj) {
        switch (obj.objectObjectType) {

            case GameObject:
            case Enemy:
            case Player:
                DealDamageTo(obj);
            case Projectile:
            case LineSpell:
            case Meteor:
            case GravityField:
            case LinkSpell:
            case IceSpell:
            case Bounce:
            case SwapProjectile:
                obj.velocity = obj.velocity.add(this
                        .DirectionalPull(obj.position, pull));
            case Explosion:
                break;
        }


        //obj.velocity = obj.velocity.add(DirectionalPull(obj.position));
    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {
//        c.drawCircle(position.x - playerx, position.y - playery, 150, paint);
//        c.drawCircle(position.x - playerx, position.y - playery, (200 - health) * 2 / 3, paint);
//
//        c.drawCircle(position.x - playerx, position.y - playery, (200 - p) * 2 / 3, paint);
        this.dRect = new RectF(this.position.x - playerx - size.x / 2, this.position.y - playery - size.y / 2, this.position.x - playerx + size.x / 2, this.position.y - playery + size.y / 2);
        //	c.drawBitmap(curr,this.position.x-playerx-size.x/2, (float) (this.position.y-playery-(size.y*1.5)),paint);
//        c.drawCircle(this.position.x-playerx, this.position.y-playery, this.size.x / 2,
//				this.paint);

            c.drawText(""+FrontBalls.get(0).angle%360,position.x-playerx,position.y-playery,paint);

//            for(Ball b:FrontBalls)
//        {
//            float i = b.angle%360;
//            if((i>180&&i<360))
//            {
//                Vector v = b.PositiononEllipse(35,15);
//                c.drawCircle(position.x+v.x-playerx,position.y+v.y-playery,10,this.paint2);
//            }
//        }

        for(Ball b:FrontBalls)
        {
            float i = b.angle%360;
            if((i>180&&i<360))
            {
                Vector v = b.PositiononEllipse(20+b.height*7,40);
                c.drawCircle(position.x+v.x-playerx,position.y+v.y-playery,10+b.height,this.paint2);
            }
        }
        for(Ball b:FrontBalls)
        {
            float i = b.angle%360;
            if((i>0&&i<180))
            {
                Vector v = b.PositiononEllipse(20+b.height*7,40);
                c.drawCircle(position.x+v.x-playerx,position.y+v.y-playery,10+b.height,this.paint);
            }
        }
    }
    private class Ball
    {
      public float angle;
        float height;
        int speed;
        public Ball(float _angle, float _height,int _speed)
        {
            angle = _angle;
            height=_height;
            speed= _speed;
        }
        public Vector PositiononEllipse(float playerx, float playery) {
            float _x = (playerx)
                    * (float) Math.cos((double) Math.toRadians(angle));
            float _y = (playery
                    * (float) Math.sin((double) Math.toRadians(angle)))-this.height*15;
            return new Vector(_x, _y);
        }
    }

}