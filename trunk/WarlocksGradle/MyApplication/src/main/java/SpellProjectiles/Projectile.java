package SpellProjectiles;

import android.util.Log;

import Game.DamageType;
import Game.GameObject;
import HUD.PopupText;
import Tools.Vector;

import com.developmental.myapplication.RenderThread;

public class Projectile extends GameObject {


    public Projectile(Vector _from, Vector _to, GameObject shooter, float _health, float _maxvelocity, Vector _size, float _damagevalue) {
        super();
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
        feet= position.get();
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
