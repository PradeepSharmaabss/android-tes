package SpellProjectiles;

import Game.GameObject;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.example.warlockgame.RenderThread;

public class MeteorProjectile extends Projectile {
	int height = 400;
	public static final int landing = 10;

	public MeteorProjectile(Vector _from, Vector _to, GameObject shooter) {
		super(_from, _to, shooter);
		this.health = 110;
		this.size = new Vector(150, 150);
		this.maxVelocity = 4;
		this.paint.setColor(Color.CYAN);
		this.objectObjectType = Game.ObjectType.Meteor;
		this.velocity = GetVel(_from, _to);
        this.pull= 10;

	}

	@Override
	protected Vector GetVel(Vector from, Vector to) {
		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
		Vector v = new Vector(this.maxVelocity * (distanceX / totalDist),
				this.maxVelocity * distanceY / totalDist);
		this.position = new Vector(to.x - v.x * 100, to.y - v.y * 100);
		return v;

	}

	@Override
	public void Update() {
		super.Update();
		if (this.height > 0)
			this.height -= 4;
		if (this.health < landing) {
			this.velocity = new Vector(0, 0);
			this.size = new Vector(250, 250);
		}
	}

	@Override
	public void Collision(GameObject obj) {

		switch (obj.objectObjectType) {
		case Projectile:
            if(this.health==landing)
            RenderThread.delObject(obj.id);
            break;
		case GameObject:
		case Player:
		case Enemy:
			if (this.health == landing)
				if (obj.id != this.owner.id)
					obj.ProjectileHit(this.velocity.multiply(this.velocity,10));
			break;
		case LineSpell:

			break;
		case Meteor:
			if (obj.id != this.owner.id) {
			}
			break;
		case GravityField:
            this.velocity = this.velocity.add(obj
                    .DirectionalPull(this.position,obj.pull));
			break;

		}
	}

	@Override
	public boolean Intersect(RectF PassedObj) {
		if (this.health == landing)
			return super.Intersect(PassedObj);
		return false;
	}

	@Override
	public void Draw(Canvas c,float playerx,float playery) {

		c.drawCircle(this.position.x-playerx, this.position.y-playery, this.size.x / 3,
				this.shadowPaint);
		c.drawCircle(this.position.x-playerx, this.position.y - this.height-playery,
				this.size.x / 3, this.paint);

	}

}
