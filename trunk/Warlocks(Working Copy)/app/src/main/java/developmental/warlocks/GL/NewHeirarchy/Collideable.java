package developmental.warlocks.GL.NewHeirarchy;

import Tools.BoundingCircle;

/**
 * Created by Scott on 19/01/14.
 * An object that can impact with/collide with another object.
 */
public class Collideable extends Moveable {



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


    protected Collideable(int _mResourceID) {
        super(_mResourceID);
    }
}
