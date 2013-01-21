package SpellProjectiles;

import Game.GameObject;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class Meteor extends Projectile {
	int height = 400;
	public static final int landing = 10;

	public Meteor(Vector _from, Vector _to, GameObject shooter) {
		super(_from, _to, shooter);
		this.health = 110;
		this.size = new Vector(150, 150);
		this.maxVelocity = 4;
		Vector from = _from.get();
		Vector to = _to.get();
		this.paint.setColor(Color.CYAN);

		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
		this.velocity = new Vector(this.maxVelocity * (distanceX / totalDist),
				this.maxVelocity * distanceY / totalDist);
		this.position = new Vector(to.x - this.velocity.x * 100, to.y
				- this.velocity.y * 100);
		// TODO Auto-generated constructor stub
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

		switch (obj.ObjectType) {
		case Projectile:
		case GameObject:
		case Enemy:
			if (this.health == landing)
				if (obj.id != this.owner.id)
					obj.ProjectileHit(this.velocity);
			break;
		case LineSpell:

			break;
		case Meteor:
			if (obj.id != this.owner.id) {
			}
			break;
		case GravityField:
			this.velocity.add(obj.DirectionalPull(this.position));
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
	public void Draw(Canvas c) {

		c.drawCircle(this.position.x, this.position.y, this.size.x / 3,
				this.shadowPaint);
		c.drawCircle(this.position.x, this.position.y - this.height,
				this.size.x / 3, this.paint);

	}

}
