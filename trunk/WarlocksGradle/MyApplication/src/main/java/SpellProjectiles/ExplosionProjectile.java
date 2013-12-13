package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import Game.ObjectType;
import HUD.PopupText;
import Particles.Particle;
import Tools.Vector;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplosionProjectile extends Projectile {
    Paint Chunks = new Paint();

    public ExplosionProjectile(Vector _to, Vector _s, GameObject shooter) {
        super(_to, _to, shooter, 1, 0, _s, 17);
        Chunks.setColor(Color.YELLOW);
        RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Poison, "Explosion Created at " + position.x + " , " + position.y, RenderThread.archie.position, 100));
        this.objectObjectType = ObjectType.Explosion;
        this.bounds.Center = position;
        this.velocity= new Vector(0,0);
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector(Global.GetRandomNumer.nextFloat() * 4 - 2, -1), Global.GetRandomNumer.nextFloat() * 20 - 10), 20, this.paint));
        RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Poison, "Explosion Created at " + position.x + " , " + position.y, RenderThread.archie.position, 100));
    }

    @Override
    public void Update() {
        super.Update();
    }



    @Override
    public void Collision(GameObject obj) {
        switch (obj.objectObjectType) {
            case Bounce:
            case IceSpell:
            case Projectile:
                if (obj.owner.id != this.id) {
                    RenderThread.delObject(obj.id);

                }
                break;
            case Player:
            case GameObject:
            case Enemy:
                if (this.owner != null)
                    if (obj.id != this.owner.id) {

                        Log.d("INET", "EXPLOSION HIT");
                        obj.velocity = Vector.multiply(obj.GetVel(obj.position, getCenter()), -1);

                        DealDamageTo(obj);
                    }
                break;
            case Meteor:
            case Explosion:
            case GravityField:
            case LinkSpell:
            case SwapProjectile:
                break;

        }
    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
    }
}
