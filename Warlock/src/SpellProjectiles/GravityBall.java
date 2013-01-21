package SpellProjectiles;

import Game.GameObject;
import Game.Type;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

public class GravityBall extends Projectile {
	protected float maxVelocity = 4f;

	public GravityBall(Vector _from, Vector _to, GameObject _shooter) {
		super(_from, _to, _shooter);
		this.size = new Vector(300, 300);
		this.paint.setColor(Color.GREEN);
		this.paint.setAlpha(127);
		this.shadowPaint.setColor(Color.argb(200, 0, 0, 0));
		this.ObjectType = Type.GravityField;

		SetVelocity(this.maxVelocity);
		this.health = 1000;
		this.pull = 1;
	}

	@Override
	public void Update() {
		super.Update();
		this.rect = new RectF(this.position.x - this.size.x / 2,
				this.position.y - this.size.y / 2, this.position.x
						+ this.size.x / 2, this.position.y + this.size.y / 2);
	}

	@Override
	public void Collision(GameObject obj) {
		obj.velocity = obj.velocity.add(DirectionalPull(obj.position));
	}

	@Override
	public void Draw(Canvas c) {
		c.drawCircle(this.position.x, this.position.y, this.size.x / 2,
				this.paint);

	}

}