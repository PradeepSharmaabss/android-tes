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

import HUD.PopupText;
import SpellProjectiles.BounceProjectile;
import SpellProjectiles.LinkProjectile;
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

    public boolean shadow = true, AI = true, shoot = false, hit = false;

    //WILL BE SENT OVER NETWORK
    public List<SpellEffect> Debuffs = new ArrayList<SpellEffect>();
	public int id = 0, health = 1000, armour = 0, resist = 0,
			maxhealth = this.health, mana = 0;
    protected float acceleration = 0.4f;
    protected float maxVelocity = 15f;
    public float pull=0.2f;
    public Vector position, size, velocity, destination, feet;
    public Spell[] Spells;
	public ObjectType objectObjectType;
    public BoundingCircle bounds;




	public GameObject(GameObject owner) {
		this();
		owner = this.owner;
	}

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
        bounds=new BoundingCircle(feet,50);
	}

	public int compareTo(GameObject o) {
        return (int)(this.rect.bottom-o.rect.bottom);
	//	return (int) (this.position.y - o.position.y);
	}

	protected void GetSprites(ArrayList<Bitmap> spriteSheet) {

	}
    public void DrawHitBox(float offsetx,float offsety,Canvas c)
    {
        Paint s = new Paint();
        s.setColor(Color.GREEN);
        s.setStyle(Paint.Style.STROKE);
       // RectF r = new RectF(this.dRect.left-offsetx,this.dRect.top-offsety,this.dRect.right-offsetx,this.dRect.bottom-offsety);
        c.drawRect(dRect,s);
       bounds.Draw(c,offsetx,offsety);
    }


	protected void DrawHealthBar(Canvas c,float playerx,float playery) {
		Paint s = new Paint();
		s.setColor(Color.BLACK);
		c.drawRect(this.dRect.left - 2-playerx, this.dRect.top - 2-playery, this.dRect.right + 2-playerx,
				this.dRect.top + 10 + 2-playery, s);
		s.setColor(Color.GRAY);
		c.drawRect(this.dRect.left-playerx, this.dRect.top-playery, this.dRect.right-playerx,
				this.dRect.top + 10-playery, s);
		if ((float) this.health / (float) this.maxhealth < 0.2)
			s.setColor(Color.RED);
		else if ((float) this.health / (float) this.maxhealth < 0.5)
			s.setColor(Color.YELLOW);
		else
			s.setColor(Color.GREEN);
		c.drawRect(
				this.dRect.left-playerx,
				this.dRect.top-playery,
				this.dRect.right
						- ((1 - ((float) this.health / (float) this.maxhealth)) * this.dRect
								.width())-playerx, this.dRect.top-playery + 10, s);
	}

	public void Damage(float dmgDealt,DamageType d) {
		if (dmgDealt > this.health)
			this.health = 0;
		else
			this.health -= dmgDealt;
    RenderThread.popupTexts.add(new PopupText(PopupText.TextType.Damage,dmgDealt+"",new Vector(this.rect.centerX(),this.rect.centerY()),4));
	}

	public boolean Intersect(RectF PassedObj) {

        return RectF.intersects(this.rect, PassedObj);
    }

    protected RectF dRect;
	public void Draw(Canvas canvas,float playerx,float playery) {

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
        if(Global.DEBUG_MODE)
        {
            if(destination!=null)
            canvas.drawLine(this.rect.centerX(),this.rect.centerY(),destination.x,destination.y,new Paint());
        }
		// super.Draw(canvas,dRect);

	}


  protected  boolean casting = false,frozen = false;
	public void Update() {

		if (!RenderThread.l.platform.Within(this.feet))
			Damage(3,DamageType.Lava);
		this.position = this.position.add(this.velocity);
        this.feet = new Vector(this.position.x + this.size.x / 2,
                this.position.y + this.size.y);

        for(int i = 0; i<Debuffs.size();i++)
        {
            casting=false;
            frozen=false;
            SpellEffect e = Debuffs.get(i);
            e.Update();
            if(e.Duration>0)
            {
                Log.d("INET" , e.effectType+ " " + e.Duration);

                if(e.effectType == SpellEffect.EffectType.Cast)
                casting = true;
                if(e.effectType == SpellEffect.EffectType.Explode)
                    casting = true;
                if(e.effectType== SpellEffect.EffectType.Freeze)
                    frozen=true;
            }
            else
            {
                Log.d("INET" ,"GET RID OF SPELL");
                e.FinalUpdate();
                Debuffs.remove(i);
            }
                Log.d("INET", "Casting");

        }
        if(!casting&&!frozen)
		if (this.destination != null && !this.hit)
			GoTo(this.destination);
        CollideMap();
		this.hit = false;

		this.rect = new RectF(this.position.x, this.position.y, this.position.x
				+ this.size.x, this.position.y + this.size.y);
        if(Spells!=null)
		for (int j=0;j<Spells.length;j++)
        {

			Spells[j].Update();
        }
        this.dRect=new RectF(rect.left-RenderThread.archie.position.x + RenderThread.size.x / 2,rect.top-RenderThread.archie.position.y+RenderThread.size.y/2,rect.right-RenderThread.archie.position.x+RenderThread.size.x/2,rect.bottom-RenderThread.archie.position.y+RenderThread.size.y/2);
	}
public void FingerUpdate(List<iVector> f,int SelectedSpell)
{

    if(SelectedSpell==-1)
    {
        if(f.size()>0)
        StartTo(new Vector (f.get(0).x,f.get(0).y));
    }
    else
    {
            Log.d("INET",SelectedSpell+"");
        if(Spells[SelectedSpell].Current==0)
            Spells[SelectedSpell].Cast(f);
        }
}

    protected Destination Marker;

	public void StartTo(Vector Dest) {
		this.destination = new Vector(Dest.x, Dest.y);
        this.Marker = new Destination(destination);
	}

	public void CollideMap() {
        if(this.position.x<0)
            this.velocity.x=Math.abs(this.velocity.x);
        if(this.position.x+this.size.x> Global.WORLD_BOUND_SIZE.x)
            this.velocity.x=-Math.abs(this.velocity.x);
        if(this.position.y+this.size.y> Global.WORLD_BOUND_SIZE.y)
            this.velocity.y=-Math.abs(this.velocity.y);
        if(this.position.y<0)
            this.velocity.y=Math.abs(this.velocity.y);
	}

	public void SetVelocity(float vel) {

		float totalVel = Math.abs(this.velocity.x) + Math.abs(this.velocity.y);
		this.velocity = new Vector(vel * this.velocity.x / totalVel, vel
				* this.velocity.y / totalVel);
	}

    //Applies a Vector to the velocity, based on accelleration and max speed, in the direction of the destination
	protected void GoTo(Vector d) {
		float distanceX = d.x - this.feet.x;
		float distanceY = d.y - this.feet.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);

		if (totalDist > this.maxVelocity) {
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
            bounds.Center=feet;
            this.destination = null;
			this.velocity = new Vector(0, 0);
		}
	}


	public void Collision(GameObject obj) {
		switch (obj.objectObjectType) {
        case Bounce:
            ((BounceProjectile)obj).findNewTarget();
            break;
		case Projectile:
			if (obj.owner.id != this.id) {
				this.ProjectileHit(obj.velocity);
				RenderThread.delObject(obj.id);

			}
			break;
        case Player:
		case GameObject:
		case Enemy:
			if (this.owner != null)
				if (obj.id != this.owner.id) {
//                    Log.d("GETME", "HIT!");
					Vector Tempvel = this.velocity.get();
					Vector Tempvel2 = obj.velocity.get();
					ProjectileHit(Tempvel2);
					obj.ProjectileHit(Tempvel);
				}
			break;
		case Meteor:
				if (obj.health == 10)
                    break;
        case Explosion:
            if(this.owner!=null)
                if (obj.id != this.owner.id)
            Log.d("INET","EXPLOSION HIT");
            velocity=   Vector.multiply(this.GetVel(position,obj.getCenter()),-1);
            break;
		case GravityField:
			this.velocity = this.velocity.add(obj
					.DirectionalPull(this.position,obj.pull));
			break;
        case LinkSpell:
            ((LinkProjectile)obj).linked=this;
            obj.paint.setColor(Color.WHITE);
            break;
            case SwapProjectile:
                Vector l;
                l = obj.owner.position;
                obj.owner.position=this.position;
                this.position=l;
                RenderThread.delObject(obj.id);
                break;
            case IceSpell:
                this.Debuffs.add(new SpellEffect(100, SpellEffect.EffectType.Freeze, Global.Sprites.get(3),this));
                RenderThread.delObject(obj.id);
                break;

		}
	}
    public Vector GetVel(Vector from, Vector to) {
        this.position = from;
        float distanceX = to.x - from.x;
        float distanceY = to.y - from.y;
        float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
        return new Vector(this.maxVelocity * (distanceX / totalDist),
                this.maxVelocity * distanceY / totalDist);
    }
    public static Vector PositiononEllipse(float _angle) {
        float _x = (RenderThread.l.platform.Size.x / 2 - (RenderThread.l.platform.Size.x / 10))
                * (float) Math.cos((double) _angle % 360)
                + RenderThread.l.platform.Position.x;
        float _y = (RenderThread.l.platform.Size.y / 2 - (RenderThread.l.platform.Size.y / 10))
                * (float) Math.sin((double) _angle % 360)
                + RenderThread.l.platform.Position.y;
        return new Vector(_x, _y);
    }
    //Applies a flat pull to the objects position.
	public Vector DirectionalPull(Vector TargetPosition,float _p) {
		Vector from = TargetPosition.get();
		Vector to = this.position.get();

		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);

		return new Vector(_p* (distanceX / totalDist),_p
				* distanceY / totalDist);
	}

	public void ProjectileHit(Vector v) {

		this.paint.setColor(Color.RED);
		this.velocity = v;
		this.position.add(this.velocity.multiply(this.velocity,2));

	}

	public Vector getCenter() {
		return new Vector(this.rect.left + this.rect.width() / 2, this.rect.top
				+ this.rect.height() / 2);
	}

}
