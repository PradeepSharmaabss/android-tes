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


    public LightningProjectile(Vector _start, Vector _dest, GameObject _parent) {
        super(R.drawable.spell_lightning,_start, _dest, _parent, 1, 4, new Vector(50, 50), 15);


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




}
