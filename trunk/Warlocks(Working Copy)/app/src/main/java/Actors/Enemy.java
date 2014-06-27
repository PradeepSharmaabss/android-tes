package Actors;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Game.Destination;
import Game.ObjectType;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;


public abstract class Enemy extends Player {

    float maxDistanceOfDetection = 300;
    int os = 0;
   protected int howOftenAttacksOccur = 50;
   protected int howOftenMovesOccur = 30;
    public Enemy(int _charsheet, SpellInfo[] _spellList, Vector _position) {
        super(_charsheet,_spellList,_position);
        this.objectObjectType = ObjectType.Enemy;
    }
    public Enemy( Vector _pos, SpellInfo[] s)
    {
        super(_pos,s);
        this.os  = Global.GetRandomNumer.nextInt()%50;
        this.objectObjectType = ObjectType.Enemy;
        this.rect = new RectF(0, 0, 100, 100);
        this.destination = new Vector(0, 0);
        this.size = new Vector(100, 100);
        this.owner = this;
    }


    protected void AIMoveUpdate()
    {

        if (!SimpleGLRenderer.l.platform.Within(this.feet))
        {
            this.destination= SimpleGLRenderer.l.platform.position.get();
        }
        else
        {
            float angle = Global.GetRandomNumer.nextFloat() * 360;
            this.destination = PositiononEllipse(angle).add(new Vector(Global.WORLD_BOUND_SIZE.x/2,Global.WORLD_BOUND_SIZE.y/2));

            Marker = new Destination(destination);
        }
    }
    protected void AIAttackUpdate()
    {
        float detect = this.maxDistanceOfDetection;
        GameObject s= null;
        for (GameObject p : SimpleGLRenderer.players) {
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


    }

    @Override
    public void Update() {

        // angle+=0.005;
        if ((this.lifePhase+this.os) % howOftenMovesOccur ==howOftenMovesOccur-1) {
            AIMoveUpdate();
        }
        if((this.lifePhase+this.os)%howOftenAttacksOccur ==howOftenAttacksOccur-1)
        {
           this.AIAttackUpdate();
        }
        super.Update();
    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        super.Draw(canvas, playerx, playery);
        if (Global.DEBUG_MODE) {
            Vector p1 = SimpleGLRenderer.archie.bounds.Center, p2 = bounds.Center;
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