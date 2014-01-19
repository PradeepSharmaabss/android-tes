package com.developmental.myapplication.GL.NewHeirachy;

import com.developmental.myapplication.RenderThread;

import Game.DamageType;
import Tools.Vector;

/**
 * Created by Scott on 19/01/14.
 */
public class glProjectile extends Collideable {

    int deathPhase = 0;
   public float damagevalue= 1;
    public glProjectile(Vector _from, Vector _to, GameObject shooter, int _health, float _maxvelocity, Vector _size, float _damagevalue) {
        super(1);
        this.owner = shooter;

        this.deathPhase = _health;
        this.maxVelocity = _maxvelocity;
        this.size = _size;
        this.damagevalue = _damagevalue;
        this.objectObjectType = Game.ObjectType.Projectile;
        Vector from = _from.get();
        Vector to = new Vector(_to.x-size.x/2,_to.y-size.y/2);

        this.velocity = GetVel(from, to.get());
        SetVelocity(this.maxVelocity);
        // feet= position.get();
        this.bounds.Center = _from;
        this.bounds.Radius = this.size.x / 2;

        //   this.bounds.Radius=size.x;
    }

    public void SetVelocity(float vel) {

        float totalVel = Math.abs(this.velocity.x) + Math.abs(this.velocity.y);
        this.velocity = new Vector(vel * this.velocity.x / totalVel, vel
                * this.velocity.y / totalVel);
    }

    public Vector GetVel(Vector from, Vector to) {
        this.position = from;
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Vector.DistanceBetween(to, from);

        return new Vector(this.maxVelocity * (distanceX / totalDist),
                this.maxVelocity * distanceY / totalDist);
    }
    public void DealDamageTo(GameObject g) {
        g.Damage(this.damagevalue, DamageType.Spell);
        owner.damageDealtThisRound += damagevalue;
    }

//    public void Damage(float dmgDealt, DamageType d) {
//        switch (d) {
//            case Lava:
//                break;
//            case Spell:
//            default:
//                if (dmgDealt > this.health)
//                    this.health = 0;
//                else
//                    this.health -= dmgDealt;
//                break;
//        }
//
//    }




    @Override
    public void Update() {
        if (this.deathPhase>lifePhase) {
            super.Update();
        } else
            RenderThread.delObject(this.id);
//        this.bounds.Center = getCenter();
    }
}
