package HUD;

import Input.Finger;
import Input.Pointer;
import Tools.Drawable;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class Button extends Drawable {

	public RectF rect;
	public boolean down = false;
	public int id = 0;
	public String name = "default";
	Paint paint2;
	Paint Cd;

	public Button(RectF r, int i) {
		super();
		this.id = i;
		this.paint.setColor(Color.RED);
		this.paint2 = new Paint();
		this.paint2.setColor(Color.WHITE);
		this.rect = r;
		this.Cd = new Paint();
		this.Cd.setColor(Color.GREEN);

	}

	public void Draw(Canvas canvas) {
		canvas.drawRect(this.rect, this.paint2);
		// draw the blue rect
		canvas.drawRect(new RectF(this.rect.left + 2, this.rect.top + 2,
				this.rect.right - 2, this.rect.bottom - 2), this.paint);
		// Draw the cooldown

	}

	public void Update() {
		for (int x = 0; x < Finger.pointers.size(); x++) {
			Pointer f = Finger.pointers.get(x);

			if (f.down == false)
				continue;

			if (this.rect.contains(f.position.x, f.position.y)) {
				this.paint.setColor(Color.RED);
				this.down = true;
				return;
			}
		}
		this.paint.setColor(Color.BLUE);
		this.down = false;

	}
}
