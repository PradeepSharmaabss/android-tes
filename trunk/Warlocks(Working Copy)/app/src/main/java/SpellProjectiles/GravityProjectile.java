package SpellProjectiles;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import com.developmental.warlocks.R;

import java.util.ArrayList;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.Global;

public class GravityProjectile extends Projectile {
    protected float maxVelocity = 10f;
    ArrayList<Ball> FrontBalls;
    public GravityProjectile(Vector _from, Vector _to, GameObject _shooter) {
        super(R.drawable.spell_gravity,_from, _to, _shooter, 200, 15f, new Vector(300, 300), 1);


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
        this.objectObjectType = Game.ObjectType.GravityField;
        Vector from = _from.get();
        Vector to =_to; //new Vector(_to.x-size.x/2,_to.y-size.y/2);

        this.velocity = GetVel(from, to.get());
        SetVelocity(this.maxVelocity);

        this.pull = 1;

//        this.damagevalue=1;
    }

    @Override
    protected void setFrames() {
      FramesNoTail();
    }

    @Override
    public void Update() {
        super.Update();

        //Animate();
        this.bounds.Center = position;
        for(Ball b:FrontBalls)
            b.angle+=b.speed;
    }

    int currFrame = 0;
    int frameRate = 1;
    int frameDelay = 2;
    int i = 0;

    public void Animate() {
     super.Animate();
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
                    * (float) Math.cos(Math.toRadians(angle));
            float _y = (playery
                    * (float) Math.sin(Math.toRadians(angle)))-this.height*15;
            return new Vector(_x, _y);
        }
    }

}