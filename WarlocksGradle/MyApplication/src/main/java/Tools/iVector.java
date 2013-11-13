package Tools;

import java.io.Serializable;

public class iVector implements Serializable {

    public short x, y;
    //public float w, h;

    public iVector() {
        this(0, 0);
    }

    public iVector(int x, int y) {
        this.setX((short) x);
        this.setY((short) y);
    }

    public iVector add(iVector v) {
        return new iVector(this.x + v.x, this.y + v.y);
        // return new Vector(x+v.x, x+v.y);
    }

    public iVector multiply(iVector v, int x) {
        return new iVector(v.x * x, v.y * x);
    }


    public iVector get() {
        return new iVector(this.x, this.y);
    }

    public iVector subtract(iVector v) {
        return new iVector(this.x - v.x, this.y - v.y);
    }

    public void addX(short val) {
        this.x += val;
    }

    public float getX() {
        return this.x;
    }

    public void setX(short x) {
        this.x = x;
    }

    public float getY() {
        return this.y;
    }

    public void setY(short y) {
        this.y = y;
    }
}
