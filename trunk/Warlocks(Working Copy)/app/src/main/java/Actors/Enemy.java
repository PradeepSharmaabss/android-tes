package Actors;

import android.util.Log;

import java.util.ArrayList;

import Actors.EnemyAI.Node;
import Spells.SpellInfo;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;


public abstract class Enemy extends Player {

    protected int howOftenAttacksOccur = 20;
    protected int howOftenMovesOccur = 10;
    float maxDistanceOfDetection = 300;
    int lifePhaseOffset = 0;
    int DestIndex = 0;
    ArrayList<Node> listOfDestinations = new ArrayList<Node>();
    public Enemy(int _charsheet, SpellInfo[] _spellList, Vector _position) {
        super(_charsheet,_spellList,_position);
        this.objectObjectType = ObjectType.Enemy;
        this.acceleration = 1.5f;
    }

    @Override
    protected void ArrivedAtDestination() {
        if (this.listOfDestinations == null)
            this.listOfDestinations = SimpleGLRenderer.navMesh.GetSafeNodePath(this.position);
        Log.e("Movement Calc", "Index = " + DestIndex + " List Size=+" + listOfDestinations.size());
        if (DestIndex >= listOfDestinations.size() - 1) {

            this.listOfDestinations = SimpleGLRenderer.navMesh.GetSafeNodePath(this.position);

            DestIndex = 0;

        } else {


            DestIndex++;
        }
        if (listOfDestinations != null && listOfDestinations.size() > 0) {

            Node d = listOfDestinations.get(DestIndex);
            if (SimpleGLRenderer.l.platform.Within(new Vector(d.x, d.y))) {
                this.destination = new Vector(d.x, d.y);
            } else {
                this.listOfDestinations = SimpleGLRenderer.navMesh.GetSafeNodePath(this.position);
                DestIndex = 0;
            }
        }
    }

    protected void AIMoveUpdate()
    {

        if (!SimpleGLRenderer.l.platform.Within(this.feet))
        {
            float an = (float) Math.atan2(SimpleGLRenderer.l.platform.center.y - feet.y, SimpleGLRenderer.l.platform.center.y - feet.y);

            this.destination = PositiononEllipse(an);

            Log.e("Movement AI", "NOT IN THE MAP! RUNNING BACK!");
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
        if (this.lifePhase == 5)
            ArrivedAtDestination();
        // angle+=0.005;
        if ((this.lifePhase + this.lifePhaseOffset) % howOftenMovesOccur == howOftenMovesOccur - 1) {
            AIMoveUpdate();
        }
        if ((this.lifePhase + this.lifePhaseOffset) % howOftenAttacksOccur == howOftenAttacksOccur - 1)
        {
            this.AIAttackUpdate();
        }
        super.Update();
    }

}