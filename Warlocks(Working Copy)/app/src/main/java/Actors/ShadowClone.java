package Actors;

import Tools.Vector;
import developmental.warlocks.GL.NewHeirarchy.GameObject;

/**
 * Created by Scott on 18/11/2014.
 */
public class ShadowClone extends GameObject {

    public ShadowClone(int resourceId, Vector _pos, Vector _size)
    {
        super(resourceId,_pos,_size);
Animate(_pos);
    }

}
