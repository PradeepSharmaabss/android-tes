package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.RectF;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

import Game.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class BoomerangProjectile extends Projectile {
    public BoomerangProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter, 90000, 30f, new Vector(50, 50), 3);
        this.acceleration = 0.5f;
        this.objectObjectType= ObjectType.Boomerang;
        this.paint.setColor(Color.GRAY);
    }

    int i = 0;

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
       // super.Draw(canvas, playerx, playery);
        float t = (i * 5)%360;
        float t2 =100;

        canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),t,t2,true,this.paint);
        canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),t,t2,true,Global.PaintOutline);
     //   canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),(i * 5)%360,(100+i*5)%360,true, Global.PaintOutline);
    }

    @Override
    public void Update() {
        super.Update();
        if (i++ % 5 == 4) {
            float td = 10000f;


            this.destination = owner.feet;
        }
        if (i > 150)
            if (this.rect.intersect(owner.rect))
                RenderThread.delObject(this.id);

    }
}
