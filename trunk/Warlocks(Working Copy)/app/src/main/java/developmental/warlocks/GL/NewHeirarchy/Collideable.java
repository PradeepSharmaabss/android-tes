package developmental.warlocks.GL.NewHeirarchy;

import com.developmental.warlocks.R;

import java.util.ArrayList;
import java.util.List;

import Actors.Player;
import Actors.ShadowClone;
import Game.DamageType;
import Game.Destination;
import HUD.PopupText;
import SpellProjectiles.AbsorptionProjectile;
import SpellProjectiles.BounceProjectile;
import SpellProjectiles.ExplosionProjectile;
import SpellProjectiles.HealProjectile;
import SpellProjectiles.LightningProjectile;
import SpellProjectiles.LinkProjectile;
import SpellProjectiles.PowerBallProjectile;
import SpellProjectiles.SwapProjectile;
import Spells.Archetype.ArchetypePower;
import Spells.SpellEffect;
import Tools.BoundingCircle;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 19/01/14.
 * An object that can impact with/collide with another object.
 */
public abstract class Collideable extends Moveable implements Comparable<Collideable> {
    public ShadowClone shadowClone;
public boolean shielded =false;
    protected boolean invisible= false;
    public boolean juggernaught = false;
    public int jugstacks = 0;
    public float health = 500;
    public float maxhealth = this.health;
    public float mana = 0;
    public boolean jumping = false;
    public float damageDealtThisRound = 0;
    public ArchetypePower archetypePower= new ArchetypePower(0,0,0,0,0,0,0);
    public boolean thrusting=false;

    public int compareTo(Collideable o) {
        return (int) (this.bounds.Center.y - o.bounds.Center.y);
        //	return (int) (this.position.y - o.position.y);
    }
    public Vector GetVel2(Vector from, Vector to,double pull) {
        //  this.position = from;
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Vector.DistanceBetween(to, from);

        return new Vector((float)pull * (distanceX / totalDist),
                (float)pull * distanceY / totalDist);
    }
    //Applies a flat pull to the objects position.
    public Vector DirectionalPull(Vector TargetPosition, double _p) {
        Vector from = TargetPosition.get();
        Vector to = this.position.get();

        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Math.abs(distanceX) + Math.abs(distanceY);

        return new Vector((float)_p * (distanceX / totalDist), (float)_p
                * distanceY / totalDist);
    }
    public void Collision2(Collideable obj)
    {
        Vector ImpulseYou = new Vector(0,0);
        Vector ImpulseObj = new Vector(0,0);
        float damageYou = 0;
        float damageObj = 0;
        switch (objectObjectType)
        {
            /***********************************************************************************************************************
             * GameObjcets/Players/Enemies
             ***********************************************************************************************************************/
            case GameObject:
            case Player:
            case Enemy:
                if (!this.jumping)
                switch (obj.objectObjectType) {

                    case GameObject:
                    case Player:
                    case Enemy:

                        if(obj.thrusting)
                            ImpulseYou =(obj.GetVel2(obj.bounds.Center, this.bounds.Center, this.knockback));
                        if (!obj.jumping)
                        if(this.thrusting)
                       ImpulseObj =  this.GetVel2(bounds.Center, obj.bounds.Center, obj.knockback);


                        break;
                    case Boomerang:
                        if (obj.owner.id != this.id) {
                            ImpulseObj = this.GetVel2(bounds.Center, obj.bounds.Center, obj.knockback);
                            if(!this.shielded) {
                                ImpulseYou = (obj.GetVel2(obj.bounds.Center, this.bounds.Center, obj.knockback));

                                damageYou = obj.damagevalue;
                            }
                            else
                            {
                                obj.owner=this;
                            }
                        }
                        break;
                    case PowerBall:
                        if (obj.owner.id != this.id) {
                            if(!this.shielded) {
                                ImpulseYou = obj.velocity;
                                SimpleGLRenderer.delObject(obj.id);
                                damageYou = obj.damagevalue * (1 + ((PowerBallProjectile) obj).stacks);
                            }

                        else
                        {
                            obj.velocity= Vector.multiply(obj.velocity,-1);
                            obj.owner = this;
                        }
                        }
                        break;
                    case Projectile:
                    case Absorb:
                        if (obj.owner.id != this.id) {
                            if(this.shielded!=true) {
                                ImpulseYou = obj.velocity;
                                SimpleGLRenderer.delObject(obj.id);
                                damageYou = obj.damagevalue;
                                ((Player) this).archetypeManager.AddStacks(obj.archetypePower, obj.owner);
                            }
                            else
                            {
                                obj.velocity= Vector.multiply(obj.velocity,-1);
                                obj.owner=this;
                            }
                        }
                        break;
                    case Piercing:
                        if (obj.owner.id != this.id) {
                            if(!shielded)
                            damageYou = obj.damagevalue;
                        }
                        break;
                    case LineSpell:
                        if ((obj.owner != null) && (this.id != obj.owner.id)) {
                            if(!shielded) {
                                ImpulseYou = this.GetVel2(((LightningProjectile) obj).Start, this.bounds.Center, ((LightningProjectile) obj).knockback);
                                damageYou = obj.damagevalue;
                            }
                        }
                        break;

                    case Illusion:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case Meteor:

                        break;
                    case GravityField:
                        if(obj.owner.id!=this.id) {
                            if (!shielded)
                            {
                                ImpulseYou = obj
                                        .DirectionalPull(this.position, obj.pull);
                            damageYou = obj.damagevalue;
                        }
                        }
                        break;
                    case LinkSpell:
                        if(this.id!=obj.owner.id)
                            if(!shielded) {
                                ((LinkProjectile) obj).linked = this;
                            }
                                else
                                {
                                    obj.velocity= Vector.multiply(obj.velocity,-1);
                                    obj.owner=this;
                                }
//                    obj.paint.setColor(Color.WHITE);
                        break;

                    case Explosion:
                        if (this.owner != null)
                            if (obj.id != this.owner.id) {

                                if(!shielded) {
                                    ImpulseYou = (this.GetVel2(obj.bounds.Center, bounds.Center, obj.knockback));
                                    damageYou = obj.damagevalue;
                                }
                            }
                        break;
                    case Bounce:
                        if(obj.owner.id!=this.id)
                        {

                            ((BounceProjectile) obj).findNewTarget();
                            if(!shielded) {
                                damageYou = obj.damagevalue;
                                ((BounceProjectile) obj).owner=this;
                            }

                        }
                        break;
                    case SwapProjectile:
                        if(obj.owner.id!=this.id) {
                            if(!shielded)
                            ((SwapProjectile) obj).Swap(this);
                        }
                        break;

                    case Drain:
                        if (obj.owner.id != this.id) {
                            if(!shielded) {
                                this.Debuffs.add(new SpellEffect(500, SpellEffect.EffectType.Slow, this, R.drawable.effect_shield, new iVector(0, 0)));
                                SimpleGLRenderer.addObject(new HealProjectile(this.position, obj.owner.bounds.Center.get(), obj.owner, 3));
                                SimpleGLRenderer.delObject(obj.id);
                            }
                        }
                        break;
                    case DrainExplosion:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.addObject(new HealProjectile(this.position, obj.owner.bounds.Center.get(), obj.owner,3));
                        }
                        break;
                    case HealHoming:

                        if(this.id==obj.owner.id)
                        {
                            Heal(obj.damagevalue);
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                }
                break;
            /***********************************************************************************************************************
             * Projectiles
             ***********************************************************************************************************************/
            case Projectile:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        if (owner.id != obj.id) {
                            if (!obj.jumping)
                            if(obj.shielded!=true) {
                                ImpulseObj = velocity;

                                SimpleGLRenderer.delObject(id);
                                damageObj = this.damagevalue;

                                ((Player) obj).archetypeManager.AddStacks(this.archetypePower, this.owner);
                            }
                            else
                            {
                                this.velocity= Vector.multiply(this.velocity,-1);
                                this.owner=obj;
                            }
                        }
                        break;
                    case Projectile:
                    case Bounce:

                    case Drain:
                    case Boomerang:

                        if ((obj.owner.id != this.owner.id)) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);


                        }
                        break;
                    case PowerBall:

                        SimpleGLRenderer.delObject(this.id);
                        ( (PowerBallProjectile)obj).dropStacks();
                        break;

                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case Absorb:

                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case LineSpell:
                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(0,this.bounds.Center.get(), obj.owner, new Vector(200, 200),5,3));
                        break;

                    case Meteor:

                        break;
                    case GravityField:
                        ImpulseYou = obj.DirectionalPull(this.position, obj.knockback);
                        break;
                    case LinkSpell:

                        ((LinkProjectile) obj).Link(this);
                        break;

                    case Explosion:
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            SimpleGLRenderer.delObject(this.id);
                        break;

                    case SwapProjectile:

                        ((SwapProjectile) obj).Swap(this);
                        break;
                }
                break;
            /***********************************************************************************************************************
             * LineSpells
             ***********************************************************************************************************************/
            case LineSpell:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:

                        if ((this.owner != null) && (obj.id != this.owner.id)) {
                            // obj.ProjectileHit(this.velocity);
                            if (!obj.jumping)
                            if(!obj.shielded) {
                                ImpulseObj = (obj.GetVel2(((LightningProjectile) this).Start, obj.bounds.Center, 30));
                                damageObj = this.damagevalue;
                            }
                        }
                        break;
                    case Absorb:

                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;

                    case Projectile:
                    case Bounce:
                    case Boomerang:
                    case Drain:
                    case Piercing:
                        SimpleGLRenderer.delObject(obj.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(0,this.bounds.Center.get(), obj.owner, new Vector(200, 200),5,3));
                        break;
                    case PowerBall:


                        ( (PowerBallProjectile)obj).dropStacks();
                        break;
                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case LineSpell:
                    case GravityField:
                    case LinkSpell:
                    case SwapProjectile:
                    case Explosion:
                    case Meteor:
                        break;
                }
                break;
            /***********************************************************************************************************************
             *Meteors
             ***********************************************************************************************************************/
            case Meteor:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:

                    case Projectile:
                    case Bounce:

                    case Boomerang:
                    case Drain:
                    case Illusion:
                    case Piercing:
                    case LineSpell:
                    case LinkSpell:
                    case SwapProjectile:
                    case Explosion:
                    case Meteor:
                        break;
                    case GravityField:
                        ImpulseYou = obj.DirectionalPull(this.position, obj.knockback);
                        break;
                }
                break;
            /***********************************************************************************************************************
             * Boomerang
             ***********************************************************************************************************************/
            case Boomerang:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        if (!obj.jumping)
                        if (obj.id != this.owner.id) {

                            ImpulseYou = (this.GetVel2(obj.bounds.Center, bounds.Center, this.knockback));

                            if(!obj.shielded) {
                                ImpulseObj = (obj.GetVel2(this.bounds.Center, obj.bounds.Center, this.knockback));
                                damageObj = this.damagevalue;
                            }
                            else
                                this.owner=obj;
                        }
                        break;
                    case PowerBall:

                        SimpleGLRenderer.delObject(this.id);
                        ( (PowerBallProjectile)obj).dropStacks();
                        break;
                    case Projectile:
                    case Bounce:

                    case Boomerang:
                    case Drain:

                        if ((obj.owner.id != this.owner.id)) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);


                        }
                        break;

                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case Absorb:
                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case LineSpell:
                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(0,this.bounds.Center.get(), obj.owner, new Vector(200, 200),5,3));
                        break;

                    case Meteor:

                        break;
                    case GravityField:
                        ImpulseYou = obj.DirectionalPull(this.position, obj.knockback);
                        break;
                    case LinkSpell:

                        ((LinkProjectile) obj).Link(this);
                        break;

                    case Explosion:
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            SimpleGLRenderer.delObject(this.id);
                        break;

                    case SwapProjectile:

                        ((SwapProjectile) obj).Swap(this);
                        break;
                }
                break;
            /***********************************************************************************************************************
             * ABSORB
             ***********************************************************************************************************************/

            case Absorb:
                switch (obj.objectObjectType) {

                    case GameObject:
                    case Player:
                    case Enemy:
                        if (!obj.jumping)
                        if (owner.id != obj.id) {
                            if(!obj.shielded) {
                                ImpulseObj = velocity;

                                SimpleGLRenderer.delObject(id);
                                damageObj = this.damagevalue;
                            }
                            else
                            {
                                velocity = Vector.multiply(velocity,-1);
                                this.owner=obj;
                            }
                        }
                        break;
                    case Projectile:
                    case Bounce:

                    case Boomerang:
                    case Drain:
                    case PowerBall:
                    case Piercing:
                        ((AbsorptionProjectile)this).Absorb(obj);
                        break;

                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case LineSpell:
                    case HealHoming:
                    case Absorb:
                        break;
                    case GravityField:
                        break;
                    case LinkSpell:
                        break;
                    case SwapProjectile:
                        ((SwapProjectile)obj).Swap(this);
                        break;
                    case Explosion:
                        break;
                    case Meteor:
                        break;
                }
                break;
            /***********************************************************************************************************************
             * Gravity
             ***********************************************************************************************************************/

            case GravityField:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        if (!obj.jumping)
                        if(obj.id!=this.owner.id)
                        {
                            if(!obj.shielded)
                            {
                            ImpulseObj=   this.DirectionalPull(obj.position, knockback);
                            damageObj  =this.damagevalue;}
                        }
                        break;
                    case Meteor:
                    case GravityField:
                    case LinkSpell:

                    case Bounce:
                    case SwapProjectile:
                    case Boomerang:
                    case Projectile:
                    case Drain:
                    case Illusion:
                        case PowerBall:
                            case Piercing:
                        ImpulseObj=   this.DirectionalPull(obj.position, knockback);

                        break;
                    case LineSpell:
                        break;

                    case Explosion:
                        break;

                }
                break;
            /***********************************************************************************************************************
             * Link
             ***********************************************************************************************************************/
            case LinkSpell:
                switch (obj.objectObjectType) {
                    case Player:
                    case Enemy:
                    case GameObject:
                    case Projectile:
                    case Bounce:
                    case Boomerang:
                    case Drain:

                    case Illusion:
                        case Piercing:
                        case PowerBall:
                            if (!obj.jumping)
                        if(obj.id!=owner.id)
                            if(!obj.shielded) {
                                ((LinkProjectile) this).Link(obj);
                            }
                            else
                            {
                                this.velocity= Vector.multiply(this.velocity,-1);
                                this.owner=obj;
                            }
                        break;
                    case GravityField:
                        ImpulseYou= velocity.add(obj
                                .DirectionalPull(this.position, obj.knockback));
                        break;
                    case Meteor:
                    case LinkSpell:
                    case Explosion:
                    case SwapProjectile:
                    case LineSpell:
                        break;
                }
                break;

            /***********************************************************************************************************************
             * Explosion
             ***********************************************************************************************************************/
            case Explosion:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        if (!obj.jumping)
                        if (this.owner != null)
                            if (obj.id != this.owner.id) {
                            if(!obj.shielded) {
                                ImpulseObj = (obj.GetVel2(bounds.Center, obj.bounds.Center, this.knockback));
                                damageObj = this.damagevalue;
                            }
                            }
                        break;
                    case Projectile:
                    case Bounce:

                    case Boomerang:
                    case Drain:
                        case Piercing:
                    case Illusion:
                        case PowerBall:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.delObject(obj.id);

                        }
                        break;
                    case LineSpell:
                    case GravityField:
                    case LinkSpell:
                    case SwapProjectile:
                    case Explosion:
                    case Meteor:
                        break;
                }
                break;
            /***********************************************************************************************************************
             * Bounce
             ***********************************************************************************************************************/
            case Bounce:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        BounceProjectile b=(BounceProjectile)this;
                        if (!obj.jumping)
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            if (b.lastTarget == null || obj.id != b.lastTarget.id) {
                                if(!obj.shielded) {
                                    ImpulseObj = this.velocity;
                                    damageObj = b.damagevalue;
                                }
                                else
                                    this.owner=obj;
                                if (b.bounces > 0) {
                                    b.lastTarget = obj;
                                    b.findNewTarget();
                                    b.bounces -= 1;
                                } else {
                                    SimpleGLRenderer.delObject(this.id);
                                }
                            }
                        break;
                    case Absorb:
                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case SwapProjectile:
                        ((SwapProjectile) obj).Swap(this);
                        break;
                    case Projectile:
                    case Bounce:

                    case Drain:
                    case Boomerang:

                        if ((obj.owner.id != this.owner.id)) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);
                        }
                        break;
                    case PowerBall:

                        SimpleGLRenderer.delObject(this.id);
                        ( (PowerBallProjectile)obj).dropStacks();
                        break;
                    case Illusion:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case LineSpell:
                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(0,this.bounds.Center.get(), obj.owner, new Vector(200, 200),5,3));
                        break;
                    case GravityField:
                        ImpulseYou = obj
                                .DirectionalPull(this.position, obj.knockback);
                        break;
                    case LinkSpell:

                        ((LinkProjectile) obj).Link(this);
                        break;

                    case Explosion:
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            SimpleGLRenderer.delObject(this.id);
                        break;
                    case Meteor:


                        break;
                }
                break;
            /***********************************************************************************************************************
             * Swap
             ***********************************************************************************************************************/
            case SwapProjectile:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Projectile:
                    case Player:
                    case Enemy:

                    case Bounce:
                    case Drain:
                    case Boomerang:
                    case Absorb:
                    case Illusion:
                        case Piercing:
                        case PowerBall:
                            if (!obj.jumping)
                        if(obj.id!=this.owner.id) {
                            if(!obj.shielded) {
                                ((SwapProjectile) this).Swap(obj);
                            }
                            else
                            {
                                velocity= Vector.multiply(velocity,-1);
                                this.owner=obj;
                            }
                        }
                        break;

                    case LineSpell:
                    case Meteor:
                    case GravityField:
                    case LinkSpell:
                    case Explosion:
                    case SwapProjectile:
                        break;
                }
                break;
            case DrainExplosion:
                switch (obj.objectObjectType)
                {
                    case GameObject:
                    case Player:
                    case Enemy:
                        if (!obj.jumping)
                        if (obj.id != this.owner.id) {
                            if(!obj.shielded)
                            SimpleGLRenderer.addObject(new HealProjectile(obj.position, owner.bounds.Center.get(), owner,3));

                        }
                        break;
                }
                break;
            case Drain:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        if (!obj.jumping)
                        if (obj.id != this.owner.id) {
                            if(!obj.shielded) {
                                obj.Debuffs.add(new SpellEffect(500, SpellEffect.EffectType.Slow, obj, R.drawable.effect_shield, new iVector(0, 0)));
                                SimpleGLRenderer.addObject(new HealProjectile(obj.position, owner.bounds.Center.get(), owner, 3));

                                SimpleGLRenderer.delObject(this.id);
                            }
                            else
                            {
                                this.velocity= Vector.multiply(this.velocity,-1);
                                this.owner=obj;
                            }


                        }
                        break;
                    case Absorb:
                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case Projectile:
                    case Bounce:
                    case Drain:
                    case Boomerang:
                        if ((obj.owner.id != this.owner.id)) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);
                        }
                        break;
                    case PowerBall:

                        SimpleGLRenderer.delObject(this.id);
                        ( (PowerBallProjectile)obj).dropStacks();
                        break;
                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case LineSpell:

                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(0,this.bounds.Center.get(), obj.owner, new Vector(200, 200),5,3));
                        break;
                    case GravityField:

                        ImpulseYou=   obj.DirectionalPull(this.position, obj.knockback);

                        break;
                    case LinkSpell:
                        if(obj.id!=owner.id)
                            ((LinkProjectile)obj).Link(this);
                        break;
                    case SwapProjectile:

                        ((SwapProjectile)obj).Swap(this);
                        break;
                    case Explosion:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.delObject(this.id);

                        }
                        break;
                    case Meteor:

                        break;
                }
                break;
            case HealHoming:
                if(this.owner.id==obj.id)
                {
                    obj.Heal(this.damagevalue);
                    SimpleGLRenderer.delObject(this.id);
                }
                break;

            case Illusion:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        break;
                    case Projectile:
                    case Bounce:
                    case LineSpell:
                    case Boomerang:
                    case Drain:
                    case HealHoming:
                    case Absorb:
                     case PowerBall:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(this.id);
                        }
                        break;
                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);
                        }
                        break;



                    case GravityField:
                        ImpulseYou=   obj.DirectionalPull(this.position, obj.knockback);

                        break;
                    case LinkSpell:
                        if(obj.id!=owner.id)
                            ((LinkProjectile)obj).Link(this);
                        break;
                    case SwapProjectile:

                        ((SwapProjectile)obj).Swap(this);
                        break;
                    case Explosion:
                        SimpleGLRenderer.delObject(this.id);
                        break;

                    case Meteor:

                        break;
                }
                break;
        }

        if(damageYou>0)
        {
            this.Damage(damageYou, DamageType.Spell);
            obj.owner.damageDealtThisRound+=damageYou;
        }
        if(damageObj>0)
        {
            obj.Damage(damageObj,DamageType.Spell);
            this.owner.damageDealtThisRound+=damageObj;
        }
        if(Vector.CurrentVelocity(ImpulseYou)>0)
        {
            int counter = 0;
            for(SpellEffect s : Debuffs)
            {
                if(s.effectType== SpellEffect.EffectType.Burn)
                    counter++;
            }
            float Multiplier = (this.mana+400)/400*(float)Math.pow(1.2,counter);
            ImpulseYou = Vector.multiply(ImpulseYou,Multiplier);
            if (!this.juggernaught)
            this.velocity= this.velocity.add(ImpulseYou);
            else {
                jugstacks += 1;

                SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Message, "Stacks:" + jugstacks, position, 20));
            }
        }

        if(Vector.CurrentVelocity(ImpulseObj)>0)
        {
            int counter = 0;
            for(SpellEffect s : obj.Debuffs)
            {
                if(s.effectType== SpellEffect.EffectType.Burn)
                    counter++;

            }
            float Multiplier = (obj.mana+400)/400*(float)Math.pow(1.2,counter);
            ImpulseObj = Vector.multiply(ImpulseObj, Multiplier);

            if (!obj.juggernaught)
            obj.velocity = obj.velocity.add(ImpulseObj);
            else {
                obj.jugstacks += 1;
                SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Message, "Stacks:" + obj.jugstacks, obj.position, 20));

            }

        }

    }


    public List<SpellEffect> Debuffs = new ArrayList<SpellEffect>();
    public void Damage(float dmgDealt, DamageType d) {

    }

    public Vector feet;
    public float damagevalue = 0;
    /**
     * the classification of the object. This is used to determine how the two objects involved in the collision will respond to it
     */
    public enum ObjectType {
        GameObject,Player, Enemy, Projectile, Bounce, LineSpell, Boomerang,Drain,HealHoming, Absorb, GravityField, LinkSpell, SwapProjectile, Explosion, Illusion, DrainExplosion, PowerBall, Piercing, Meteor
    }
    public ObjectType objectObjectType;
    /**
     * How far the object will send another object flying if it impacts
     */
    public double knockback= 5;
    /**
     * A Circle, defined by a radius and a position, that will determine whether or not it is impacting with another Collideable
     */
    public BoundingCircle bounds;

    public int HealthRegenPer150Updates = 5;
    protected void Heal(float HealAmount) {

    }

    @Override
    public void Update() {
        super.Update();
       this.bounds.Center = this.position;

    }
    protected Destination Marker;
    //Applies a Vector to the velocity, based on accelleration and max speed, in the direction of the destination
    protected void MoveTowards(Vector d, float _maxVelocity, float _acceleration) {

        float distanceX = d.x - this.bounds.Center.x;
        float distanceY = d.y - this.bounds.Center.y;

        float totalDist = (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

        if (totalDist > Vector.CurrentVelocity(velocity) + _acceleration) {
            Vector newvelocity = new Vector(_maxVelocity
                    * (distanceX / totalDist), _maxVelocity * distanceY
                    / totalDist);
            if (Math.abs(newvelocity.x - this.velocity.x) > _acceleration)
                if (newvelocity.x > this.velocity.x)
                    newvelocity.x = this.velocity.x + _acceleration;
                else
                    newvelocity.x = this.velocity.x - _acceleration;
            if (Math.abs(newvelocity.y - this.velocity.y) > _acceleration)
                if (newvelocity.y > this.velocity.y)
                    newvelocity.y = this.velocity.y + _acceleration;
                else
                    newvelocity.y = this.velocity.y - _acceleration;
            this.velocity = newvelocity;
        } else {

            this.feet = this.destination;
            //bounds.Center=feet;
            this.destination = null;
            this.Marker=null;
            this.velocity = new Vector(0, 0);
        }
    }

    protected Player FindClosestPlayer(float maxDistance) {
        Player player = null;
        for (Player p : SimpleGLRenderer.players) {
            if (p.id != owner.id) {
                if(!p.dead) {
                    float totalDist = Vector.DistanceBetween(this.bounds.Center, p.bounds.Center);
                    if (totalDist < maxDistance) {
                        maxDistance = totalDist;
                        player = p;

                    }
                }
            }
        }
        return player;
    }
    public Vector getCenter() {
        return this.bounds.Center.get();
    }
    public Vector  destination;
    public float pull = 0.2f;
    protected Collideable(int _mResourceID, Vector _pos, Vector _size, float _health, float _damage) {
        super(_mResourceID);
        this.position= _pos;
        this.size= _size;
        damagevalue=_damage;
        health=_health;
        maxhealth=health;
        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y-size.y*7/10 );
        bounds = new BoundingCircle(feet, _size.x/2);

    }
    boolean lightningCollidesWith(Collideable obj1, Collideable obj2)
    {   if(obj2.objectObjectType == ObjectType.GravityField)
        return false;
        if(obj2.owner!=null)
            if(obj1.id==obj2.owner.id)
                return false;
        if(obj1.owner!=null)
            if(obj2.id==obj1.owner.id)
                return false;
        LightningProjectile l = (LightningProjectile) obj2;
        Vector ClosestPoint = obj1.bounds.closestpointonline(l.Dest, l.Start);
        double distance = Math.sqrt((ClosestPoint.x - obj1.bounds.Center.x) * (ClosestPoint.x - obj1.bounds.Center.x) + (ClosestPoint.y - obj1.bounds.Center.y) * (ClosestPoint.y - obj1.bounds.Center.y));
        double distance2 = Math.sqrt((ClosestPoint.x - l.Start.x) * (ClosestPoint.x - l.Start.x) + (ClosestPoint.y - l.Start.y) * (ClosestPoint.y - l.Start.y));
        if (distance < obj1.bounds.Radius && distance2 < l.Range && l.Start.x > ClosestPoint.x == l.Start.x > l.Dest.x && l.Start.y > ClosestPoint.y == l.Start.y > l.Dest.y) {
            l.Dest.x = ClosestPoint.x;
            l.Dest.y = ClosestPoint.y;
            l.mGrid= Grid.LightningLineGrid(Vector.DistanceBetween(l.Start,l.Dest));
            return true;
        }
        return false;
    }
    public boolean CollidesWith(Collideable objj) {
        if ((this.objectObjectType == ObjectType.LineSpell) && (objj.objectObjectType != ObjectType.LineSpell)) {
            return this.lightningCollidesWith(objj,this);
        } else if ((objj.objectObjectType == ObjectType.LineSpell) && (objectObjectType != ObjectType.LineSpell)) {
            return lightningCollidesWith(this,objj);
        } else if (objj.objectObjectType != ObjectType.LineSpell && objectObjectType != ObjectType.LineSpell) {
            return objj.bounds.CollidesWith(this.bounds);

        }
        return false;
    }
}
