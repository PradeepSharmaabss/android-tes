package Game;

import Input.Finger;
import Spells.GravitySpell;
import Spells.LightningSpell;
import Spells.MeteorSpell;
import Spells.Spell;
import Spells.WallSpell;
import Tools.SpriteSheet;
import Tools.Vector;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import android.util.Log;
import com.example.warlockgame.RenderThread;

public abstract class GameObject implements Comparable<GameObject> {
	public GameObject owner = null;
	public Bitmap curr = null;
	public RectF rect;
	public Paint paint, shadowPaint;
	public SpriteSheet spriteSheet;

	public int id = 0, health = 1000, armour = 0, resist = 0,
			maxhealth = this.health, mana = 0;

	protected float acceleration = 0.4f, maxVelocity = 15, pull = 0.2f;
	public boolean shadow = true, AI = true, shoot = false, hit = false;
	public Game.ObjectType objectObjectType;
	public Vector position, size, velocity, destination, feet;

	public Spell[] Spells;

	public GameObject(GameObject owner) {
		this();
		owner = this.owner;
	}

	public GameObject() {
		this.objectObjectType = Game.ObjectType.GameObject;
		this.position = new Vector(0, 0);
		this.size = new Vector(50, 50);
		this.velocity = new Vector(0, 0);
        Log.d("HI I AM IN HERE","HI");
		this.Spells = new Spell[10];
		this.paint = new Paint();
		this.paint.setColor(Color.RED);
		this.shadowPaint = new Paint();
		this.shadowPaint.setColor(Color.BLACK);
		this.shadowPaint.setMaskFilter(new BlurMaskFilter(30,
				BlurMaskFilter.Blur.INNER));

		for (int x = 0; x < 10; x++) {
			this.Spells[x] = new Spell(this);
			if (x == 1)
				this.Spells[x] = new LightningSpell(this);
			if (x == 2)
				this.Spells[x] = new WallSpell(this);
			if (x == 3)
				this.Spells[x] = new MeteorSpell(this);
			if (x == 4)
				this.Spells[x] = new GravitySpell(this);

		}
		this.rect = new RectF(this.position.x, this.position.y, this.position.x
				+ this.size.x, this.position.y + this.size.y);
		this.feet = new Vector(this.position.x + this.size.x / 2,
				this.position.y - this.size.y);
	}

	public int compareTo(GameObject o) {
        return (int)(this.rect.bottom-o.rect.bottom);
	//	return (int) (this.position.y - o.position.y);
	}

	protected void GetSprites() {

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
	}

	public boolean Intersect(RectF PassedObj) {

		if (RectF.intersects(this.rect, PassedObj))
			return true;
		return false;
	}

    protected RectF dRect;
	public void Draw(Canvas canvas,float playerx,float playery) {
        this.dRect=new RectF(rect.left-playerx,rect.top-playery,rect.right-playerx,rect.bottom-playery);
		canvas.save();
		canvas.translate(this.rect.left + this.dRect.width() / 2-playerx, -playery+this.rect.top
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
		// super.Draw(canvas,dRect);

	}

	static Vector isoCoordsForPoint(Vector point, int[][] map) {
		float tw = 64;// tileSize_.width;
		float th = 64;// tileSize_.height;
		float mw = map[0].length;// mapSize_.width;
		float mh = 24;// mapSize_.height;

		int posY = (int) (mh - point.x / tw + mw / 2 - point.y / th);
		int posX = (int) (mh + point.x / tw - mw / 2 - point.y / th);

		return (new Vector(posX, posY));
	}

	static Vector test(Vector touch) {

		int ts = 40;
		int isoy = (int) ((((touch.y / ts) + (touch.x - (10 * ts / 2)) / ts) - 10) * -1);
		int isox = (int) ((((touch.y / ts) - (touch.x - (10 * ts / 2)) / ts) - 10) * -1);
		return new Vector(isox, isoy);
	}
	public void Update() {
            this.feet = new Vector(this.position.x + this.size.x / 2,
                                   this.position.y + this.size.y);
		if (!RenderThread.l.platform.Within(this.feet))
			Damage(3,DamageType.Lava);
		this.position = this.position.add(this.velocity);
		if (this.destination != null && !this.hit)
			GoTo(this.destination);
		this.hit = false;
		if (Finger.down == true && Finger.position.position.y < RenderThread.size.y
				&& this.objectObjectType.equals(Game.ObjectType.Player) && !Finger.fired)
			StartTo(new Vector( (Finger.position.WorldPos().x),
					Finger.position.WorldPos().y));
		this.rect = new RectF(this.position.x, this.position.y, this.position.x
				+ this.size.x, this.position.y + this.size.y);

		for (Spell s : this.Spells)
			s.Update();

	}

    protected Destination Marker;

	public void StartTo(Vector Dest) {
		this.destination = new Vector(Dest.x, Dest.y);
        this.Marker = new Destination(destination);
	}

	public void CollideScreen() {
		if (this.position.x + this.size.x > RenderThread.l.bounds.right)
			this.velocity.x = -10;
		if (this.position.x < RenderThread.l.bounds.left)
			this.velocity.x = 10;
		if (this.position.y + this.size.y < RenderThread.l.bounds.top)
			this.velocity.y = 10;
		if (this.position.y + this.size.y > RenderThread.l.bounds.bottom)
			this.velocity.y = -10;
	}

	protected void SetVelocity(float vel) {

		float totalVel = Math.abs(this.velocity.x) + Math.abs(this.velocity.y);
		this.velocity = new Vector(vel * this.velocity.x / totalVel, vel
				* this.velocity.y / totalVel);
	}

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
			setNull();
			this.velocity = new Vector(0, 0);
		}
	}

	public void setNull() {
		this.destination = null;
	}

	public void Collision(GameObject obj) {
		switch (obj.objectObjectType) {

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
                    Log.d("GETME", "HIT!");
					Vector Tempvel = this.velocity.get();
					Vector Tempvel2 = obj.velocity.get();
					ProjectileHit(Tempvel2);
					obj.ProjectileHit(Tempvel);
				}
			break;
		case Meteor:
            if(this.owner!=null)
			if (obj.id != this.owner.id)
				if (obj.health == 10)
					this.velocity = obj.velocity;
			break;
		case GravityField:
			this.velocity = this.velocity.add(obj
					.DirectionalPull(this.position));
			break;
		}
	}

	public Vector DirectionalPull(Vector EnemyPosition) {
		Vector from = EnemyPosition.get();
		Vector to = this.position.get();

		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);

		return new Vector(this.pull * (distanceX / totalDist), this.pull
				* distanceY / totalDist);
	}

	public void ProjectileHit(Vector v) {

		this.paint.setColor(Color.RED);
		this.velocity = v;
		this.position.add(this.velocity.get());

	}

	public Vector getCenter() {
		return new Vector(this.rect.left + this.rect.width() / 2, this.rect.top
				+ this.rect.height() / 2);
	}

}
