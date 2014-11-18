package SpellProjectiles;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 2/01/14.
 */
public class AbsorptionProjectile extends Projectile {
    private int projectiles = 0;
    public AbsorptionProjectile(Vector _from, Vector _to, GameObject _shooter,int Rank) {
        super(R.drawable.spell_grenade,_from, _to, _shooter,Rank);
        this.FramesNoTail();
        this.objectObjectType= ObjectType.Absorb;
    }
    public void Absorb(Collideable g)
    {
        projectiles+=1;
        this.bounds.Radius+=g.bounds.Radius;
       this.size=this.size.add(new Vector(g.bounds.Radius*2,g.bounds.Radius*2));
        this.FramesNoTail();
        SimpleGLRenderer.delObject(g.id);
    }
@Override
protected void Stats(int rank)
{
    this.maxVelocity = 5;

    switch (rank)
    {
        case 1:
            this.health = 300;
            this.knockback =7;
            this.size = new Vector(150,150);
            this.damagevalue = 6;

            break;
        case 2:
            this.health = 300;
            this.knockback =8.5;
            this.size = new Vector(150,150);
            this.damagevalue = 7;
            break;
        case 3:
            this.health = 300;
            this.knockback =10;
            this.size = new Vector(150,150);
            this.damagevalue = 8;
            break;
        case 4:
            this.health = 300;
            this.knockback =11.5;
            this.size = new Vector(150,150);
            this.damagevalue = 9;
            break;
        case 5:
            this.health = 300;
            this.knockback =13;
            this.size = new Vector(150,150);
            this.damagevalue = 10;
            break;
        case 6:
            this.health = 300;
            this.knockback =14.5;
            this.size = new Vector(150,150);
            this.damagevalue = 11;
            break;
        case 7:
            this.health = 300;
            this.knockback =16;
            this.size = new Vector(150,150);
            this.damagevalue = 12;
            break;
    }


}

}
