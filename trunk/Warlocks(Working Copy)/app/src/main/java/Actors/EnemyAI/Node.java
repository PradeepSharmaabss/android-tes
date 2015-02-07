package Actors.EnemyAI;

import Game.Destination;
import Tools.Vector;
import Tools.iVector;
import developmental.warlocks.GL.SimpleGLRenderer;

/**
 * Created by Scott on 24/10/2014.
 */
public class Node extends iVector {
    public boolean isEqualTo(Node dest) {
        if (dest.x == x && dest.y == y)
            return true;
        return false;
    }

    public boolean isSafe() {
        return type == Type.Ice || type == Type.Platform;
    }

    enum Type { Platform, Ice, Lava};
    public Type type;
    public int indexX;
    public int indexY;
    public Destination d;

    public Node(int _x, int _y, int x, int y)
    {
        super (x,y);
        indexX = _x;
        indexY = _y;
        type = Node.CheckType(x,y);
        d = new Destination2(new Vector(x, y), this);
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
