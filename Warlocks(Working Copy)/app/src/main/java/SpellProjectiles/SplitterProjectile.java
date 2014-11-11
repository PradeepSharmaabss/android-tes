package SpellProjectiles;


import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 28/08/13.
 */
public class SplitterProjectile extends FireballProjectile {


    public SplitterProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.health = 200;
        i = (int) (Math.random() * 360f);
        this.maxVelocity = 4;
        SetVelocity(maxVelocity);
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

            SimpleGLRenderer.addObject(new SplitterChildrenProjectile(this.bounds.Center, Dest1, this.owner));







        }
    }
}
