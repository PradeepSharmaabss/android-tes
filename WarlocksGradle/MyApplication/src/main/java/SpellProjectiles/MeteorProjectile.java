package SpellProjectiles;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

import Game.DamageType;
import Game.GameObject;
import Particles.Particle;
import Tools.Vector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.RenderThread;

public class MeteorProjectile extends Projectile {
	float height = 400;
	public final int landing = 10;
Paint Chunks;
	public MeteorProjectile(Vector _from, Vector _to, GameObject shooter) {
		super(_from, _to, shooter,110,4,new Vector(150,150),20);
        Chunks= new Paint();
        Chunks.setARGB(255,85,64,64);
		this.paint.setColor(Color.CYAN);
		this.objectObjectType = Game.ObjectType.Meteor;
		this.velocity = GetVel(_from, _to);
        this.pull= 10;
        this.curr= Global.Sprites.get(4).get(0);
	}

	@Override
    public Vector GetVel(Vector from, Vector to) {
		float distanceX = to.x - from.x;
		float distanceY = to.y - from.y;
		float totalDist = Math.abs(distanceX) + Math.abs(distanceY);
		Vector v = new Vector(this.maxVelocity * (distanceX / totalDist),
				this.maxVelocity * distanceY / totalDist);
		this.position = new Vector(to.x - v.x * 100-size.x/2, to.y - v.y * 100-size.y/2);
		return v;

	}
boolean landed = false;
	@Override
	public void Update() {
		super.Update();
		if (this.height > 0)
        {
            this.height -= 4;
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y-height), Vector.multiply(this.velocity, -Global.GetRandomNumer.nextFloat()),40,this.paint));
        }

		if (this.health < landing) {
			this.velocity = new Vector(0, 0);

            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.Chunks));
            RenderThread.addParticle(new Particle(new Vector(this.getCenter().x,this.getCenter().y), Vector.multiply(new Vector( Global.GetRandomNumer.nextFloat()*4-2,-1), Global.GetRandomNumer.nextFloat()*20-10),20,this.paint));
			this.size = new Vector(250, 250);
            bounds.Radius= 125;
            this.curr= Global.Sprites.get(5).get(0);
            if(!landed)
            {
                landed=true;
            this.position.x-=50;
            this.position.y-=50;
            }
		}
	}

	@Override
	public void Collision(GameObject obj) {
		switch (obj.objectObjectType) {
            case Bounce:
            case IceSpell:
            case Projectile:
            if(this.health==landing)
            RenderThread.delObject(obj.id);
            break;
		case GameObject:
		case Player:
		case Enemy:
			if (this.health == landing)
				if (obj.id != this.owner.id)
                {
                    obj.velocity=   Vector.multiply(obj.GetVel(obj.position,getCenter()),-1);

           DealDamageTo(obj);
                }
			break;
		case LineSpell:
		case Meteor:
        case Explosion:
       case LinkSpell:
                break;
		case GravityField:
            this.velocity = this.velocity.add(obj
                    .DirectionalPull(this.position,obj.pull));
			break;

		}
	}

	@Override
	public boolean Intersect(RectF PassedObj) {
		if (this.health == landing)
			return super.Intersect(PassedObj);
		return false;
	}

	@Override
	public void Draw(Canvas c,float playerx,float playery) {
        if(landed==false)
        {
		c.drawCircle(this.rect.centerX()-playerx, this.rect.centerY()-playery, this.size.x / 2*(1-(height/400)),
				this.shadowPaint);
        c.drawBitmap(this.curr, this.position.x-playerx, this.position.y-playery-this.height,
                this.paint);

        }
// c.drawCircle(this.rect.centerX()-playerx, this.rect.centerY() - this.height-playery,
//				this.size.x / 3, this.paint);

	}

}
