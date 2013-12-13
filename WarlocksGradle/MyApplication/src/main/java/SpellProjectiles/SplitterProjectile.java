package SpellProjectiles;

import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import Tools.Vector;

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
        if (i++ % 5 == 0) {
            float _x = this.rect.left
                    + this.rect.width() / 2
                    + (float) (Math.cos((float) i * 5 / 180 * Math.PI));
            float _y = this.rect.top
                    + this.rect.height() / 2
                    + (float) (Math.sin((float) i * 5 / 180 * Math.PI));


            RenderThread.addObject(new SplitterChildrenProjectile(new Vector(this.rect.left
                    + this.rect.width() / 2, this.rect.top
                    + this.rect.height() / 2), new Vector(_x, _y), this.owner));
            RenderThread.gameObjects.get(RenderThread.gameObjects.size() - 1).velocity.add(this.velocity);
            _x = this.rect.left
                    + this.rect.width() / 2
                    + (float) (Math.cos((float) (i + 180) * 5 / 180 * Math.PI));
            _y = this.rect.top
                    + this.rect.height() / 2
                    + (float) (Math.sin((float) (i + 180) * 5 / 180 * Math.PI));


            RenderThread.addObject(new SplitterChildrenProjectile(new Vector(this.rect.left
                    + this.rect.width() / 2, this.rect.top
                    + this.rect.height() / 2), new Vector(_x, _y), this.owner));
            RenderThread.gameObjects.get(RenderThread.gameObjects.size() - 1).velocity.add(this.velocity);
        }
    }
}
