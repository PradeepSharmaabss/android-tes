package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 28/08/13.
 */
public class SplitterChildrenProjectile extends Projectile {
    public SplitterChildrenProjectile(Vector _from, Vector _to, Collideable shooter,int Rank) {
        super(R.drawable.spell_fireball,_from,_to,shooter,Rank);

    }
    @Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 20;
                this.knockback =3;
                this.size = new Vector(15,15);
                this.damagevalue = 3;

                break;
            case 2:
                this.health = 20;
                this.knockback =3;
                this.size = new Vector(15,15);
                this.damagevalue = 3;
                break;
            case 3:
                this.health = 20;
                this.knockback =4;
                this.size = new Vector(15,15);
                this.damagevalue = 3;
                break;
            case 4:
                this.health = 20;
                this.knockback =4;
                this.size = new Vector(15,15);
                this.damagevalue = 4;
                break;
            case 5:
                this.health = 20;
                this.knockback =4;
                this.size = new Vector(15,15);
                this.damagevalue = 4;
                break;
            case 6:
                this.health = 20;
                this.knockback =5;
                this.size = new Vector(15,15);
                this.damagevalue = 4;
                break;
            case 7:
                this.health = 20;
                this.knockback =5;
                this.size = new Vector(15,15);
                this.damagevalue = 5;
                break;
        }


    }
}
