package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;

import Game.GameObject;
import Game.ObjectType;
import Game.SpellEffect;
import Particles.IceParticle;
import Tools.Vector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

/**
 * Created by Scott on 7/29/13.
 */
public class IceProjectile extends Projectile {
    public IceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter, 100, 10, new Vector(50, 50), 6);
        this.paint.setColor(Color.BLUE);
        this.objectObjectType = ObjectType.IceSpell;
    }

    @Override
    public void Collision(GameObject obj) {
        switch (obj.objectObjectType) {
            case IceSpell:
            case Projectile:
            case Bounce:
                if (obj.owner.id != this.owner.id) {
                    RenderThread.delObject(obj.id);
                    RenderThread.delObject(this.id);
                }
                break;
            case GravityField:
                this.velocity = this.velocity.add(obj
                        .DirectionalPull(this.position, obj.pull));
                break;
            case GameObject:
            case Enemy:
            case Player:
                obj.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, Global.Sprites.get(3), obj));
                RenderThread.delObject(this.id);

                DealDamageTo(obj);
                break;


            case LineSpell:
                RenderThread.delObject(this.id);
                RenderThread.addObject(new ExplosionProjectile(this.getCenter(), new Vector(200, 200), obj.owner));
                break;
            case Meteor:
                if (obj.health == ((MeteorProjectile) obj).landing)
                    RenderThread.delObject(this.id);
                break;
            case Explosion:
                if ((this.owner != null) && (obj.id != this.owner.id))
                    RenderThread.delObject(this.id);
                break;
            case LinkSpell:
                ((LinkProjectile) obj).Link(this);
                break;


            case SwapProjectile:
                ((SwapProjectile) obj).Swap(this);
                break;
        }


    }

    @Override
    public void Update() {
        super.Update();
        RenderThread.addParticle(new IceParticle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        RenderThread.addParticle(new IceParticle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        RenderThread.addParticle(new IceParticle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
        RenderThread.addParticle(new IceParticle(this.getCenter(), this.velocity.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()), 10, this.paint));
    }

    @Override
    public void Draw(Canvas c, float playerx, float playery) {

        c.drawCircle(this.rect.centerX() - playerx, this.rect.centerY() - playery, this.size.x / 2,
                this.paint);

    }

}
