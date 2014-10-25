package Scene;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Mission extends Action {
    List<Action> ToDo;
    List<Action>NotToDo;
    public Mission(ArrayList<Action> a,ArrayList<Action>n) {
        super();
        this.ToDo = a;
        this.NotToDo = n;
    }
    boolean Failed()
    {
        for(Action a:this.NotToDo)
        {
            if(a.Done())
            {
                return true;
            }
        }
        return false;
    }

    @Override
    boolean Done() {
        for (Action a : this.ToDo)
            if (!a.Done())
                return false;
        return true;
    }
}
