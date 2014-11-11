package SpellProjectiles;


import android.util.Log;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.NewHeirarchy.Collideable;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;


/**
 * Created by Scott on 27/07/13.
 */
public class LinkProjectile extends LightningProjectile {

    public Collideable linked = null;

    public LinkProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(shooter.bounds.Center,_to, shooter);
        this.objectObjectType = ObjectType.LinkSpell;
        this.health=500;

        this.pull = 2;
seter = -1;
        this.damagevalue = 0.1f;
        Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+_to.x+","+_to.y);
    }

    @Override
    public void Update() {

if(linked!=null)
    if(linked.health<=0||linked.position==null)
        {
            linked=null;
            //Start = this.linked.bounds.Center;
        }
        if(owner.health<=0)
        {

        }
        if (this.health > 0) {
            if (linked == null) {
                super.Update();
                Dest= owner.bounds.Center;
                //   Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+owner.position.x+","+owner.position.y+"AND "+owner.velocity.x+","+owner.velocity.y);
                Start = this.bounds.Center;
            } else {
                owner.velocity = owner.velocity.add(linked
                        .DirectionalPull(owner.position, this.pull));
                linked.velocity = linked.velocity.add(owner
                        .DirectionalPull(linked.position, this.pull));


                Start = linked.bounds.Center;
                Dest = this.owner.bounds.Center;
                switch (linked.objectObjectType) {
                    case Player:
                    case Enemy:
                    case GameObject:

                        DealDamageTo(linked);

                        break;
                }
                health -= 1;
            }
            Range= Vector.DistanceBetween(Start,Dest);
          mGrid=  Grid.LightningLineGrid(Range);
        } else {

            SimpleGLRenderer.delObject(this.id);
        }

    }



    public void Link(Collideable g) {
        if(linked==null)
        this.linked = g;
//        paint.setColor(Color.WHITE);
//        paint.setStrokeWidth(4);
    }

}
