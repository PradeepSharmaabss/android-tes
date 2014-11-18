package SpellProjectiles;


import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 28/08/13.
 */
public class SplitterProjectile extends FireballProjectile {

int Rank = 1;
    public SplitterProjectile(Vector _from, Vector _to, GameObject shooter,int Rank) {
        super(_from, _to, shooter, Rank);
        this.Rank=Rank;
        this.health = 200;
        i = (int) (Math.random() * 360f);
        this.maxVelocity = 4;
        SetVelocity(maxVelocity);
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

    int i = 0;
    int p = 0;
    @Override
    public void Update() {
        super.Update();
        if (i++ % 4 == 3) {

            double degrees1 = i * 2+(120*p++);



            float w = Vector.DistanceBetween(this.bounds.Center,this.velocity);
            Vector Dest1 = new Vector((float)(w*Math.cos(degrees1)+ this.bounds.Center.x),(float)(w*Math.sin(degrees1)+ this.bounds.Center.y));

            SimpleGLRenderer.addObject(new SplitterChildrenProjectile(this.bounds.Center, Dest1, this.owner,this.Rank));







        }
    }
}
