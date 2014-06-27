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

    @Override
    public void Update() {
        super.Update();
        if (i++ % 5 == 4) {
            float _x =bounds.Center.x
                    + (float) (Math.cos((float) i * 5 / 180 * Math.PI));
            float _y =bounds.Center.y
                    + (float) (Math.sin((float) i * 5 / 180 * Math.PI));


            SimpleGLRenderer.addObject(new SplitterChildrenProjectile(new Vector(this.rect.left
                    + this.rect.width() / 2, this.rect.top
                    + this.rect.height() / 2), new Vector(_x, _y), this.owner));
            SimpleGLRenderer.gameObjects.get(SimpleGLRenderer.gameObjects.size() - 1).velocity.add(this.velocity);
            _x = bounds.Center.x
                    + (float) (Math.cos((float) (i + 180) * 5 / 180 * Math.PI));
            _y = bounds.Center.x
                    + (float) (Math.sin((float) (i + 180) * 5 / 180 * Math.PI));


            SimpleGLRenderer.addObject(new SplitterChildrenProjectile(new Vector(this.rect.left
                    + this.rect.width() / 2, this.rect.top
                    + this.rect.height() / 2), new Vector(_x, _y), this.owner));
            SimpleGLRenderer.gameObjects.get(SimpleGLRenderer.gameObjects.size() - 1).velocity.add(this.velocity);
        }
    }
}
