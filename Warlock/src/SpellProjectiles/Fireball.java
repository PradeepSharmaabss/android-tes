package SpellProjectiles;

import Game.GameObject;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;

public class Fireball extends Projectile {
	public Fireball(Vector _from, Vector _to, GameObject _shooter) {
		super(_from, _to, _shooter);
		this.paint.setColor(Color.RED);
		this.shadowPaint.setColor(Color.argb(200, 0, 0, 0));
	}

	@Override
	public void Draw(Canvas c) {
		c.drawCircle(this.position.x, this.position.y, this.size.x / 2,
				this.paint);
		for (int x = 0; x < 10; x++) {
			this.paint.setARGB(((10 - x) * 25), 255, x
					* (int) (Math.random() * 25), 0);
			// paint.setColor(paint.getColor()+(int)(x*Math.random()*10));
			float posx = this.position.x
					- (int) (20 * Math.random() - 10 + this.velocity.x * x), posy = this.position.y
					- (int) (20 * Math.random() - 10 + this.velocity.y * x);
			c.drawCircle(posx + 20, posy + 20, this.size.x / 3,
					this.shadowPaint);
			c.drawCircle(posx, posy, this.size.x / 3, this.paint);

		}

	}

}
