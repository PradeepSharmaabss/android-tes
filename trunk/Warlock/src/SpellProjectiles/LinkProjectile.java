package SpellProjectiles;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import com.example.warlockgame.RenderThread;

import Game.DamageType;
import Game.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 27/07/13.
 */
public class LinkProjectile extends Projectile {

public GameObject linked = null;
    public LinkProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        this.objectObjectType= ObjectType.LinkSpell;
        this.pull=2;
        paint.setColor(Color.YELLOW);
        shadowPaint = new Paint();
        shadowPaint.setStrokeWidth(4);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.OUTER));
        shadowPaint.setColor(Color.WHITE);

    }
    @Override
    public void Update()
    {
        if (this.health > 0) {
          if(linked==null)
            {
              super.Update();

            }
            else
          {
              owner.velocity = owner.velocity.add(linked
                      .DirectionalPull(owner.position,this.pull));
              linked.velocity = linked.velocity.add(owner
                      .DirectionalPull(linked.position,this.pull));
              this.rect=linked.rect;
              linked.Damage(1, DamageType.Spell);
              health-=1;
          }

        }
        else
        {

            RenderThread.delObject(this.id);
        }

    }


@Override
public void Collision(GameObject obj) {

    switch (obj.objectObjectType)
    {
        case LinkSpell:
            return;

    }
   this.linked=obj;
    paint.setColor(Color.WHITE);
    paint.setStrokeWidth(4);

}
@Override
public void Draw(Canvas canvas,float playerx,float playery) {
    if(linked==null)
    {
        canvas.drawCircle(getCenter().x-playerx,getCenter().y-playery,7,paint);
        canvas.drawLine(this.owner.getCenter().x-playerx,this.owner.getCenter().y-playery,getCenter().x-playerx,getCenter().y-playery,paint);
        canvas.drawCircle(getCenter().x-playerx,getCenter().y-playery,7,shadowPaint);
        canvas.drawLine(this.owner.getCenter().x-playerx,this.owner.getCenter().y-playery,getCenter().x-playerx,getCenter().y-playery,shadowPaint);

    }
    else
    {
        canvas.drawCircle(getCenter().x-playerx,getCenter().y-playery,7,paint);
        canvas.drawLine(this.owner.getCenter().x-playerx,this.owner.getCenter().y-playery,this.linked.getCenter().x-playerx,this.linked.getCenter().y-playery,paint);
        canvas.drawCircle(getCenter().x-playerx,getCenter().y-playery,7,shadowPaint);
        canvas.drawLine(this.owner.getCenter().x-playerx,this.owner.getCenter().y-playery,this.linked.getCenter().x-playerx,this.linked.getCenter().y-playery,shadowPaint);
    }
}
}
