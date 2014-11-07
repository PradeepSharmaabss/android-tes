package Actors.EnemyAI;

import com.developmental.warlocks.R;

import javax.microedition.khronos.opengles.GL10;

import Game.Destination;
import Tools.Vector;
import developmental.warlocks.Global;

/**
 * Created by Scott on 7/11/2014.
 */
public class Destination2 extends Destination {
    Node p;
    public Destination2(Vector vector,Node _p) {
        super(vector);
        p=_p;
    }

    @Override
    public void draw(GL10 gl, float offsetX, float offsetY, boolean dontDrawInRelationToWorld) {
        switch (p.type) {
            case Platform:
                setTextureName(Global.resources.get(R.drawable.button_shield));
                break;
            case Ice:
                setTextureName(Global.resources.get(R.drawable.button_eyeball));
                break;
            case Lava:
                setTextureName(Global.resources.get(R.drawable.button_gravity));

                break;
        }
        super.draw(gl, offsetX, offsetY, dontDrawInRelationToWorld);
    }
}
