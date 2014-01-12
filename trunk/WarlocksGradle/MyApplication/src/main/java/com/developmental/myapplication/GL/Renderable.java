package com.developmental.myapplication.GL;

/**
 * Created by Scott on 5/01/14.
 */

import Tools.Vector;

/**
 * Base class defining the core set of information necessary to render (and move
 * an object on the screen.  This is an abstract type and must be derived to
 * add methods to actually draw (see CanvasSprite and GLSprite).
 */
public abstract class Renderable {
    // Position.
public Vector position;
    public float z=0;

    // Velocity.
public Vector velocity;
    public float velocityZ=0;

    // Size.
    public float width;
    public float height;

   public int lifePhase = 0;
   public int frameRate = 5;
    public int frame;
   public boolean se = false;
}


