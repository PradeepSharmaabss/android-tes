package SpellProjectiles;

import Game.GameObject;
import Tools.Vector;
import android.graphics.Canvas;
import android.graphics.Color;

import java.util.ArrayList;
import java.util.List;

public class FireballProjectile extends Projectile {
    List<Vector> path = new ArrayList<Vector>();
	public FireballProjectile(Vector _from, Vector _to, GameObject _shooter) {
		super(_from, _to, _shooter);
        for(int i = 0; i<15; i++)
            path.add(new Vector(position.x-velocity.x*i,position.y-velocity.y*i));
		this.paint.setColor(Color.RED);
		this.shadowPaint.setColor(Color.argb(200, 0, 0, 0));
	}
@Override
public void Update()
{
    Vector v = new Vector(rect.centerX(),rect.centerY());

    super.Update();
    for(int i = 1; i<15;i++)
    {
        float posx = (float) (4 * Math.random() - 2  );
                float posy = (float)(4 * Math.random() - 2);

        path.set(i,path.get(i-1).add(new Vector(posx,posy)));
//        v = path.get(i);
    }
   path.set(0,v);
}

	@Override
	public void Draw(Canvas c,float playerx,float playery) {
        for (int x = 0; x < 15; x++) {
            //this.paint.setARGB(((10 - x) * 25), 255, x* (int) (Math.random() * 25), 0);
            // paint.setColor(paint.getColor()+(int)(x*Math.random()*10));
            //float posx = this.position.x
            //		- (int) (20 * Math.random() - 10 + this.velocity.x * x), posy = this.position.y
            //		- (int) (20 * Math.random() - 10 + this.velocity.y * x);
            c.drawCircle(path.get(x).x + 20-playerx,path.get(x).y + 20-playery, this.size.x / 3,
                    this.shadowPaint);


        }
        c.drawCircle(this.rect.centerX()+20-playerx, this.rect.centerY()+20-playery, this.size.x / 2,
                this.shadowPaint);

        c.drawCircle(this.rect.centerX()-playerx, this.rect.centerY()-playery, this.size.x / 2,
				this.paint);

        for (int x = 0; x < 15; x++) {
            //this.paint.setARGB(((10 - x) * 25), 255, x* (int) (Math.random() * 25), 0);
            // paint.setColor(paint.getColor()+(int)(x*Math.random()*10));
            //float posx = this.position.x
            //		- (int) (20 * Math.random() - 10 + this.velocity.x * x), posy = this.position.y
            //		- (int) (20 * Math.random() - 10 + this.velocity.y * x);

            c.drawCircle(path.get(x).x-playerx, path.get(x).y-playery, this.size.x / 3, this.paint);

        }
	}

}
