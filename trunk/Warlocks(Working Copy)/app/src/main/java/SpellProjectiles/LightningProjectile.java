package SpellProjectiles;


import com.developmental.warlocks.R;

import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;
import javax.microedition.khronos.opengles.GL11Ext;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

public class LightningProjectile extends Projectile {
    public Vector Start, Dest;
    public float Range;
    public ArrayList<Integer> collisions = new ArrayList<Integer>();


    public LightningProjectile(Vector _start, Vector _dest, GameObject _parent,int Rank) {
        super(R.drawable.spell_lightning,_start, _dest, _parent,Rank);


        Range = 600;

mGrid= Grid.LightningLineGrid(Range);

    Start = _start.get();
        this.Dest = _dest;
        float dx = this.Start.x - this.Dest.x;
        float dy = this.Start.y - this.Dest.y;
        float ToteDist = Math.abs(dx) + Math.abs(dy);
        this.objectObjectType = ObjectType.LineSpell;
        this.Dest = new Vector(Start.x - ((dx / ToteDist) * Range), Start.y - ((dy / ToteDist) * Range));
        // Dest=new Vector(dx/ToteDist*maxVelocity,dy/ToteDist*maxVelocity);
        //this.health = 3;
        // shadowPaint = new Paint();
        // this.damagevalue=15;

        this.knockback =30;

       // SimpleGLRenderer.addParticle(new glParticle(Start, Dest, this.velocity, 7,  R.drawable.fireball));
        //this.paint.setAlpha(125);
    }
@Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;
        this.health=1;
        switch (rank)
        {
            case 1:
                this.knockback =7;
                this.size = new Vector(50,50);
                this.damagevalue = 6;

                break;
            case 2:
                this.knockback =8.5;
                this.size = new Vector(50,50);
                this.damagevalue = 7;
                break;
            case 3:
                this.knockback =10;
                this.size = new Vector(50,50);
                this.damagevalue = 8;
                break;
            case 4:
                this.knockback =11.5;
                this.size = new Vector(50,50);
                this.damagevalue = 9;
                break;
            case 5:
                this.knockback =13;
                this.size = new Vector(60,60);
                this.damagevalue = 10;
                break;
            case 6:
                this.knockback =14.5;
                this.size = new Vector(60,60);
                this.damagevalue = 11;
                break;
            case 7:
                this.knockback =16;
                this.size = new Vector(60,60);
                this.damagevalue = 12;
                break;
        }

    }


}
