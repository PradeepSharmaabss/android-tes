package Tools;

import java.io.Serializable;

public class Vector implements Serializable {

    public float x, y;
    //public float w, h;

    public Vector() {
        this(0, 0);
    }

    public Vector(float x, float y) {
        this.setX(x);
        this.setY(y);
    }
    public static float DistanceBetween(Vector v1, Vector v2)
    {

        float distanceX = v1.x - v2.x;
        float distanceY = v1.y - v2.y;
        return Math.abs(distanceX) + Math.abs(distanceY);

    }

    public Vector add(Vector v) {
        return new Vector(this.x + v.x, this.y + v.y);
        // return new Vector(x+v.x, x+v.y);
    }

    public static Vector multiply(Vector v, float x) {
        return new Vector(v.x * x, v.y * x);
    }


    public Vector get() {
        return new Vector(this.x, this.y);
    }

    public Vector subtract(Vector v) {
        return new Vector(this.x - v.x, this.y - v.y);
    }

    public void addX(float val) {
        this.x += val;
    }

    public float getX() {
        return this.x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(float y) {
        this.y = y;
    }
}
