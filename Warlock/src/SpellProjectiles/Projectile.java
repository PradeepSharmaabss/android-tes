package SpellProjectiles;

import Game.GameObject;
import Game.Type;
import Tools.Vector;

import com.example.warlockgame.RenderThread;

public class Projectile extends GameObject {
	public Projectile(Vector _from, Vector _to, GameObject shooter) {
		super();
		this.health = 100;
		this.owner = shooter;
		this.ObjectType = Type.Projectile;
		Vector from = _from.get();
		Vector to = _to.get();
		this.velocity = GetVel(from, to);
		SetVelocity(this.maxVelocity);

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
		switch (obj.ObjectType) {
		case Projectile:
			if (obj.owner.id != this.owner.id) {
				RenderThread.delObject(obj.id);
				RenderThread.delObject(this.id);
			}
			break;
		case GameObject:
			if (obj.id != this.owner.id) {
				obj.ProjectileHit(this.velocity);
				RenderThread.delObject(this.id);
			}
			break;
		case Enemy:
			if (obj.id != this.owner.id) {
				obj.ProjectileHit(this.velocity);
				RenderThread.delObject(this.id);
			}
			break;
		case LineSpell:
			RenderThread.delObject(this.id);
			break;
		case Meteor:
			if (obj.id != this.owner.id)
				if (obj.health == 1)
					RenderThread.delObject(this.id);
			break;
		case GravityField:
			this.velocity = this.velocity.add(obj
					.DirectionalPull(this.position));
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
