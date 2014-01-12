package com.developmental.myapplication.GL;

/**
 * Created by Scott on 5/01/14.
 */

import android.os.SystemClock;

import Tools.Vector;

/**
 * A simple runnable that updates the position of each sprite on the screen
 * every frame by applying a very simple gravity and bounce simulation.  The
 * sprites are jumbled with random velocities every once and a while.
 */
public class Mover implements Runnable {
    private GLSprite[] mRenderables;
    private long mLastTime;
    private long mLastJumbleTime;
    private int mViewWidth;
    private int mViewHeight;

    static final float COEFFICIENT_OF_RESTITUTION = 0.75f;
    static final float SPEED_OF_GRAVITY = 150.0f;
    static final long JUMBLE_EVERYTHING_DELAY = 15 * 1000;
    static final float MAX_VELOCITY = 8000.0f;

    public void run() {
        // Perform a single simulation step.
        if (mRenderables != null) {
            final long time = SystemClock.uptimeMillis();
            final long timeDelta = time - mLastTime;
            final float timeDeltaSeconds =
                    mLastTime > 0.0f ? timeDelta / 1000.0f : 0.0f;
            mLastTime = time;

            // Check to see if it's time to jumble again.
            final boolean jumble =
                    (time - mLastJumbleTime > JUMBLE_EVERYTHING_DELAY);
            if (jumble) {
                mLastJumbleTime = time;
            }

            for (int x = 0; x < mRenderables.length; x++) {
                GLSprite object = mRenderables[x];
                object.Update(timeDeltaSeconds);

                // Jumble!  Apply random velocities.
                if (jumble) {
                    object.velocity.add(new Vector((MAX_VELOCITY / 2.0f)
                            - (float)(Math.random() * MAX_VELOCITY),(MAX_VELOCITY / 2.0f)
                            - (float)(Math.random() * MAX_VELOCITY)));
                }

                // Move.

                // Apply Gravity.
                object.velocity.y -= SPEED_OF_GRAVITY * timeDeltaSeconds;

                // Bounce.



            }
        }

    }

    public void setRenderables(GLSprite[] renderables) {
        mRenderables = renderables;
    }

    public void setViewSize(int width, int height) {
        mViewHeight = height;
        mViewWidth = width;
    }

}