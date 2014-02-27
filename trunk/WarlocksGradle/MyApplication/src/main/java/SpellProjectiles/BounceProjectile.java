package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;
import com.developmental.myapplication.RenderThread;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class BounceProjectile extends Projectile {
    public BounceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(R.drawable.bomerang,_from, _to, shooter, 100, 20f, new Vector(50, 50), 10);
        health = 300;
        this.maxVelocity = 40;
        this.size = new Vector(30, 30);
        SetVelocity(maxVelocity);
        lastTarget = owner;
        objectObjectType = ObjectType.Bounce;
        this.damagevalue = 4;
    }

   public int bounces = 3;
    public GameObject lastTarget = null;

    public void Collision(GameObject obj) {
        switch (obj.objectObjectType) {
            case Projectile:
            case IceSpell:
            case Bounce:
                if ((obj.owner.id != this.owner.id)) {
                    RenderThread.delObject(obj.id);
                    RenderThread.delObject(this.id);
                }
                break;
            case GameObject:
            case Player:
            case Enemy:
                if ((this.owner != null) && (obj.id != this.owner.id))
                    if (lastTarget == null || obj.id != lastTarget.id) {
                        obj.ProjectileHit(this.velocity);
                        DealDamageTo(obj);
                        if (bounces > 0) {
                            lastTarget = obj;
                            findNewTarget();
                            bounces -= 1;
                        } else {
                            RenderThread.delObject(this.id);
                        }
                    }
                break;
            case LineSpell:
                RenderThread.delObject(this.id);
                RenderThread.addObject(new ExplosionProjectile(this.bounds.Center, new Vector(200, 200), obj.owner));
                break;
            case Meteor:
                if (obj.health == ((MeteorProjectile) obj).landing)
                    RenderThread.delObject(this.id);
                break;
            case Explosion:
                if ((this.owner != null) && (obj.id != this.owner.id))
                    RenderThread.delObject(this.id);
                break;
            case LinkSpell:
                ((LinkProjectile) obj).Link(this);
                break;
            case GravityField:
                this.velocity = this.velocity.add(obj
                        .DirectionalPull(this.position, obj.pull));
                break;
            case SwapProjectile:

                ((SwapProjectile) obj).Swap(this);
                break;
        }

    }

    protected void DrawBlade(Canvas canvas, float playerx, float playery, float angle) {

        float centerx = (float) (playerx+bounds.Radius*Math.cos(Math.toRadians(angle)));
        float centery = (float) (playery + bounds.Radius*Math.sin(Math.toRadians(angle)));
        float t2 =30;
        //canvas.drawRect(new RectF(centerx-bounds.Radius/2,centery-bounds.Radius/2,bounds.Radius/2+centerx,bounds.Radius/2+centery),Global.PaintCyan);
        canvas.drawArc(new RectF(centerx-bounds.Radius,centery-bounds.Radius,bounds.Radius+centerx,bounds.Radius+centery),(angle+180)%360,t2,true,this.paint);
        canvas.drawArc(new RectF(centerx-bounds.Radius,centery-bounds.Radius,bounds.Radius+centerx,bounds.Radius+centery),(angle+180)%360,t2,true, Global.PaintOutline);

    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        // super.Draw(canvas, playerx, playery);
//bounds.Draw(canvas,playerx,playery,Global.PaintOutline);
        canvas.drawCircle(bounds.Center.x-playerx,bounds.Center.y-playery,bounds.Radius/2+5,this.paint);

        canvas.drawCircle(bounds.Center.x-playerx,bounds.Center.y-playery,bounds.Radius/2+5,Global.PaintOutline);
        for(int x=0; x<6; x++)
        {

            DrawBlade(canvas,bounds.Center.x-playerx,bounds.Center.y-playery,(lifePhase*15+60*x)%360);
        }

        // bounds.Draw(canvas,playerx,playery,Global.PaintOutline);
        //   canvas.drawArc(new RectF(this.position.x-playerx,this.position.y-playery,100+this.position.x-playerx,100+this.position.y-playery),(i * 5)%360,(100+i*5)%360,true, Global.PaintOutline);
    }

    GameObject CurrentTarget = null;

    public void findNewTarget() {
        CurrentTarget = null;
        float minD = 10000;

        for (GameObject p : RenderThread.players) {
            if (p.id != owner.id) {
                if ((lastTarget == null) || (lastTarget.id != p.id)) {
                    float totalDist = Vector.DistanceBetween(this.bounds.Center,p.bounds.Center);
                    if (totalDist < minD) {
                        minD = totalDist;
                        CurrentTarget = p;
                        Log.d("INET", "TARGET SET TO" + CurrentTarget.id);
                    }
                }
            }
        }


        this.velocity = this.GetVel(this.position, CurrentTarget.position);
        SetVelocity(maxVelocity);
        Log.d("INET", "TARGET SET TO" + CurrentTarget.feet.x + "," + CurrentTarget.feet.y);

    }

}
