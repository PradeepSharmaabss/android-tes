package SpellProjectiles;

import com.developmental.warlocks.R;

import Spells.Archetype.ArchetypePower;
import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 31/12/13.
*/
public class FiresprayProjectile extends Projectile {


    public FiresprayProjectile(Vector _from, Vector _to, GameObject _shooter,int Rank) {
        super(R.drawable.spell_firespray,_from, _to, _shooter,Rank);
        archetypePower= new ArchetypePower(0,0,0,0,0,0,20);
    }
    @Override
    protected void Stats(int rank)
    {
        this.maxVelocity = 15;

        switch (rank)
        {
            case 1:
                this.health = 100;
                this.knockback =3;
                this.size = new Vector(50,50);
                this.damagevalue = 6;

                break;
            case 2:
                this.health = 110;
                this.knockback =3;
                this.size = new Vector(50,50);
                this.damagevalue = 7;
                break;
            case 3:
                this.health = 120;
                this.knockback =3;
                this.size = new Vector(50,50);
                this.damagevalue = 8;
                break;
            case 4:
                this.health = 130;
                this.knockback =3;
                this.size = new Vector(50,50);
                this.damagevalue = 9;
                break;
            case 5:
                this.health = 140;
                this.knockback =3;
                this.size = new Vector(60,60);
                this.damagevalue = 10;
                break;
            case 6:
                this.health = 150;
                this.knockback =3;
                this.size = new Vector(60,60);
                this.damagevalue = 11;
                break;
            case 7:
                this.health = 160;
                this.knockback =3;
                this.size = new Vector(60,60);
                this.damagevalue = 12;
                break;
        }


    }

}
