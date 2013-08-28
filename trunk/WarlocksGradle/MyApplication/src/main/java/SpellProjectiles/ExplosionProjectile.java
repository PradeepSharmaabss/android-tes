package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.DamageType;
import Game.GameObject;
import Game.ObjectType;
import Game.Particle;
import Game.SpellEffect;
import Tools.Vector;

/**
 * Created by Scott on 27/08/13.
 */
public class ExplosionProjectile extends Projectile {
Paint Chunks = new Paint();
    public ExplosionProjectile(Vector _to,Vector _s, GameObject shooter) {
        super(_to, _to, shooter);
        velocity=new Vector(0,0);
        size= _s;
        Chunks.setColor(Color.YELLOW);
        this.health=10;
        this.position=new Vector(_to.x-size.x/2,_to.y-size.y/2);
        this.bounds.Center= _to.get();
     //   Log.d("INET","EXPLOSION CREATED");
        this.objectObjectType = ObjectType.Explosion;

        this.damagevalue=10;
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
        RenderThread.addParticle(new Particle(_to, Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
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

                        Log.d("INET","EXPLOSION HIT");
                        obj.velocity=   Vector.multiply(obj.GetVel(obj.position,getCenter()),-1);

                        obj.Damage(damagevalue, DamageType.Spell);
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
