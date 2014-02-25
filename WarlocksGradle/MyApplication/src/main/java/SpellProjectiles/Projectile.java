package SpellProjectiles;

import Game.DamageType;

import com.developmental.myapplication.GL.Grid;
import com.developmental.myapplication.GL.NewHeirachy.GameObject;

import Tools.Vector;

import com.developmental.myapplication.Global;
import com.developmental.myapplication.R;
import com.developmental.myapplication.RenderThread;

import java.util.ArrayList;

public class Projectile extends GameObject {

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
    public Projectile(Vector _from, Vector _to, GameObject shooter, float _health, float _maxvelocity, Vector _size, float _damagevalue) {
        super(R.drawable.fireball);
  this.mGrid= new ArrayList<Grid>();
        Grid backgroundGrid = new Grid(2, 2, false);
        backgroundGrid.set(0, 0,  -_size.x/2, -_size.y/2, 0.0f, 0.0f, 1.0f, null);
        backgroundGrid.set(1, 0,_size.x/2, -_size.y/2, 0.0f, 1.0f, 1.0f, null);
        backgroundGrid.set(0, 1, -_size.x/2, _size.y/2, 0.0f, 0.0f, 0.0f, null);
        backgroundGrid.set(1, 1, _size.x/2,_size.y/2, 0.0f,1.0f, 0.0f, null );
        mGrid.add(backgroundGrid);

        this.owner = shooter;

        this.health = _health;
        this.maxVelocity = _maxvelocity;
        this.size = _size;
        this.damagevalue = _damagevalue;
        this.objectObjectType = Game.ObjectType.Projectile;
        Vector from = _from.get();
        Vector to = new Vector(_to.x-size.x/2,_to.y-size.y/2);

        this.velocity = GetVel(from, to.get());
        SetVelocity(this.maxVelocity);
       // feet= position.get();
        this.bounds.Center = feet;
        this.bounds.Radius = this.size.x / 2;

        //   this.bounds.Radius=size.x;
    }

    public void DealDamageTo(GameObject g) {
        g.Damage(this.damagevalue, DamageType.Spell);
        owner.damageDealtThisRound += damagevalue;
    }

    public void Damage(float dmgDealt, DamageType d) {
        switch (d) {
            case Lava:
                break;
            case Spell:
            default:
                if (dmgDealt > this.health)
                    this.health = 0;
                else
                    this.health -= dmgDealt;
                break;
        }

    }




    @Override
    public void Update() {
        if (this.health > 0) {
            super.Update();
            this.health--;
        } else
            RenderThread.delObject(this.id);
        this.bounds.Center = getCenter();
    }
}
