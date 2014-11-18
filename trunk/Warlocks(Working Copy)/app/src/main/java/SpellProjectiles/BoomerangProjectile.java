package SpellProjectiles;



import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangProjectile extends Projectile {
    public BoomerangProjectile(Vector _from, Vector _to, GameObject shooter,int Rank) {
        super(R.drawable.spell_boomerang,_from, _to, shooter,Rank);

        this.objectObjectType= ObjectType.Boomerang;


    }

    @Override
    protected void setFrames() {
        FramesNoTail();
    }

@Override
protected void Stats(int rank)
{
    this.maxVelocity = 15;
    this.acceleration = 0.5f;
    switch (rank)
    {
        case 1:
            this.health = 9000;
            this.knockback =7;
            this.size = new Vector(80,80);
            this.damagevalue = 6;

            break;
        case 2:
            this.health = 9000;
            this.knockback =8.5;
            this.size = new Vector(80,80);
            this.damagevalue = 7;
            break;
        case 3:
            this.health = 9000;
            this.knockback =10;
            this.size = new Vector(80,80);
            this.damagevalue = 8;
            break;
        case 4:
            this.health = 9000;
            this.knockback =11.5;
            this.size = new Vector(80,80);
            this.damagevalue = 9;
            break;
        case 5:
            this.health = 9000;
            this.knockback =13;
            this.size = new Vector(80,80);
            this.damagevalue = 10;
            break;
        case 6:
            this.health = 9000;
            this.knockback =14.5;
            this.size = new Vector(80,80);
            this.damagevalue = 11;
            break;
        case 7:
            this.health = 9000;
            this.knockback =16;
            this.size = new Vector(80,80);
            this.damagevalue = 12;
            break;
    }


}


    @Override
    public void Update() {
        super.Update();
        if (this.destination != null)
            MoveTowards(this.destination, maxVelocity , acceleration );


        if(lifePhase>15)
        if (    lifePhase% 5 == 4) {



            this.destination = owner.feet;
        }
        if (lifePhase > 50)
            if (this.bounds.CollidesWith(owner.bounds))
                SimpleGLRenderer.delObject(this.id);

    }

    @Override
    protected void Rotate() {
        this.rotation+=5;
    }
}
