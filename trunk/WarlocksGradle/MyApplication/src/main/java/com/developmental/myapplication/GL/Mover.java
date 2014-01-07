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
    private Renderable[] mRenderables;
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
                Renderable object = mRenderables[x];
                object.lifePhase++;

                if(object.lifePhase%object.frameRate ==object.frameRate-1)
                    if(object.se)
                    object.frame++;

                    if(object.frame>=((GLSprite)object).getGrid().size())
                       object.frame=0;
                // Jumble!  Apply random velocities.
                if (jumble) {
                    object.velocityX += (MAX_VELOCITY / 2.0f)
                            - (float)(Math.random() * MAX_VELOCITY);
                    object.velocityY += (MAX_VELOCITY / 2.0f)
                            - (float)(Math.random() * MAX_VELOCITY);
                }

                // Move.
                object.x = object.x + (object.velocityX * timeDeltaSeconds);
                object.y = object.y + (object.velocityY * timeDeltaSeconds);
                object.z = object.z + (object.velocityZ * timeDeltaSeconds);
                ((GLSprite) object).Animate(new Vector(object.velocityX,-object.velocityY));
                // Apply Gravity.
                object.velocityY -= SPEED_OF_GRAVITY * timeDeltaSeconds;

                // Bounce.
                if ((object.x < 0.0f && object.velocityX < 0.0f)
                        || (object.x > mViewWidth - object.width
                        && object.velocityX > 0.0f)) {
                    object.velocityX =
                            -object.velocityX * COEFFICIENT_OF_RESTITUTION;
                    object.x = Math.max(0.0f,
                            Math.min(object.x, mViewWidth - object.width));
                    if (Math.abs(object.velocityX) < 0.1f) {
                        object.velocityX = 0.0f;
                    }
                }

                if ((object.y < 0.0f && object.velocityY < 0.0f)
                        || (object.y > mViewHeight - object.height
                        && object.velocityY > 0.0f)) {
                    object.velocityY =
                            -object.velocityY * COEFFICIENT_OF_RESTITUTION;
                    object.y = Math.max(0.0f,
                            Math.min(object.y, mViewHeight - object.height));
                    if (Math.abs(object.velocityY) < 0.1f) {
                        object.velocityY = 0.0f;
                    }
                }


            }
        }

    }

    public void setRenderables(Renderable[] renderables) {
        mRenderables = renderables;
    }

    public void setViewSize(int width, int height) {
        mViewHeight = height;
        mViewWidth = width;
    }

}