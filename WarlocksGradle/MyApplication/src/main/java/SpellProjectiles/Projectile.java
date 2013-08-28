package SpellProjectiles;

import android.util.Log;

import Game.DamageType;
import Game.GameObject;
import Tools.Vector;

import com.developmental.myapplication.MenuActivity;
import com.developmental.myapplication.RenderThread;

public class Projectile extends GameObject {
    float damagevalue = 10;
	public Projectile(Vector _from, Vector _to, GameObject shooter) {
		super();
		this.owner = shooter;
		this.health = 100;
		this.objectObjectType = Game.ObjectType.Projectile;
		Vector from = _from.get();
		Vector to = _to.get();
		this.velocity = GetVel(from, to);
		SetVelocity(this.maxVelocity);

	}
    public void Damage(float dmgDealt, DamageType d) {
       switch (d)
       {
           case Lava:
               break;
           case Spell:
               default:
        if (dmgDealt > this.health)
            this.health = 0;
        else
            this.health -= dmgDealt;
               break;
        }
    }


	@Override
	public void Collision(GameObject obj) {
        MenuActivity.sp.play(MenuActivity.explosion,1,1,0,0,1);
		switch (obj.objectObjectType) {
		case Projectile:

            case IceSpell:
        case Bounce:
			if ((obj.owner.id != this.owner.id)) {
				RenderThread.delObject(obj.id);
				RenderThread.delObject(this.id);

                Log.d("INET", "PROJECTILE COLLISION");
        }
			break;
		case GameObject:
		case Player:
		case Enemy:
			if ((this.owner != null) && (obj.id != this.owner.id)) {
				obj.ProjectileHit(this.velocity);
				RenderThread.delObject(this.id);
                obj.Damage(damagevalue,DamageType.Spell);
			}
			break;
		case LineSpell:
			RenderThread.delObject(this.id);
            RenderThread.addObject(new ExplosionProjectile(this.getCenter(),new Vector(200,200),obj.owner));
			break;
		case Meteor:
				if (obj.health ==((MeteorProjectile)obj).landing)
                    break;
        case Explosion:
                if ((this.owner != null) && (obj.id != this.owner.id))
					RenderThread.delObject(this.id);
			break;
		case GravityField:
			this.velocity = this.velocity.add(obj
					.DirectionalPull(this.position,obj.pull));
			break;
            case LinkSpell:
                ((LinkProjectile)obj).Link(this);
                break;
        case SwapProjectile:
            ((SwapProjectile)obj).Swap(this);
            break;
		}

	}

	@Override
	public void Update() {
		if (this.health > 0) {
			super.Update();
			this.health--;
		} else
			RenderThread.delObject(this.id);

	}
}
