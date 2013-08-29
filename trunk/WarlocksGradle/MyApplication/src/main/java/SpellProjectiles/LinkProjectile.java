package SpellProjectiles;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import Game.DamageType;
import Game.GameObject;
import Game.ObjectType;
import Tools.Vector;
import com.developmental.myapplication.RenderThread;

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

        this.damagevalue=1;
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
              switch (linked.objectObjectType)
              {
                  case Player:
                      case Enemy:
                          case GameObject:

                              linked.Damage(damagevalue, DamageType.Spell);
                              break;
              }
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
        case Meteor:
            if (obj.health <=((MeteorProjectile)obj).landing)
                break;
        case GameObject:
        case Projectile:
        case Bounce:
        case Player:
        case Enemy:
        case GravityField:
        case IceSpell:

            Link(obj);
            break;
        case LinkSpell:
        case Explosion:
        case SwapProjectile:
        case LineSpell:
            break;
    }

}
    public void Link(GameObject g)
    {
        this.linked=g;
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
