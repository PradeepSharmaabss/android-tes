package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 2/01/14.
 */
public class PiercingProjectile extends Projectile {
    private int projectiles = 0;

    @Override
    protected void setFrames() {
        FramesTail();
    }

    public PiercingProjectile(Vector _from, Vector _to, GameObject _shooter,int Rank) {
        super(R.drawable.spell_piercing,_from, _to, _shooter,Rank);

        this.objectObjectType= ObjectType.Piercing ;
    }

    @Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 0.3f;

                break;
            case 2:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 0.5f;
                break;
            case 3:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 0.7f;
                break;
            case 4:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 0.9f;
                break;
            case 5:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 1.1f;
                break;
            case 6:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 1.3f;
                break;
            case 7:
                this.health = 500;
                this.knockback =0;
                this.size = new Vector(150,150);
                this.damagevalue = 1.5f;
                break;
        }


    }

}
