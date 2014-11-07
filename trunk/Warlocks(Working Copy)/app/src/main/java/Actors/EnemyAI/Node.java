package Actors.EnemyAI;

import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 24/10/2014.
 */
public class Node extends iVector {
    enum Type { Platform, Ice, Lava};
    public Type type;
    public Node(int x, int y)
    {
        super (x,y);
        type = Node.CheckType(x,y);
      //  d = new Destination2(new Vector(x,y),this);
    }

//public Destination2 d;
    public static Type CheckType(int x, int y) {
        if(SimpleGLRenderer.l.iceplatform.Within(new Vector(x,y)))
            return Type.Ice;
        if(SimpleGLRenderer.l.platform.Within(new Vector(x,y)))
            return Type.Platform;

        return Type.Lava;
    }
}
