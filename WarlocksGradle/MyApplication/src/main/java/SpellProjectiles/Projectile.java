package SpellProjectiles;

import android.util.Log;

import Game.DamageType;
import Game.GameObject;
import Tools.Vector;

import com.developmental.myapplication.MenuActivity;
import com.developmental.myapplication.RenderThread;

public class Projectile extends GameObject {
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
	protected Vector GetVel(Vector from, Vector to) {
		this.position = from;
		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
		return new Vector(this.maxVelocity * (distanceX / totalDist),
				this.maxVelocity * distanceY / totalDist);
	}

	@Override
	public void Collision(GameObject obj) {
        MenuActivity.sp.play(MenuActivity.explosion,1,1,0,0,1);
		switch (obj.objectObjectType) {
		case Projectile:
        case IceSpell:
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
			}
			break;
		case LineSpell:
			RenderThread.delObject(this.id);
			break;
		case Meteor:
			if ((this.owner != null) && (obj.id != this.owner.id))
				if (obj.health == 1)
					RenderThread.delObject(this.id);
			break;
		case GravityField:
			this.velocity = this.velocity.add(obj
					.DirectionalPull(this.position,obj.pull));
			break;
        case SwapProjectile:
            Vector l;
            l = obj.owner.position;
            obj.owner.position=this.position;
            this.position=l;
            RenderThread.delObject(obj.id);
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
