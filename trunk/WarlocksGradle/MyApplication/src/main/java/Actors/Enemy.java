package Actors;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import java.util.ArrayList;

import Game.Destination;
import Game.ObjectType;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

public abstract class Enemy extends Player {

    float maxDistanceOfDetection = 300;
    int os = 0;
    public Enemy(ArrayList<Bitmap> _spriteSheet, Vector _pos)
    {
        super(_spriteSheet, _pos);
        this.os  = Global.GetRandomNumer.nextInt()%50;
        super.objectObjectType = ObjectType.Player;
        this.rect = new RectF(0, 0, 100, 100);
        this.destination = new Vector(0, 0);
        this.size = new Vector(100, 100);
        this.owner = this;
    }


    protected void AIMoveUpdate()
    {

        if (!RenderThread.l.platform.Within(this.feet))
        {
            this.destination=RenderThread.l.platform.Position.get();
        }
        else
        {
            float angle = Global.GetRandomNumer.nextFloat() * 360;
            this.destination = PositiononEllipse(angle);

            Marker = new Destination(destination);
        }
    }
    protected void AIAttackUpdate()
    {
        float detect = this.maxDistanceOfDetection;
        Player s= null;
        for (Player p : RenderThread.players) {
            if (p.id != this.id) {
                float distanceX = this.position.x - p.position.x;
                float distanceY = this.position.y - p.position.y;
                float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
                if (totalDist < detect) {
                    detect = totalDist;
                    s = p;

                }
            }
        }
        if(s!=null)
        Spells[0].Cast(new iVector((int)s.bounds.Center.x,(int)s.bounds.Center.y));
//        if(s!=null)
//            for(int i = 0; i<10;i++)
//            {
//
//                if(Spells[i].Current==0)
//                {
//                    Spells[i].Cast(new iVector((int)s.bounds.Center.x,(int)s.bounds.Center.y));
//
//                    return;
//                }
//            }


    }

    @Override
    public void Update() {

        // angle+=0.005;
        if ((this.lifePhase+this.os) % 50 == 49) {
            AIMoveUpdate();
        }
        if((this.lifePhase+this.os)%30 ==1)
        {
           this.AIAttackUpdate();
        }
        super.Update();
    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        super.Draw(canvas, playerx, playery);
        if (Global.DEBUG_MODE) {
            Vector p1 = RenderThread.archie.bounds.Center, p2 = bounds.Center;
            this.paint.setColor(Color.GREEN);
            canvas.drawLine(p2.x - playerx, p2.y - playery, p1.x - playerx, p1.y - playery, this.paint);
            if (destination != null) {
                this.paint.setColor(Color.BLUE);

                canvas.drawLine(p2.x - playerx, p2.y - playery, this.destination.x - playerx, this.destination.y - playery,
                        this.paint);
            }
            this.paint.setColor(Color.WHITE);
            canvas.drawLine(p2.x - playerx, p2.y - playery, p2.x + 30 * this.velocity.x - playerx, p2.y + 30
                    * this.velocity.y - playery, this.paint);

            Paint j = new Paint();
            j.setStyle(Paint.Style.STROKE);
            canvas.drawCircle(this.bounds.Center.x - playerx, this.bounds.Center.y - playery, this.maxDistanceOfDetection, j);

    }
}
    }