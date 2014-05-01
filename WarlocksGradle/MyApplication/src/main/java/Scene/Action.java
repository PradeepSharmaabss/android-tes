package Scene;

import com.developmental.myapplication.GL.NewHeirachy.GameObject;
import Tools.Vector;

public class Action {
    private Type ActionType;
    private Vector Destination;
    private GameObject Target;

    Action() {

    }

    public Action(Type _a, Vector _destination) {
        this.Destination = _destination;

    }

    public Action(Type _a, GameObject _Target) {
        this.Target = _Target;
    }

    public enum Type {
        Kill, GoTo, Conversation
    }

    boolean Done() {
        switch (this.ActionType) {
            case Kill:
                if (this.Target.health > 0)
                    return false;
                else
                    return true;
            case GoTo:

                break;
            case Conversation:
                break;
        }
        return false;
    }

}
