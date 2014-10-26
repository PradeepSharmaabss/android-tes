package SpellProjectiles;


import android.util.Log;

import com.developmental.warlocks.R;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;


/**
 * Created by Scott on 27/07/13.
 */
public class LinkProjectile extends Projectile {

    public GameObject linked = null;

    public LinkProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.spell_lightning,_from, _to, shooter, 100, 20, new Vector(50, 50), 1);
        this.objectObjectType = ObjectType.LinkSpell;
        this.pull = 2;

        Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+_to.x+","+_to.y);
    }

    @Override
    public void Update() {
    //   Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+owner.position.x+","+owner.position.y+"AND "+owner.velocity.x+","+owner.velocity.y);
if(linked!=null)
    if(linked.health<=0||linked.position==null)
        {
            linked=null;

        }
        if(owner.health<=0)
        {

        }
        if (this.health > 0) {
            if (linked == null) {
                super.Update();

            } else {
                owner.velocity = owner.velocity.add(linked
                        .DirectionalPull(owner.position, this.pull));
                linked.velocity = linked.velocity.add(owner
                        .DirectionalPull(linked.position, this.pull));
                this.bounds = linked.bounds;
                switch (linked.objectObjectType) {
                    case Player:
                    case Enemy:
                    case GameObject:

                        DealDamageTo(linked);

                        break;
                }
                health -= 1;
            }

        } else {

            SimpleGLRenderer.delObject(this.id);
        }

    }



    public void Link(GameObject g) {
        this.linked = g;
//        paint.setColor(Color.WHITE);
//        paint.setStrokeWidth(4);
    }

}
