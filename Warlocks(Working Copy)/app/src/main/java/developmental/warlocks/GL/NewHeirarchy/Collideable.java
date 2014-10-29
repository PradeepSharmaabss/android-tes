package developmental.warlocks.GL.NewHeirarchy;

import com.developmental.warlocks.R;

import java.util.ArrayList;
import java.util.List;

import Actors.Player;
import Game.DamageType;
import HUD.PopupText;
import SpellProjectiles.AbsorptionProjectile;
import SpellProjectiles.BounceProjectile;
import SpellProjectiles.ExplosionProjectile;
import SpellProjectiles.HealProjectile;
import SpellProjectiles.IcesplosionProjectile;
import SpellProjectiles.LightningProjectile;
import SpellProjectiles.LinkProjectile;
import SpellProjectiles.MeteorProjectile;
import SpellProjectiles.SwapProjectile;
import Spells.SpellEffect;
import Tools.BoundingCircle;
import Tools.Vector;
import developmental.warlocks.GL.Grid;
import developmental.warlocks.GL.SimpleGLRenderer;
import developmental.warlocks.Global;

/**
 * Created by Scott on 19/01/14.
 * An object that can impact with/collide with another object.
 */
public abstract class Collideable extends Moveable implements Comparable<Collideable> {

    public int compareTo(Collideable o) {
        return (int) (this.bounds.Center.y - o.bounds.Center.y);
        //	return (int) (this.position.y - o.position.y);
    }
    public Vector GetVel2(Vector from, Vector to,int pull) {
        //  this.position = from;
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Vector.DistanceBetween(to, from);

        return new Vector(pull * (distanceX / totalDist),
                pull * distanceY / totalDist);
    }
    //Applies a flat pull to the objects position.
    public Vector DirectionalPull(Vector TargetPosition, float _p) {
        Vector from = TargetPosition.get();
        Vector to = this.position.get();

        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Math.abs(distanceX) + Math.abs(distanceY);

        return new Vector(_p * (distanceX / totalDist), _p
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

                switch (obj.objectObjectType) {

                    case GameObject:
                    case Player:
                    case Enemy:
                        ImpulseYou =(obj.GetVel2(obj.bounds.Center, this.bounds.Center, this.knockback));
                        ImpulseObj =  this.GetVel2(bounds.Center, obj.bounds.Center, obj.knockback);


                        break;
                    case Boomerang:
                        if (obj.owner.id != this.id) {
                            ImpulseYou = (obj.GetVel2(obj.bounds.Center, this.bounds.Center, obj.knockback));
                            ImpulseObj = this.GetVel2(bounds.Center, obj.bounds.Center, obj.knockback);
                            damageYou = obj.damagevalue;
                        }
                        break;
                    case Projectile:
                    case Absorb:
                        if (obj.owner.id != this.id) {
                            ImpulseYou = obj.velocity;
                            SimpleGLRenderer.delObject(obj.id);
                            damageYou = obj.damagevalue;
                        }
                        break;

                    case LineSpell:
                        if ((obj.owner != null) && (this.id != obj.owner.id)) {

                            ImpulseYou= this.GetVel2(((LightningProjectile)obj).Start,this.bounds.Center, ((LightningProjectile) obj).knockback);
                            damageYou= obj.damagevalue;
                        }
                        break;

                    case Illusion:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case Meteor:
                        if (this.owner != null)
                            if (obj.id != this.owner.id)
                                if (obj.health == 10) {
                                    ImpulseYou = (this.GetVel2( obj.bounds.Center,bounds.Center, obj.knockback));
                                    damageYou=obj.damagevalue;
                                }
                        break;
                    case GravityField:
                        if(obj.owner.id!=this.id)
                        {
                            ImpulseYou=obj
                                    .DirectionalPull(this.position, obj.pull);
                            damageYou= obj.damagevalue;
                        }
                        break;
                    case LinkSpell:
                        ((LinkProjectile) obj).linked = this;
//                    obj.paint.setColor(Color.WHITE);
                        break;
                    case IceSpell:
                        if (this.id != obj.owner.id) {
                            ((GameObject)this).Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze,  this, R.drawable.effect_ice));
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case Explosion:
                        if (this.owner != null)
                            if (obj.id != this.owner.id) {


                                ImpulseYou = (this.GetVel2( obj.bounds.Center,bounds.Center, obj.knockback));
                                damageYou = obj.damagevalue;
                            }
                        break;
                    case Bounce:
                        if(obj.owner.id!=this.id)
                        {
                            ((BounceProjectile) obj).findNewTarget();
                            damageYou= obj.damagevalue;

                        }
                        break;
                    case SwapProjectile:
                        ((SwapProjectile)obj).Swap(this);
                        break;

                    case Drain:
                        this.Debuffs.add(new SpellEffect(500, SpellEffect.EffectType.Slow,this,R.drawable.effect_shield));
                        SimpleGLRenderer.addObject(new HealProjectile(this.position,obj.owner.bounds.Center.get(),obj.owner));
                        SimpleGLRenderer.delObject(obj.id);
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
                            ImpulseObj = velocity;

                            SimpleGLRenderer.delObject(id);
                            damageObj = this.damagevalue;
                        }
                        break;
                    case Projectile:
                    case Bounce:
                    case IceSpell:
                    case Drain:
                    case Boomerang:

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
                        SimpleGLRenderer.addObject(new ExplosionProjectile(this.bounds.Center.get(), new Vector(200, 200), obj.owner));
                        break;

                    case Meteor:
                        if (obj.health == ((MeteorProjectile) obj).landing)
                            SimpleGLRenderer.delObject(this.id);

                        break;
                    case GravityField:
                        ImpulseYou = obj.DirectionalPull(this.position, obj.pull);
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
                            ImpulseObj= (obj.GetVel2( ((LightningProjectile) this).Start,obj.bounds.Center, 30));
                            damageObj= this.damagevalue;
                        }
                        break;
                    case Absorb:

                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case IceSpell:
                        SimpleGLRenderer.delObject(obj.id);
                        SimpleGLRenderer.addObject(new IcesplosionProjectile(obj.bounds.Center.get(), new Vector(500, 500), this.owner));
                        break;
                    case Projectile:
                    case Bounce:
                    case Boomerang:
                    case Drain:

                        SimpleGLRenderer.delObject(obj.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(obj.bounds.Center.get(), new Vector(200, 200), this.owner));
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
                        if (this.health ==((MeteorProjectile)this).landing)
                            if (obj.id != this.owner.id) {
                                ImpulseObj = (obj.GetVel2( bounds.Center,obj.bounds.Center,this.knockback));
                                damageObj = this.damagevalue;
                            }
                        break;
                    case Projectile:
                    case Bounce:
                    case IceSpell:
                    case Boomerang:
                    case Drain:
                    case Illusion:
                        if (this.health ==((MeteorProjectile)this).landing)
                            SimpleGLRenderer.delObject(obj.id);
                        break;
                    case LineSpell:
                    case LinkSpell:
                    case SwapProjectile:
                    case Explosion:
                    case Meteor:
                        break;
                    case GravityField:
                        ImpulseYou = obj.DirectionalPull(this.position, obj.pull);
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
                        if (obj.id != this.owner.id) {
                            ImpulseYou = (this.GetVel2(obj.bounds.Center, bounds.Center, this.knockback));
                            ImpulseObj = (obj.GetVel2(this.bounds.Center, obj.bounds.Center, this.knockback));
                            damageObj = this.damagevalue;
                        }
                        break;
                    case Projectile:
                    case Bounce:
                    case IceSpell:
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
                        SimpleGLRenderer.addObject(new ExplosionProjectile(this.bounds.Center.get(), new Vector(200, 200), obj.owner));
                        break;

                    case Meteor:
                        if (obj.health == ((MeteorProjectile) obj).landing)
                            SimpleGLRenderer.delObject(this.id);

                        break;
                    case GravityField:
                        ImpulseYou = obj.DirectionalPull(this.position, obj.pull);
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
                        if (owner.id != obj.id) {
                            ImpulseObj = velocity;

                            SimpleGLRenderer.delObject(id);
                            damageObj = this.damagevalue;
                        }
                        break;
                    case Projectile:
                    case Bounce:
                    case IceSpell:
                    case Boomerang:
                    case Drain:
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
                        if(obj.id!=this.owner.id)
                        {
                            ImpulseObj=   this.DirectionalPull(obj.position, pull);
                            damageObj  =this.damagevalue;
                        }
                        break;
                    case Meteor:
                    case GravityField:
                    case LinkSpell:
                    case IceSpell:
                    case Bounce:
                    case SwapProjectile:
                    case Boomerang:
                    case Projectile:
                    case Drain:
                    case Illusion:
                        ImpulseObj=   this.DirectionalPull(obj.position, pull);

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
                    case IceSpell:
                    case Illusion:
                        if(obj.id!=owner.id)
                            ((LinkProjectile)this).Link(obj);
                        break;
                    case GravityField:
                        ImpulseYou= velocity.add(obj
                                .DirectionalPull(this.position, obj.pull));
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
             * Ice
             ***********************************************************************************************************************/
            case IceSpell:
                switch (obj.objectObjectType) {
                    case IceSpell:
                    case Projectile:
                    case Bounce:
                    case Boomerang:
                    case Drain:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);
                        }
                        break;

                    case Illusion:
                        if (obj.owner.id != this.owner.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case GravityField:
                        ImpulseYou=obj.DirectionalPull(this.position, obj.pull);
                        break;
                    case Absorb:
                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case GameObject:
                    case Enemy:
                    case Player:
                        if(obj.id!=owner.id)
                        {
                            obj.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze,  obj,R.drawable.effect_ice));
                            SimpleGLRenderer.delObject(this.id);

                            damageObj = this.damagevalue;
                        }
                        break;


                    case LineSpell:
                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new IcesplosionProjectile(this.bounds.Center, new Vector(500, 500), obj.owner));
                        break;
                    case Meteor:
                        if (obj.health == ((MeteorProjectile) obj).landing)
                            SimpleGLRenderer.delObject(this.id);
                        break;
                    case Explosion:
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            SimpleGLRenderer.delObject(this.id);
                        break;
                    case LinkSpell:
                        ((LinkProjectile) obj).Link(this);
                        break;


                    case SwapProjectile:
                        ((SwapProjectile) obj).Swap(this);
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
                        if (this.owner != null)
                            if (obj.id != this.owner.id) {

                                ImpulseObj = (obj.GetVel2( bounds.Center,obj.bounds.Center, this.knockback));
                                damageObj = this.damagevalue;
                            }
                        break;
                    case Projectile:
                    case Bounce:
                    case IceSpell:
                    case Boomerang:
                    case Drain:
                    case Illusion:
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
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            if (b.lastTarget == null || obj.id != b.lastTarget.id) {
                                ImpulseObj = this.velocity;
                                damageObj = b.damagevalue;
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
                    case IceSpell:
                    case Drain:
                    case Boomerang:

                        if ((obj.owner.id != this.owner.id)) {
                            SimpleGLRenderer.delObject(obj.id);
                            SimpleGLRenderer.delObject(this.id);
                        }
                        break;

                    case Illusion:
                        if (obj.owner.id != this.id) {
                            SimpleGLRenderer.delObject(obj.id);
                        }
                        break;
                    case LineSpell:
                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(this.bounds.Center, new Vector(200, 200), obj.owner));
                        break;
                    case GravityField:
                        ImpulseYou = obj
                                .DirectionalPull(this.position, obj.pull);
                        break;
                    case LinkSpell:

                        ((LinkProjectile) obj).Link(this);
                        break;

                    case Explosion:
                        if ((this.owner != null) && (obj.id != this.owner.id))
                            SimpleGLRenderer.delObject(this.id);
                        break;
                    case Meteor:
                        if (obj.health == ((MeteorProjectile) obj).landing)
                            SimpleGLRenderer.delObject(this.id);
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
                    case IceSpell:
                    case Bounce:
                    case Drain:
                    case Boomerang:
                    case Absorb:
                    case Illusion:
                        ((SwapProjectile)this).Swap(obj);
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
            case Drain:
                switch (obj.objectObjectType) {
                    case GameObject:
                    case Player:
                    case Enemy:
                        obj.Debuffs.add(new SpellEffect(500, SpellEffect.EffectType.Slow, obj,R.drawable.effect_shield));
                        SimpleGLRenderer.addObject(new HealProjectile(obj.position, owner.bounds.Center.get(), owner));

                        SimpleGLRenderer.delObject(this.id);
                        break;
                    case Absorb:
                        ((AbsorptionProjectile)obj).Absorb(this);
                        break;
                    case Projectile:
                    case Bounce:
                    case IceSpell:
                    case Drain:
                    case Boomerang:
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
                    case LineSpell:

                        SimpleGLRenderer.delObject(this.id);
                        SimpleGLRenderer.addObject(new ExplosionProjectile(this.bounds.Center.get(), new Vector(200, 200), obj.owner));
                        break;
                    case GravityField:

                        ImpulseYou=   obj.DirectionalPull(this.position, obj.pull);

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
                        if (obj.health ==((MeteorProjectile)obj).landing)
                            SimpleGLRenderer.delObject(this.id);
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
                    case IceSpell:
                    case LineSpell:
                    case Boomerang:
                    case Drain:
                    case HealHoming:
                    case Absorb:
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
                        ImpulseYou=   obj.DirectionalPull(this.position, obj.pull);

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
                        SimpleGLRenderer.delObject(this.id);
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
            if(Global.DEBUG_MODE)
                SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Poison, "" + (this.mana+400)/400, this.position.get(), 100));
            this.velocity= this.velocity.add(ImpulseYou);

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
            if(Global.DEBUG_MODE)
                SimpleGLRenderer.popupTexts.add(new PopupText(PopupText.TextType.Poison,""+(obj.mana + 400) / 400,obj.position.get(),100));
            obj.velocity = obj.velocity.add(ImpulseObj);


        }

    }

    public float health = 500;
    public float maxhealth = this.health;
    public float mana = 0;
    public float damageDealtThisRound = 0;

    public List<SpellEffect> Debuffs = new ArrayList<SpellEffect>();
    public void Damage(float dmgDealt, DamageType d) {

    }

    public Vector feet;
    public float damagevalue = 0;
    /**
     * the classification of the object. This is used to determine how the two objects involved in the collision will respond to it
     */
    public enum ObjectType {
        GameObject,Player, Enemy, Projectile, Bounce, IceSpell, LineSpell, Boomerang,Drain,HealHoming, Absorb, GravityField, LinkSpell, SwapProjectile, Explosion, Illusion, Meteor
    }
    public ObjectType objectObjectType;
    /**
     * How far the object will send another object flying if it impacts
     */
    public int knockback= 5;
    /**
     * A Circle, defined by a radius and a position, that will determine whether or not it is impacting with another Collideable
     */
    public BoundingCircle bounds;

    public int burnCounter = 0;
    public int burnTicker = 0;
    public int burnHit = 0;
    public int HealthRegenPer150Updates = 5;
    protected void Heal(float HealAmount) {

    }

    @Override
    public void Update() {
        super.Update();
       this.bounds.Center = this.position;
    }

    protected Player FindClosestPlayer(float maxDistance) {
        Player player = null;
        for (Player p : SimpleGLRenderer.players) {
            if (p.id != owner.id) {
                float totalDist = Vector.DistanceBetween(this.bounds.Center, p.bounds.Center);
                if (totalDist < maxDistance) {
                    maxDistance = totalDist;
                    player = p;

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
                this.position.y -_size.x/2 );
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
