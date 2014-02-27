package SpellProjectiles;

import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Game.ObjectType;
import Tools.Vector;

import com.developmental.myapplication.R;
import com.developmental.myapplication.RenderThread;

/**
 * Created by Scott on 27/07/13.
 */
public class LinkProjectile extends Projectile {

    public GameObject linked = null;

    public LinkProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.lightning,_from, _to, shooter, 100, 20, new Vector(50, 50), 1);
        this.objectObjectType = ObjectType.LinkSpell;
        this.pull = 2;
        paint.setColor(Color.YELLOW);
        shadowPaint = new Paint();
        shadowPaint.setStrokeWidth(4);
        shadowPaint.setMaskFilter(new BlurMaskFilter(8, BlurMaskFilter.Blur.OUTER));
        shadowPaint.setColor(Color.WHITE);
        Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+_to.x+","+_to.y);
    }

    @Override
    public void Update() {
    //   Log.e("LINK MADE", this.position.x+","+this.position.y + "AND "+owner.position.x+","+owner.position.y+"AND "+owner.velocity.x+","+owner.velocity.y);
if(linked!=null)
    if(linked.health<=0||linked.position==null)
        {
            linked=null;

        }
        if(owner.health<=0)
        {

        }
        if (this.health > 0) {
            if (linked == null) {
                super.Update();

            } else {
                owner.velocity = owner.velocity.add(linked
                        .DirectionalPull(owner.position, this.pull));
                linked.velocity = linked.velocity.add(owner
                        .DirectionalPull(linked.position, this.pull));
                this.bounds = linked.bounds;
                switch (linked.objectObjectType) {
                    case Player:
                    case Enemy:
                    case GameObject:

                        DealDamageTo(linked);

                        break;
                }
                health -= 1;
            }

        } else {

            RenderThread.delObject(this.id);
        }

    }



    public void Link(GameObject g) {
        this.linked = g;
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(4);
    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        if (linked == null) {
            canvas.drawCircle(bounds.Center.x - playerx, bounds.Center.y - playery, 7, paint);
            canvas.drawLine(this.owner.bounds.Center.x - playerx, this.owner.bounds.Center.y - playery, bounds.Center.x - playerx, bounds.Center.y - playery, paint);
            canvas.drawCircle(bounds.Center.x - playerx, bounds.Center.y - playery, 7, shadowPaint);
            canvas.drawLine(this.owner.bounds.Center.x - playerx, this.owner.bounds.Center.y - playery, bounds.Center.x - playerx, bounds.Center.y - playery, shadowPaint);

        } else {
            canvas.drawCircle(bounds.Center.x - playerx, bounds.Center.y - playery, 7, paint);
            canvas.drawLine(this.owner.bounds.Center.x - playerx, this.owner.bounds.Center.y - playery, this.linked.bounds.Center.x - playerx, this.linked.bounds.Center.y - playery, paint);
            canvas.drawCircle(bounds.Center.x - playerx, bounds.Center.y - playery, 7, shadowPaint);
            canvas.drawLine(this.owner.bounds.Center.x - playerx, this.owner.bounds.Center.y - playery, this.linked.bounds.Center.x - playerx, this.linked.bounds.Center.y - playery, shadowPaint);
        }
    }
}
