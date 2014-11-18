package SpellProjectiles;

import android.util.Log;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 13/11/2014.
 */
public class OrbitalProjectile extends Projectile {
        float offset = 0;

    @Override
    protected void setFrames() {
        this.FramesNoTail();

    }
    Vector Home ;
    public OrbitalProjectile(Vector _from, Vector _to, GameObject shooter, float _offset,int Rank) {
        super(R.drawable.spell_orbital, _from, _to, shooter,Rank);
        offset = _offset;
        Movement();

    }
    @Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 100;
                this.knockback =7;
                this.size = new Vector(50,50);
                this.damagevalue = 6;

                break;
            case 2:
                this.health = 110;
                this.knockback =8.5;
                this.size = new Vector(50,50);
                this.damagevalue = 7;
                break;
            case 3:
                this.health = 120;
                this.knockback =10;
                this.size = new Vector(50,50);
                this.damagevalue = 8;
                break;
            case 4:
                this.health = 130;
                this.knockback =11.5;
                this.size = new Vector(50,50);
                this.damagevalue = 9;
                break;
            case 5:
                this.health = 140;
                this.knockback =13;
                this.size = new Vector(60,60);
                this.damagevalue = 10;
                break;
            case 6:
                this.health = 150;
                this.knockback =14.5;
                this.size = new Vector(60,60);
                this.damagevalue = 11;
                break;
            case 7:
                this.health = 160;
                this.knockback =16;
                this.size = new Vector(60,60);
                this.damagevalue = 12;
                break;
        }


    }

    @Override
    protected void Movement() {
        float angle = (float) Math.toRadians(offset + lifePhase * 5);
        float w = 100;
        Vector Dest1 = new Vector((float) (w * Math.cos(angle) + this.owner.bounds.Center.x), (float) (w * Math.sin(angle) + this.owner.bounds.Center.y));

        this.position = Dest1;
    }

    @Override
    protected void Rotate() {

    }

}
