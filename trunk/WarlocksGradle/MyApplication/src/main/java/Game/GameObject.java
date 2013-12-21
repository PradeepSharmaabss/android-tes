package Game;

import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import Actors.Player;
import HUD.PopupText;
import Particles.HealthDisplay;
import SpellProjectiles.BoomerangProjectile;
import SpellProjectiles.BounceProjectile;
import SpellProjectiles.ExplosionProjectile;
import SpellProjectiles.LightningProjectile;
import SpellProjectiles.LinkProjectile;
import SpellProjectiles.MeteorProjectile;
import SpellProjectiles.Projectile;
import SpellProjectiles.SwapProjectile;
import Spells.Spell;
import Tools.BoundingCircle;
import Tools.Vector;
import Tools.iVector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

public abstract class GameObject implements Comparable<GameObject> {
    public GameObject owner;// = null;
    public Bitmap curr = null;
    public RectF rect;
    public Paint paint, shadowPaint;
    public ArrayList<Bitmap> spriteSheet;
    public float damageDealtThisRound = 0;
    public boolean shadow = true, AI = true, shoot = false, hit = false, dead = false;

    //WILL BE SENT OVER NETWORK
    public List<SpellEffect> Debuffs = new ArrayList<SpellEffect>();
    public int id = 0;
    public float health = 1000;
    public int armour = 0;
    public int resist = 0;
    public float maxhealth = this.health;
    public int mana = 0;
    protected float acceleration = 0.75f;
    protected float maxVelocity = 15f;
    public float pull = 0.2f;
    public Vector position, size, velocity, destination, feet;
    public Spell[] Spells;
    public ObjectType objectObjectType;
    public BoundingCircle bounds;

    protected int lifePhase = 0;
    public GameObject() {
        this.objectObjectType = ObjectType.GameObject;
        this.position = new Vector(0, 0);
        this.size = new Vector(50, 50);
        this.velocity = new Vector(0, 0);
        //this.Spells = new Spell[10];
        this.paint = new Paint();
        this.paint.setColor(Color.RED);
        this.shadowPaint = new Paint();
        this.shadowPaint.setColor(Color.BLACK);
        this.shadowPaint.setMaskFilter(new BlurMaskFilter(30,
                BlurMaskFilter.Blur.INNER));


        this.rect = new RectF(this.position.x, this.position.y, this.position.x
                + this.size.x, this.position.y + this.size.y);
        this.dRect = new RectF(this.position.x, this.position.y, this.position.x
                + this.size.x, this.position.y + this.size.y);

        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y - this.size.y);
        bounds = new BoundingCircle(feet, 33);
    }

    public int compareTo(GameObject o) {
        return (int) (this.rect.bottom - o.rect.bottom);
        //	return (int) (this.position.y - o.position.y);
    }

    protected void GetSprites(ArrayList<Bitmap> spriteSheet) {

    }

    public void DrawHitBox(float offsetx, float offsety, Canvas c) {
        Paint s = new Paint();
        s.setColor(Color.GREEN);
        s.setStyle(Paint.Style.STROKE);
//        c.drawRect(dRect,s);
        bounds.Draw(c, offsetx, offsety, s);
    }


    public void DrawHealthBar(Canvas c, float playerx, float playery) {
        Paint s = new Paint();
        s.setColor(Color.BLACK);
        c.drawRect(this.dRect.left - 2 - playerx, this.dRect.top - 2 - playery, this.dRect.right + 2 - playerx,
                this.dRect.top + 10 + 2 - playery, s);
        s.setColor(Color.GRAY);
        c.drawRect(this.dRect.left - playerx, this.dRect.top - playery, this.dRect.right - playerx,
                this.dRect.top + 10 - playery, s);
        if (this.health / this.maxhealth < 0.2)
            s.setColor(Color.RED);
        else if (this.health / this.maxhealth < 0.5)
            s.setColor(Color.YELLOW);
        else
            s.setColor(Color.GREEN);
        c.drawRect(
                this.dRect.left - playerx,
                this.dRect.top - playery,
                this.dRect.right
                        - ((1 - (this.health / this.maxhealth)) * this.dRect
                        .width()) - playerx, this.dRect.top - playery + 10, s);
    }
    public float damagevalue = 0;
    public void Damage(float dmgDealt, DamageType d) {
        if (dmgDealt > this.health) {
            this.health = 0;
            if (!Global.DEBUG_MODE) {
                this.dead = true;
                RenderThread.delObject(this.id);
            }
        } else {
           // RenderThread.addParticle(new HealthDisplay(position.get(), velocity.get(), 20, paint, this));
            this.health -= dmgDealt;
        }
        this.mana += dmgDealt;
        this.displayhealth = 20;
          RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Damage,dmgDealt+"",this.bounds.Center.get(),12));
    }

    public int displayhealth = 0;

    public boolean Intersect(RectF PassedObj) {

        return RectF.intersects(this.rect, PassedObj);
    }

    protected RectF dRect;

    public void Draw(Canvas canvas, float playerx, float playery) {
        this.dRect = new RectF(rect.left - RenderThread.archie.position.x + RenderThread.size.x / 2, rect.top - RenderThread.archie.position.y + RenderThread.size.y / 2, rect.right - RenderThread.archie.position.x + RenderThread.size.x / 2, rect.bottom - RenderThread.archie.position.y + RenderThread.size.y / 2);

        canvas.save();
        canvas.translate(this.dRect.left + this.dRect.width() / 2, +this.dRect.top
                - this.dRect.height() / 2);
        canvas.rotate(45);
        if (this.curr != null)
            canvas.drawBitmap(this.curr.extractAlpha(), null, new RectF(
                    this.size.x / 2, 0, this.dRect.width() + this.size.x / 3,
                    this.dRect.height()), this.shadowPaint);
        else
            canvas.drawRect(new RectF(this.size.x / 2, 0, this.dRect.width()
                    + this.size.x / 3, this.dRect.height()), this.shadowPaint);
        canvas.restore();
        if (this.curr == null)
            canvas.drawRect(this.dRect, this.paint);
        if (Global.DEBUG_MODE) {
            if (destination != null)
                canvas.drawLine(this.rect.centerX() - playerx, this.rect.centerY() - playery, destination.x - playerx, destination.y - playery, Global.PaintBlack);
        }
        // super.Draw(canvas,dRect);

    }


    protected boolean casting = false, frozen = false, stunned = false;
    public void Update() {
        this.lifePhase++;
        if (displayhealth > 0)
            this.displayhealth -= 1;
        switch (objectObjectType)
        {
            case GameObject:
                case Player:
                    case Enemy:
                        if (!RenderThread.l.platform.Within(this.feet)) {

                            Damage(3, DamageType.Lava);
                        } else {
                            if(displayhealth==0)
                            velocity = Vector.multiply(velocity, 0.99f);
                        }
                break;

        }

        if (!casting && !frozen)
            if (this.destination != null && !this.hit)
                GoTo(this.destination);
        this.position = this.position.add(this.velocity);

        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y + this.size.y - bounds.Radius);
        bounds.Center = feet;
        casting = false;
        frozen = false;
        stunned = false;
        for (int i = 0; i < Debuffs.size(); i++) {

            SpellEffect e = Debuffs.get(i);
            e.Update();
            if (e.Duration > 0) {
                Log.d("INET", e.effectType + " " + e.Duration);

                if (e.effectType == SpellEffect.EffectType.Cast)
                    casting = true;
                if (e.effectType == SpellEffect.EffectType.Explode)
                    casting = true;
                if (e.effectType == SpellEffect.EffectType.Freeze)
                    frozen = true;
                if (e.effectType == SpellEffect.EffectType.Stun)
                    stunned = true;
            } else {
                Log.d("INET", "GET RID OF SPELL");
                e.FinalUpdate();
                Debuffs.remove(i);
            }
            Log.d("INET", "Casting");

        }

        CollideMap();
        this.hit = false;

        this.rect = new RectF(this.position.x, this.position.y, this.position.x
                + this.size.x, this.position.y + this.size.y);
        if (Spells != null)
            for (int j = 0; j < Spells.length; j++) {

                Spells[j].Update();
            }
    }

    public boolean CollidesWith(GameObject g) {
        if ((this.objectObjectType == ObjectType.LineSpell) && (g.objectObjectType != ObjectType.LineSpell)) {
            LightningProjectile l = (LightningProjectile) this;
            Vector ClosestPoint = g.bounds.closestpointonline(l.Dest, l.Start);
            double distance = Math.sqrt((ClosestPoint.x - g.bounds.Center.x) * (ClosestPoint.x - g.bounds.Center.x) + (ClosestPoint.y - g.bounds.Center.y) * (ClosestPoint.y - g.bounds.Center.y));
            double distance2 = Math.sqrt((ClosestPoint.x - l.Start.x) * (ClosestPoint.x - l.Start.x) + (ClosestPoint.y - l.Start.y) * (ClosestPoint.y - l.Start.y));
            if (distance < g.bounds.Radius && distance2 < l.Range && l.Start.x > ClosestPoint.x == l.Start.x > l.Dest.x && l.Start.y > ClosestPoint.y == l.Start.y > l.Dest.y) {
                l.Dest = ClosestPoint;
                return true;
            }
        } else if ((g.objectObjectType == ObjectType.LineSpell) && (objectObjectType != ObjectType.LineSpell)) {
            LightningProjectile l = (LightningProjectile) g;
            Vector ClosestPoint = this.bounds.closestpointonline(l.Dest, l.Start);
            double distance = Math.sqrt((ClosestPoint.x - this.bounds.Center.x) * (ClosestPoint.x - this.bounds.Center.x) + (ClosestPoint.y - this.bounds.Center.y) * (ClosestPoint.y - this.bounds.Center.y));
            double distance2 = Math.sqrt((ClosestPoint.x - l.Start.x) * (ClosestPoint.x - l.Start.x) + (ClosestPoint.y - l.Start.y) * (ClosestPoint.y - l.Start.y));
            if (distance < g.bounds.Radius && distance2 < l.Range && l.Start.x > ClosestPoint.x == l.Start.x > l.Dest.x && l.Start.y > ClosestPoint.y == l.Start.y > l.Dest.y) {
                l.Dest = ClosestPoint;
                return true;
            }
        } else if (g.objectObjectType != ObjectType.LineSpell && objectObjectType != ObjectType.LineSpell) {
            return g.bounds.CollidesWith(this.bounds);

        }
        return false;
    }

    protected Player FindClosestPlayer(float maxDistance) {
        Player player = null;
        for (Player p : RenderThread.players) {
            if (p.id != owner.id) {
                float totalDist = Vector.DistanceBetween(this.bounds.Center, p.bounds.Center);
                if (totalDist < maxDistance) {
                    maxDistance = totalDist;
                    player = p;
                    Log.d("INET", "TARGET SET");
                }
            }
        }
        return player;
    }

    public void FingerUpdate(iVector[] f, int SelectedSpell) {

        if (SelectedSpell == -1) {
            if (f.length > 0)
                StartTo(new Vector(f[0].x, f[0].y));
        } else {

            if (Spells[SelectedSpell].Current == 0)
            {
                if(Spells[SelectedSpell].Cast(f))
                    for(Spell spell:Spells)
                    {
                        if(spell.Current<10)
                            spell.Current=Global.GlobalCooldown;
                    }
            }
        }
    }

    protected Destination Marker;

    public void StartTo(Vector Dest) {
        this.destination = new Vector(Dest.x, Dest.y);
        this.Marker = new Destination(destination);
    }

    public void CollideMap() {
        if (this.position.x < 0)
            this.velocity.x = Math.abs(this.velocity.x);
        if (this.position.x + this.size.x > Global.WORLD_BOUND_SIZE.x)
            this.velocity.x = -Math.abs(this.velocity.x);
        if (this.position.y + this.size.y > Global.WORLD_BOUND_SIZE.y)
            this.velocity.y = -Math.abs(this.velocity.y);
        if (this.position.y < 0)
            this.velocity.y = Math.abs(this.velocity.y);
    }

    public void SetVelocity(float vel) {

        float totalVel = Math.abs(this.velocity.x) + Math.abs(this.velocity.y);
        this.velocity = new Vector(vel * this.velocity.x / totalVel, vel
                * this.velocity.y / totalVel);
    }

    public float CurrentVelocity(Vector vel) {
        float distanceX = vel.x;
        float distanceY = vel.y;
        return (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

    }

    //Applies a Vector to the velocity, based on accelleration and max speed, in the direction of the destination
    protected void GoTo(Vector d) {
        float distanceX = d.x - this.feet.x;
        float distanceY = d.y - this.feet.y;
        float totalDist = (float) Math.sqrt(Math.pow(distanceX, 2) + Math.pow(distanceY, 2));

        if (totalDist > this.CurrentVelocity(velocity) + acceleration) {
            Vector newvelocity = new Vector(this.maxVelocity
                    * (distanceX / totalDist), this.maxVelocity * distanceY
                    / totalDist);
            if (Math.abs(newvelocity.x - this.velocity.x) > this.acceleration)
                if (newvelocity.x > this.velocity.x)
                    newvelocity.x = this.velocity.x + this.acceleration;
                else
                    newvelocity.x = this.velocity.x - this.acceleration;
            if (Math.abs(newvelocity.y - this.velocity.y) > this.acceleration)
                if (newvelocity.y > this.velocity.y)
                    newvelocity.y = this.velocity.y + this.acceleration;
                else
                    newvelocity.y = this.velocity.y - this.acceleration;
            this.velocity = newvelocity;
        } else {

            this.feet = this.destination;
            // bounds.Center=feet;
            this.destination = null;
            this.velocity = new Vector(0, 0);
        }
    }

public void Collision2(GameObject obj)
{
    Vector ImpulseYou = new Vector(0,0);
    Vector ImpulseObj = new Vector(0,0);
    float damageYou = 0;
    float damageObj = 0;
    DamageType d = DamageType.Spell;
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
                    ImpulseYou = Vector.multiply(this.GetVel2(position, obj.bounds.Center, 5), -1);
                    ImpulseObj = Vector.multiply(obj.GetVel2(obj.position, this.bounds.Center, 5), -1);


                    break;
                case Boomerang:
                    ImpulseYou = Vector.multiply(this.GetVel2(position, obj.bounds.Center, 25), -1);
                    ImpulseObj = Vector.multiply(obj.GetVel2(obj.position, this.bounds.Center, 25), -1);
                    damageYou = obj.damagevalue;

                    break;
                case Projectile:
                    if (obj.owner.id != this.id) {
                        ImpulseYou = obj.velocity;
                        RenderThread.delObject(obj.id);
                        damageYou = obj.damagevalue;
                    }
                    break;
                case LineSpell:
                    if ((obj.owner != null) && (this.id != obj.owner.id)) {

                        ImpulseYou= Vector.multiply(obj.GetVel2(obj.position, ((LightningProjectile) this).Start, 30), -1);
                        damageYou= obj.damagevalue;
                    }
                    break;

                case Meteor:
                    if (this.owner != null)
                        if (obj.id != this.owner.id)
                            if (obj.health == 10) {
                                ImpulseYou = Vector.multiply(this.GetVel2(position, obj.bounds.Center, 10), -1);
                                damageYou=obj.damagevalue;
                            }
                    break;
                case GravityField:
                    ImpulseYou=obj
                            .DirectionalPull(this.position, obj.pull);
                    break;
                case LinkSpell:
                    ((LinkProjectile) obj).linked = this;
                    obj.paint.setColor(Color.WHITE);
                    break;
                case IceSpell:
                    if (this.id != obj.owner.id) {
                        this.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, Global.Sprites.get(3), this));
                        RenderThread.delObject(obj.id);
                    }
                    break;
                case Explosion:
                    if (this.owner != null)
                        if (obj.id != this.owner.id) {


                            ImpulseYou = Vector.multiply(this.GetVel2(position, obj.bounds.Center, 10), -1);

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
                        RenderThread.delObject(id);
                        damageObj = this.damagevalue;
                    }
                    break;
                case Projectile:
                case Bounce:
                case IceSpell:
                case Boomerang:
                    if ((obj.owner.id != this.owner.id)) {
                        RenderThread.delObject(obj.id);
                        RenderThread.delObject(this.id);


                    }
                    break;
                case LineSpell:
                    RenderThread.delObject(this.id);
                    RenderThread.addObject(new ExplosionProjectile(this.bounds.Center.get(), new Vector(200, 200), obj.owner));
                    break;

                case Meteor:
                    if (obj.health == ((MeteorProjectile) obj).landing)
                        RenderThread.delObject(this.id);

                    break;
                case GravityField:
                    ImpulseYou = obj.DirectionalPull(this.position, obj.pull);
                    break;
                case LinkSpell:

                    ((LinkProjectile) obj).Link(this);
                    break;

                case Explosion:
                    if ((this.owner != null) && (obj.id != this.owner.id))
                        RenderThread.delObject(this.id);
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
                       ImpulseObj= Vector.multiply(obj.GetVel2(obj.position, ((LightningProjectile) this).Start, 30), -1);
                        damageObj= this.damagevalue;
                    }
                    break;
                case Projectile:
                case Bounce:
                case IceSpell:
                case Boomerang:
                    RenderThread.delObject(obj.id);
                    RenderThread.addObject(new ExplosionProjectile(obj.bounds.Center.get(), new Vector(200, 200), this.owner));
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
                            ImpulseObj = Vector.multiply(obj.GetVel2(obj.position, bounds.Center,10), -1);
                            damageObj = this.damagevalue;
                        }
                    break;
                case Projectile:
                case Bounce:
                case IceSpell:
                case Boomerang:
                    if (this.health ==((MeteorProjectile)this).landing)
                        RenderThread.delObject(obj.id);
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
                    ImpulseYou = Vector.multiply(this.GetVel2(position, obj.bounds.Center, 25), -1);
                    ImpulseObj = Vector.multiply(obj.GetVel2(obj.position, this.bounds.Center, 25), -1);
                    damageObj = this.damagevalue;
                    break;
                case Projectile:
                case Bounce:
                case IceSpell:
                case Boomerang:
                    if ((obj.owner.id != this.owner.id)) {
                        RenderThread.delObject(obj.id);
                        RenderThread.delObject(this.id);


                    }
                    break;
                case LineSpell:
                    RenderThread.delObject(this.id);
                    RenderThread.addObject(new ExplosionProjectile(this.bounds.Center.get(), new Vector(200, 200), obj.owner));
                    break;

                case Meteor:
                    if (obj.health == ((MeteorProjectile) obj).landing)
                        RenderThread.delObject(this.id);

                    break;
                case GravityField:
                    ImpulseYou = obj.DirectionalPull(this.position, obj.pull);
                    break;
                case LinkSpell:

                    ((LinkProjectile) obj).Link(this);
                    break;

                case Explosion:
                    if ((this.owner != null) && (obj.id != this.owner.id))
                        RenderThread.delObject(this.id);
                    break;

                case SwapProjectile:

                    ((SwapProjectile) obj).Swap(this);
                    break;
            }
            break;
        /***********************************************************************************************************************
         * Gravity
         ***********************************************************************************************************************/
        case GravityField:
            switch (obj.objectObjectType) {
                case GameObject:
                case Projectile:
                case Player:
                case Enemy:
                case Meteor:
                case GravityField:
                case LinkSpell:
                case IceSpell:
                case Bounce:
                case SwapProjectile:
                case Boomerang:
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

                case IceSpell:
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
                    if (obj.owner.id != this.owner.id) {
                        RenderThread.delObject(obj.id);
                        RenderThread.delObject(this.id);
                    }
                    break;
                case GravityField:
                   ImpulseYou=obj.DirectionalPull(this.position, obj.pull);
                    break;
                case GameObject:
                case Enemy:
                case Player:
                    if(obj.id!=owner.id)
                    {
                        obj.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, Global.Sprites.get(3), obj));
                        RenderThread.delObject(this.id);

                        damageObj = this.damagevalue;
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

                            ImpulseObj = Vector.multiply(obj.GetVel2(obj.position, bounds.Center, 10), -1);
                           damageObj = this.damagevalue;
                        }
                    break;
                case Projectile:
                case Bounce:
                case IceSpell:
                case Boomerang:
                    if (obj.owner.id != this.id) {
                        RenderThread.delObject(obj.id);

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
                                RenderThread.delObject(this.id);
                            }
                        }
                    break;
                case SwapProjectile:
                ((SwapProjectile) obj).Swap(this);
                    break;
                case Projectile:
                case Bounce:
                case IceSpell:
                case Boomerang:

                    if ((obj.owner.id != this.owner.id)) {
                        RenderThread.delObject(obj.id);
                        RenderThread.delObject(this.id);
                    }
                    break;
                case LineSpell:
                    RenderThread.delObject(this.id);
                    RenderThread.addObject(new ExplosionProjectile(this.bounds.Center, new Vector(200, 200), obj.owner));
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
                        RenderThread.delObject(this.id);
                    break;
                case Meteor:
                    if (obj.health == ((MeteorProjectile) obj).landing)
                        RenderThread.delObject(this.id);
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
                    case Boomerang:
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
    }

   if(damageYou>0)
   {
        this.Damage(damageYou,DamageType.Spell);
       obj.owner.damageDealtThisRound+=damageYou;
   }
   if(damageObj>0)
   {
       obj.Damage(damageObj,DamageType.Spell);
       this.owner.damageDealtThisRound+=damageObj;
   }
      if(this.CurrentVelocity(ImpulseYou)>0)
    {

        ImpulseYou = Vector.multiply(ImpulseYou,(this.mana+400)/400);
        RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Poison, "" + this.CurrentVelocity(ImpulseYou), this.position.get(), 100));
        this.velocity= ImpulseYou;
    }

    if(this.CurrentVelocity(ImpulseObj)>0)
    {
        ImpulseObj = Vector.multiply(ImpulseObj, (obj.mana + 400) / 400);
        RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Poison,""+this.CurrentVelocity(ImpulseObj),obj.position.get(),100));
        obj.velocity =ImpulseObj;
    }
    
}

    public void Collision(GameObject obj) {

        switch (obj.objectObjectType) {
            case Bounce:
                ((BounceProjectile) obj).findNewTarget();
                this.Damage(((BounceProjectile) obj).damagevalue, DamageType.Spell);
                break;
            case Projectile:
                if (obj.owner.id != this.id) {
                    this.ProjectileHit(obj.velocity);
                    RenderThread.delObject(obj.id);
                    this.Damage(((Projectile) obj).damagevalue, DamageType.Spell);
                }
                break;
            case LineSpell:
                this.velocity = Vector.multiply(this.GetVel(this.position, ((LightningProjectile) obj).Start), -1);
                this.position.add(Vector.multiply(this.velocity, 2));
                ((LightningProjectile) (obj)).DealDamageTo(this);
                break;
            case Player:
            case GameObject:
            case Enemy:
                velocity = Vector.multiply(this.GetVel(position, obj.bounds.Center), -1);
                SetVelocity(maxVelocity);
                obj.velocity = Vector.multiply(obj.GetVel(obj.position, this.bounds.Center), -1);
                obj.SetVelocity(obj.maxVelocity);

                break;
            case Meteor:
                if (this.owner != null)
                    if (obj.id != this.owner.id)
                        if (obj.health == 10) {
                            velocity = Vector.multiply(this.GetVel(position, obj.bounds.Center), -1);
                            Damage(((Projectile) (obj)).damagevalue, DamageType.Spell);
                        }
                break;
            case Explosion:
                if (this.owner != null)
                    if (obj.id != this.owner.id) {


                        velocity = Vector.multiply(this.GetVel(position, obj.bounds.Center), -1);

                    }
                break;
            case GravityField:
                this.velocity = this.velocity.add(obj
                        .DirectionalPull(this.position, obj.pull));
                break;
            case LinkSpell:
                ((LinkProjectile) obj).linked = this;
                obj.paint.setColor(Color.WHITE);
                break;
            case SwapProjectile:
                ((SwapProjectile)obj).Swap(this);
//                Vector l;
//                l = obj.owner.position;
//                obj.owner.position = this.position;
//                this.position = l;
//                RenderThread.delObject(obj.id);

                break;
            case IceSpell:
                if (this.id != obj.owner.id) {
                    this.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, Global.Sprites.get(3), this));
                    RenderThread.delObject(obj.id);
                }
                break;

        }
    }

    public Vector GetVel(Vector from, Vector to) {
        this.position = from;
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Vector.DistanceBetween(to, from);

        return new Vector(this.maxVelocity * (distanceX / totalDist),
                this.maxVelocity * distanceY / totalDist);
    }
    public Vector GetVel2(Vector from, Vector to,int pull) {
        this.position = from;
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Vector.DistanceBetween(to, from);

        return new Vector(pull * (distanceX / totalDist),
                pull * distanceY / totalDist);
    }

    public static Vector PositiononEllipse(float _angle) {
        float _x = (RenderThread.l.platform.Size.x / 2 - (RenderThread.l.platform.Size.x / 10))
                * (float) Math.cos(Math.toRadians(_angle))
                + RenderThread.l.platform.Position.x;
        float _y = (RenderThread.l.platform.Size.y / 2 - (RenderThread.l.platform.Size.y / 10))
                * (float) Math.sin(Math.toRadians(_angle))
                + RenderThread.l.platform.Position.y;
        return new Vector(_x, _y);
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

    public void ProjectileHit(Vector v) {

        this.paint.setColor(Color.RED);
        this.velocity = v;
        this.position.add(Vector.multiply(this.velocity, 2));

    }

    public Vector getCenter() {
        return new Vector(this.rect.left + this.rect.width() / 2, this.rect.top
                + this.rect.height() / 2);
    }

}
