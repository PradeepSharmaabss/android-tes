package com.example.androidproject;
public class Vector {

	public float x,y;
	public float w,h;
	public Vector()
	{
		this(0,0);
	}
	public Vector(float x ,float y)
	{
		this.setX(x);
		this.setY(y);
	}
	public Vector add(Vector v)
	{
		return new Vector(x+v.x, y+v.y);
		//return new Vector(x+v.x, x+v.y);
	}
	public Vector get()
	{
		return new Vector(x,y);
	}
	public Vector subtract(Vector v)
	{
		return new Vector(x-v.x, x-v.y);
	}
	public void addX(float val)
	{
		this.x += val;
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
}
