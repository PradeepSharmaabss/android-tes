package Actors;

import Game.Destination;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;


public abstract class Enemy extends Player {

    protected int howOftenAttacksOccur = 20;
   protected int howOftenMovesOccur = 30;
    float maxDistanceOfDetection = 300;
    int os = 0;
    public Enemy(int _charsheet, SpellInfo[] _spellList, Vector _position) {
        super(_charsheet,_spellList,_position);
        this.objectObjectType = ObjectType.Enemy;
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
            //  AIMoveUpdate();
        }
        if((this.lifePhase+this.os)%howOftenAttacksOccur ==howOftenAttacksOccur-1)
        {
           this.AIAttackUpdate();
        }
        super.Update();
    }

}