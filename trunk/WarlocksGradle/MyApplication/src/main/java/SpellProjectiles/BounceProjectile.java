package SpellProjectiles;

import android.graphics.Canvas;
import android.util.Log;

import com.developmental.myapplication.MenuActivity;
import com.developmental.myapplication.RenderThread;

import Actors.Player;
import Game.DamageType;
import Game.GameObject;
import Game.ObjectType;
import Tools.Vector;

/**
 * Created by Scott on 28/08/13.
 */
public class BounceProjectile extends FireballProjectile {
    public BounceProjectile(Vector _from, Vector _to, GameObject shooter) {
        super(_from, _to, shooter);
        health = 300;
        this.maxVelocity = 40;
        this.size = new Vector(30, 30);
        SetVelocity(maxVelocity);
        lastTarget = owner;
        objectObjectType = ObjectType.Bounce;
        this.damagevalue = 4;
    }

    int bounces = 3;
    GameObject lastTarget = null;

    public void Collision(GameObject obj) {
        MenuActivity.sp.play(MenuActivity.explosion, 1, 1, 0, 0, 1);
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
                RenderThread.addObject(new ExplosionProjectile(this.getCenter(), new Vector(200, 200), obj.owner));
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
                Vector l;
                l = obj.owner.position;
                obj.owner.position = this.position;
                this.position = l;
                RenderThread.delObject(obj.id);
                break;
        }

    }

    @Override
    public void Draw(Canvas canvas, float playerx, float playery) {
        super.Draw(canvas, playerx, playery);
        canvas.drawLine(feet.x - playerx, feet.y - playery, lastTarget.feet.x - playerx, lastTarget.feet.y - playery, paint);
        if (CurrentTarget != null)
            canvas.drawLine(feet.x - playerx, feet.y - playery, CurrentTarget.feet.x - playerx, CurrentTarget.feet.y - playery, paint);
    }

    Player CurrentTarget = null;

    public void findNewTarget() {
        CurrentTarget = null;
        float minD = 10000;
        for (Player p : RenderThread.players) {
            if (p.id != owner.id) {
                if ((lastTarget == null) || (lastTarget.id != p.id)) {
                    float distanceX = this.position.x - p.position.x;
                    float distanceY = this.position.y - p.position.y;
                    float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
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
