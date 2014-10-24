package Actors.EnemyAI;

import Tools.Vector;
import Tools.iVector;

/**
 * Created by Scott on 24/10/2014.
 */
public class Node extends iVector {
    enum Type { Platform, Ice, Lava};
    public Type type;
    public Node(int x, int y, Type t)
    {
        super (x,y);
        type = t;
    }
}
